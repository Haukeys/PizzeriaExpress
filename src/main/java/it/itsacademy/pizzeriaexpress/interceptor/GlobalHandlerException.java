package it.itsacademy.pizzeriaexpress.interceptor;

import it.itsacademy.pizzeriaexpress.dto.ErroreDTO;
import it.itsacademy.pizzeriaexpress.exception.BadRequestException;
import it.itsacademy.pizzeriaexpress.exception.NotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class GlobalHandlerException {
    //for check in list or search
    @ExceptionHandler(NotFoundException.class)
    public ResponseEntity<ErroreDTO> error404NotFound() {
        return new ResponseEntity<>(new ErroreDTO(), HttpStatus.NOT_FOUND);
    }
    //for ordine
    @ExceptionHandler(BadRequestException.class)
    public ResponseEntity<ErroreDTO> error400BadRequest() {
        return new ResponseEntity<>(new ErroreDTO(), HttpStatus.BAD_REQUEST);
    }
    @ExceptionHandler(BadRequestException.class)
    public ResponseEntity<ErroreDTO> error409BadRequest() {
        return new ResponseEntity<>(new ErroreDTO(), HttpStatus.CONFLICT);
    }

}
