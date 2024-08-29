package com.telezon.service;

import com.telezon.dao.CallDao;
import com.telezon.dao.CustomerDao;
import com.telezon.model.Call;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CallService {

    @Autowired
    private CallDao callDao;

    @Autowired
    private CustomerDao customerDao;
    
    public List<Call> getAllCalls() {
        return callDao.findAll();
    }

    public Optional<Call> getCallById(int id) {
        return callDao.findById(id);
    }

    public void saveCall(Call call) {
        callDao.save(call);
        
    }

    public void updateCall(Call call) {
        callDao.save(call); // save can handle both insert and update
    }

    public void deleteCall(int id) {
        callDao.deleteById(id);
    }
}
