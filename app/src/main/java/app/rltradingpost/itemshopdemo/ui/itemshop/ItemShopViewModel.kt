package app.rltradingpost.itemshopdemo.ui.itemshop

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import app.rltradingpost.itemshopdemo.data.repository.ItemShopRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ItemShopViewModel @Inject constructor(
    private val repository: ItemShopRepository
) : ViewModel() {

//    init {
//        println("getting things")
//        getThings()
//    }

    fun getThings() {
        viewModelScope.launch {
            repository.getItemShop().collect { result ->
                println(result)
            }
        }
    }

}