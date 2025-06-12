package com.example.compiler.data.repository

import com.example.compiler.data.network.CompilerApiService
import com.example.compiler.data.network.ExecuteResponse
import com.example.compiler.data.network.ParseResponse
import com.example.compiler.data.network.ParseRequest

class CompilerRepository(private val apiService: CompilerApiService) {

    suspend fun parseCode(code: String, language: String): ParseResponse {
        return apiService.parseCode(ParseRequest(code, language))
    }

    suspend fun executeCode(code: String, language: String): ExecuteResponse {
        return apiService.executeCode(ParseRequest(code, language))
    }
}
