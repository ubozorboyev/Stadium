package ubr.personal.stadium.data.repository

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.withContext
import ubr.personal.stadium.data.ApiServer
import ubr.personal.stadium.data.model.ImageListData
import ubr.personal.stadium.data.model.StadiumData
import ubr.personal.stadium.util.Common
import ubr.personal.stadium.util.DataState
import javax.inject.Inject


class HomeRepository @Inject constructor(private val apiServer: ApiServer) {


    suspend fun getAllStation(categoryId: Int) = flow {

        try {
            emit(DataState.Loading)

            val response = apiServer.getStadiumByCategory(categoryId)

            if (response.isSuccessful)
                emit(DataState.ResponseData(response.body()?.data))
            else emit(DataState.Error(response.errorBody()?.string()))

        } catch (e: Exception) {
            emit(DataState.Error(e.message))
            e.printStackTrace()
        }

    }


    suspend fun getAllCategory() = flow {

        try {

            emit(DataState.Loading)
            val response = apiServer.getCategory()

            if (response.isSuccessful)
                emit(DataState.ResponseData(response.body()?.data))
            else emit(DataState.Error(response.errorBody()?.string()))

        } catch (e: Exception) {
            emit(DataState.Error(e.message))
            e.printStackTrace()
        }
    }


    suspend fun getAllImages(): ImageListData? = withContext(Dispatchers.IO) {

        return@withContext try {
            val response = apiServer.getAllImages()
            response?.body()

        } catch (e: Exception) {
            e.printStackTrace()
            return@withContext null
        }

    }


}