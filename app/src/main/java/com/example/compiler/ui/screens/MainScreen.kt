package com.example.compiler.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.DarkMode
import androidx.compose.material.icons.filled.LightMode
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.compiler.utils.CodeExamples
import com.example.compiler.viewmodel.CompilerViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MainScreen(viewModel: CompilerViewModel) {
    val navController = rememberNavController()
    var expanded by remember { mutableStateOf(false) }
    var selectedExampleName by remember { mutableStateOf("Examples") }
    var isDarkTheme by remember { mutableStateOf(false) }

    // Hardcoded theme definitions
    val lightColors = lightColorScheme(
        primary = Color(0xFFFF0000),
        secondary = Color(0xFF03DAC6),
        background = Color(0xFFFFFFFF),
        surface = Color(0xFFFFFFFF),
        onPrimary = Color.White,
        onSecondary = Color.Black,
        onBackground = Color.Black,
        onSurface = Color.Black,
    )

    val darkColors = darkColorScheme(
        primary = Color(0xFFFF0000),
        secondary = Color(0xFF03DAC6),
        background = Color(0xFF121212),
        surface = Color(0xFF121212),
        onPrimary = Color.Black,
        onSecondary = Color.Black,
        onBackground = Color.White,
        onSurface = Color.White,
    )

    MaterialTheme(
        colorScheme = if (isDarkTheme) darkColors else lightColors
    ) {
        Scaffold(
            topBar = {
                Surface(shadowElevation = 4.dp) {
                    Box(
                        modifier = Modifier
                            .padding(start = 12.dp, end = 12.dp, top = 12.dp) // 🟢 Add padding around the top bar
                            .fillMaxWidth()
                            .height(72.dp)
                            .clip(RoundedCornerShape(24.dp)) // 🔄 Rounded corners on all sides
                            .background(MaterialTheme.colorScheme.surfaceVariant)
                            .statusBarsPadding()
                    ) {
                        // Title: placed at the center
                        Text(
                            "Code View",
                            style = MaterialTheme.typography.titleLarge.copy(
                                color = MaterialTheme.colorScheme.primary
                            ),
                            modifier = Modifier
                                .offset(x = 110.dp, y = 22.dp) // 🔁 Change values to position text exactly
                        )


                        // Theme toggle: top-left or anywhere you want
                        IconButton(
                            onClick = { isDarkTheme = !isDarkTheme },
                            modifier = Modifier
                                .align(Alignment.CenterStart)
                                .padding(start = 16.dp)
                        ) {
                            Icon(
                                imageVector = if (isDarkTheme) Icons.Filled.DarkMode else Icons.Filled.LightMode,
                                contentDescription = "Toggle Theme",
                                tint = MaterialTheme.colorScheme.onSurface
                            )
                        }

                        // Examples dropdown: top-right or anywhere you want
                        Box(
                            modifier = Modifier
                                .align(Alignment.CenterEnd)
                                .padding(end = 16.dp)
                        ) {
                            OutlinedButton(
                                onClick = { expanded = true },
                                colors = ButtonDefaults.outlinedButtonColors(
                                    contentColor = MaterialTheme.colorScheme.primary
                                ),
                                contentPadding = PaddingValues(horizontal = 16.dp, vertical = 8.dp)
                            ) {
                                Text(
                                    text = selectedExampleName,
                                    style = MaterialTheme.typography.labelLarge
                                )
                            }

                            DropdownMenu(
                                expanded = expanded,
                                onDismissRequest = { expanded = false },
                                modifier = Modifier
                                    .background(MaterialTheme.colorScheme.surface)
                                    .padding(horizontal = 8.dp)
                            ) {
                                CodeExamples.examples.forEach { example ->
                                    DropdownMenuItem(
                                        text = {
                                            Text(
                                                example.name,
                                                color = MaterialTheme.colorScheme.onSurface
                                            )
                                        },
                                        onClick = {
                                            viewModel.setEditorCode(example.code)
                                            selectedExampleName = example.name
                                            expanded = false
                                        }
                                    )
                                }
                            }
                        }
                    }
                }
            }


        ) { innerPadding ->
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(innerPadding)
                    .background(MaterialTheme.colorScheme.background)
            ) {
                NavHost(
                    navController = navController,
                    startDestination = "editor",
                    modifier = Modifier
                        .weight(1f)
                        .padding(horizontal = 16.dp, vertical = 8.dp)

                ) {
                    composable("editor") {
                        CodeEditorScreen(navController = navController, viewModel = viewModel)
                    }
                    composable("result") {
                        ResultScreen(navController = navController, viewModel = viewModel)
                    }
                }
            }
        }
    }
}