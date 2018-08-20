package com.lgdias.testexbrain.converter;

import java.sql.Date;
import java.time.LocalDate;
import javax.persistence.AttributeConverter;

public class LocalDateAttributeConverter implements AttributeConverter<LocalDate, Date> {

  @Override
  public Date convertToDatabaseColumn(LocalDate localDate) {
    return (localDate == null ? null : Date.valueOf(localDate));
  }

  @Override
  public LocalDate convertToEntityAttribute(Date date) {
    return (date == null ? null : date.toLocalDate());
  }
}
