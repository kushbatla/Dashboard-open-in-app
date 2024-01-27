package com.kushbatla.dashboard.ui

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.kushbatla.dashboard.data.RecentLink
import com.kushbatla.dashboard.data.TopLink
import com.kushbatla.dashboard.repository.Repository
import kotlinx.coroutines.launch

class MainViewModel(private val repository: Repository) : ViewModel() {

    //not exposing
    private val _topLinkResponse: MutableLiveData<List<TopLink>> = MutableLiveData()
    private val _recentLinkResponse: MutableLiveData<List<RecentLink>> = MutableLiveData()

    //exposing the LiveData
    val topLinkResponse: LiveData<List<TopLink>> = _topLinkResponse
    val recentLinkResponse: LiveData<List<RecentLink>> = _recentLinkResponse

     fun getTopLink(){
        viewModelScope.launch {
            val response = repository.getDashboardData()
            if (response.isSuccessful){
                _topLinkResponse.value = response.body()!!.data.top_links
            }
        }
    }

    fun getRecentLink() {
        viewModelScope.launch {
            val response = repository.getDashboardData()
            if(response.isSuccessful){
                _recentLinkResponse.value = response.body()!!.data.recent_links
            }
        }
    }
}