package ubr.personal.stadium.ui.order

import androidx.lifecycle.*
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import ubr.personal.stadium.data.model.StadiumData
import ubr.personal.stadium.data.repository.OrderRepository
import ubr.personal.stadium.util.DataState
import javax.inject.Inject

@HiltViewModel
class OrderViewModel @Inject constructor(
    private val repository: OrderRepository, private val state: SavedStateHandle
) : ViewModel() {


    private val _stadiumData = MutableLiveData<DataState<StadiumData>>()
    val stadiumData: LiveData<DataState<StadiumData>> = _stadiumData


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


}