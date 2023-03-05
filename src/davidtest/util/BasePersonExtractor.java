/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package davidtest.util;

import davidtest.model.Child;
import davidtest.model.Person;
import org.eclipse.persistence.descriptors.ClassExtractor;
import org.eclipse.persistence.sessions.Record;
import org.eclipse.persistence.sessions.Session;

/**
 *
 * @author lenovo
 */
public class BasePersonExtractor extends ClassExtractor {

    @Override
    public Class extractClassFromRow(Record record, Session sn) {
      if (record.containsKey("EYE_COLOR")) {
            return Person.class;
        } else   {
            return Child.class;
        }   
    }
    
}
