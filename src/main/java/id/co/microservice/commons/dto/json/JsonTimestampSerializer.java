package id.co.microservice.commons.dto.json;

import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;

import id.co.microservice.commons.constant.CommonConstants;

public class JsonTimestampSerializer extends JsonSerializer<Date> {
	
	@Override
	public void serialize(final Date date, final JsonGenerator gen, final SerializerProvider provider)
		throws IOException {
		if (date == null) {
			gen.writeString("");
		} else {
			final DateFormat dateFormat = new SimpleDateFormat(CommonConstants.DEFAUL_DATE_PATTERN);
			final String formattedDate = dateFormat.format(date);
			gen.writeString(formattedDate);
		}
	}

}
