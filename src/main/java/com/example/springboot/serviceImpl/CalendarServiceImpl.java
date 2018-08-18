package com.example.springboot.serviceImpl;

import com.example.springboot.entity.CalendarEvent;
import com.example.springboot.repository.CalendarEventRepository;
import com.example.springboot.service.CalendarService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class CalendarServiceImpl implements CalendarService {

    @Autowired
    CalendarEventRepository calendarEventRepository;

    @Override
    public void deleteById(Integer id){
        calendarEventRepository.delete(id);
    }

    @Override
    public Integer save(CalendarEvent calendarEvent){
        return calendarEventRepository.save(calendarEvent).getId();
    }

    @Override
    public List<Map<String, Object>> getCalendarEventByMonth(Integer year, Integer month) {
        List<CalendarEvent> calendarEvents = calendarEventRepository.findAllByYearAndMonthOrderByIdAsc(year, month);

        List<CalendarEvent> calendarEventsLastOneMonth;
        if (month == 0){
            calendarEventsLastOneMonth = calendarEventRepository.findAllByYearAndMonthOrderByIdAsc(year-1,11);
        }else{
            calendarEventsLastOneMonth = calendarEventRepository.findAllByYearAndMonthOrderByIdAsc(year,month -1);
        }

        if (calendarEventsLastOneMonth.size()>0){
            calendarEvents.addAll(calendarEventsLastOneMonth);
        }

        List<CalendarEvent> calendarEventsNextMonth;
        if (month == 11){
            calendarEventsNextMonth = calendarEventRepository.findAllByYearAndMonthOrderByIdAsc(year+1,0);
        }else{
            calendarEventsNextMonth = calendarEventRepository.findAllByYearAndMonthOrderByIdAsc(year,month + 1);
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
