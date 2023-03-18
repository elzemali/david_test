/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package davidtest.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import davidtest.util.LocalDateTimeAttributeConverter;
import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Convert;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.Lob;
import javax.persistence.OneToMany;
import javax.persistence.Table;

/**
 *
 * @author lenovo
 */
@Entity
@Table(name = "person") 
public class Person extends BasePerson {
    @Column(name = "EYE_COLOR")
    private final String eyeColor;
    
    @Enumerated(EnumType.STRING)
    @Column(name = "GENDER")
    private final PersonGender gender;
     
    @Column(name = "DATE_OF_BIRTH" )
    @Convert(converter = LocalDateTimeAttributeConverter.class)
    //faire attention à l'ordre d'apparaision
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "[dd/MM/yyyy][dd-MM-yyyy][yyyy-MM-dd'T'HH:mm:ss]")  
   
    private final LocalDateTime dateOfBirth;
 
    @Column(name = "EMAIL")
    private final String email;
    
    @JsonIgnore
    @Column(name = "PHONE")
    private final long phone;
    
    @Column(name = "ADDRESS")
    private final String address;
    
    @Column(name = "COUNTRY")
    private final String country;
    
    @Lob
    @Column(name = "ABOUT")
    private final String about;
    
    @Basic(optional = false)
    @Column(name = "FL_ACTIVE")
    private final boolean isActive;
    
    
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "[dd/MM/yy][yyyy-MM-dd][yyyy-MM-d]")  
    @Column(name = "REGISTERED")
   
    private final LocalDate registered;

    @OneToMany(cascade = CascadeType.PERSIST)
     @JoinTable(
            name="person_children",
            joinColumns = @JoinColumn( name="ID_PERSON"),
            inverseJoinColumns = @JoinColumn( name="ID_CHILD")
        )
    private final List<Child> children;

    
    //A enlever
    public Person() {
        super();
        this.eyeColor = "";
        this.gender = PersonGender.other;
        this.dateOfBirth = null;
        this.email = "";
        this.phone = 0;
        this.address = "";
        this.country = "";
        this.about = "";
        this.isActive = false;
        this.registered = null;
        this.children = new ArrayList();
    }

    public Person(String _id, String firstName, String lastName, String eyeColor, PersonGender gender, LocalDateTime dateOfBirth, String email, long phone, String address, String country, String about, boolean isActive, LocalDate registered, List children) {
        super(_id, firstName, lastName);
        this.eyeColor = eyeColor;
        this.gender = gender;
        this.dateOfBirth = dateOfBirth;
        this.email = email;
        this.phone = phone;
        this.address = address;
        this.country = country;
        this.about = about;
        this.isActive = isActive;
        this.registered = registered;
        //Constructeur vraiment long
        //TODO ça peut etre nulle
        this.children = children;
    }
   

    public String getEyeColor() {
        return eyeColor;
    }

    public PersonGender getGender() {
        return gender;
    }

    public LocalDateTime getDateOfBirth() {
        return dateOfBirth;
    }

    public String getEmail() {
        return email;
    }

    public long getPhone() {
        return phone;
    }

    public String getAddress() {
        return address;
    }

    public String getCountry() {
        return country;
    }

    public String getAbout() {
        return about;
    }

    public boolean isIsActive() {
        return isActive;
    }

    public LocalDate getRegistered() {
        return registered;
    }

    public List getChildren() {
        return new ArrayList(children);
    }

    @Override
    public String toString() {
        return "Person{" + "eyeColor=" + eyeColor + ", gender=" + gender + ", dateOfBirth=" + dateOfBirth + ", email=" + email + ", phone=" + phone + ", address=" + address + ", country=" + country + ", about=" + about + ", isActive=" + isActive + ", registered=" + registered + ", children=" + children + '}';
    }

}
