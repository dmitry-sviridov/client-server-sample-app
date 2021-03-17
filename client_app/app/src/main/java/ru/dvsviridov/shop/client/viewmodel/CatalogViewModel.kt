package ru.dvsviridov.shop.client.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import ru.dvsviridov.shop.client.model.ShopApiRepository
import ru.dvsviridov.shop.client.model.dto.Item
import ru.dvsviridov.shop.client.ui.ResourceState
import javax.inject.Inject

@HiltViewModel
class CatalogViewModel
@Inject constructor(
    private val shopApiRepository: ShopApiRepository
) : ViewModel() {
    
    private val _resourceState = MutableLiveData<ResourceState<List<Item>>>()

    val resourceState: LiveData<ResourceState<List<Item>>>
        get() = _resourceState

    init {
        getItemsFromShop()
    }

    fun getItemsFromShop() = viewModelScope.launch {
        _resourceState.postValue(ResourceState.loading(null))
        delay(1500) // для видимости загрузки
        shopApiRepository.getItemsFromServer().let { response ->
            if (response.isSuccessful) {
                Log.d(TAG, "getItemsFromShop: ${response.body()}")
                _resourceState.postValue(ResourceState.success(response.body()))
            } else {
                Log.d(TAG, "getItemsFromShop: ${response.errorBody()}")
                _resourceState.postValue(ResourceState.error(response.errorBody().toString(), null))
            }
        }
    }
    companion object {
        private val TAG = CatalogViewModel::class.java.simpleName
    }
}