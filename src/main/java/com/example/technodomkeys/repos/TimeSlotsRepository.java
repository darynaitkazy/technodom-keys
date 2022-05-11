package com.example.technodomkeys.repos;

import com.example.technodomkeys.model.TimeSlots;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.sql.Time;
import java.util.List;

public interface TimeSlotsRepository extends JpaRepository<TimeSlots, Long> {
    @Query("SELECT u FROM TimeSlots u")
    List<TimeSlots> getAll();

    @Query("SELECT u FROM TimeSlots u WHERE u.city_name = ?1")
    List<TimeSlots> getAllTimeSlotsByCity(String cityName);

    @Query("SELECT a FROM TimeSlots a WHERE a.city_name = ?2 AND a.time_slot = ?1")
    List<TimeSlots> getTimeSlotsByTimeAndCity(String time, String cityName);

    @Query("SELECT a.count_kbt FROM TimeSlots a WHERE a.id = ?1")
    Long getCountKbt(Long Id);
    @Query("SELECT a.count_nonkbt FROM TimeSlots a WHERE a.id = ?1")
    Long getCountNonKbt(Long Id);
}
