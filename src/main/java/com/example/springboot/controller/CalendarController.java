package com.example.springboot.controller;

import com.example.springboot.entity.CalendarEvent;
import com.example.springboot.repository.CalendarEventRepository;
import com.example.springboot.service.CalendarService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@RestController
public class CalendarController {
    @Autowired
    private CalendarService calendarService;

    @PostMapping("/api/calendar/save")
    public Integer saveCalendarEvent(@RequestBody CalendarEvent calendarEvent){
        calendarEvent.setLastUpdateTime(new Date());
        return calendarService.save(calendarEvent);
    }

    @GetMapping("/api/calendar/{year}/{month}")
    public List<Map<String,Object>> getCalendarEventByMonth(@PathVariable Integer year, @PathVariable Integer month){
        return calendarService.getCalendarEventByMonth(year, month);
    }

    @PostMapping("/api/calendar/delete/{id}")
    public void deleteById(@PathVariable Integer id){
        calendarService.deleteById(id);
    }
}
