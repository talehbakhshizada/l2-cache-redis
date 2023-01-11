package com.main.controller;

import com.main.model.CarRequestDto;
import com.main.model.CarResponseDto;
import com.main.service.CarService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class CarController {

    private final CarService carService;

    @GetMapping("/{id}")
    public CarResponseDto getCarById(@PathVariable Long id){
        return carService.getCarById(id);
    }

    @PostMapping
    public void saveCar(@RequestBody CarRequestDto carRequestDto){
        carService.saveCar(carRequestDto);
    }

    @PutMapping
    public void updateCar(@PathVariable Long id,CarRequestDto carRequestDto){
        carService.updateCar(id,carRequestDto);
    }

    @DeleteMapping("/{id}")
    public void deleteCar(@PathVariable Long id){
        carService.deleteCar(id);
    }
}
