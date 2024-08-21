package com.telezon.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Component;

import com.telezon.model.Postpaid;

@Component
public interface PostpaidDao  extends JpaRepository<Postpaid,Integer>{

}