/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package davidtest.util;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.ser.std.StdSerializer;
import davidtest.model.models.Children;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author lenovo
 */
public class ChildrenSerializer extends StdSerializer<Children>{

    public ChildrenSerializer(Class<Children> t) {
        super(t);
    }
    
    
    @Override
    public void serialize(Children t, JsonGenerator jg, SerializerProvider sp) throws IOException {
        jg.writeFieldName("Children");
        t.forEach(x-> {
            try {
                jg.writeStartObject(x);
            } catch (IOException ex) {
                Logger.getLogger(ChildrenSerializer.class.getName()).log(Level.SEVERE, null, ex);
            }
        });
    }
    
}
