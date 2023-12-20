package davzuzu.springdatamongodb;

import davzuzu.springdatamongodb.model.Expense;
import davzuzu.springdatamongodb.model.ExpenseCategory;
import davzuzu.springdatamongodb.repository.ExpenseRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.DynamicPropertyRegistry;
import org.springframework.test.context.DynamicPropertySource;
import org.testcontainers.containers.MongoDBContainer;

import java.math.BigDecimal;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
class SpringDataMongoDbApplicationTests {

	@Autowired
	private ExpenseRepository expenseRepository;

	static MongoDBContainer mongoDBContainer = new MongoDBContainer("mongo:4.4.2");

	{
		mongoDBContainer.start();
	}

	@DynamicPropertySource
	static void setProperties(DynamicPropertyRegistry registry) {
		registry.add("spring.data.mongodb.uri", mongoDBContainer::getReplicaSetUrl);
	}

	@Test
	@DisplayName("Verificar que los gastos fueron cargados con mongock")
	void shouldReturnPrePopulatedExpenses() {
		List<Expense> expenses = expenseRepository.findAll();
		assertEquals(7, expenses.size());
	}

	@Test
	@DisplayName("Ingreso y consulta de un registro")
	void shouldFindExpenseByName() {
		Expense expectedExpense = new Expense();
		expectedExpense.setExpenseName("Evening Drinks");
		expectedExpense.setExpenseCategory(ExpenseCategory.MISCELLANEOUS);
		expectedExpense.setExpenseAmount(BigDecimal.TEN);
		expenseRepository.save(expectedExpense);

		Expense actualExpense = expenseRepository.findByName("Evening Drinks").orElseThrow();
		assertEquals(expectedExpense.getExpenseName(), actualExpense.getExpenseName());
		assertEquals(expectedExpense.getExpenseCategory(), actualExpense.getExpenseCategory());
		assertEquals(expectedExpense.getExpenseAmount(), actualExpense.getExpenseAmount());
	}

}
