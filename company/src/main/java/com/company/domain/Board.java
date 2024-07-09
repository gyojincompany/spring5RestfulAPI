package com.company.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Board {
    private int no;
    private String title;
    private String content;
    private String author;
    private String resdate;
    private int hits;
}
