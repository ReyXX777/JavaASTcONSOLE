package com.example.compiler.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.darkColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.dp
import com.example.compiler.utils.ASTNode
import com.example.compiler.utils.parseAST

@Composable
fun ASTViewer(ast: String) {
    // Define hardcoded dark theme colors
    val darkColors = darkColorScheme(
        primary = Color(0xFFBB86FC),     // Purple accent
        secondary = Color(0xFF03DAC6),   // Teal accent
        background = Color(0xFF121212),  // Dark background
        surface = Color(0xFF1E1E1E),     // Slightly lighter dark surface
        onPrimary = Color.Black,
        onSecondary = Color.Black,
        onBackground = Color.White,
        onSurface = Color.White,
        error = Color(0xFFCF6679)        // Error color
    )

    MaterialTheme(colorScheme = darkColors) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp)
                .background(MaterialTheme.colorScheme.surface)  // Use theme surface color
        ) {
            Text(
                "Abstract Syntax Tree (AST)",
                style = MaterialTheme.typography.titleMedium,
                color = MaterialTheme.colorScheme.primary    // Use theme primary color
            )

            if (ast.isBlank()) {
                Text(
                    "No AST available",
                    style = MaterialTheme.typography.bodyMedium,
                    color = MaterialTheme.colorScheme.onSurface  // Theme-aware text color
                )
                return@Column
            }

            val root = remember(ast) {
                try {
                    parseAST(ast)
                } catch (e: Exception) {
                    null
                }
            }

            if (root == null) {
                Text(
                    "Failed to parse AST",
                    style = MaterialTheme.typography.bodyMedium,
                    color = MaterialTheme.colorScheme.error      // Theme-aware error color
                )
            } else {
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .background(MaterialTheme.colorScheme.background)  // Theme background
                        .padding(16.dp)
                ) {
                    ASTTree(root)
                }
            }
        }
    }
}

@Composable
fun ASTTree(node: ASTNode, prefix: String = "", isLast: Boolean = true) {
    val currentPrefix = if (isLast) "└── " else "├── "
    val newPrefix = prefix + if (isLast) "    " else "│   "

    val annotatedText = buildAnnotatedString {
        withStyle(SpanStyle(color = MaterialTheme.colorScheme.onSurface)) {  // Theme-aware prefix color
            append(prefix + currentPrefix)
        }
        withStyle(
            SpanStyle(
                color = if (node.isNamed) MaterialTheme.colorScheme.primary else MaterialTheme.colorScheme.onSurface,  // Theme-aware node color
                fontWeight = if (node.label.contains(":")) MaterialTheme.typography.bodyMedium.fontWeight
                else MaterialTheme.typography.bodyMedium.fontWeight
            )
        ) {
            append(node.label)
        }
    }

    Text(
        text = annotatedText,
        style = MaterialTheme.typography.bodyMedium,
        modifier = Modifier.padding(vertical = 2.dp)
    )

    node.children.forEachIndexed { index, child ->
        ASTTree(child, newPrefix, index == node.children.size - 1)
    }
}