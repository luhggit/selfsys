package com.example.springboot.repository;

import com.example.springboot.entity.CalendarEvent;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CalendarEventRepository extends JpaRepository<CalendarEvent,Integer> {
    /**
     * @param year
     * @param month
     * @return
     */
    List<CalendarEvent> findAllByYearAndMonthOrderByIdAsc(Integer year,Integer month);
}
