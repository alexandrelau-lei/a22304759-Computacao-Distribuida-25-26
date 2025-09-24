package pt.ulusofona.cd.primeiroprojeto.service;


import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import pt.ulusofona.cd.primeiroprojeto.exception.ProductNotFoundException;
import pt.ulusofona.cd.primeiroprojeto.mapper.ProductMapper;
import pt.ulusofona.cd.primeiroprojeto.model.Product;
import pt.ulusofona.cd.primeiroprojeto.model.dto.ProductRequest;
import pt.ulusofona.cd.primeiroprojeto.repository.ProductRepository;

import java.time.Instant;
import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class ProductService {

    private final ProductRepository repo;

    public Product createProduct(ProductRequest request) {
        if (repo.existsBySkuIgnoreCase(request.getSku())) {
            throw new IllegalArgumentException("SKU must be unique!!!");
        }

        Product product = ProductMapper.toEntity(request);

        return repo.save(product);
    }

    public Product getProductById(UUID id) {
        return repo.findById(id)
                .orElseThrow(() -> new ProductNotFoundException(id));
    }

    public List<Product> getAllProducts() {
        return repo.findAll();
    }

    public Product updateProduct(UUID id, ProductRequest request) {
        Product existing = getProductById(id);

        existing.setName(request.getName().trim());
        existing.setDescription(request.getDescription());

        if (!existing.getSku().equalsIgnoreCase(request.getSku())
                && repo.existsBySkuIgnoreCase(request.getSku())) {
            throw new IllegalArgumentException("SKU must be unique");
        }

        existing.setSku(request.getSku().trim());
        existing.setPrice(request.getPrice());
        existing.setStock(request.getStock());
        existing.setCurrency(request.getCurrency() != null ? request.getCurrency() : "EUR");

        return repo.save(existing);
    }

    public void deleteProduct(UUID id) {
        if (!repo.findById(id).isPresent()) {
            throw new ProductNotFoundException(id);
        }
        repo.deleteById(id);
    }
}