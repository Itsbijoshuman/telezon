package com.telezon.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Component;

import com.telezon.model.Prepaid;

@Component
public interface PrepaidDao  extends JpaRepository<Prepaid,Integer>{

}