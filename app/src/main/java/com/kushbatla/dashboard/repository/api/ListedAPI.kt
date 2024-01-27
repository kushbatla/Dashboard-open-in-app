package com.kushbatla.dashboard.repository.api

import com.kushbatla.dashboard.data.DashboardDataClass
import retrofit2.Response
import retrofit2.http.GET

interface ListedAPI {

    @GET("dashboardNew")
    suspend fun getDashboard() : Response<DashboardDataClass>
}