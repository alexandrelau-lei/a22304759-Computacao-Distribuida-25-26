package pt.ulusofona.cd.primeiroprojeto.repository;

import org.springframework.stereotype.Repository;
import pt.ulusofona.cd.primeiroprojeto.model.Product;

import java.util.Optional;

@Repository
public class ProductRepository extends InMemoryRepository<Product> {

    public Optional<Product> findBySkuIgnoreCase(String sku) {
        return findAll().stream()
                .filter(p -> p.getSku().equalsIgnoreCase(sku))
                .findFirst();
    }

    public boolean existsBySkuIgnoreCase(String sku) {
        return findBySkuIgnoreCase(sku).isPresent();
    }
}