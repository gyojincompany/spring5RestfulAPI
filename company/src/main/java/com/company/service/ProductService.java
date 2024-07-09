package com.company.service;

import java.io.IOException;
import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import com.company.domain.Product;

public interface ProductService {
    List<Product> getProductList();
    Product getProduct(int pno);
    void insert(Product product, MultipartFile img1, MultipartFile img2, MultipartFile img3) throws IOException;
    void update(Product product, MultipartFile img1, MultipartFile img2, MultipartFile img3) throws IOException;
    void delete(int pno) throws IOException;
}