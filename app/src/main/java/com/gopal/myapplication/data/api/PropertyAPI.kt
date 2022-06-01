package com.gopal.myapplication.data.api


import com.gopal.myapplication.data.model.FacilitiesAPI
import io.reactivex.rxjava3.core.Single
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Query

interface PropertyAPI {

    @GET("iranjith4/ad-assignment/db")
    fun getFacilitiesList(
    ): Single<FacilitiesAPI.Facilities>

}