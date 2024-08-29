package com.telezon.service;

import com.telezon.dao.DataDao;
import com.telezon.model.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class DataService {

    @Autowired
    private DataDao dataDao;

    public List<Data> getAllData() {
        return dataDao.findAll();
    }

    public Optional<Data> getDataById(int id) {
        return dataDao.findById(id);
    }

    public void saveData(Data data) {
        dataDao.save(data);
    }

    public void updateData(Data data) {
        dataDao.save(data); // save can handle both insert and update
    }

    public void deleteData(int id) {
        dataDao.deleteById(id);
    }
}
