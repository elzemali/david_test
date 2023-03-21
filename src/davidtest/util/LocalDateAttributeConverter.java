/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package davidtest.util;

import java.text.SimpleDateFormat;
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
        SimpleDateFormat dateFormat1 = new SimpleDateFormat("DD/MM/YY");
        SimpleDateFormat dateFormat2 = new SimpleDateFormat("YYYY-MM-DD");
        return y == null ? null : y.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
    }

}
