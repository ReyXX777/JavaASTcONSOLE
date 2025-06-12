package com.example.compiler.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.example.compiler.data.network.SymbolInfo

@Composable
fun SymbolTable(entries: Map<String, SymbolInfo>) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp)
    ) {
        Text(
            "Symbol Table",
            style = MaterialTheme.typography.headlineSmall,
            color = MaterialTheme.colorScheme.primary,
            modifier = Modifier.padding(bottom = 12.dp)
        )

        if (entries.isEmpty()) {
            Text(
                "No symbols found",
                style = MaterialTheme.typography.bodyMedium,
                color = MaterialTheme.colorScheme.onSurfaceVariant
            )
        } else {
            SymbolTableGrid(entries)
        }
    }
}

@Composable
private fun SymbolTableGrid(entries: Map<String, SymbolInfo>) {
    Column(
        modifier = Modifier.fillMaxWidth(),
        verticalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        // Header
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .background(MaterialTheme.colorScheme.primaryContainer)
                .padding(12.dp),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Text(
                "Symbol",
                style = MaterialTheme.typography.labelLarge,
                fontWeight = FontWeight.Bold,
                color = MaterialTheme.colorScheme.onPrimaryContainer
            )
            Text(
                "Details",
                style = MaterialTheme.typography.labelLarge,
                fontWeight = FontWeight.Bold,
                color = MaterialTheme.colorScheme.onPrimaryContainer
            )
        }

        // Entries
        entries.forEach { (name, info) ->
            SymbolEntry(name = name, info = info)
        }
    }
}

@Composable
private fun SymbolEntry(name: String, info: SymbolInfo) {
    Card(
        modifier = Modifier.fillMaxWidth(),
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.surfaceVariant
        ),
        elevation = CardDefaults.cardElevation(defaultElevation = 1.dp)
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(12.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            // Symbol name with type badge
            Column {
                Text(
                    text = name,
                    style = MaterialTheme.typography.titleMedium,
                    fontWeight = FontWeight.Bold,
                    color = MaterialTheme.colorScheme.primary
                )
                TypeBadge(info.type)
            }

            // Symbol details
            Column(horizontalAlignment = Alignment.End) {
                ValueChip(info.value)
                Text(
                    text = "Line ${info.line}",
                    style = MaterialTheme.typography.labelSmall,
                    color = MaterialTheme.colorScheme.onSurfaceVariant
                )
            }
        }
    }
}

@Composable
private fun TypeBadge(type: String) {
    val typeColor = when (type.lowercase()) {
        "int" -> Color(0xFF4CAF50)
        "string" -> Color(0xFF2196F3)
        "float", "double" -> Color(0xFF9C27B0)
        "boolean" -> Color(0xFFFF9800)
        else -> MaterialTheme.colorScheme.secondary
    }

    Box(
        modifier = Modifier
            .clip(MaterialTheme.shapes.small)
            .background(typeColor.copy(alpha = 0.2f))
            .padding(horizontal = 8.dp, vertical = 4.dp)
    ) {
        Text(
            text = type,
            style = MaterialTheme.typography.labelMedium,
            color = typeColor,
            fontWeight = FontWeight.Medium
        )
    }
}

@Composable
private fun ValueChip(value: String) {
    Box(
        modifier = Modifier
            .clip(MaterialTheme.shapes.small)
            .background(MaterialTheme.colorScheme.surface)
            .padding(horizontal = 8.dp, vertical = 4.dp)
    ) {
        Text(
            text = value,
            style = MaterialTheme.typography.bodyMedium,
            color = MaterialTheme.colorScheme.onSurface,
            fontWeight = FontWeight.Normal
        )
    }
}