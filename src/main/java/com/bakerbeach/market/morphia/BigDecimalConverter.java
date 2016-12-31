package com.bakerbeach.market.morphia;

import java.math.BigDecimal;

import org.mongodb.morphia.converters.SimpleValueConverter;
import org.mongodb.morphia.converters.TypeConverter;
import org.mongodb.morphia.mapping.MappedField;

public class BigDecimalConverter extends TypeConverter implements SimpleValueConverter {
	
	public BigDecimalConverter() {
		super(BigDecimal.class);
	}
	
	@Override
	public Object encode(Object value, MappedField optionalExtraInfo) {
		if (value != null && value instanceof BigDecimal) {
			return value.toString();
		} else {
			return null;
		}
	}

	@Override
	public Object decode(Class<?> targetClass, Object fromDBObject, MappedField optionalExtraInfo) {
		
		return new BigDecimal((String) fromDBObject);
	}

}
