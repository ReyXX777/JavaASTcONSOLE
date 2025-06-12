package com.example.compiler.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.compiler.data.network.*
import com.example.compiler.data.repository.CompilerRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class CompilerViewModel(private val repository: CompilerRepository) : ViewModel() {

    private val _tokens = MutableStateFlow<List<NetworkToken>>(emptyList())
    val tokens: StateFlow<List<NetworkToken>> = _tokens

    private val _ast = MutableStateFlow<String>("")
    val ast: StateFlow<String> = _ast

    private val _cfg = MutableStateFlow<List<CFGNode>>(emptyList())
    val cfg: StateFlow<List<CFGNode>> = _cfg

    private val _symbolTable = MutableStateFlow<Map<String, SymbolInfo>>(emptyMap())
    val symbolTable: StateFlow<Map<String, SymbolInfo>> = _symbolTable

    private val _executionTrace = MutableStateFlow<List<String>>(emptyList())
    val executionTrace: StateFlow<List<String>> = _executionTrace

    private val _output = MutableStateFlow("")
    val output: StateFlow<String> = _output

    private val _errorMessage = MutableStateFlow<String?>(null)
    val errorMessage: StateFlow<String?> = _errorMessage

    private val _isLoading = MutableStateFlow(false)
    val isLoading: StateFlow<Boolean> = _isLoading

    private val _editorCode = MutableStateFlow("")
    val editorCode: StateFlow<String> = _editorCode

    fun setEditorCode(code: String) {
        _editorCode.value = code
    }


    fun parseCode(code: String, language: String) {
        viewModelScope.launch {
            _isLoading.value = true
            try {
                val response = repository.parseCode(code, language)
                _tokens.value = response.tokens
                _ast.value = response.ast
                _cfg.value = response.cfg
                _errorMessage.value = null
            } catch (e: Exception) {
                _errorMessage.value = "Parse failed: ${e.localizedMessage}"
            } finally {
                _isLoading.value = false
            }
        }
    }

    fun executeCode(code: String, language: String) {
        viewModelScope.launch {
            _isLoading.value = true
            try {
                val response = repository.executeCode(code, language)
                _output.value = response.output
                _executionTrace.value = response.executionTrace
                _symbolTable.value = response.symbolTable
                _tokens.value = response.tokens
                _ast.value = response.ast
                _cfg.value = response.cfg
                _errorMessage.value = null
            } catch (e: Exception) {
                _errorMessage.value = "Execution failed: ${e.localizedMessage}"
            } finally {
                _isLoading.value = false
            }
        }
    }
}