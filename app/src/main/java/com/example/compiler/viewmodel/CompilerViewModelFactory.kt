package com.example.compiler.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.compiler.data.repository.CompilerRepository

class CompilerViewModelFactory(
    private val repository: CompilerRepository
) : ViewModelProvider.Factory {
    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return CompilerViewModel(repository) as T
    }
}
