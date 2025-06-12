package com.example.compiler.ui.screens

import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.*
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.input.OffsetMapping
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.text.input.TransformedText
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.example.compiler.viewmodel.CompilerViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlin.math.min

private val LineHeight = 18.sp
private val EditorFontSize = 16.sp
private val GutterWidth = 48.dp
private val NavyMirrorColor = Color(0xFF0B1A2F) // A deep glossy navy-like tone


@Composable
fun CodeEditorScreen(navController: NavHostController, viewModel: CompilerViewModel) {
    val code by viewModel.editorCode.collectAsState()
    val errorMessage by viewModel.errorMessage.collectAsState()
    val scrollState = rememberScrollState()

    // Count the number of lines in the code
    val lineCount = code.lines().size

    Surface(
        modifier = Modifier.fillMaxSize(),
        color = MaterialTheme.colorScheme.background
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(8.dp)
                .imePadding()
        ) {
            EditorToolbar(viewModel, navController, code, errorMessage)

            Box(
                modifier = Modifier
                    .weight(1f)
                    .border(1.dp, MaterialTheme.colorScheme.outline, MaterialTheme.shapes.medium)
                    .background(NavyMirrorColor)
            ) {
                Row(modifier = Modifier.fillMaxSize()) {
                    // Make both the line numbers and the editor use the same scroll state
                    LineNumbersColumn(
                        lineCount = lineCount,
                        errorMessage = errorMessage,
                        scrollState = scrollState
                    )
                    CodeEditorField(
                        code = code,
                        onCodeChange = { viewModel.setEditorCode(it) },
                        errorMessage = errorMessage,
                        scrollState = scrollState,
                        modifier = Modifier
                            .weight(1f)
                            .padding(8.dp)
                    )
                }
            }
        }
    }
}

@Composable
private fun EditorToolbar(
    viewModel: CompilerViewModel,
    navController: NavHostController,
    code: String,
    errorMessage: String?
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(bottom = 8.dp),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Row(
            horizontalArrangement = Arrangement.spacedBy(8.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                "Code Editor",
                style = MaterialTheme.typography.titleMedium,
                color = MaterialTheme.colorScheme.primary
            )
            if (errorMessage != null) {
                Icon(
                    Icons.Default.Warning,
                    contentDescription = "Error",
                    tint = MaterialTheme.colorScheme.error,
                    modifier = Modifier.size(20.dp)
                )
            }
        }

        Row(horizontalArrangement = Arrangement.spacedBy(8.dp)) {
            IconButton(
                onClick = {
                    viewModel.setEditorCode("")
                }
            ) {
                Icon(Icons.Default.Clear, contentDescription = "Clear")
            }
            IconButton(
                onClick = {
                    viewModel.parseCode(code, "java")
                }
            ) {
                Icon(Icons.Default.Build, contentDescription = "Parse")
            }
            FilledTonalButton(
                onClick = {
                    viewModel.parseCode(code, "java")
                    viewModel.executeCode(code, "java")
                    navController.navigate("result")
                },
                colors = ButtonDefaults.filledTonalButtonColors(
                    containerColor = MaterialTheme.colorScheme.primaryContainer
                ),
                elevation = ButtonDefaults.buttonElevation(defaultElevation = 4.dp)
            ) {
                Icon(Icons.Default.PlayArrow, contentDescription = "Run")
                Spacer(modifier = Modifier.width(4.dp))
                Text("Run")
            }
        }
    }
}

@Composable
private fun LineNumbersColumn(
    lineCount: Int,
    errorMessage: String?,
    scrollState: ScrollState
) {
    val hasError = errorMessage != null

    Box(
        modifier = Modifier
            .width(GutterWidth)
            .background(MaterialTheme.colorScheme.surfaceVariant)
            .verticalScroll(scrollState)
    ) {
        Column(
            modifier = Modifier
                .padding(start = 12.dp, end = 8.dp, top = 8.dp)
                .fillMaxHeight()
        ) {
            repeat(maxOf(1, lineCount)) { i ->
                Text(
                    text = (i + 1).toString(),
                    style = TextStyle(
                        fontFamily = FontFamily.Monospace,
                        fontSize = EditorFontSize,
                        color = if (hasError && errorMessage?.contains("line ${i+1}") == true)
                            MaterialTheme.colorScheme.error
                        else
                            MaterialTheme.colorScheme.onSurfaceVariant,
                        lineHeight = LineHeight
                    ),
                    modifier = Modifier
                        .height(LineHeight.value.dp)
                        .padding(bottom = 2.dp)
                )
            }
            // Add extra padding at the bottom to ensure scrolling works properly
            Spacer(modifier = Modifier.height(8.dp))
        }
    }
}

@Composable
private fun CodeEditorField(
    code: String,
    onCodeChange: (String) -> Unit,
    errorMessage: String?,
    scrollState: ScrollState,
    modifier: Modifier = Modifier
) {
    var textFieldValue by remember { mutableStateOf(TextFieldValue(code)) }
    val scope = rememberCoroutineScope()

    // Syntax highlighting colors
    val keywordColor = Color(0xFF0000FF)
    val classColor = Color(0xFF2B91AF)
    val functionColor = Color(0xFFCC7832)
    val stringColor = Color(0xFF6A8759)
    val commentColor = Color(0xFF808080)
    val errorColor = Color.Red

    // Enhanced keyword list
    val keywords = setOf(
        "package", "import", "fun", "val", "var", "private", "public", "protected", "class",
        "interface", "object", "if", "else", "when", "for", "while", "do", "return",
        "by", "in", "is", "null", "try", "catch", "finally", "throw"
    )

    // Autocomplete suggestions
    val suggestions = remember { mutableStateListOf<String>() }

    fun highlightSyntax(code: String, hasError: Boolean): AnnotatedString {
        return buildAnnotatedString {
            append(code)

            // Comments
            Regex("//.*?(?=\n|$)").findAll(code).forEach { match ->
                addStyle(SpanStyle(color = commentColor), match.range.first, match.range.last + 1)
            }
            Regex("/\\*[\\s\\S]*?\\*/").findAll(code).forEach { match ->
                addStyle(SpanStyle(color = commentColor), match.range.first, match.range.last + 1)
            }

            // Strings
            Regex("\"[^\"]*\"").findAll(code).forEach { match ->
                addStyle(SpanStyle(color = stringColor), match.range.first, match.range.last + 1)
            }

            // Keywords
            keywords.forEach { keyword ->
                Regex("\\b$keyword\\b").findAll(code).forEach { match ->
                    addStyle(SpanStyle(color = keywordColor), match.range.first, match.range.last + 1)
                }
            }

            // Classes
            Regex("[A-Z][a-zA-Z0-9]*").findAll(code).forEach { match ->
                addStyle(SpanStyle(color = classColor), match.range.first, match.range.last + 1)
            }

            // Functions
            Regex("[a-zA-Z][a-zA-Z0-9]*\\s*(?=\\()").findAll(code).forEach { match ->
                addStyle(SpanStyle(color = functionColor), match.range.first, match.range.last + 1)
            }

            // Error highlight
            if (hasError) {
                // Try to extract line number from error message if available
                val lineNumberRegex = Regex("line (\\d+)")
                val lineMatch = errorMessage?.let { lineNumberRegex.find(it) }
                val lineNumber = lineMatch?.groupValues?.getOrNull(1)?.toIntOrNull()

                if (lineNumber != null) {
                    // Find the start and end positions of the specified line
                    val lines = code.split("\n")
                    if (lineNumber > 0 && lineNumber <= lines.size) {
                        var startPos = 0
                        for (i in 0 until lineNumber - 1) {
                            startPos += lines[i].length + 1 // +1 for the newline
                        }
                        val endPos = startPos + lines[lineNumber - 1].length

                        // Highlight the specific line with error
                        addStyle(
                            SpanStyle(textDecoration = TextDecoration.Underline, color = errorColor),
                            startPos,
                            endPos
                        )
                    }
                } else {
                    // Fallback: highlight first line if error exists but no line number found
                    val firstLineEnd = code.indexOf('\n').takeIf { it >= 0 } ?: code.length
                    addStyle(
                        SpanStyle(textDecoration = TextDecoration.Underline, color = errorColor),
                        0,
                        firstLineEnd
                    )
                }
            }
        }
    }

    // Sync textFieldValue with ViewModel code
    LaunchedEffect(code) {
        if (textFieldValue.text != code) {
            textFieldValue = TextFieldValue(
                text = code,
                selection = TextRange(min(code.length, textFieldValue.selection.end))
            )
        }
    }

    // Debounced code change
    LaunchedEffect(textFieldValue.text) {
        scope.launch {
            delay(300)
            if (textFieldValue.text != code) {
                onCodeChange(textFieldValue.text)
            }
        }
    }

    // Autocomplete logic
    LaunchedEffect(textFieldValue.selection.start) {
        val cursorPos = textFieldValue.selection.start
        val textBeforeCursor = if (cursorPos > 0 && cursorPos <= textFieldValue.text.length) {
            textFieldValue.text.substring(0, cursorPos)
        } else {
            ""
        }
        val lastWord = textBeforeCursor.takeLastWhile { it.isLetterOrDigit() }
        suggestions.clear()
        if (lastWord.isNotEmpty()) {
            suggestions.addAll(keywords.filter { it.startsWith(lastWord) }.take(5))
        }
    }

    // Get highlighted text based on current code and error state
    val highlightedText = remember(textFieldValue.text, errorMessage) {
        highlightSyntax(textFieldValue.text, errorMessage != null)
    }

    Box(modifier = modifier) {
        BasicTextField(
            value = textFieldValue,
            onValueChange = { newValue ->
                textFieldValue = newValue
            },
            modifier = Modifier
                .fillMaxSize()
                .verticalScroll(scrollState)
                .focusable(),
            textStyle = TextStyle(
                fontFamily = FontFamily.Monospace,
                fontSize = EditorFontSize,
                lineHeight = LineHeight,
                color = MaterialTheme.colorScheme.onSurface
            ),
            visualTransformation = { originalText ->
                TransformedText(
                    highlightedText,
                    OffsetMapping.Identity
                )
            },
            decorationBox = { innerTextField ->
                Column {
                    Box(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(top = 8.dp)
                    ) {
                        innerTextField()
                    }
                    if (suggestions.isNotEmpty()) {
                        SuggestionBox(suggestions) { selected ->
                            val cursorPos = textFieldValue.selection.start
                            val textBefore = textFieldValue.text.substring(0, cursorPos)
                            val lastWordStart = textBefore.takeLastWhile { it.isLetterOrDigit() }.length
                            val newText = textBefore.dropLast(lastWordStart) + selected + textFieldValue.text.substring(cursorPos)
                            textFieldValue = TextFieldValue(
                                text = newText,
                                selection = TextRange(textBefore.length - lastWordStart + selected.length)
                            )
                            suggestions.clear()
                        }
                    }
                }
            }
        )

        // Error tooltip
        if (errorMessage != null) {
            Box(
                modifier = Modifier
                    .align(Alignment.BottomStart)
                    .padding(8.dp)
                    .background(MaterialTheme.colorScheme.errorContainer, MaterialTheme.shapes.small)
                    .padding(8.dp)
            ) {
                Text(
                    text = errorMessage,
                    style = MaterialTheme.typography.bodySmall,
                    color = MaterialTheme.colorScheme.onErrorContainer
                )
            }
        }
    }
}

@Composable
private fun SuggestionBox(
    suggestions: List<String>,
    onSuggestionSelected: (String) -> Unit
) {
    Surface(
        modifier = Modifier
            .width(200.dp)
            .shadow(4.dp),
        color = MaterialTheme.colorScheme.surface
    ) {
        Column {
            suggestions.forEach { suggestion ->
                Text(
                    text = suggestion,
                    modifier = Modifier
                        .fillMaxWidth()
                        .clickable { onSuggestionSelected(suggestion) }
                        .padding(8.dp),
                    style = MaterialTheme.typography.bodyMedium
                )
            }
        }
    }
}