package com.example.restapi.librarymanagement.validator;

import com.example.restapi.librarymanagement.model.BookFormat;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import java.util.List;

public class BookFormatValidator implements ConstraintValidator<ValidateBookFormat,String> {

    private List<String> accepedValueList;
    @Override
    public void initialize(ValidateBookFormat validateEnumString) {
        for(BookFormat format : BookFormat.values()) {
           accepedValueList.add(format.name());
        }
    }

    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {
        return accepedValueList.contains(value.toUpperCase());
    }
}
