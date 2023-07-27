package com.example.demo.hobby.domain.request;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class ConnectRequest {
    private Long memberId;
    private Long hobbyId;
}
