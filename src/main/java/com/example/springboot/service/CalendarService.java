package com.example.springboot.service;

import com.example.springboot.entity.CalendarEvent;

import java.util.List;
import java.util.Map;

public interface CalendarService {
    void deleteById(Integer id);
    Integer save(CalendarEvent calendarEvent);
    List<Map<String,Object>> getCalendarEventByMonth(Integer year,Integer month);
}
