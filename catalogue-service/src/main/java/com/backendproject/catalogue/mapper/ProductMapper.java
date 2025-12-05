// ProductMapper.java
package com.backendproject.catalogue.mapper;

import com.backendproject.catalogue.dto.*;
import com.backendproject.catalogue.entity.Product;
import java.util.List;

public final class ProductMapper {
    private ProductMapper() {}

    public static Product toEntity(ProductRequest r) {
        var p = new Product();
        p.setTitle(r.title());
        p.setDescription(r.description());
        p.setImageUrl(r.imageUrl());
        p.setSku(r.sku());
        p.setBarcode(r.barcode());
        p.setUnit(r.unit());
        p.setMinOrderQuantity(r.minOrderQuantity());
        p.setMaxOrderQuantity(r.maxOrderQuantity());
        p.setMrp(r.mrp());
        p.setDiscountPrice(r.discountPrice());
        p.setSellerName(r.sellerName());
        p.setSellerDescription(r.sellerDescription());
        return p;
    }

    public static ProductResponse toResponse(Product p) {
        return new ProductResponse(
                p.getId(),
                p.getTitle(),
                p.getDescription(),
                p.getImageUrl(),
                p.getSku(),
                p.getBarcode(),
                p.getUnit(),
                p.getMinOrderQuantity(),
                p.getMaxOrderQuantity(),
                p.getMrp(),
                p.getDiscountPrice(),
                p.getSellerName(),
                p.getSellerDescription(),
                p.getCreatedAt(),
                p.getUpdatedAt()
        );
    }

    public static ProductCard toCard(Product p) {
        return new ProductCard(
                p.getId(),
                p.getTitle(),
                p.getImageUrl(),
                p.getMrp(),
                p.getDiscountPrice()
        );
    }

    public static List<ProductCard> toCards(List<Product> products) {
        return products.stream().map(ProductMapper::toCard).toList();
    }
}
