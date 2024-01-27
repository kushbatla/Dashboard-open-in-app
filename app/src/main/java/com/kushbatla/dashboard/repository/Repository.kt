package com.kushbatla.dashboard.repository

import com.kushbatla.dashboard.data.DashboardDataClass
import com.kushbatla.dashboard.repository.api.RetrofitInstance
import retrofit2.Response

class Repository {

    suspend fun getDashboardData() :Response<DashboardDataClass>{
        return RetrofitInstance.api.getDashboard()
    }
}