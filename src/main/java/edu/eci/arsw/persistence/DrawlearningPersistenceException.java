package edu.eci.arsw.persistence;

public class DrawlearningPersistenceException extends Exception{
    public static final String NO_USER = "No se encontro el usuario en el juego";

    public DrawlearningPersistenceException(String message){
        super(message);
    }

    public DrawlearningPersistenceException(String message, Throwable cause){
        super(message,cause);
    }
}
