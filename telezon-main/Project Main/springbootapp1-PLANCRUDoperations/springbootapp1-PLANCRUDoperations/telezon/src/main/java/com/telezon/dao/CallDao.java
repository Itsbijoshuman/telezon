package com.telezon.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.telezon.model.Call;

@Repository
public interface CallDao extends JpaRepository<Call, Integer> {
}
