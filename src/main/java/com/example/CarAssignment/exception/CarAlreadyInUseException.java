package com.example.CarAssignment.exception;

public class CarAlreadyInUseException extends RuntimeException
{

    public CarAlreadyInUseException()
    {
        super();
    }

    public  CarAlreadyInUseException(String message)
    {
        super(message);
    }
}
