package ubr.personal.stadium.data.repository

import kotlinx.coroutines.flow.flow
import ubr.personal.stadium.data.ApiServer
import ubr.personal.stadium.data.model.FavoriteModel
import ubr.personal.stadium.data.model.OrderAreaRequest
import ubr.personal.stadium.util.Common
import ubr.personal.stadium.util.DataState
import javax.inject.Inject

class OrderRepository @Inject constructor(private val apiServer: ApiServer) {


    suspend fun getStationById(stadiumId: Int) = flow {

        emit(DataState.Loading)

        try {

            val token = if (Common.token.isNotEmpty()) "Bearer ${Common.token}" else ""

            val response = apiServer.getStadiumById(token, stadiumId)

            if (response.isSuccessful)
                emit(DataState.ResponseData(response.body()))
            else emit(DataState.Error(response.message()))

        } catch (e: Exception) {
            e.printStackTrace()
            emit(DataState.Error(e.message))
        }
    }

    suspend fun postFavoriteData(data: FavoriteModel) = flow {

        try {
            emit(DataState.Loading)

            val response = apiServer.postFavorite(data)

            if (response.isSuccessful)
                emit(DataState.ResponseData(response.body()?.success))
            else emit(DataState.Error(response.message()))

        } catch (e: Exception) {
            e.printStackTrace()
        }
    }

    suspend fun getFreeTime(stadiumId: Int, day: String) = flow {

        try {
            emit(DataState.Loading)

            val response = apiServer.getFreeTime(stadiumId, day)

            if (response.isSuccessful)
                emit(DataState.ResponseData(response.body()))
            else emit(DataState.Error(response.message()))


        } catch (e: Exception) {
            e.printStackTrace()
        }

    }


    suspend fun bronTime(orderAreaRequest: OrderAreaRequest) = flow {
        try {
            emit(DataState.Loading)

            val response = apiServer.orderStation("Bearer ${Common.token}", orderAreaRequest)

            if (response.isSuccessful)
                emit(DataState.ResponseData(response.body()))
            else emit(DataState.Error(response.message()))

        } catch (e: Exception) {
            e.printStackTrace()
        }

    }


}