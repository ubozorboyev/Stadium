package ubr.personal.stadium.ui.profile

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.channels.ticker
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import okhttp3.internal.lockAndWaitNanos
import ubr.personal.stadium.data.model.SignInModelResponse
import ubr.personal.stadium.data.model.SuccessData
import ubr.personal.stadium.data.model.UserCreateData
import ubr.personal.stadium.data.repository.ProfileRepository
import ubr.personal.stadium.util.DataState
import javax.inject.Inject


@HiltViewModel
class ProfileViewModel @Inject constructor(private val repository: ProfileRepository) :
    ViewModel() {

    private val _signInModel = MutableLiveData<DataState<SignInModelResponse>>()
     val signInModel: LiveData<DataState<SignInModelResponse>> = _signInModel

    private val _successData = MutableLiveData<DataState<SuccessData>>()
     val successData: LiveData<DataState<SuccessData>> = _successData


    fun userCreate(userCreateData: UserCreateData) {

        viewModelScope.launch {
            repository.userCreate(userCreateData).collect {
                _signInModel.postValue(it)
            }
        }
    }

    fun logOut() {
        viewModelScope.launch {
            repository.logOut().collect {
                _successData.postValue(it)
            }
        }
    }


}