package ubr.personal.stadium.data

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

    @GET("admin/stadion/{id}?include=files,favourite")
    suspend fun getStadiumById(
        @Header("Authorization") token: String,
        @Path("id") id: Int
    ): Response<StadiumData>


    @POST("admin/user/order")
    suspend fun orderStation(
        @Header("Authorization") token: String,
        @Body data: OrderAreaRequest
    )

    @GET("admin/stadion/list")
    suspend fun getFreeTime(
        @Query("stadion_id") stadiumId: Int,
        @Query("day") day: String
    ): Response<TImeListResponse>

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