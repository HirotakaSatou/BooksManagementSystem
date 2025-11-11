package com.example.demo.validation;

import org.springframework.beans.factory.annotation.Autowired;

import com.example.demo.service.UsersService;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class UniqueValidator implements ConstraintValidator<Unique, String> {

	@Autowired
	private UsersService service;
	
	public void initialize(Unique constraintAnnotation) {
    }
	
	@Override
	public boolean isValid(String value, ConstraintValidatorContext context) {
		
//		Optional<Users> email = service.findByEmail(value);
		
		String email = service.duplicate(value);
		
		if (email == null) {
			return true;
		}
		return false;
	}
	

}
