package com.example.compiler.data.network

import retrofit2.http.Body
import retrofit2.http.POST

interface CompilerApiService {

    @POST("/parse")
    suspend fun parseCode(@Body request: ParseRequest): ParseResponse

    @POST("/execute")
    suspend fun executeCode(@Body request: ParseRequest): ExecuteResponse
}
