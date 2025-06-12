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
import com.example.compiler.data.network.NetworkToken

@OptIn(ExperimentalLayoutApi::class)
@Composable
fun TokenList(tokens: List<NetworkToken>) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp)
    ) {
        // Header with decorative elements
        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier.padding(bottom = 12.dp)
        ) {
            Box(
                modifier = Modifier
                    .width(4.dp)
                    .height(24.dp)
                    .background(MaterialTheme.colorScheme.primary)
            )
            Text(
                text = "  TOKENS",
                style = MaterialTheme.typography.titleMedium,
                fontWeight = FontWeight.Bold,
                color = MaterialTheme.colorScheme.primary
            )
        }

        if (tokens.isEmpty()) {
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp),
                contentAlignment = Alignment.Center
            ) {
                Text(
                    text = "No tokens found",
                    style = MaterialTheme.typography.bodyMedium,
                    color = MaterialTheme.colorScheme.onSurfaceVariant
                )
            }
        } else {
            // Grid layout for tokens
            FlowRow(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(8.dp),
                verticalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                tokens.forEach { token ->
                    TokenCard(token)
                }
            }
        }
    }
}

@Composable
private fun TokenCard(token: NetworkToken) {
    val tokenColor = when (token.type) {
        "identifier" -> Color(0xFF2196F3)
        "decimal_integer_literal" -> Color(0xFF4CAF50)
        "string_fragment" -> Color(0xFF9C27B0)
        "void_type", "type_identifier" -> Color(0xFFFF9800)
        else -> MaterialTheme.colorScheme.secondary
    }

    Card(
        modifier = Modifier.widthIn(min = 100.dp, max = 200.dp),
        colors = CardDefaults.cardColors(
            containerColor = tokenColor.copy(alpha = 0.1f)
        ),
        elevation = CardDefaults.cardElevation(defaultElevation = 2.dp)
    ) {
        Column(
            modifier = Modifier.padding(12.dp)
        ) {
            // Token type badge
            Box(
                modifier = Modifier
                    .clip(MaterialTheme.shapes.small)
                    .background(tokenColor.copy(alpha = 0.2f))
                    .padding(horizontal = 8.dp, vertical = 4.dp)
            ) {
                Text(
                    text = token.type.replace("_", " "),
                    style = MaterialTheme.typography.labelSmall,
                    color = tokenColor,
                    fontWeight = FontWeight.Bold
                )
            }

            Spacer(modifier = Modifier.height(8.dp))

            // Token value
            Text(
                text = token.value,
                style = MaterialTheme.typography.bodyLarge.copy(
                    fontWeight = FontWeight.Bold
                ),
                color = MaterialTheme.colorScheme.onSurface
            )

            Spacer(modifier = Modifier.height(4.dp))

            // Position indicator
            Text(
                text = "Line ${token.startPosition.row + 1}, Col ${token.startPosition.column + 1}",
                style = MaterialTheme.typography.labelSmall,
                color = MaterialTheme.colorScheme.onSurfaceVariant
            )
        }
    }
}