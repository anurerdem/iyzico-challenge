package com.iyzico.challenge.seat.dto.request;

import com.iyzico.challenge.seat.dto.response.SeatCreateResponse;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;


@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class SeatCreateRequest {
    private Long id;
    private List<SeatCreateResponse> seats;
}