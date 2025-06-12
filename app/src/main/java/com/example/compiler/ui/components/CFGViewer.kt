package com.example.compiler.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.compiler.data.network.CFGNode

@Composable
fun CFGViewer(cfg: List<CFGNode>) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            "Control Flow Visualization",
            style = MaterialTheme.typography.headlineSmall,
            color = MaterialTheme.colorScheme.primary,
            modifier = Modifier.padding(bottom = 12.dp)
        )

        if (cfg.isEmpty()) {
            Text(
                "No control flow data available",
                style = MaterialTheme.typography.bodyMedium,
                color = MaterialTheme.colorScheme.onSurfaceVariant
            )
        } else {
            FlowVisualization(cfg)
        }
    }
}

@Composable
private fun FlowVisualization(nodes: List<CFGNode>) {
    val nodeMap = nodes.associateBy { it.id }

    Column(
        modifier = Modifier.fillMaxWidth(),
        verticalArrangement = Arrangement.spacedBy(4.dp)
    ) {
        nodes.forEach { node ->
            FlowNode(node)

            node.next?.let { nextId ->
                if (nodeMap.containsKey(nextId)) {
                    FlowConnectionArrow()
                }
            }
        }
    }
}

@Composable
private fun FlowNode(node: CFGNode) {
    Surface(
        modifier = Modifier.fillMaxWidth(),
        shape = RoundedCornerShape(12.dp),
        color = when (node.type) {
            "branch" -> MaterialTheme.colorScheme.secondaryContainer
            "loop" -> MaterialTheme.colorScheme.tertiaryContainer
            else -> MaterialTheme.colorScheme.surfaceVariant
        },
        tonalElevation = 2.dp,
        shadowElevation = 1.dp
    ) {
        Column(
            modifier = Modifier.padding(16.dp)
        ) {
            // Node header with ID and type
            Row(
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier.padding(bottom = 8.dp)
            ) {
                // ID badge
                Box(
                    modifier = Modifier
                        .size(28.dp)
                        .clip(RoundedCornerShape(6.dp))
                        .background(MaterialTheme.colorScheme.primary),
                    contentAlignment = Alignment.Center
                ) {
                    Text(
                        text = node.id.toString(),
                        color = MaterialTheme.colorScheme.onPrimary,
                        fontSize = 14.sp,
                        fontWeight = FontWeight.Bold
                    )
                }

                Spacer(modifier = Modifier.width(12.dp))

                // Type label
                Text(
                    text = node.type.replaceFirstChar { it.uppercase() },
                    style = MaterialTheme.typography.labelLarge,
                    color = MaterialTheme.colorScheme.primary,
                    fontWeight = FontWeight.Medium
                )
            }

            // Code line
            Text(
                text = node.line,
                style = MaterialTheme.typography.bodyLarge,
                color = MaterialTheme.colorScheme.onSurface,
                modifier = Modifier.padding(bottom = 4.dp)
            )

            // Next indicator if exists
            node.next?.let {
                Text(
                    text = "Next: $it",
                    style = MaterialTheme.typography.labelSmall,
                    color = MaterialTheme.colorScheme.onSurfaceVariant,
                    modifier = Modifier.padding(top = 4.dp)
                )
            }
        }
    }
}

@Composable
private fun FlowConnectionArrow() {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(start = 32.dp, top = 4.dp, bottom = 4.dp),
        verticalArrangement = Arrangement.spacedBy(2.dp)
    ) {
        // Arrow line
        Box(
            modifier = Modifier
                .height(24.dp)
                .width(2.dp)
                .background(MaterialTheme.colorScheme.primary.copy(alpha = 0.7f))
        )
        // Arrow head
        Box(
            modifier = Modifier
                .size(10.dp)
                .rotate(45f)
                .background(MaterialTheme.colorScheme.primary)
        )
    }
}