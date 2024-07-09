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

import com.company.domain.Dataroom;
import com.company.persistence.DataroomMapper;

@Service
public class DataroomServiceImpl implements DataroomService {

    private final DataroomMapper dataroomMapper;

    @Autowired
    public DataroomServiceImpl(DataroomMapper dataroomMapper) {
        this.dataroomMapper = dataroomMapper;
    }

    @Override
    public List<Dataroom> getDataroomList() {
        return dataroomMapper.getDataroomList();
    }

    @Override
    public Dataroom getDataroom(int dno) {
        return dataroomMapper.getDataroom(dno);
    }

    @Override
    public void insert(Dataroom dataroom, MultipartFile file) {
        String filename = saveFile(file);
        dataroom.setDatafile(filename);
        dataroomMapper.insert(dataroom);
    }

    @Override
    public void update(Dataroom dataroom, MultipartFile file) {
        if (!file.isEmpty()) {
            deleteFile(dataroom.getDatafile());
            String filename = saveFile(file);
            dataroom.setDatafile(filename);
        }
        dataroomMapper.update(dataroom);
    }

    @Override
    public void delete(int dno) {
        Dataroom dataroom = dataroomMapper.getDataroom(dno);
        deleteFile(dataroom.getDatafile());
        dataroomMapper.delete(dno);
    }

    private String saveFile(MultipartFile file) {
        try {
            String filename = file.getOriginalFilename();
            Path path = Paths.get("uploads/" + filename);
            Files.write(path, file.getBytes());
            return filename;
        } catch (IOException e) {
            throw new RuntimeException("Failed to store file", e);
        }
    }

    private void deleteFile(String filename) {
        try {
            Path path = Paths.get("uploads/" + filename);
            Files.deleteIfExists(path);
        } catch (IOException e) {
            throw new RuntimeException("Failed to delete file", e);
        }
    }
}