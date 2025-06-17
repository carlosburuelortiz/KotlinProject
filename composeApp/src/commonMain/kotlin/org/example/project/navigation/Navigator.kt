package org.example.project.navigation

import androidx.compose.foundation.background
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import moe.tlaster.precompose.flow.collectAsStateWithLifecycle
import moe.tlaster.precompose.navigation.NavHost
import moe.tlaster.precompose.navigation.Navigator
import moe.tlaster.precompose.navigation.path
import moe.tlaster.precompose.viewmodel.viewModel
import org.example.project.data.ExpenseManager
import org.example.project.domain.ExpenseRepositoryImpl
import org.example.project.getColorsTheme
import org.example.project.presentation.ExpensesViewModel
import org.example.project.ui.ExpensesScreen

@Composable
fun Navigator(navigator: Navigator) {
    val colors = getColorsTheme()
    val viewModel = viewModel(modelClass = ExpensesViewModel::class) {
        ExpensesViewModel(ExpenseRepositoryImpl(ExpenseManager))
    }

    NavHost(
        modifier = Modifier.background(colors.backgroundColor),
        navigator = navigator,
        initialRoute = "/home"
    ) {
        scene(route = "/home") {
            val uiState by viewModel.uiState.collectAsStateWithLifecycle()
            ExpensesScreen(uiState) { expense ->
                navigator.navigate("/addExpenses/${expense.id}")
            }
        }

        scene(route = "/addExpenses/{id}") { backStackEntry ->
            val idFromPath = backStackEntry.path<Long>("id")
            val isAddExpense = idFromPath?.let { id -> viewModel.getExpenseWithId(id) }

            // Crear expensesDetailScreen
        }
    }
}