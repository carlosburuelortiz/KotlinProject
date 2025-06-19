package org.example.project.ui

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Divider
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.SheetValue
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.platform.SoftwareKeyboardController
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import kotlinx.coroutines.launch
import org.example.project.getColorsTheme
import org.example.project.model.Expense
import org.example.project.model.ExpenseCategory
import org.example.project.utils.extension.orDefault
import org.example.project.utils.extension.orZero

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ExpensesDetailScreen(
    expenseToEdit: Expense? = null,
    categoryList: List<ExpenseCategory> = emptyList(),
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

    ModalBottomSheet(
        onDismissRequest = {
            scope.launch { sheetState.hide() }
        },
        sheetState = sheetState,
    ) {
        CategoryBottomSheetContent(categoryList) {
            expenseCategory = it.name
            categorySelected = it.name
        }
    }

    Column(
        modifier = Modifier.fillMaxSize().padding(vertical = 16.dp, horizontal = 16.dp).background(Color.Red)
    ) {
        ExpanseAmount(
            priceContent = price,
            onPriceChange = {
                price = it
            },
            keyboardController = keyboardController,
        )
        Spacer(modifier = Modifier.height(30.dp))
    }
}

@Composable
private fun ExpanseAmount(
    priceContent: Double,
    onPriceChange: (Double) -> Unit,
    keyboardController: SoftwareKeyboardController?,
) {
    val colors = getColorsTheme()
    var text by remember { mutableStateOf("$priceContent") }

    Column(
        modifier = Modifier.fillMaxWidth(),
        horizontalAlignment = Alignment.Start,
        verticalArrangement = Arrangement.Center,
    ) {
        Text(
            text = "Amount",
            fontSize = 20.sp,
            color = Color.Gray,
            fontWeight = FontWeight.SemiBold,
        )
        Row(
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = text,
                fontSize = 25.sp,
                fontWeight = FontWeight.ExtraBold,
                color = colors.textColor
            )
            TextField(
                modifier = Modifier.weight(1f),
                value = text,
                onValueChange = { newText ->
                    val numericText = newText.filter { it.isDigit() || it == '.' }
                    text = if (numericText.isNotEmpty() && numericText.count { it == '.' } <= 1) {
                        try {
                            val newValue = numericText.toDouble()
                            onPriceChange(newValue)
                            numericText
                        } catch (e: NumberFormatException) {
                            ""
                        }
                    } else {
                        onPriceChange(0.0)
                        ""
                    }
                },
                keyboardOptions = KeyboardOptions.Default.copy(
                    keyboardType = KeyboardType.Decimal,
                    imeAction = ImeAction.Done,
                ),
                keyboardActions = KeyboardActions(
                    onDone = {
                        keyboardController?.hide()
                    }
                ),
                singleLine = true,
                colors = TextFieldDefaults.colors(
                    focusedTextColor = colors.textColor,
                    unfocusedTextColor = Color.Transparent,
                    focusedContainerColor = colors.backgroundColor,
                    unfocusedContainerColor = Color.Transparent,
                ),
                textStyle = TextStyle(
                    fontSize = 35.sp,
                    fontWeight = FontWeight.ExtraBold,
                )
            )

            Text(
                text = "USD",
                fontSize = 20.sp,
                fontWeight = FontWeight.ExtraBold,
                color = Color.Gray,
            )
        }
        Divider(color = Color.Black, thickness = 2.dp)
    }
}

@Composable
private fun CategoryBottomSheetContent(
    categories: List<ExpenseCategory>,
    onCategorySelected: (ExpenseCategory) -> Unit,
) {
    LazyVerticalGrid(
        modifier = Modifier.padding(16.dp),
        columns = GridCells.Fixed(3),
        verticalArrangement = Arrangement.Center,
        horizontalArrangement = Arrangement.Center,
    ) {
        items(categories) { category ->
            CategoryItem(category, onCategorySelected)
        }
    }
}

@Composable
private fun CategoryItem(
    category: ExpenseCategory,
    onCategorySelected: (ExpenseCategory) -> Unit,
) {
    Column(
        modifier = Modifier.fillMaxWidth().padding(8.dp).clickable {
            onCategorySelected(category)
        },
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        Image(
            modifier = Modifier.size(40.dp).clip(CircleShape),
            imageVector = category.icon,
            contentDescription = "Category icon",
            contentScale = ContentScale.Crop,
        )
        Text(text = category.name)
    }
}