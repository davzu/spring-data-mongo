package davzuzu.springdatamongodb;

import davzuzu.springdatamongodb.model.Expense;
import davzuzu.springdatamongodb.repository.ExpenseRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
@ContextConfiguration(initializers = {MongoDBContainerInitializer.class})
public class MongoDBContainerInitializerTest {

    @Autowired
    private ExpenseRepository expenseRepository;

    @Test
    @DisplayName("Verificar que los gastos fueron cargados con mongock")
    void shouldReturnPrePopulatedExpenses() {
        List<Expense> expenses = expenseRepository.findAll();
        assertEquals(7, expenses.size());
    }
}
