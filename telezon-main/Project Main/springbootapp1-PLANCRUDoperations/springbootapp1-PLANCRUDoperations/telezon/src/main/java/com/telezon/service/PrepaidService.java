package com.telezon.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.telezon.dao.PrepaidDao;
import com.telezon.model.Prepaid;

import jakarta.transaction.Transactional;
import java.util.List;

@Service
@Transactional
public class PrepaidService {

    @Autowired
    private PrepaidDao prepaidDao;

    public List<Prepaid> getPrepaidPlans() {
        return prepaidDao.findAll();
    }

    public void addPrepaidPlan(Prepaid prepaid) {
        prepaidDao.save(prepaid);
    }
}