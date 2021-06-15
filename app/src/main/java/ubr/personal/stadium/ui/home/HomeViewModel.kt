package ubr.personal.stadium.ui.home

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import ubr.personal.stadium.data.model.CategoryData
import ubr.personal.stadium.data.model.StadiumData
import ubr.personal.stadium.data.repository.HomeRepository
import ubr.personal.stadium.util.DataState
import javax.inject.Inject


@HiltViewModel
class HomeViewModel @Inject constructor(private val repository: HomeRepository) : ViewModel() {


    private val _stadiumList = MutableLiveData<DataState<List<StadiumData>>>()
    val stadiumList: LiveData<DataState<List<StadiumData>>> get() = _stadiumList

    private val _categoryList = MutableLiveData<DataState<List<CategoryData>>>()
    val categoryList :LiveData<DataState<List<CategoryData>>> = _categoryList

    init {
        getStadiumList(1)
        getCategoryList()
    }


    fun getStadiumList(categoryId:Int){

        viewModelScope.launch {
            repository.getAllStation(categoryId).collect {
                _stadiumList.postValue(it)
            }

        }
    }


    private fun getCategoryList(){
        viewModelScope.launch {
            repository.getAllCategory().collect {
                _categoryList.postValue(it)
            }
        }
    }




}