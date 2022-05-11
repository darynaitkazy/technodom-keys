package com.example.technodomkeys.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.sql.Date;

@Entity
public class TimeSlots {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String time_slot;
    private int count_kbt;
    private int count_nonkbt;
    private String city_name;
    private Date date;

    public TimeSlots() {

    }

    public TimeSlots(String time_slot, int count_kbt, int count_nonkbt, String city_name, Date date) {

        this.time_slot = time_slot;
        this.count_kbt = count_kbt;
        this.count_nonkbt = count_nonkbt;
        this.city_name = city_name;
        this.date = date;
    }

    public Long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getTime_slot() {
        return time_slot;
    }

    public void setTime_slot(String time_slot) {
        this.time_slot = time_slot;
    }

    public int getCount_kbt() {
        return count_kbt;
    }

    public void setCount_kbt(int count_kbt) {
        this.count_kbt = count_kbt;
    }

    public int getCount_nonkbt() {
        return count_nonkbt;
    }

    public void setCount_nonkbt(int count_nonkbt) {
        this.count_nonkbt = count_nonkbt;
    }

    public String getCity_name() {
        return city_name;
    }

    public void setCity_name(String city_name) {
        this.city_name = city_name;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }
}
