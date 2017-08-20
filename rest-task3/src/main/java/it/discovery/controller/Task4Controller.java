package it.discovery.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDate;
import java.time.LocalTime;

/**
 * @author isegodin
 */
@RestController
@RequestMapping("/task4")
public class Task4Controller {

    @RequestMapping("/date")
    public LocalDate getDate() {
        return LocalDate.now();
    }

    @RequestMapping("/time")
    public LocalTime getTime() {
        return LocalTime.now();
    }
}
