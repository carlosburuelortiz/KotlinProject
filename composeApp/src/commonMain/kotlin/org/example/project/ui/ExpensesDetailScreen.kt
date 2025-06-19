package org.example.project.ui

import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.SheetValue
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import org.example.project.getColorsTheme
import org.example.project.model.Expense
import org.example.project.utils.extension.orDefault
import org.example.project.utils.extension.orZero

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ExpensesDetailScreen(
    expenseToEdit: Expense? = null,
    addExpenseAndNavigateBack: (Expense) -> Unit,
) {
    val colors = getColorsTheme()
    val defaultCategory = "Select a category"

    var price by remember { mutableStateOf(expenseToEdit?.amount.orZero()) }
    var description by remember { mutableStateOf(expenseToEdit?.description.orEmpty()) }
    var expenseCategory by remember { mutableStateOf(expenseToEdit?.category?.name.orEmpty()) }
    var categorySelected by remember {
        mutableStateOf(
            expenseToEdit?.category?.name.orDefault(defaultCategory)
        )
    }
    val sheetState = rememberModalBottomSheetState()
    val keyboardController = LocalSoftwareKeyboardController.current
    val scope = rememberCoroutineScope()

    LaunchedEffect(sheetState.targetValue) {
        if (sheetState.targetValue == SheetValue.Expanded) {
            keyboardController?.hide()
        }
    }
}