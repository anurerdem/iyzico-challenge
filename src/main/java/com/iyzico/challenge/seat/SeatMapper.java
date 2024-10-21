package com.iyzico.challenge.seat;

import com.iyzico.challenge.seat.dto.response.SeatCreateResponse;
import com.iyzico.challenge.seat.dto.response.SeatResponseResponse;
import com.iyzico.challenge.entity.Seat;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "Spring")
public interface SeatMapper {

    @Mapping(target = "id", source = "id")
    SeatResponseResponse toSeatDto(Seat entity);

    Seat toSeatEntity(SeatCreateResponse dto);

    List<SeatResponseResponse> convertSeatEntityListToSeatDtoList(List<Seat> entityList);


}
