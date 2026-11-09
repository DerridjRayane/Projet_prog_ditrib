package com.example.rental;

import java.util.*;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

@RestController
public class RentalWebService {

    private final Map<String, Car> cars = new LinkedHashMap<>();

    public RentalWebService() {
        cars.put("11AA22", new Car("11AA22", "Ferrari", 100));
        cars.put("AA11BB", new Car("AA11BB", "Renault", 40));
        cars.put("CC33DD", new Car("CC33DD", "Peugeot", 35));
    }

    private Car find(String plateNumber) {
        Car car = cars.get(plateNumber);
        if (car == null) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Voiture inconnue");
        }
        return car;
    }

    @GetMapping("/cars")
    public List<Car> listOfCars() {
        return cars.values().stream().filter(c -> !c.isRented()).toList();
    }

    @GetMapping("/cars/{plateNumber}")
    public Car aCar(@PathVariable("plateNumber") String plateNumber) {
        return find(plateNumber);
    }

    @PutMapping(value = "/cars/{plateNumber}", params = "rent=true")
    public void rent(@PathVariable("plateNumber") String plateNumber,
                     @RequestBody Dates dates) {
        Car car = find(plateNumber);
        if (car.isRented()) {
            throw new ResponseStatusException(HttpStatus.CONFLICT, "Déjà louée");
        }
        car.setRented(true);
        car.setDates(dates);
    }

    @PutMapping(value = "/cars/{plateNumber}", params = "rent=false")
    public void giveBack(@PathVariable("plateNumber") String plateNumber) {
        Car car = find(plateNumber);
        if (!car.isRented()) {
            throw new ResponseStatusException(HttpStatus.CONFLICT, "Pas en location");
        }
        car.setRented(false);
        car.setDates(null);
    }
}