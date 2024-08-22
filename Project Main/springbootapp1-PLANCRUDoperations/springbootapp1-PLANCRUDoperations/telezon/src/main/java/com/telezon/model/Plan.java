package com.telezon.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name="plans")
public class Plan {
    @Id
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer pid;

    @Column(name="pname")
    private String pname;

    @Column(name="duration")
    private Integer duration;

    public Plan() {}

    public Plan(Integer pid, String pname, Integer duration) {
        this.pid = pid;
        this.pname = pname;
        this.duration = duration;
    }

    // Getters and Setters
    public Integer getPid() {
        return pid;
    }

    public void setPid(Integer pid) {
        this.pid = pid;
    }

    public String getPname() {
        return pname;
    }

    public void setPname(String pname) {
        this.pname = pname;
    }

    public Integer getDuration() {
        return duration;
    }

    public void setDuration(Integer duration) {
        this.duration = duration;
    }

    @Override
    public String toString() {
        return "Plan [pid=" + pid + ", pname=" + pname + ", duration=" + duration + "]";
    }
}
