package com.example.compiler.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.CheckCircle
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun OutputCard(output: String) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        // Header with decorative elements
        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier.padding(bottom = 12.dp)
        ) {
            Box(
                modifier = Modifier
                    .width(24.dp)
                    .height(3.dp)
                    .background(MaterialTheme.colorScheme.primary)
            )
            Text(
                text = " PROGRAM OUTPUT ",
                style = MaterialTheme.typography.titleMedium,
                color = MaterialTheme.colorScheme.primary,
                fontWeight = FontWeight.SemiBold,
                modifier = Modifier.padding(horizontal = 8.dp)
            )
            Box(
                modifier = Modifier
                    .width(24.dp)
                    .height(3.dp)
                    .background(MaterialTheme.colorScheme.primary)
            )
        }

        // Main output card
        Card(
            modifier = Modifier.fillMaxWidth(),
            colors = CardDefaults.cardColors(
                containerColor = MaterialTheme.colorScheme.surfaceVariant
            ),
            elevation = CardDefaults.cardElevation(defaultElevation = 4.dp),
            shape = RoundedCornerShape(12.dp)
        ) {
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(24.dp),
                contentAlignment = Alignment.Center
            ) {
                if (output.isBlank()) {
                    Text(
                        text = "No output generated",
                        style = MaterialTheme.typography.bodyMedium,
                        color = MaterialTheme.colorScheme.onSurfaceVariant
                    )
                } else {
                    Column(horizontalAlignment = Alignment.CenterHorizontally) {
                        // Decorative icon
                        Icon(
                            imageVector = Icons.Default.CheckCircle,
                            contentDescription = "Output",
                            tint = MaterialTheme.colorScheme.primary,
                            modifier = Modifier
                                .size(48.dp)
                                .padding(bottom = 12.dp)
                        )

                        // Output text with monospace font for code-like appearance
                        Text(
                            text = output,
                            style = MaterialTheme.typography.bodyLarge.copy(
                                fontFamily = FontFamily.Monospace,
                                fontSize = 18.sp
                            ),
                            color = MaterialTheme.colorScheme.onSurface,
                            textAlign = TextAlign.Center,
                            modifier = Modifier.padding(horizontal = 16.dp)
                        )

                        // Success message
                        Text(
                            text = "Program executed successfully",
                            style = MaterialTheme.typography.labelMedium,
                            color = MaterialTheme.colorScheme.primary,
                            modifier = Modifier.padding(top = 12.dp)
                        )
                    }
                }
            }
        }

        // Footer decoration
        Box(
            modifier = Modifier
                .padding(top = 12.dp)
                .width(100.dp)
                .height(2.dp)
                .background(MaterialTheme.colorScheme.primary.copy(alpha = 0.5f))
        )
    }
}