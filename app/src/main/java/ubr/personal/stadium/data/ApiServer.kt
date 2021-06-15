package ubr.personal.stadium.data

import android.app.Notification
import retrofit2.Response
import retrofit2.http.*
import ubr.personal.stadium.data.model.*

interface ApiServer {

    @GET("admin/stadion?include=files")
    suspend fun getStadiumByCategory(
        @Query("filter[category_id]") categoryId: Int
    ): Response<GetStadiumResponse>


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

    @GET("admin/category")
    suspend fun getCategory(): Response<CategoryResponse>

    @GET("admin/stadion/{id}?include=files")
    suspend fun getStadiumById(
//        @Header("Authorization") token: String,
        @Path("id") id: Int
    ): Response<StadiumData>


}