package com.main.mapper;

import com.main.dao.entity.Car;
import com.main.model.CarRequestDto;
import com.main.model.CarResponseDto;


public class CarMapper {

    public static Car carRequestDtoToCarEntity(CarRequestDto carRequestDto){
        return Car.builder()
                .name(carRequestDto.getName())
                .build();
    }


    public static CarResponseDto carEntityToCarResponseDto(Car car){
        return CarResponseDto.builder()
                .id(car.getId())
                .name(car.getName())
                .build();
    }

    public static Car updateCar(CarRequestDto carRequestDto,Car car){
        return Car.builder()
                .name(car.getName())
                .build();
    }

}
