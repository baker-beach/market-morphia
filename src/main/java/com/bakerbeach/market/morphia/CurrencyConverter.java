package com.bakerbeach.market.morphia;

import java.util.Currency;

import org.mongodb.morphia.converters.SimpleValueConverter;
import org.mongodb.morphia.converters.TypeConverter;
import org.mongodb.morphia.mapping.MappedField;

public class CurrencyConverter extends TypeConverter implements SimpleValueConverter {
	
	public CurrencyConverter() {
		super(Currency.class);
	}
	
	@Override
	public Object encode(Object value, MappedField optionalExtraInfo) {
		if (value != null && value instanceof Currency) {
			return ((Currency) value).getCurrencyCode();
		} else {
			return null;
		}
	}

	@Override
	public Object decode(Class<?> targetClass, Object fromDBObject, MappedField optionalExtraInfo) {
		return Currency.getInstance((String) fromDBObject);
	}

}
