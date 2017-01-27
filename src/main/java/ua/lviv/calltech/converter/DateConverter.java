package ua.lviv.calltech.converter;

import java.sql.Timestamp;
import java.time.LocalDateTime;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;

@Converter(autoApply=true)
public class DateConverter implements AttributeConverter<LocalDateTime, Timestamp>{

	public Timestamp convertToDatabaseColumn(LocalDateTime attribute) {
		return (attribute == null ? null : Timestamp.valueOf(attribute));
	}

	public LocalDateTime convertToEntityAttribute(Timestamp dbData) {
		return (dbData == null ? null : dbData.toLocalDateTime());
	}

}
