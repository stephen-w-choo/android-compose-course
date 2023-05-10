package com.example.marsphotos.network

import com.example.marsphotos.network.AppContainer
import com.example.marsphotos.data.DefaultMarsPhotosRepository
import com.example.marsphotos.data.MarsPhoto
import com.example.marsphotos.data.MarsPhotosRepository
import com.jakewharton.retrofit2.converter.kotlinx.serialization.asConverterFactory
import kotlinx.serialization.json.Json
import okhttp3.MediaType.Companion.toMediaType
import retrofit2.Retrofit
import retrofit2.http.GET



class DefaultAppContainer: AppContainer {
    private val BASE_URL =
        "https://android-kotlin-fun-mars-server.appspot.com"

    private val retrofit: Retrofit = Retrofit.Builder()
        .addConverterFactory(Json.asConverterFactory("application/json".toMediaType()))
        .baseUrl(BASE_URL)
        .build()

    private val retrofitService: MarsApiService by lazy {
        retrofit.create(MarsApiService::class.java)
    }

    override val marsPhotosRepository: MarsPhotosRepository by lazy {
        DefaultMarsPhotosRepository(retrofitService)
    }
}


interface MarsApiService {
    @GET("photos") // specifies that we're requesting the endpoint /photos
    suspend fun getPhotos(): List<MarsPhoto>
    // when this method is invoked, the string 'photos' will be appended to the base URL
}

