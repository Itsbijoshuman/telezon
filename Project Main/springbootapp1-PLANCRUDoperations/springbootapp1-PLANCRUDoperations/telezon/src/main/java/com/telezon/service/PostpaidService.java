package com.telezon.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.telezon.dao.PostpaidDao;
import com.telezon.model.Postpaid;

import jakarta.transaction.Transactional;
import java.util.List;

@Service
@Transactional
public class PostpaidService {

    @Autowired
    private PostpaidDao postpaidDao;

    public List<Postpaid> getPostpaidPlans() {
        return postpaidDao.findAll();
    }

    public void addPostpaidPlan(Postpaid postpaid) {
        postpaidDao.save(postpaid);
    }
}
