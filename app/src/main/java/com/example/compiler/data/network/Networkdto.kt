package com.example.compiler.data.network

data class ParseRequest(
    val code: String,
    val language: String
)

data class Position(
    val row: Int,
    val column: Int
)

data class NetworkToken(
    val value: String,
    val type: String,
    val startPosition: Position,
    val endPosition: Position
)

data class ParseResponse(
    val tokens: List<NetworkToken>,
    val ast: String,
    val cfg: List<CFGNode> 

)

data class SymbolInfo(
    val type: String,
    val value: String,
    val line: Int
)

data class ExecuteResponse(
    val output: String,
    val error: String,
    val exitCode: Int,
    val executionTrace: List<String>,
    val symbolTable: Map<String, SymbolInfo>,
    val tokens: List<NetworkToken>,
    val ast: String,
    val cfg: List<CFGNode>
)
data class CFGNode(
    val id: Int,
    val type: String,
    val line: String,
    val next: Int?
)
