package com.example.compiler.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun ExecutionTraceViewer(trace: List<String>) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp)
    ) {
        // Header with visual indicator
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
                text = "  EXECUTION TRACE",
                style = MaterialTheme.typography.titleMedium,
                fontWeight = FontWeight.Bold,
                color = MaterialTheme.colorScheme.primary
            )
        }

        if (trace.isEmpty()) {
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp),
                contentAlignment = Alignment.Center
            ) {
                Text(
                    text = "No execution trace available",
                    style = MaterialTheme.typography.bodyMedium,
                    color = MaterialTheme.colorScheme.onSurfaceVariant
                )
            }
        } else {
            // Timeline-style visualization
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(start = 12.dp)
            ) {
                trace.forEachIndexed { index, line ->
                    ExecutionStep(
                        stepNumber = index + 1,
                        content = line,
                        isLast = index == trace.size - 1
                    )
                }
            }
        }
    }
}

@Composable
private fun ExecutionStep(stepNumber: Int, content: String, isLast: Boolean) {
    Row(
        modifier = Modifier.padding(vertical = 8.dp),
        verticalAlignment = Alignment.Top
    ) {
        // Timeline indicator
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier.width(24.dp)
        ) {
            // Circle with step number
            Box(
                modifier = Modifier
                    .size(24.dp)
                    .background(MaterialTheme.colorScheme.primary, CircleShape)
                    .padding(4.dp),
                contentAlignment = Alignment.Center
            ) {
                Text(
                    text = stepNumber.toString(),
                    color = MaterialTheme.colorScheme.onPrimary,
                    fontSize = 12.sp,
                    fontWeight = FontWeight.Bold
                )
            }

            // Vertical line (except for last step)
            if (!isLast) {
                Box(
                    modifier = Modifier
                        .width(2.dp)
                        .height(24.dp)
                        .background(MaterialTheme.colorScheme.primary.copy(alpha = 0.5f))
                )
            }
        }

        // Content card
        Card(
            modifier = Modifier
                .fillMaxWidth()
                .padding(start = 12.dp),
            colors = CardDefaults.cardColors(
                containerColor = MaterialTheme.colorScheme.surfaceVariant
            ),
            elevation = CardDefaults.cardElevation(defaultElevation = 1.dp)
        ) {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(12.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = content,
                    style = MaterialTheme.typography.bodyLarge.copy(
                        fontFamily = FontFamily.Monospace
                    ),
                    color = MaterialTheme.colorScheme.onSurface,
                    modifier = Modifier.weight(1f)
                )

                // Small execution indicator
                Box(
                    modifier = Modifier
                        .size(8.dp)
                        .background(Color(0xFF00C853), CircleShape)
                )
            }
        }
    }
}