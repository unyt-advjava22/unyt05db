/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.eltonb.datatier.jpa.textbook.ex02.model;

import javax.persistence.*;
//import javax.xml.bind.annotation.XmlRootElement;
import java.io.Serializable;

/**
 *
 * @author elton.ballhysa
 */
@Entity
@Table(name = "ADDRESSES")
//@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Addresses.findAll", query = "SELECT a FROM Addresses a"),
    @NamedQuery(name = "Addresses.findByAddressid", query = "SELECT a FROM Addresses a WHERE a.addressid = :addressid"),
    @NamedQuery(name = "Addresses.findByFirstname", query = "SELECT a FROM Addresses a WHERE a.firstname = :firstname"),
    @NamedQuery(name = "Addresses.findByLastname", query = "SELECT a FROM Addresses a WHERE a.lastname = :lastname"),
    @NamedQuery(name = "Addresses.findByEmail", query = "SELECT a FROM Addresses a WHERE a.email = :email"),
    @NamedQuery(name = "Addresses.findByPhonenumber", query = "SELECT a FROM Addresses a WHERE a.phonenumber = :phonenumber")})
public class Addresses implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "ADDRESSID")
    private Integer addressid;

    @Basic(optional = false)
    @Column(name = "FIRSTNAME")
    private String firstname;

    @Basic(optional = false)
    @Column(name = "LASTNAME")
    private String lastname;

    @Basic(optional = false)
    @Column(name = "EMAIL")
    private String email;

    @Basic(optional = false)
    @Column(name = "PHONENUMBER")
    private String phonenumber;

    public Addresses() {
    }

    public Addresses(Integer addressid) {
        this.addressid = addressid;
    }

    public Addresses(Integer addressid, String firstname, String lastname, String email, String phonenumber) {
        this.addressid = addressid;
        this.firstname = firstname;
        this.lastname = lastname;
        this.email = email;
        this.phonenumber = phonenumber;
    }

    public Integer getAddressid() {
        return addressid;
    }

    public void setAddressid(Integer addressid) {
        this.addressid = addressid;
    }

    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhonenumber() {
        return phonenumber;
    }

    public void setPhonenumber(String phonenumber) {
        this.phonenumber = phonenumber;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (addressid != null ? addressid.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Addresses)) {
            return false;
        }
        Addresses other = (Addresses) object;
        if (    (this.addressid == null && other.addressid != null) || 
                (this.addressid != null && !this.addressid.equals(other.addressid))) {
            return false;
        }

        return true;
    }

    @Override
    public String toString() {
        return "al.edu.unyt.jpa.Addresses[ addressid=" + addressid + " ]";
    }
    
}
