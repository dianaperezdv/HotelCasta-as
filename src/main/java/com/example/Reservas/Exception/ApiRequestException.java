package com.example.Reservas.Exception;

public class ApiRequestException  extends RuntimeException{

    public ApiRequestException(String message){
        super(message);
    }
}