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

import static davzuzu.springdatamongodb.model.ExpenseCategory.*;

@ChangeUnit(id="database-initializer", order="001", author="Mongock")
public class DatabaseInitializerChangeUnit {

    @Execution
    public void initDatabase(ExpenseRepository expenseRepository) {
        System.out.println("============ database-initializer ============");
        System.out.println(LocalDateTime.now().toString());

        List<Expense> expenseList = new ArrayList<>();
        expenseList.add(createNewExpense("Movie Tickets", ENTERTAINMENT, BigDecimal.valueOf(40)));
        expenseList.add(createNewExpense("Dinner", RESTAURANT, BigDecimal.valueOf(60)));
        expenseList.add(createNewExpense("Netflix", ENTERTAINMENT, BigDecimal.valueOf(10)));
        expenseList.add(createNewExpense("Gym", MISCELLANEOUS, BigDecimal.valueOf(20)));
        expenseList.add(createNewExpense("Internet", UTILITIES, BigDecimal.valueOf(30)));

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
