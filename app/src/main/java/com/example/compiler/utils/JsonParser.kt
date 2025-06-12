package com.example.compiler.utils

import com.example.compiler.data.network.NetworkToken
import com.example.compiler.data.network.SymbolInfo
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

object JsonParser {
    private val gson = Gson()

    fun parseTokens(json: String): List<NetworkToken> {
        val type = object : TypeToken<List<NetworkToken>>() {}.type
        return gson.fromJson(json, type)
    }

    fun parseAST(json: String): String {
        // If the AST is returned as a JSON string, just return it as-is
        return json
    }

    fun parseCFG(json: String): String {
        // Same approach for CFG: it's just a raw string (e.g., DOT or plaintext)
        return json
    }

    fun parseSymbolTable(json: String): Map<String, SymbolInfo> {
        val type = object : TypeToken<Map<String, SymbolInfo>>() {}.type
        return gson.fromJson(json, type)
    }
}
