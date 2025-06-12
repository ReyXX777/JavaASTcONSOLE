// AstTreeView.kt
package com.example.compiler.ui.components

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.compiler.utils.ASTNode

@Composable
fun ASTTreeView(node: ASTNode, indent: Int = 0) {
    Column(modifier = Modifier.padding(start = indent.dp)) {
        Text(text = node.label, style = MaterialTheme.typography.bodyMedium)
        node.children.forEach { child ->
            ASTTreeView(node = child, indent = indent + 12)
        }
    }
}
