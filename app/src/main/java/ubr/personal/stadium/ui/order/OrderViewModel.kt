package ubr.personal.stadium.ui.order

import androidx.lifecycle.*
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import ubr.personal.stadium.data.model.FavoriteModel
import ubr.personal.stadium.data.model.StadiumData
import ubr.personal.stadium.data.repository.OrderRepository
import ubr.personal.stadium.util.Common
import ubr.personal.stadium.util.DataState
import javax.inject.Inject

@HiltViewModel
class OrderViewModel @Inject constructor(
    private val repository: OrderRepository, private val state: SavedStateHandle
) : ViewModel() {


    private val _stadiumData = MutableLiveData<DataState<StadiumData>>()
    val stadiumData: LiveData<DataState<StadiumData>> = _stadiumData

    private val _postFavorite = MutableLiveData<DataState<Boolean>>()
    val postFavorite: LiveData<DataState<Boolean>> get() = _postFavorite

    init {
        state.get<Int>("STADIUM_ID")?.let { getStadiumById(it) }
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


}