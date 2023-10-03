package ro.fortech.bigproject;


import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import ro.fortech.project.entities.ImageData;
import ro.fortech.project.entities.Product;
import ro.fortech.project.repository.ProductRepository;
import ro.fortech.project.services.ProductService;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.Assert.assertSame;
import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class ProductServiceTests {

    @InjectMocks
    private ProductService productService;

    @Mock
    private ProductRepository productRepository;

    @Test
    public void testAddProduct(){
        ImageData image = new ImageData();
        Product product = new Product("paste carbonara","paste",image, BigDecimal.valueOf(35.00),"ff bun ne place",true);
        when(productRepository.save(product)).thenReturn(product);
        Product savedProduct = productService.addProduct(product);
        assertSame(product,savedProduct);
        verify(productRepository,times(1)).save(product);
    }

    @Test
    public void testUpdateProduct(){
        ImageData image = new ImageData();
        Product product = new Product("paste carbonara","paste",image,BigDecimal.valueOf(35.00),"ff bun ne place",true);
        product.setId(1L);
        when(productRepository.findById(product.getId())).thenReturn(Optional.of(product));
        Product modifiedProduct = new Product("paste carbonara","paste",image,BigDecimal.valueOf(40.00),"ff bun ne place",true);
        modifiedProduct.setId(1L);
        when(productRepository.save(modifiedProduct)).thenReturn(modifiedProduct);
        Product updatedProduct = productService.updateProduct(modifiedProduct);
        assertSame(modifiedProduct, updatedProduct);

    }

    @Test
    public void testDeleteProduct(){
        Long productId = 25L;
        ProductService productService = new ProductService(productRepository);
        productService.deleteProduct(productId);
        verify(productRepository, times(1)).deleteById(productId);
    }

    @Test
    public void testFindAllProducts(){
        ImageData image1 = new ImageData();
        ImageData image2 = new ImageData();
        ImageData image3 = new ImageData();
        List<Product> expectedProducts = Arrays.asList(
                new Product("paste carbonara","paste",image1,BigDecimal.valueOf(35.00),"ff bun ne place",true),
                new Product("cola", "sucuri", image2, BigDecimal.valueOf(12.00), "cancer", true),
                new Product("pizza margherita", "pizza", image3, BigDecimal.valueOf(40.00),"italiano vero",true)
        );
        when(productRepository.findAll()).thenReturn(expectedProducts);
        ProductService productService = new ProductService(productRepository);
        List<Product> actualProducts = productService.findAllProducts();
        assertSame(expectedProducts,actualProducts);
        verify(productRepository, times(1)).findAll();
    }

    @Test
    public void testFindProductById(){
        Long productId = 1L;
        ImageData image = new ImageData();
        Product product = new Product(1L,"paste carbonara","paste",image,BigDecimal.valueOf(35.00),"ff bun ne place",true);
        when(productRepository.findById(productId)).thenReturn(Optional.of(product));
        Product foundProduct = productService.getProductById((long) productId);
        assertSame(product, foundProduct);
    }
}