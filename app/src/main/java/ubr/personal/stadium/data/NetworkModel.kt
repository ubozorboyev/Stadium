package ubr.personal.stadium.data

import android.content.Context
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import okhttp3.Cache
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import ubr.personal.stadium.util.Common
import java.util.concurrent.TimeUnit
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
object NetworkModel {

    private const val MAX_SCALE = 60 * 60 * 24 * 30 // 30 day
    private const val CACHE_SIZE = 30 * 1024 * 1024L // 20 MB
    private const val TAG = "NetworkModel"

    @Provides
    fun getOkHttp(interceptor: Interceptor, @ApplicationContext context: Context): OkHttpClient {

        val logging = HttpLoggingInterceptor()


        logging.level = HttpLoggingInterceptor.Level.BODY

        val cache = Cache(context.cacheDir, CACHE_SIZE)

        return OkHttpClient.Builder()
            .cache(cache)
            .addInterceptor(logging)
            .addInterceptor(interceptor)
            .readTimeout(60, TimeUnit.SECONDS)
            .writeTimeout(60, TimeUnit.SECONDS)
            .connectTimeout(120, TimeUnit.SECONDS)
            .build()
    }


    @Provides
    @Singleton
    fun getApiService(okHttpClient: OkHttpClient): ApiServer = Retrofit.Builder()
        .baseUrl(Common.BASE_URL)
        .client(okHttpClient)
        .addConverterFactory(GsonConverterFactory.create())
        .build()
        .create(ApiServer::class.java)


}