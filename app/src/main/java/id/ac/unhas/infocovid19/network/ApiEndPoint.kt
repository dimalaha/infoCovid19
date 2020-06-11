package id.ac.unhas.infocovid19.network

import id.ac.unhas.infocovid19.model.DataProvinsi
import retrofit2.Call
import retrofit2.http.GET

interface ApiEndPoint {
    //base url + value = https://indonesia-covid-19.mathdro.id/api/provinsi
    @GET("provinsi")
    fun getDataProvinsi() : Call<DataProvinsi>
}