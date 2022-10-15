package com.desafios.desafio_quality.exception;

import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@Builder
public class QualityExceptionDetails {
        private String title;
        private int status;
        private String message;
        private LocalDateTime timeStamp;
}
