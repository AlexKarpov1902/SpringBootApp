package com.karpov.app.model;

import javax.persistence.*;

@Entity
@Table(name="TABLE_COMPANY")
public class InsCompEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
//    @OneToOne()
    private Long id;
    
    @Column(name="inn")
    private String inn;
    
    @Column(name="ogrn")
    private String ogrn;
    
    @Column(name="fullname", nullable=false, length=80)
    private String fullname;
    
    @Column(name="address", length=80)
    private String address;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getInn() {
        return inn;
    }

    public void setInn(String inn) {
        this.inn = inn;
    }

    public String getOgrn() {
        return ogrn;
    }

    public void setOgrn(String ogrn) {
        this.ogrn = ogrn;
    }

    public String getFullname() {
        return fullname;
    }

    public void setFullname(String fullname) {
        this.fullname = fullname;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }
    
    
    @Override
    public String toString() {
        return "InsCompEntity [id=" + id + ", inn=" + inn + 
                ", ogrn=" + ogrn + ", fullname=" + fullname  + ", address=" + address +"]";
    }
}