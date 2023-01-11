package com.main.service;

import com.main.dao.entity.Car;
import com.main.dao.repository.CarRepository;
import com.main.mapper.CarMapper;
import com.main.model.CarRequestDto;
import com.main.model.CarResponseDto;
import lombok.RequiredArgsConstructor;

import org.modelmapper.ModelMapper;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class CarService {

    private final CarRepository carRepository;
    private  final ModelMapper mapper;

    @Cacheable(value = "cash_car", key = "#id")
    public CarResponseDto getCarById(Long id) {
        return carRepository.findById(id).map(CarMapper::carEntityToCarResponseDto)
                .orElseThrow(() -> new RuntimeException("Not found"));
    }


    @CacheEvict(value = "cash_car")
    public void deleteCar(Long id) {
        carRepository.deleteById(id);
    }

    public void saveCar(CarRequestDto carRequestDto) {
        carRepository.save(mapper.map(carRequestDto, Car.class));
    }

    @CachePut(value = "cash_car")
    public void updateCar(Long id, CarRequestDto carRequestDto) {
        var car = carRepository.findById(id).orElseThrow(RuntimeException::new);
        carRepository.save(CarMapper.updateCar(carRequestDto, car));
    }
}
