// ProductServiceImpl.java
package com.backendproject.catalogue.service.impl;

import com.backendproject.catalogue.dto.*;
import com.backendproject.catalogue.entity.Product;
import com.backendproject.catalogue.mapper.ProductMapper;
import com.backendproject.catalogue.repository.ProductRepo;
import com.backendproject.catalogue.service.ProductService;
import com.backendproject.catalogue.dto.PageResponse;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class ProductServiceImpl implements ProductService {

    private final ProductRepo repo;

    public ProductServiceImpl(ProductRepo repo) { this.repo = repo; }

    @Override
    public ProductResponse create(ProductRequest request) {
        Product saved = repo.save(ProductMapper.toEntity(request));
        return ProductMapper.toResponse(saved);
    }

    @Override
    public ProductResponse getById(Long id) {
        return repo.findById(id).map(ProductMapper::toResponse)
                .orElseThrow(() -> new IllegalArgumentException("Product not found"));
    }

    @Override
    public PageResponse<ProductCard> search(String q, Pageable pageable) {
        Page<Product> page = (q == null || q.isBlank())
                ? repo.findAll(pageable)
                : repo.findByTitleContainingIgnoreCaseOrDescriptionContainingIgnoreCase(q, q, pageable);
        return new PageResponse<>(
                ProductMapper.toCards(page.getContent()),
                page.getNumber(),
                page.getSize(),
                page.getTotalElements(),
                page.getTotalPages(),
                page.isFirst(),
                page.isLast()
        );
    }
}
