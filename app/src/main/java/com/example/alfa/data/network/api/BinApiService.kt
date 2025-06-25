package com.example.alfa.data.network.api

import com.example.alfa.data.network.dto.BinBaseResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path

interface BinApiService {
    @GET("{bin}")
    suspend fun getBinInfo(@Path("bin") bin: String): Response<BinBaseResponse>
}