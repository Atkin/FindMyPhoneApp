package ru.projectatkin.findmyphone

import okhttp3.RequestBody
import okhttp3.ResponseBody
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.POST

interface APIService {
    @POST("/rest/listMettic")
    suspend fun createEmployee(@Body requestBody: RequestBody): Response<ResponseBody>
}