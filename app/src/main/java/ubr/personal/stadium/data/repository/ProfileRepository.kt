package ubr.personal.stadium.data.repository

import kotlinx.coroutines.flow.flow
import ubr.personal.stadium.data.ApiServer
import ubr.personal.stadium.data.model.UserCreateData
import ubr.personal.stadium.util.Common
import ubr.personal.stadium.util.DataState
import javax.inject.Inject

class ProfileRepository @Inject constructor(private val apiServer: ApiServer) {


    suspend fun userCreate(data: UserCreateData) = flow {

        try {

            emit(DataState.Loading)

            val response = apiServer.userCreate(data)

            if (response.isSuccessful)
                emit(DataState.ResponseData(response.body()))
            else emit(DataState.Error(response.message()))


        } catch (e: Exception) {
            e.printStackTrace()
        }

    }

    suspend fun logOut() = flow {

        try {
            emit(DataState.Loading)

            val response = apiServer.logOut("Bearer ${Common.token}")

            if (response.isSuccessful) emit(DataState.ResponseData(response.body()))
            else emit(DataState.Error(response.message()))
        } catch (e: Exception) {
            e.printStackTrace()
        }

    }


}