package pt.ulusofona.cd.primeiroprojeto.controller;

import jakarta.validation.Valid;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;
import pt.ulusofona.cd.primeiroprojeto.mapper.ProductMapper;
import pt.ulusofona.cd.primeiroprojeto.model.Product;
import pt.ulusofona.cd.primeiroprojeto.model.dto.ProductRequest;
import pt.ulusofona.cd.primeiroprojeto.model.dto.ProductResponse;
import pt.ulusofona.cd.primeiroprojeto.service.ProductService;

import java.util.*;

@RestController
@RequestMapping("/api/v1/products")
public class ProductController {

    private final ProductService service;

    public ProductController(ProductService service) {
        this.service = service;
    }

    @PostMapping
    public ResponseEntity<ProductResponse> create(
            @Valid @RequestBody ProductRequest request
    ) {
        Product created = service.createProduct(request);
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(ProductMapper.toResponse(created));
    }

    @GetMapping("/{id}")
    public ResponseEntity<ProductResponse> getById(@PathVariable UUID id) {
        Product product = service.getProductById(id);
        return ResponseEntity.ok(ProductMapper.toResponse(product));
    }

    @GetMapping
    public ResponseEntity<List<ProductResponse>> getAll() {
        List<Product> products = service.getAllProducts();
        List<ProductResponse> responseList = products.stream()
                .map(ProductMapper::toResponse)
                .toList();

        return ResponseEntity.ok(responseList);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ProductResponse> update(
            @PathVariable UUID id,
            @Valid @RequestBody ProductRequest request
    ) {
        Product updated = service.updateProduct(id, request);
        return ResponseEntity.ok(ProductMapper.toResponse(updated));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable UUID id) {
        service.deleteProduct(id);
        return ResponseEntity.noContent().build();
    }
}