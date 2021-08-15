package ru.projectatkin.findmyphone

import android.Manifest
import android.annotation.SuppressLint
import android.content.Context
import android.content.SharedPreferences
import android.content.pm.PackageManager
import android.location.Location
import android.location.LocationManager
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Looper
import android.provider.Settings
import android.telephony.*
import android.util.Log
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.core.app.ActivityCompat
import com.google.android.gms.location.*
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import java.lang.Exception
import java.lang.reflect.Type
import java.util.*

class MainActivity : AppCompatActivity() {

    private lateinit var mFusedLocationProviderClient: FusedLocationProviderClient
    private var mLatitude : Double = 0.0
    private var mLongitude: Double = 0.0
    var dataList: MutableList<LocationData> = mutableListOf()
    var locationData: LocationData = LocationData(0, "0", 0.0, 0.0, "0", "0", 0, 0, 0, 0, 0)
    val type: Type = object : TypeToken<MutableList<LocationData>>() {}.type
    var oldDataList: MutableList<LocationData> = mutableListOf()

    @RequiresApi(Build.VERSION_CODES.R)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val mPrefs: SharedPreferences = getPreferences(MODE_PRIVATE)
        if (ActivityCompat.checkSelfPermission(
                this,
                Manifest.permission.ACCESS_FINE_LOCATION
            ) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(
                this,
                Manifest.permission.ACCESS_COARSE_LOCATION
            ) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(
                this,
                Manifest.permission.READ_PHONE_STATE
            ) != PackageManager.PERMISSION_GRANTED
        ) {
            requestPermissions(arrayOf(Manifest.permission.ACCESS_FINE_LOCATION,
                Manifest.permission.ACCESS_COARSE_LOCATION,
                Manifest.permission.READ_PHONE_STATE), REQUEST_CODE)
        }


        mFusedLocationProviderClient = LocationServices.getFusedLocationProviderClient(this)


        Timer().scheduleAtFixedRate(object : TimerTask(){
            lateinit var json: String


            override fun run() {
                Log.i("DataInfo", "${dataList.size}")
                onGetInfo()

                requestNewLocationData()

                val gson = Gson()

                json = mPrefs.getString("location1", "").toString()
                if (dataList.isEmpty()) {
                    if (json.isNotEmpty()) {
                        dataList = gson.fromJson(json, type)
                    }
                    Log.i("DataInfo", "${dataList.size}")
                    if (dataList.isEmpty()) {
                        dataList += locationData
                        json = gson.toJson(dataList)
                        mPrefs.edit().putString("location1", json).commit()
                    } else {
                        dataList += locationData
                        json = gson.toJson(dataList)
                        mPrefs.edit().putString("location1", json).commit()
                    }
                } else {
                    if (json.isNotEmpty()) {
                        oldDataList = gson.fromJson(json, type)
                    }
                    dataList += locationData
                    for (data in dataList) {
                        oldDataList += data
                    }
                    json = gson.toJson(dataList)
                    mPrefs.edit().putString("location1", json).commit()
                }



            }

        }, 0, 10000)
    }

    @RequiresApi(Build.VERSION_CODES.R)
    @SuppressLint("HardwareIds")
    fun onGetInfo(){
        if (ActivityCompat.checkSelfPermission(
                this,
                Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED){
            requestPermissions(arrayOf(Manifest.permission.ACCESS_FINE_LOCATION), REQUEST_CODE)
        }
        val telephonyManager: TelephonyManager = getSystemService(Context.TELEPHONY_SERVICE) as TelephonyManager
        val cellLocation = telephonyManager.allCellInfo
        if (cellLocation != null) {
            for (info in cellLocation) {
                if (info is CellInfoLte){
                    val identityLte = info.cellIdentity
                    val signalStrength = info.cellSignalStrength
                    val defineCI = identityLte.ci
                    val defineTAC = identityLte.tac
                    val defineRSRP = signalStrength.rsrp
                    val defineRSRQ = signalStrength.rsrq
                    val defineSINR = if (signalStrength.rssnr != 2147483647){
                        signalStrength.rssnr / 10
                    } else {
                        null
                    }

                    locationData.cell_id = defineCI
                    Log.i("Infooo", "lte ci: $defineCI")

                    locationData.lac = defineTAC
                    Log.i("Infooo", "lte tac: $defineTAC")

                    locationData.rsrp = defineRSRP
                    Log.i("Infooo", "lte rsrp: $defineRSRP")

                    locationData.rsrq = defineRSRQ
                    Log.i("Infooo", "lte rsrq: $defineRSRQ")

                    locationData.sinr = defineSINR
                    Log.i("Infooo", "lte SINR: $defineSINR")
                }
            }
        }
        val deviceID = Settings.Secure.getString(contentResolver, Settings.Secure.ANDROID_ID)
        locationData.device_id = deviceID
        Log.i("Infooo", "device id: $deviceID")

        val userID = deviceID
        locationData.user_id = userID
        Log.i("Infooo", "user id: $userID")

        var defineIMSI: String? = null
        try {
            defineIMSI = telephonyManager.subscriberId
        } catch (e: SecurityException){}
        locationData.imsi = defineIMSI
        Log.i("Infooo", "IMSI: $defineIMSI")

    }

    private fun isLocationEnabled(): Boolean {
        val locationManager : LocationManager = getSystemService(Context.LOCATION_SERVICE) as LocationManager
        return locationManager.isProviderEnabled(LocationManager.GPS_PROVIDER)
                || locationManager.isProviderEnabled(LocationManager.NETWORK_PROVIDER)
    }

    @SuppressLint("MissingPermission")
    @RequiresApi(Build.VERSION_CODES.M)
    private fun requestNewLocationData(){
        var mLocationRequest = LocationRequest()
        mLocationRequest.priority = LocationRequest.PRIORITY_BALANCED_POWER_ACCURACY
        mLocationRequest.interval = 1000
        mLocationRequest.numUpdates = 1



        mFusedLocationProviderClient.requestLocationUpdates(mLocationRequest, mLocationCallBack, Looper.getMainLooper())
    }

    private val mLocationCallBack = object : LocationCallback() {
        override fun onLocationResult(locationResult: LocationResult) {
            val mLastLocation: Location = locationResult.lastLocation
            mLatitude = mLastLocation.latitude
            locationData.latitude = mLatitude
            Log.i("Infooo", "latitude: $mLatitude")
            mLongitude = mLastLocation.longitude
            locationData.longitude = mLongitude
            Log.i("Infooo", "longitude: $mLongitude")
        }
    }


}
private const val REQUEST_CODE = 1
interface CellClickListener {
    fun onCellClickListener(title: String?)
}