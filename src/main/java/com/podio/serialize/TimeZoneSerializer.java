package com.podio.serialize;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;

import java.io.IOException;
import java.util.TimeZone;

public class TimeZoneSerializer extends JsonSerializer<TimeZone> {

	@Override
	public void serialize(TimeZone value, JsonGenerator jgen,
			SerializerProvider provider) throws IOException  {
		jgen.writeString(value.getID());
	}

}
