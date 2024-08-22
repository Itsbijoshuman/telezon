package com.telezon.service;

import com.telezon.dao.DataDao;
import com.telezon.model.DataUsage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class DataService {

    @Autowired
    private DataDao dataDao;

    public List<DataUsage> getAllDataUsages() {
        return dataDao.findAll();
    }

    public Optional<DataUsage> getDataUsageById(int id) {
        return dataDao.findById(id);
    }

    public void saveDataUsage(DataUsage dataUsage) {
        dataDao.save(dataUsage);
    }

    public void updateDataUsage(DataUsage dataUsage) {
        dataDao.save(dataUsage); // save can handle both insert and update
    }

    public void deleteDataUsage(int id) {
        dataDao.deleteById(id);
    }
}
