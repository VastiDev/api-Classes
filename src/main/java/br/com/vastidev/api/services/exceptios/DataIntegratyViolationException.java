package br.com.vastidev.api.services.exceptios;

public class DataIntegratyViolationException extends RuntimeException{
    public DataIntegratyViolationException(String message) {

        super(message);
    }
}
