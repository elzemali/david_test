/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package davidtest.model;

import com.fasterxml.jackson.annotation.JsonEnumDefaultValue;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 *
 * @author lenovo
 */
public enum PersonGender {
    @JsonProperty("male")
    MALE("male"),  
    @JsonProperty("female")
    FEMALE("female"),
    @JsonEnumDefaultValue
    OTHER("other");
    
    private final String name;
    
    private PersonGender(String name) {
        this.name= name;
    }

    public String getName() {
        return name;
    }
}
