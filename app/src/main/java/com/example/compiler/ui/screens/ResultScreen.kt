package com.example.compiler.ui.screens

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.example.compiler.ui.components.*
import com.example.compiler.viewmodel.CompilerViewModel

@Composable
fun ResultScreen(navController: NavHostController, viewModel: CompilerViewModel) {
    val tokens by viewModel.tokens.collectAsState()
    val ast by viewModel.ast.collectAsState()
    val cfg by viewModel.cfg.collectAsState()
    val symbolTable by viewModel.symbolTable.collectAsState()
    val output by viewModel.output.collectAsState()
    val executionTrace by viewModel.executionTrace.collectAsState()
    val isLoading by viewModel.isLoading.collectAsState()
    val errorMessage by viewModel.errorMessage.collectAsState()

    Surface(
        modifier = Modifier.fillMaxSize()
    ) {
        if (isLoading) {
            Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                CircularProgressIndicator()
            }
        } else {
            LazyColumn(
                modifier = Modifier.fillMaxSize(),
                verticalArrangement = Arrangement.spacedBy(16.dp),
                contentPadding = PaddingValues(16.dp)
            ) {
                if (!errorMessage.isNullOrBlank()) {
                    item { ErrorCard(message = errorMessage ?: "Unknown error") }
                }

                item { TokenList(tokens) }
                item { ASTViewer(ast) }
                item { CFGViewer(cfg) }
                item { SymbolTable(entries = symbolTable) }
                item { OutputCard(output) }

                if (executionTrace.isNotEmpty()) {
                    item {
                        ExecutionTraceViewer(trace = executionTrace)
                    }
                }
            }
        }
    }
}


@Composable
private fun TraceItemCard(traceItem: String) {
    Card(
        modifier = Modifier.fillMaxWidth()
    ) {
        Text(
            text = traceItem,
            style = MaterialTheme.typography.bodyMedium,
            modifier = Modifier.padding(16.dp)
        )
    }
}

@Composable
private fun ErrorCard(message: String) {
    Card(
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.errorContainer,
            contentColor = MaterialTheme.colorScheme.onErrorContainer
        ),
        modifier = Modifier.fillMaxWidth()
    ) {
        Text(
            text = message,
            style = MaterialTheme.typography.bodyMedium,
            modifier = Modifier.padding(16.dp)
        )
    }
}
