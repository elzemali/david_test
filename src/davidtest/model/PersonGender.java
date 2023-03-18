/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package davidtest.model;

/**
 *
 * @author lenovo
 */
public enum PersonGender {
    //Utiliser Convention de nommage de ENUM
    MALE{

        @Override
        public String toString() {
            return "male";
        }
    },
    FEMALE,
    other
    
}
