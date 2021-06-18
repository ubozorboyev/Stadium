package ubr.personal.stadium.data.repository

import kotlinx.coroutines.flow.flow
import ubr.personal.stadium.data.ApiServer
import ubr.personal.stadium.data.model.FavoriteModel
import ubr.personal.stadium.util.Common
import ubr.personal.stadium.util.DataState
import javax.inject.Inject

class FavoriteRepository @Inject constructor(private val apiServer: ApiServer) {





    suspend fun getFavoriteList() = flow {

        try {
            emit(DataState.Loading)

            val response = apiServer.getFavoriteList("Bearer ${Common.token}")

            if (response.isSuccessful)
                emit(DataState.ResponseData(response.body()?.data))
            else emit(DataState.Error(response.message()))


        } catch (e: Exception) {
            e.printStackTrace()
        }

    }



}