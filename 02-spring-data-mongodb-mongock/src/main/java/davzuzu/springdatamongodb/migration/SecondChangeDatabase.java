package davzuzu.springdatamongodb.migration;

import davzuzu.springdatamongodb.model.Expense;
import davzuzu.springdatamongodb.model.ExpenseCategory;
import davzuzu.springdatamongodb.repository.ExpenseRepository;
import io.mongock.api.annotations.ChangeUnit;
import io.mongock.api.annotations.Execution;
import io.mongock.api.annotations.RollbackExecution;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import static davzuzu.springdatamongodb.model.ExpenseCategory.ENTERTAINMENT;
import static davzuzu.springdatamongodb.model.ExpenseCategory.GROCERIES;

@ChangeUnit(id="second-change", order="002", author="davzuzu")
public class SecondChangeDatabase {

    @Execution
    public void addData(ExpenseRepository expenseRepository) {
        System.out.println("============ second-change ============");
        System.out.println(LocalDateTime.now().toString());

        List<Expense> expenseList = new ArrayList<>();
        expenseList.add(createNewExpense("Cinema", ENTERTAINMENT, BigDecimal.valueOf(45)));
        expenseList.add(createNewExpense("Cookies", GROCERIES, BigDecimal.valueOf(10)));

        expenseRepository.insert(expenseList);
    }

    private Expense createNewExpense(String expenseName, ExpenseCategory expenseCategory, BigDecimal amount) {
        Expense expense = new Expense();
        expense.setExpenseName(expenseName);
        expense.setExpenseAmount(amount);
        expense.setExpenseCategory(expenseCategory);
        return expense;
    }

    @RollbackExecution
    public void rollback() {
    }
}
