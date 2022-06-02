package com.bivizul.newsapp.ui.main

import android.content.ContentValues.TAG
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.bivizul.newsapp.data.api.TestRepo
import com.bivizul.newsapp.models.NewsResponse
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(private val repository: TestRepo) : ViewModel() {

//    private val _all = MutableLiveData<NewsResponse>()
//    val all: LiveData<NewsResponse> = _all

    private val _all = MutableStateFlow<NewsResponse?>(null)
    val all: StateFlow<NewsResponse?> = _all.asStateFlow()

    init {
        getAll()
    }

    fun getAll() {
        viewModelScope.launch(Dispatchers.IO) {
            repository.getAll().let {
                if (it.isSuccessful) {
                    _all.value = it.body()
                } else {
                    Log.d(TAG, "Failed load: ${it.errorBody()}")
                }
            }
        }
    }
}