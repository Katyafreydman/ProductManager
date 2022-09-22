import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class ProductManagerTest {
    ProductRepository repository = new ProductRepository();
    ProductManager manager = new ProductManager(repository);
    Product book = new Book(100, "Петровы в гриппе", 600, "Сальников А.");
    Product smartphone = new Smartphone(200, "Samsung", 25000, "Samsung");
    Product product = new Product(300, "Cake", 50);

    @Test
    void shouldAdd() {
        manager.add(book);
        Product[] expected = {book};
        Product[] actual = repository.findAll();
        Assertions.assertArrayEquals(expected, actual);
    }

    @Test
    void shouldAddAll() {
        manager.add(book);
        manager.add(smartphone);
        manager.add(product);

        Product[] expected = {book, smartphone, product};
        Product[] actual = repository.findAll();
        Assertions.assertArrayEquals(expected, actual);
    }

    @Test
    void shouldSearch() {
        manager.add(book);
        manager.add(smartphone);
        manager.add(product);
        String name = "Петровы в гриппе";

        Product[] expected = {book};
        Product[] actual = manager.searchBy(name);
        Assertions.assertArrayEquals(expected, actual);
    }

    @Test
    void searchNoExistProduct() {
        manager.add(book);
        manager.add(smartphone);
        manager.add(product);
        String name = "Xiomi";

        Product[] expected = {};
        Product[] actual = manager.searchBy(name);
        Assertions.assertArrayEquals(expected, actual);
    }

    @Test
    void searchExistProduct() {
        manager.add(book);
        manager.add(smartphone);
        manager.add(product);
        String name = "Samsung";
        Product[] expected = {smartphone};
        Product[] actual = manager.searchBy(name);
        Assertions.assertArrayEquals(expected, actual);
    }
}