package com.company.service;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.company.domain.Product;
import com.company.persistence.ProductMapper;

@Service
public class ProductServiceImpl implements ProductService {

    private final ProductMapper productMapper;

    @Autowired
    public ProductServiceImpl(ProductMapper productMapper) {
        this.productMapper = productMapper;
    }

    @Override
    public List<Product> getProductList() {
        return productMapper.getProductList();
    }

    @Override
    public Product getProduct(int pno) {
        return productMapper.getProduct(pno);
    }

    @Override
    public void insert(Product product, MultipartFile img1, MultipartFile img2, MultipartFile img3) throws IOException {
        String img1Path = saveFile(img1);
        String img2Path = saveFile(img2);
        String img3Path = saveFile(img3);

        product.setImg1(img1Path);
        product.setImg2(img2Path);
        product.setImg3(img3Path);

        productMapper.insert(product);
    }

    @Override
    public void update(Product product, MultipartFile img1, MultipartFile img2, MultipartFile img3) throws IOException {
        if (!img1.isEmpty()) {
            deleteFile(product.getImg1());
            product.setImg1(saveFile(img1));
        }
        if (!img2.isEmpty()) {
            deleteFile(product.getImg2());
            product.setImg2(saveFile(img2));
        }
        if (!img3.isEmpty()) {
            deleteFile(product.getImg3());
            product.setImg3(saveFile(img3));
        }
        productMapper.update(product);
    }

    @Override
    public void delete(int pno) throws IOException {
        Product product = productMapper.getProduct(pno);
        deleteFile(product.getImg1());
        deleteFile(product.getImg2());
        deleteFile(product.getImg3());
        productMapper.delete(pno);
    }

    private String saveFile(MultipartFile file) throws IOException {
        if (file.isEmpty()) {
            return null;
        }
        String filename = System.currentTimeMillis() + "_" + file.getOriginalFilename();
        Path path = Paths.get("uploads/" + filename);
        Files.write(path, file.getBytes());
        return filename;
    }

    private void deleteFile(String filename) throws IOException {
        if (filename == null) {
            return;
        }
        Path path = Paths.get("uploads/" + filename);
        Files.deleteIfExists(path);
    }
}