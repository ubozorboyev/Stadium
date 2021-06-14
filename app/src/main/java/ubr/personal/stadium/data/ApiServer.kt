package ubr.personal.stadium.data

import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.POST
import ubr.personal.stadium.data.model.CreateStadiumModel
import ubr.personal.stadium.data.model.SignInModelResponse
import ubr.personal.stadium.data.model.SignInRequest

interface ApiServer {

    @GET("admin/stadion?include=category")
    suspend fun getStadiumByCategory(
        categoryId: Int
    )


    @POST("admin/stadion/create")
    suspend fun createStation(
        @Header("Authorization") token: String,
        @Body createModel: CreateStadiumModel
    )

    @POST("admin/user/sign-in")
    suspend fun signIn(
        @Body signInRequest: SignInRequest
    ): Response<SignInModelResponse>

    @POST("admin/user/order")
    suspend fun orderStation(

    )


}