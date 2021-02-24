package mal.art.daznsportscalendar.util.koin

import com.google.gson.Gson
import com.google.gson.GsonBuilder
import mal.art.daznsportscalendar.database.api.APIService
import mal.art.daznsportscalendar.util.base.BaseValues
import okhttp3.OkHttpClient
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

val restModule = module {
    single<OkHttpClient> {
        val okHttpClientBuilder = OkHttpClient.Builder()
        okHttpClientBuilder.build()
    }
    single<Gson> { GsonBuilder().create() }
    single<Retrofit> {
        Retrofit.Builder()
            .baseUrl(BaseValues.Url.baseUrl)
            .addConverterFactory(GsonConverterFactory.create(get()))
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .client(get())
            .build()
    }
    single<APIService> { get<Retrofit>().create<APIService>(APIService::class.java) }
}