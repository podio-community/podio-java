package com.podio.serialize;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;

import java.io.IOException;
import java.util.Locale;

public class LocaleSerializer extends JsonSerializer<Locale> {

	@Override
	public void serialize(Locale value, JsonGenerator jgen,
			SerializerProvider provider) throws IOException {
		jgen.writeString(value.getLanguage() + "_" + value.getCountry());
	}

}
