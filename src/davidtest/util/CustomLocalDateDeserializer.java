/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package davidtest.util;

import com.fasterxml.jackson.core.JacksonException;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.deser.std.StdDeserializer;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;

/**
 *
 * @author lenovo
 */
public class CustomLocalDateDeserializer extends StdDeserializer<LocalDate>{

    public CustomLocalDateDeserializer() {
        this(null);
    }

    public CustomLocalDateDeserializer(Class t) {
        super(t);
    }
    
    @Override
    public LocalDate deserialize(JsonParser jp, DeserializationContext dc) throws IOException, JacksonException {
       String text = jp.readValueAs(String.class);
       SimpleDateFormat s = new SimpleDateFormat("DD/MM/YY");
       SimpleDateFormat s1 = new SimpleDateFormat("YYYY-MM-DD");
       Date d = null;
        try {
            d = s.parse(text);
        } catch (ParseException ex) {
           try {
               d = s1.parse(text);
           } catch (ParseException ex1) {
               d = null;
           }
        }
       return (d != null) ? d.toInstant().atZone(ZoneId.systemDefault()).toLocalDate(): null;  
    }
    
}
