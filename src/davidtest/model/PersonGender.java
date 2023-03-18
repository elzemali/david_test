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
    MALE {
        
        @Override
        public String toString() {
            return "male";
        }
        
    },
    @JsonProperty("female")
    FEMALE {
        
        @Override
        public String toString() {
            return "female";
        }
    },
    @JsonEnumDefaultValue
    OTHER {
    
        @Override
        public String toString() {
            return "other";
        }
    }
}
