package ubr.personal.stadium.ui.favorite

import androidx.lifecycle.*
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import ubr.personal.stadium.data.model.FavoriteModel
import ubr.personal.stadium.data.model.StadiumData
import ubr.personal.stadium.data.repository.FavoriteRepository
import ubr.personal.stadium.util.DataState
import javax.inject.Inject

@HiltViewModel
class FavoriteViewModel @Inject constructor(
    private val repository: FavoriteRepository,
    private val stateHandle: SavedStateHandle
) : ViewModel() {


    private val _stadiumList = MutableLiveData<DataState<List<StadiumData>>>()
    val stadiumList: LiveData<DataState<List<StadiumData>>> get() = _stadiumList




    init {
        getFavoriteList()
    }

    private fun getFavoriteList() {
        viewModelScope.launch {
            repository.getFavoriteList().collect {
                _stadiumList.postValue(it)
            }
        }
    }


}