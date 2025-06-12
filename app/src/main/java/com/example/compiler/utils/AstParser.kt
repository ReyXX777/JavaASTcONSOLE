// AstParser.kt
package com.example.compiler.utils

data class ASTNode(
    val label: String,
    val children: List<ASTNode> = emptyList(),
    val isNamed: Boolean = false // To handle nodes with "name: type" format
)

fun parseAST(input: String): ASTNode {
    val tokens = Regex("""\(|\)|[^\s()]+|:\s*[^\s()]+""").findAll(input).map { it.value.trim() }.toList()
    var index = 0

    fun parse(): ASTNode {
        if (tokens[index] != "(") {
            val token = tokens[index++]
            // Handle named nodes like "name: identifier"
            return if (token.contains(":")) {
                val parts = token.split(":")
                ASTNode("${parts[0].trim()}: ${parts[1].trim()}", emptyList(), true)
            } else {
                ASTNode(token)
            }
        }

        index++ // skip '('
        val label = tokens[index++]
        val children = mutableListOf<ASTNode>()

        while (index < tokens.size && tokens[index] != ")") {
            children.add(parse())
        }

        index++ // skip ')'
        return ASTNode(label, children)
    }

    return parse()
}

fun printAST(node: ASTNode, prefix: String = "", isLast: Boolean = true) {
    val currentPrefix = if (isLast) "└── " else "├── "
    println(prefix + currentPrefix + node.label)

    val newPrefix = prefix + if (isLast) "    " else "│   "
    node.children.forEachIndexed { index, child ->
        printAST(child, newPrefix, index == node.children.size - 1)
    }
}

