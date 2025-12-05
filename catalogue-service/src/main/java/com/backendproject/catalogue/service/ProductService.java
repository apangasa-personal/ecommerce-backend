// ProductService.java
package com.backendproject.catalogue.service;

import com.backendproject.catalogue.dto.*;
import org.springframework.data.domain.Pageable;

public interface ProductService {
    ProductResponse create(ProductRequest request);
    ProductResponse getById(Long id);
    PageResponse<ProductCard> search(String q, Pageable pageable);
}
