package com.company.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Product {
    private int pno;
    private String cate;
    private String pname;
    private String pcontent;
    private String img1;
    private String img2;
    private String img3;
    private String resdate; 
    private int hits;
}
