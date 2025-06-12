package com.example.compiler.ui.components

import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun CodeInputField(
    code: String,
    onCodeChange: (String) -> Unit
) {
    val scrollState = rememberScrollState()

    Surface(
        color = MaterialTheme.colorScheme.surface, // Ensures proper background
        tonalElevation = 2.dp,
        shape = MaterialTheme.shapes.medium,
        modifier = Modifier
            .fillMaxWidth()
            .height(250.dp)
            .border(1.dp, MaterialTheme.colorScheme.primary, MaterialTheme.shapes.medium)
    ) {
        Box(
            modifier = Modifier
                .padding(12.dp)
                .fillMaxSize()
        ) {
            BasicTextField(
                value = code,
                onValueChange = onCodeChange,
                textStyle = TextStyle.Default.copy(
                    fontSize = 14.sp,
                    color = MaterialTheme.colorScheme.onSurface
                ),
                modifier = Modifier
                    .fillMaxSize()
                    .verticalScroll(scrollState) // Apply scrolling here
            )

            // Placeholder text only when code is empty
            if (code.isEmpty()) {
                Text(
                    text = "Enter your code here...",
                    color = Color.Gray,
                    fontSize = 14.sp,
                    modifier = Modifier.padding(4.dp)
                )
            }
        }
    }
}
