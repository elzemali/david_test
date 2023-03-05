/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package davidtest.util;

import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;
import javax.persistence.AttributeConverter;
import javax.persistence.Converter;

/**
 *
 * @author lenovo
 */
@Converter(autoApply = true)
public class LocalDateAttributeConverter implements AttributeConverter<LocalDate, Date> {

    @Override
    public Date convertToDatabaseColumn(LocalDate x) {
        if (x == null) {
            return null;
        } else {
            ZoneId defaultZoneId = ZoneId.systemDefault();
            return Date.from(x.atStartOfDay(defaultZoneId).toInstant());
        }

    }

    @Override
    public LocalDate convertToEntityAttribute(Date y) {
        return y == null ? null : y.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
    }

}
