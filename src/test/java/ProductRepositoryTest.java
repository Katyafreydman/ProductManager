import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class ProductRepositoryTest {
    private ProductRepository repository = new ProductRepository();
    Product book = new Book(100, "Петровы в гриппе", 600, "Сальников А.");
    Product smartphone = new Smartphone(200, "Samsung A71", 25000, "Samsung");
    Product product = new Product(300, "Cake", 50);

    @Test
    public void saveItem() {
        repository.save(book);

        Product[] expected = {book};
        Product[] actual = repository.findAll();
        Assertions.assertArrayEquals(expected, actual);
    }

    @Test
    public void removeById() {
        repository.save(book);
        repository.save(smartphone);
        repository.save(product);
        repository.removeById(100);

        Product[] expected = {smartphone, product};
        Product[] actual = repository.findAll();
        Assertions.assertArrayEquals(expected, actual);
    }

    @Test
    public void removeAllById() {
        repository.save(book);
        repository.save(smartphone);
        repository.save(product);
        repository.removeById(100);
        repository.removeById(200);
        repository.removeById(300);

        Product[] expected = {};
        Product[] actual = repository.findAll();
        Assertions.assertArrayEquals(expected, actual);
    }

    @Test
    public void removeIncorrrectById() {
        repository.save(book);
        repository.save(smartphone);
        repository.save(product);

        Assertions.assertThrows(NotFoundException.class,
                () -> repository.removeById(4)
        );

    }
}


