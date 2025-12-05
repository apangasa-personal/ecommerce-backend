// ProductController.java
package com.backendproject.catalogue.controller;

import com.backendproject.catalogue.dto.*;
import com.backendproject.catalogue.service.ProductService;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/api/v1/products")
public class ProductController {
    private final ProductService service;
    public ProductController(ProductService service) { this.service = service; }

    @PostMapping
    public ProductResponse create(@RequestBody ProductRequest request) {
        return service.create(request);
    }

    @GetMapping("/{id}")
    public ProductResponse get(@PathVariable Long id) {
        return service.getById(id);
    }

    @GetMapping
    public PageResponse<ProductCard> list(
            @RequestParam(required = false) String q,
            Pageable pageable
    ) {
        return service.search(q, pageable);
    }
}
