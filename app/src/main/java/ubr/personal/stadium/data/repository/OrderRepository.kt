package ubr.personal.stadium.data.repository

import kotlinx.coroutines.flow.flow
import ubr.personal.stadium.data.ApiServer
import ubr.personal.stadium.util.DataState
import java.lang.Exception
import javax.inject.Inject

class OrderRepository @Inject constructor(private val apiServer: ApiServer) {


    suspend fun getStationById(stadiumId: Int) = flow {

        emit(DataState.Loading)

        try {

            val response = apiServer.getStadiumById(stadiumId)

            if (response.isSuccessful)
                emit(DataState.ResponseData(response.body()))
            else emit(DataState.Error(response.message()))

        } catch (e: Exception) {
            e.printStackTrace()
            emit(DataState.Error(e.message))
        }

    }


}