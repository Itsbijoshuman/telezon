package com.telezon.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.telezon.model.DataUsage;

@Repository
public interface DataDao extends JpaRepository<DataUsage, Integer> {
}
