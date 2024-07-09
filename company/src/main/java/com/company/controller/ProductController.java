package com.company.controller;

import java.io.IOException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import com.company.domain.Product;
import com.company.service.ProductService;

@RestController
@RequestMapping("/products")
public class ProductController {

    private final ProductService productService;

    @Autowired
    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @GetMapping("/list")
    public ResponseEntity<List<Product>> getProductList() {
        List<Product> products = productService.getProductList();
        return new ResponseEntity<>(products, HttpStatus.OK);
    }

    @GetMapping("/detail")
    public ResponseEntity<Product> getProduct(@RequestParam("pno") int pno) {
        Product product = productService.getProduct(pno);
        if (product != null) {
            return new ResponseEntity<>(product, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping("/insert")
    public ResponseEntity<String> insertProduct(
            @RequestParam("cate") String cate,
            @RequestParam("pname") String pname,
            @RequestParam("pcontent") String pcontent,
            @RequestParam("img1") MultipartFile img1,
            @RequestParam("img2") MultipartFile img2,
            @RequestParam("img3") MultipartFile img3) throws IOException {

        Product product = new Product();
        product.setCate(cate);
        product.setPname(pname);
        product.setPcontent(pcontent);
        
        productService.insert(product, img1, img2, img3);
        return new ResponseEntity<>("Product inserted successfully", HttpStatus.CREATED);
    }

    @PutMapping("/update")
    public ResponseEntity<String> updateProduct(
            @RequestParam("pno") int pno,
            @RequestParam("cate") String cate,
            @RequestParam("pname") String pname,
            @RequestParam("pcontent") String pcontent,
            @RequestParam("img1") MultipartFile img1,
            @RequestParam("img2") MultipartFile img2,
            @RequestParam("img3") MultipartFile img3) throws IOException {

        Product product = productService.getProduct(pno);
        if (product == null) {
            return new ResponseEntity<>("Product not found", HttpStatus.NOT_FOUND);
        }

        product.setCate(cate);
        product.setPname(pname);
        product.setPcontent(pcontent);

        productService.update(product, img1, img2, img3);
        return new ResponseEntity<>("Product updated successfully", HttpStatus.OK);
    }

    @DeleteMapping("/delete")
    public ResponseEntity<String> deleteProduct(@RequestParam("pno") int pno) throws IOException {
        productService.delete(pno);
        return new ResponseEntity<>("Product deleted successfully", HttpStatus.OK);
    }
}