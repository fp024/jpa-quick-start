package org.fp024.jpaquick.shopping.biz.service;

import org.fp024.jpaquick.shopping.biz.domain.Product;
import org.fp024.jpaquick.shopping.biz.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductService {
    private final ProductRepository productRepository;

    @Autowired
    public ProductService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    // 상품 등록 혹은 수정
    public void insertOrUpdateProduct(Product product) {
        productRepository.insertOrUpdateProduct(product);
    }

    // 상품 상세 조회
    public Product getProduct(Long productId) {
        return productRepository.getProduct(productId);
    }

    // 상품 목록 검색
    public List<Product> getProductList() {
        return productRepository.getProductList();
    }
}
