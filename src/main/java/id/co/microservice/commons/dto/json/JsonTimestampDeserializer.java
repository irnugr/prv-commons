package id.co.microservice.commons.dto.json;

import java.util.Date;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;

import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;

import id.co.microservice.commons.constant.CommonConstants;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;

public class JsonTimestampDeserializer extends JsonDeserializer<Date> {
	
	@Override
	public Date deserialize(final JsonParser jsonParser, final DeserializationContext ctx)
		throws IOException, JsonProcessingException {
		
		final String date = jsonParser.getText();
		
		if (date == null || date.length() == 0) {
			return null;
		}
		
		try {
			final SimpleDateFormat format = new SimpleDateFormat(CommonConstants.DEFAUL_DATE_PATTERN);
			return format.parse(date);
		} catch (final ParseException e) {
			// TODO: handle exception
			throw new RuntimeException(e);
		}
	}

}
