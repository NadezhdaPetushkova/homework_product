import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class ProductTest {

    ShopRepository repo = new ShopRepository();

    Product product1 = new Product(1, "Product 1", 1_000);
    Product product2 = new Product(2, "Product 2", 800);
    Product product3 = new Product(3, "Product 3", 1500);
    Product product4 = new Product(4, "Product 4", 1700);
    Product product5 = new Product(5, "Product 5", 600);
    Product product6 = new Product(6, "Product 6", 3_000);

    @BeforeEach
    public void setup() {
        repo.save(product1);
        repo.save(product2);
        repo.save(product3);
        repo.save(product4);
        repo.save(product5);
        repo.save(product6);
    }

    @Test
    public void shouldAddNewProduct() {
        Product newProduct = new Product(7, "New Product", 5000);
        repo.add(newProduct);

        Product[] expected = {product1, product2, product3, product4, product5, product6, newProduct};
        Product[] actual = repo.findAll();

        Assertions.assertArrayEquals(expected, actual);
    }

    @Test
    public void shouldExceptionRemoveById() {

        Assertions.assertThrows(NotFoundException.class, () -> {
            repo.removeById(15);
        });
    }

    @Test
    public void shouldSaveProduct() {
        Product[] expected = {product1, product2, product3, product4, product5, product6};
        Product[] actual = repo.findAll();

        Assertions.assertArrayEquals(expected, actual);
    }

    @Test
    public void shouldExceptionSaveProduct() {

        Assertions.assertThrows(AlreadyExistsException.class, () -> {
            repo.save(product5);
        });
    }
}