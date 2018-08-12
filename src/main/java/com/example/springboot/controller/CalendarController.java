package com.example.springboot.controller;

import com.example.springboot.entity.CalendarEvent;
import com.example.springboot.repository.CalendarEventRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@RestController
public class CalendarController {

    @Autowired
    private CalendarEventRepository calendarEventRepository;

    @PostMapping("/api/calendar/event/save")
    public Integer saveCalendarEvent(@RequestBody CalendarEvent calendarEvent){
        calendarEvent.setLastUpdateTime(new Date());
        calendarEvent = calendarEventRepository.save(calendarEvent);
        return calendarEvent.getId();
    }

    @GetMapping("/api/calendar/event/{year}/{month}")
    public List<Map<String,Object>> getCalendarEventByMonth(@PathVariable Integer year, @PathVariable Integer month){
        List<CalendarEvent> calendarEvents = calendarEventRepository.findAllByYearAndMonthOrderByCreateTime(year, month);

        List<CalendarEvent> calendarEventsLastOneMonth;
        if (month == 0){
            calendarEventsLastOneMonth = calendarEventRepository.findAllByYearAndMonthOrderByCreateTime(year-1,11);
        }else{
            calendarEventsLastOneMonth = calendarEventRepository.findAllByYearAndMonthOrderByCreateTime(year,month -1);
        }

        if (calendarEventsLastOneMonth.size()>0){
            calendarEvents.addAll(calendarEventsLastOneMonth);
        }

        List<CalendarEvent> calendarEventsNextMonth;
        if (month == 11){
            calendarEventsNextMonth = calendarEventRepository.findAllByYearAndMonthOrderByCreateTime(year+1,0);
        }else{
            calendarEventsNextMonth = calendarEventRepository.findAllByYearAndMonthOrderByCreateTime(year,month + 1);
        }

        if (calendarEventsNextMonth.size()>0){
            calendarEvents.addAll(calendarEventsNextMonth);
        }

        List<Map<String,Object>> datas = new ArrayList<>();
        Map<String,Object> data;
        for (CalendarEvent calendarEvent:calendarEvents){
            data = new HashMap<>();
            data.put("id",calendarEvent.getId());
            data.put("content",calendarEvent.getEventContent());
            datas.add(data);
        }
        return datas;
    }
}
