package com.company.service;

import java.util.List;

import com.company.domain.Dataroom;
import org.springframework.web.multipart.MultipartFile;

public interface DataroomService {
    List<Dataroom> getDataroomList();
    Dataroom getDataroom(int dno);
    void insert(Dataroom dataroom, MultipartFile file);
    void update(Dataroom dataroom, MultipartFile file);
    void delete(int dno);
}