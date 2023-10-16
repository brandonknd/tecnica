package com.alf.tecnica.viewmodels

import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import androidx.lifecycle.viewModelScope
import com.alf.tecnica.model.MockiDataModel
import com.alf.tecnica.repository.MockiRepositori
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import kotlin.coroutines.CoroutineContext

class RecyclerMockiViewModel(app:Application):AndroidViewModel(app), CoroutineScope{
    private val _itemSelected = MutableLiveData<MockiDataModel?>()
    var itemDataSelected: MockiDataModel? = null

    private val _ListState = MutableLiveData<java.util.ArrayList <MockiDataModel>>()
    var listState: LiveData<java.util.ArrayList <MockiDataModel>> = _ListState

    private val _progressState = MutableLiveData<Boolean>()
    var progressState: LiveData<Boolean> = _progressState

    private val repository: MockiRepositori = MockiRepositori()
    lateinit var observerOnCategorySelected: Observer<MockiDataModel>

    private val viewModelJob = Job()
    override val coroutineContext:CoroutineContext
        get() = viewModelJob + Dispatchers.Default

    init {
        initObserver()
    }

    private fun initObserver() {
        observerOnCategorySelected = Observer { value ->
            value.let{
                _itemSelected.value = it
            }
        }
    }

    fun clearSelection(){
        _itemSelected.value = null
    }

    fun setItemSelection(item: MockiDataModel){
        itemDataSelected = item
    }

    fun fetchPokemonData(){
        _progressState.value = true
        viewModelScope.launch {
            val response = repository.getMocki()

            response?.body()?.let{list ->
                Log.e(">>>>>>>>>>>", list.toString())
                _ListState.value = list
            }
        }
    }

    //memory clear
    override fun onCleared() {
        super.onCleared()
        viewModelJob.cancel()
    }

}