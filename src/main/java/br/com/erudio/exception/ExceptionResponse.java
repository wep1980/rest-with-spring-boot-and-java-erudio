package br.com.erudio.exception;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.util.Date;


public record ExceptionResponse(
        //@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd/MM/yyyy HH:mm", timezone = "America/Sao_Paulo")
        Date timestamp,
        String message,
        String details) {
}
