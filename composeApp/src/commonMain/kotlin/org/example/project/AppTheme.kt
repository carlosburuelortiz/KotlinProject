package org.example.project

import androidx.compose.foundation.shape.AbsoluteCutCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import org.example.project.utils.extension.select

@Composable
fun AppTheme(
    content: @Composable () -> Unit
) {
    MaterialTheme(
        colorScheme = MaterialTheme.colorScheme.copy(
            primary = Color.Black,
        ),
        shapes = MaterialTheme.shapes.copy(
            small = AbsoluteCutCornerShape(0.dp),
            medium = AbsoluteCutCornerShape(0.dp),
            large = AbsoluteCutCornerShape(0.dp),
        )
    ) {
        content()
    }
}

@Composable
fun getColorsTheme(): DarkModeColors {
    val isDarkMode = false

    val Purple = Color(0xFF6A66FF)
    val ColorExpenseItem = isDarkMode.select(Color(0xFF090808), Color(0xFFF1F1F1))
    val BackgroundColor = isDarkMode.select(Color(0x1E1C1C), Color.White)
    val TextColor = isDarkMode.select(Color.White, Color.Black)
    val AdditionalColor = isDarkMode.select(Purple, Color.Black)
    val ColorArrowRound = isDarkMode.select(Purple, Color.Gray.copy(alpha = .2f))

    return DarkModeColors(
        purple = Purple,
        colorExpenseItem = ColorExpenseItem,
        backgroundColor = BackgroundColor,
        textColor = TextColor,
        additionalColor = AdditionalColor,
        colorArrowRound = ColorArrowRound,
    )
}

data class DarkModeColors(
    val purple: Color,
    val colorExpenseItem: Color,
    val backgroundColor: Color,
    val textColor: Color,
    val additionalColor: Color,
    val colorArrowRound: Color,
)