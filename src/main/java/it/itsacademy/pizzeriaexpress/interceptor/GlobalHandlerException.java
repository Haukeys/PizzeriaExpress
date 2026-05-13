package it.itsacademy.pizzeriaexpress.interceptor;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.NoSuchElementException;

public class GlobalHandlerException {

    @ExceptionHandler(NoSuchElementException.class)
    public ResponseEntity<String> gestisciAssenzaDelDato(NoSuchElementException e){
        return ResponseEntity.noContent().build();
    }

    @ExceptionHandler(Throwable.class)
    public ResponseEntity<String> gestisciAssenzaDelDato(Throwable e){
        return ResponseEntity.internalServerError().body(e.getMessage());
    }

}
