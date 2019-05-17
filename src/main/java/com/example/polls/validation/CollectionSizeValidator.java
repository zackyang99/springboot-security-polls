package com.example.polls.validation;

import java.util.Collection;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class CollectionSizeValidator implements ConstraintValidator<CollectionSize, Collection> {
	int min = 0;
	int max = Integer.MAX_VALUE;
	
	@Override
	public void initialize(CollectionSize collectionConstraint) {
		this.min = collectionConstraint.min();
		this.max = collectionConstraint.max();
    }

	@Override
	public boolean isValid(Collection element, ConstraintValidatorContext ctx) {
		return element != null && element.size() >= min && element.size() <= max;
	}

}
