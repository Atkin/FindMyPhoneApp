package ru.projectatkin.findmyphone

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.LinearLayout
import android.widget.TextView
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.gson.GsonBuilder
import com.google.gson.JsonParser
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.RequestBody.Companion.toRequestBody
import org.json.JSONObject
import retrofit2.Retrofit

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [fragment_Parent.newInstance] factory method to
 * create an instance of this fragment.
 */
class fragment_parent : Fragment(), CellClickListener {

    private lateinit var contactsAdapter: ContactsAdapter
    private lateinit var contactsRecyclerView: RecyclerView
    private lateinit var contactsModel: ContactsModel
    private lateinit var button: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {

        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment__parent, container, false)

        contactsRecyclerView = view.findViewById(R.id.recyclerViewContacts)
        contactsModel = ContactsModel(ContactsDataBase())
        contactsAdapter = ContactsAdapter(this,contactsModel.getContacts())
        contactsRecyclerView.adapter = contactsAdapter
        contactsRecyclerView.layoutManager = LinearLayoutManager(activity, LinearLayoutManager.VERTICAL ,false)

        //contactViewModel.dataListContacts.observe(viewLifecycleOwner, Observer(contactsAdapter::updateList) )
        //contactViewModel.loadContacts()

        // Inflate the layout for this fragment
        button = view.findViewById(R.id.contactAddButton)
        button.setOnClickListener {
            rawJSON()
        }

        return view
    }

    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment fragment_Parent.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            fragment_parent().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }

    override fun onCellClickListener(index: String?) {
        val bundle = Bundle()
        bundle.putInt("position", index!!.toInt())

        findNavController().navigate(R.id.action_fragment_parent2_to_mapsFragment, bundle)
    }

    fun rawJSON() {

        // Create Retrofit
        val retrofit = Retrofit.Builder()
            .baseUrl("http://localhost:8080")
            .build()

        // Create Service
        val service = retrofit.create(APIService::class.java)

        // Create JSON using JSONObject
        val jsonObject = JSONObject()
        jsonObject.put("id", 5)
        jsonObject.put( "deviceId", 1)
        jsonObject.put("timeStamp", 1629018970526)
        jsonObject.put( "longitude", 36.8)
        jsonObject.put("latitude", 54.62)
        jsonObject.put("cellid", 123414469921)
        jsonObject.put("lac", 10500)
        jsonObject.put("rsrp", -102)
        jsonObject.put("rsrq", 10)
        jsonObject.put("sinr", 0)
        jsonObject.put("deviceid", "c7e6f446f282712b")
        jsonObject.put("userid", "7e6f446f282712b")
        jsonObject.put("imsi", "null")



        // Convert JSONObject to String
        val jsonObjectString = jsonObject.toString()

        // Create RequestBody ( We're not using any converter, like GsonConverter, MoshiConverter e.t.c, that's why we use RequestBody )
        val requestBody = jsonObjectString.toRequestBody("application/json".toMediaTypeOrNull())

        CoroutineScope(Dispatchers.IO).launch {
            // Do the POST request and get response
            val response = service.createEmployee(requestBody)

            withContext(Dispatchers.Main) {
                if (response.isSuccessful) {

                    // Convert raw JSON to pretty JSON using GSON library
                    val gson = GsonBuilder().setPrettyPrinting().create()
                    val prettyJson = gson.toJson(
                        JsonParser.parseString(
                            response.body()
                                ?.string() // About this thread blocking annotation : https://github.com/square/retrofit/issues/3255
                        )
                    )

                    Log.d("Pretty Printed JSON :", prettyJson)

                } else {

                    Log.e("RETROFIT_ERROR", response.code().toString())

                }
            }
        }
    }


}