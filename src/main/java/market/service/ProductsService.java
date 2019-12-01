package market.service;

import com.geekbrains.bootdata.entities.Product;
import com.geekbrains.bootdata.repositories.ProductRepository;
import com.geekbrains.bootdata.repositories.ProductSpecifications;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProductsService {
    private ProductRepository productRepository;
    private static ProductSpecifications productSpecifications;

    @Autowired
    public void setProductRepository(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    public void setProductSpecification(ProductSpecifications productSpecifications){
        this.productSpecifications = productSpecifications;
    }

    public Page<Product> findByParam(Specification<Product> spec, int page, int size, String property) {
        return productRepository.findAll(spec, PageRequest.of(page, size, Sort.Direction.ASC, property));
    }

    public List<Product> findAll() {
        return productRepository.findAll();
    }

    public void insert(Product product) {
        productRepository.save(product);
    }

    public Optional<Product> findById(Long id) {
        return productRepository.findById(id);
    }

    public boolean existById(Long id){
        return productRepository.existsById(id);
    }

    public static Specification<Product> titleContains(String word) {
        return productSpecifications.titleContains("%" + word + "%");
    }

    public static Specification<Product> priceGreaterThanOrEq(int value) {
        return productSpecifications.priceGreaterThanOrEq(value);
    }

    public static Specification<Product> priceLesserThanOrEq(int value) {
        return productSpecifications.priceLesserThanOrEq(value);
    }
}
