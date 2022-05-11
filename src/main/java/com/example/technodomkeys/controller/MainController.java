package com.example.technodomkeys.controller;

import com.example.technodomkeys.model.TimeSlots;
import com.example.technodomkeys.repos.TimeSlotsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.sql.Date;
import java.sql.Time;
import java.util.List;

@Controller
public class MainController {
    @Autowired
    TimeSlotsRepository timeSlotsRepository;

    @GetMapping("/")
    public String main() {
        return "index";
    }

    @PostMapping("/answer")
    public String answer(Model model, @RequestParam("city") String city, @RequestParam("kbt") String kbt,
                         @RequestParam("time_slot") String time_slot, @RequestParam("count") int cnt, @RequestParam("date") Date date) {
        Iterable<TimeSlots> timeSlots = timeSlotsRepository.getTimeSlotsByTimeAndCity(time_slot, city);
        Iterable<TimeSlots> timeSlotsByCity = timeSlotsRepository.getAllTimeSlotsByCity(city);
        model.addAttribute("timeSlots", timeSlots);
        model.addAttribute("timeSlotsByCity", timeSlotsByCity);
        boolean check_kbt = false;
        long sumKbt = 0;
        long sumNonKbt = 0;

        if (kbt.equals("Да")) {
            for (TimeSlots f : timeSlots) {
                System.out.println(f.getDate());
                if (f.getDate().equals(date)) {
                    Long cur = timeSlotsRepository.getCountKbt(f.getId());
                    sumKbt += cur;
                }
            }
            Long q = sumKbt;
            if (q >= cnt) {
                check_kbt = true;
            }
        }
        else if (kbt.equals("Нет")) {
            for (TimeSlots f : timeSlots) {
                if (f.getDate().equals(date)) {
                    Long cur = timeSlotsRepository.getCountNonKbt(f.getId());
                    sumNonKbt += cur;
                }
            }
            Long q = sumNonKbt;
            if (q >= cnt) {
                check_kbt = true;
            }
        }


        model.addAttribute("checkKbt", check_kbt);

        if (check_kbt && kbt.equals("Да")) {
            for (TimeSlots f: timeSlots) {
                f.setCount_kbt(f.getCount_kbt() - cnt);
                timeSlotsRepository.save(f);
            }
        }

        if (check_kbt && kbt.equals("Нет")) {
            for (TimeSlots f: timeSlots) {
                f.setCount_nonkbt(f.getCount_nonkbt() - cnt);
                timeSlotsRepository.save(f);
            }
        }

        return "answer";
    }
    @GetMapping("/add")
    public String add() {
        return "addTimeSlot";
    }

    @PostMapping("/add")
    public String addSlot(Model model, @RequestParam("city") String city, @RequestParam("time_slot") String time_slot,
                          @RequestParam("count_kbt") int count_kbt, @RequestParam("count_nonkbt") int count_nonkbt,
                          @RequestParam("date")Date date) {
        TimeSlots timeSlots = new TimeSlots(time_slot, count_kbt, count_nonkbt, city, date);
        timeSlotsRepository.save(timeSlots);
        return "addTimeSlot";
    }
    @GetMapping("/all")
    public String all(Model model) {
        Iterable<TimeSlots>t = timeSlotsRepository.getAll();
        model.addAttribute("timeSlots", t);
        return "all";
    }
}
