package com.podio.serialize;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;

import java.io.IOException;
import java.util.TimeZone;

public class TimeZoneDeserializer extends JsonDeserializer<TimeZone> {

	@Override
	public TimeZone deserialize(JsonParser jp, DeserializationContext ctxt)
			throws IOException, JsonProcessingException {
		return TimeZone.getTimeZone(jp.getText());
	}
}
