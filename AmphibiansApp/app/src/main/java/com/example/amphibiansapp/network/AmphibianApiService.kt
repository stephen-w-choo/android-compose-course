package com.example.amphibiansapp.network

import com.example.amphibiansapp.data.AmphibiansDataRepository
import com.example.amphibiansapp.data.DefaultAmphibiansDataRepository
import com.jakewharton.retrofit2.converter.kotlinx.serialization.asConverterFactory
import kotlinx.serialization.json.Json
import okhttp3.MediaType.Companion.toMediaType
import retrofit2.Retrofit
import retrofit2.http.GET



class DefaultAppContainer {
    private val BASE_URL =
        "https://android-kotlin-fun-mars-server.appspot.com/"

    private val retrofit: Retrofit = Retrofit.Builder()
        .addConverterFactory(Json.asConverterFactory("application/json".toMediaType()))
        .baseUrl(BASE_URL)
        .build()

    private val retrofitService: AmphibianApiService by lazy {
        retrofit.create(AmphibianApiService::class.java)
    }

    val amphibiansDataRepository: AmphibiansDataRepository by lazy {
        DefaultAmphibiansDataRepository(retrofitService)
    }
}

interface AmphibianApiService {
    @GET("amphibians")
    suspend fun getData(): List<AmphibianData>
}