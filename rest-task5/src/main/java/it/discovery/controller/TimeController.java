package it.discovery.controller;

import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDate;
import java.time.LocalTime;

/**
 * @author isegodin
 */
@RestController
@RequestMapping("/api")
public class TimeController {

    @RequestMapping("/date")
    @Cacheable("dateCache")
    public LocalDate getDate() {
        return LocalDate.now();
    }

    @RequestMapping("/time")
    @Cacheable("timeCache")
    public LocalTime getTime() {
        return LocalTime.now();
    }

    @RequestMapping("/clearCache")
    @CacheEvict(cacheNames = {"dateCache", "timeCache"})
    public void clearCache() {}
}
