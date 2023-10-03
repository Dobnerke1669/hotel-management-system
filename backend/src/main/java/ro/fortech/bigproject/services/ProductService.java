package ro.fortech.bigproject.services;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;
import ro.fortech.bigproject.entities.Product;
import ro.fortech.bigproject.repository.ProductRepository;

import java.util.List;

@Service
public class ProductService {

    @Autowired
    private ProductRepository productRepository;

    public ProductService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    public Product addProduct(Product product) {
        productRepository.save(product);
        return product;
    }

    public Product updateProduct(Product product) {
        Long id = product.getId();
        productRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Product with id" + product.getId() + " not found"));
        productRepository.save(product);
        return product;
    }

    public void deleteProduct(Long id) {
        productRepository.deleteById(id);
    }

    public List<Product> findAllProducts() {
        return productRepository.findAll();
    }

    public Product getProductById(Long id) {
        return productRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Error: Product is not found."));
    }

    public void markUnavailable(Long productId) {
        Product unavailableProduct = productRepository.findById(productId)
                .orElseThrow(() -> new RuntimeException("Error: Product is not found."));
        unavailableProduct.setAvailable(false);
        productRepository.save(unavailableProduct);

    }
}
