package br.com.caelum.fj91.escalabilidade.helpers;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;

import java.util.Optional;

@Component
public class BindingResultHelper {

    public Optional<ResponseEntity<?>> hasErrorIn(BindingResult result) {

        if (result.hasErrors()) {

            String[] errors = result.getFieldErrors().stream().map(ResponseError::new).map(ResponseError::getDescription).toArray(String[]::new);


            return Optional.of(ResponseEntity.badRequest().body(errors));
        }

        return Optional.empty();
    }
}


class ResponseError {
    private final FieldError error;

    public ResponseError(FieldError error) {
        this.error = error;
    }

    public String getDescription(){
        return error.getField() + ":" + error.getDefaultMessage();
    }
}