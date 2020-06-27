package com.api.dto;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class contrasenaIgualValidador implements ConstraintValidator<contrasenaIgualValidacion, Object>{
    public void initialize(contrasenaIgualValidacion constraintAnnotation) {
    	
    }

    public boolean isValid(Object value, ConstraintValidatorContext context) {
       UsuarioDTO user = (UsuarioDTO) value;
       return user.getContrasena().equals(user.getContrasenaRep());
    }
}