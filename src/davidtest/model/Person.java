/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package davidtest.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.sun.xml.internal.ws.developer.Serialization;
import davidtest.model.models.Children;
import davidtest.util.ChildrenSerializer;
import davidtest.util.LocalDateTimeAttributeConverter;
import java.time.LocalDate;
import java.time.LocalDateTime;
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
    private String eyeColor;
    @Enumerated(EnumType.STRING)
    @Column(name = "GENDER")
    private PersonGender gender;
    @Column(name = "DATE_OF_BIRTH")
    @Convert(converter = LocalDateTimeAttributeConverter.class)
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "[yyyy-MM-dd'T'HH:mm:ss][dd/MM/yyyy][dd-MM-yyyy]")
    private LocalDateTime dateOfBirth;
    @Column(name = "EMAIL")
    private String email;
    @Column(name = "PHONE")
    private Long phone;
    @Column(name = "ADDRESS")
    private String address;
    @Column(name = "COUNTRY")
    private String country;
    @Lob
    @Column(name = "ABOUT")
    private String about;
    @Basic(optional = false)
    @Column(name = "FL_ACTIVE")
    private boolean isActive;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "[dd/MM/yy][yyyy-MM-dd][yyyy-MM-d]")
    @Column(name = "REGISTERED")
    private LocalDate registered;

    @OneToMany(cascade = CascadeType.PERSIST)
    @JoinTable(
            name = "person_children",
            joinColumns = @JoinColumn(name = "ID_PERSON"),
            inverseJoinColumns = @JoinColumn(name = "ID_CHILD")
    )
    
    @JsonSerialize(using =ChildrenSerializer.class)
    private Children children;

    public Person() {
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

    public Long getPhone() {
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

    public boolean isActive() {
        return isActive;
    }

    public LocalDate getRegistered() {
        return registered;
    }

    public Children getChildren() {
        return new Children((List<Child>)children);
    }

    public void setEyeColor(String eyeColor) {
        this.eyeColor = eyeColor;
    }

    public void setGender(PersonGender gender) {
        this.gender = gender;
    }

    public void setDateOfBirth(LocalDateTime dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setPhone(Long phone) {
        this.phone = phone;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public void setAbout(String about) {
        this.about = about;
    }

    public void setIsActive(boolean isActive) {
        this.isActive = isActive;
    }

    public void setRegistered(LocalDate registered) {
        this.registered = registered;
    }

    public void setChildren(Children children) {
        this.children = children;
    }
   
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        
        sb.append("Person{");
        sb.append("id=");
        sb.append(getId());
        sb.append(", firstName=");
        sb.append(getFirstName());
        sb.append(", lastName=");
        sb.append(getLastName());
        sb.append(", eyeColor=");
        sb.append(eyeColor);
        sb.append(", gender=");
        sb.append(gender);
        sb.append(", dateOfBirth=");
        sb.append(dateOfBirth);
        sb.append(", email=");
        sb.append(email);
        sb.append(", phone=");
        sb.append(phone);
        sb.append(", country=");
        sb.append(country );
        sb.append(", address=");
        sb.append(address);
        sb.append(", about=");
        sb.append(about);
        sb.append(", isActive=");
        sb.append(isActive);
        sb.append(", registered=");
        sb.append(registered);
        sb.append(", children=");
        sb.append(children);
        sb.append("}"); 
        
        return sb.toString();
    }

}
