package org.barmaley.vkr.domain;

import javax.persistence.*;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

/**
 * Created by Flugh on 22.04.2017.
 */

@Entity
@Table(name = "USERS")
public class Users implements Serializable {

    private static final long serialVersionUID = -5527566248002296042L;

    @Id
    @Column(name = "ID")
    @GeneratedValue
    private Integer id;

    @Column(name = "EXT_ID")
    private String extId;

    @Column(name = "ORIGIN")
    private String origin;

    @Column(name = "ENABLED")
    private Boolean enabled;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "ROLE_MEMBERS", joinColumns = @JoinColumn(name = "MEMBERS_ID"),
            inverseJoinColumns = @JoinColumn(name = "ROLES_ID"))
    private Set<Roles> roles;

    @Column(name = "SURNAME")
    private String surname;

    @Column(name = "FIRST_NAME")
    private String firstName;

    @Column(name = "SECOND_NAME")
    private String secondName;

    @Column(name = "SURNAME_ENG")
    private String surnameEng;

    @Column(name = "FIRST_NAME_ENG")
    private String firstNameEng;

    @Column(name = "SECOND_NAME_ENG")
    private String secondNameEng;

    @Column(name = "EMAIL")
    private String email;

    @Column(name = "PHONE_NUMBER")
    private String phoneNumber;

    @OneToMany(mappedBy = "coordinator", cascade = CascadeType.ALL, fetch = FetchType.EAGER, orphanRemoval = true)
    private Set<CoordinatorRights> coordinatorRights = new HashSet<CoordinatorRights>();

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getExtId() {
        return extId;
    }

    public void setExtId(String extId) {
        this.extId = extId;
    }

    public String getOrigin() {
        return origin;
    }

    public void setOrigin(String origin) {
        this.origin = origin;
    }

    public Set<Roles> getRoles() {
        return roles;
    }

    public void setRoles(Set<Roles> roles) {
        this.roles = roles;
    }

    public void setEnabled(Boolean enabled) {
        this.enabled = enabled;
    }

    public Boolean getEnabled() {
        return enabled;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getSecondName() {
        return secondName;
    }

    public void setSecondName(String secondName) {
        this.secondName = secondName;
    }

    public String getSurnameEng() {
        return surnameEng;
    }

    public void setSurnameEng(String surnameEng) {
        this.surnameEng = surnameEng;
    }

    public String getFirstNameEng() {
        return firstNameEng;
    }

    public void setFirstNameEng(String firstNameEng) {
        this.firstNameEng = firstNameEng;
    }

    public String getSecondNameEng() {
        return secondNameEng;
    }

    public void setSecondNameEng(String secondNameEng) {
        this.secondNameEng = secondNameEng;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public static long getSerialVersionUID() {
        return serialVersionUID;
    }


    public Set<CoordinatorRights> getCoordinatorRights() {
        return coordinatorRights;
    }

    public void setCoordinatorRights(Set<CoordinatorRights> coordinatorRights) {
        this.coordinatorRights = coordinatorRights;
    }
}
