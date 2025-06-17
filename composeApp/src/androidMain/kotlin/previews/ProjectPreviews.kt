package previews

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import org.example.project.data.ExpenseManager
import org.example.project.presentation.ExpensesUiState
import org.example.project.ui.AllExpensesHeader
import org.example.project.ui.ExpensesItem
import org.example.project.ui.ExpensesScreen
import org.example.project.ui.ExpensesTotalHeader

@Preview(showBackground = true)
@Composable
fun ExpensesTotalHeaderPreview(modifier: Modifier = Modifier) {
    ExpensesTotalHeader(total = 170693.0)
}

@Preview(showBackground = true)
@Composable
fun AllExpensesPreview() {
    AllExpensesHeader()
}

@Preview(showBackground = true)
@Composable
fun ExpensesItemPreview() {

    Box(modifier = Modifier.padding(8.dp)) {
        ExpensesItem(
            expense = ExpenseManager.fakeExpenseList[0],
            onExpenseClick = {}
        )
    }
}

@Preview(showBackground = true)
@Composable
private fun ExpensesScreenPreview() {
    ExpensesScreen(
        uiState = ExpensesUiState(
            expenses = ExpenseManager.fakeExpenseList,
            total = 1052.2
        ),
        onExpenseClick = {}
    )
}