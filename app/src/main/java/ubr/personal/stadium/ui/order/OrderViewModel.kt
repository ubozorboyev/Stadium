package ubr.personal.stadium.ui.order

import android.util.Log
import androidx.lifecycle.*
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import ubr.personal.stadium.data.model.*
import ubr.personal.stadium.data.repository.OrderRepository
import ubr.personal.stadium.util.Common
import ubr.personal.stadium.util.DataState
import javax.inject.Inject

@HiltViewModel
class OrderViewModel @Inject constructor(
    private val repository: OrderRepository, private val state: SavedStateHandle
) : ViewModel() {


    private val TAG = "OrderViewModel"

    private val _stadiumData = MutableLiveData<DataState<StadiumData>>()
    val stadiumData: LiveData<DataState<StadiumData>> = _stadiumData

    private val _postFavorite = MutableLiveData<DataState<Boolean>>()
    val postFavorite: LiveData<DataState<Boolean>> get() = _postFavorite

    private val _stadiumTime = MutableLiveData<DataState<TImeListResponse>>()
    val stadiumTime: LiveData<DataState<TImeListResponse>> get() = _stadiumTime

    private val _orderTime = MutableLiveData<DataState<OrderResponseModel>>()
    val orderTime: LiveData<DataState<OrderResponseModel>> get() = _orderTime

    private val stadiumId: Int?

    init {
        stadiumId = state.get<Int>("STADIUM_ID")

        stadiumId?.let {
            getStadiumById(it)
        }
    }


    private fun getStadiumById(stadiumId: Int) {
        viewModelScope.launch {
            repository.getStationById(stadiumId).collect {
                _stadiumData.postValue(it)
            }
        }

    }

    fun postFavoriteData() {

        val model = FavoriteModel(Common.userId, state.get<Int>("STADIUM_ID") ?: 0)

        viewModelScope.launch {
            repository.postFavoriteData(model).collect {
                _postFavorite.postValue(it)
            }
        }
    }

    fun getFreeTimes(day: String) {
        viewModelScope.launch {
            stadiumId?.let {
                repository.getFreeTime(it, day).collect {
                    _stadiumTime.postValue(it)
                }
            }
        }
    }

    fun bronFromToTo(from: String, to: String) {

        viewModelScope.launch {

            repository.bronTime(OrderAreaRequest(Common.userId, stadiumId ?: 0, from, to)).collect {
                _orderTime.postValue(it)
            }
        }

    }

}