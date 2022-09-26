import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class ProductManagerTest {
    ProductRepository repository = new ProductRepository();
    ProductManager manager = new ProductManager(repository);
    Product book = new Book(100, "Петровы в гриппе", 600, "Сальников А.");

    Product smartphone = new Smartphone(200, "Samsung", 25000, "Samsung");
    Product product = new Product(300, "Cake", 50);
    Product product2  = new Product(301, "Cake", 60);
    Product smartphone2  = new Smartphone(201,"Samsung A71",30000,"Samsung");

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

    @Test
    void searchWithFewProduct() {
        manager.add(book);
        manager.add(smartphone);
        manager.add(product);
        manager.add(product2);

        Product[] expected = {product, product2};
        Product[] actual = manager.searchBy("Cake");
        Assertions.assertArrayEquals(expected, actual);
    }
@Test
    void searchWithOneProduct () {

    manager.add(book);
    manager.add(smartphone);
    manager.add(smartphone2);
    manager.add(product);
    manager.add(product2);

    Product[] expected = {smartphone2};
    Product[] actual = manager.searchBy("Samsung A71");
    Assertions.assertArrayEquals(expected, actual);
}

    @Test
    void searchWithAllProductNoFound () {

        manager.add(book);
        manager.add(smartphone);
        manager.add(smartphone2);
        manager.add(product);
        manager.add(product2);

        Product[] expected = {};
        Product[] actual = manager.searchBy("Куприн");
        Assertions.assertArrayEquals(expected, actual);
    }

}