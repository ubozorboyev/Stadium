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


    @GET("admin/category")
    suspend fun getCategory(): Response<CategoryResponse>

    @POST("admin/stadion/create")
    suspend fun createStation(
        @Header("Authorization") token: String,
        @Body createModel: CreateStadiumModel
    )

    // Order Page

    @GET("admin/stadion/{id}?include=files")
    suspend fun getStadiumById(
        @Path("id") id: Int
    ): Response<StadiumData>


    @POST("admin/user/order")
    suspend fun orderStation(

    )

    // Favorite Page

    @POST("admin/favourite")
    suspend fun postFavorite(
        @Body data: FavoriteModel
    ): Response<SuccessData>

    @GET("admin/user/favourite?include=files")
    suspend fun getFavoriteList(@Header("Authorization") token: String)
            : Response<GetStadiumResponse>

    // Profile Page

    @POST("admin/user/sign-in")
    suspend fun signIn(
        @Body signInRequest: SignInRequest
    ): Response<SignInModelResponse>

    @POST("admin/user/create")
    suspend fun userCreate(
        @Body data: UserCreateData
    ): Response<SignInModelResponse>


    @POST("admin/user/logout")
    suspend fun logOut(
        @Header("Authorization") token: String
    ): Response<SuccessData>


}