package com.instrumentStore.store;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.Date;

@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
public class Instrument {

    @XmlAttribute(name="name")
    private String name;

    @XmlAttribute(name="category")
    private String category;

    @XmlAttribute(name="price")
    private Double price;

    @XmlAttribute(name="date of production")
    private Date dateOfProduction;

    @XmlAttribute(name="price")
    private String photoPath;

    public Instrument() {
    }

    public Instrument(String name, String category, Double price, Date dateOfProduction, String photoPath) {
        this.name = name;
        this.category = category;
        this.price = price;
        this.dateOfProduction = dateOfProduction;
        this.photoPath = photoPath;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public Date getDateOfProduction() {
        return dateOfProduction;
    }

    public void setDateOfProduction(Date dateOfProduction) {
        this.dateOfProduction = dateOfProduction;
    }

    public String getPhotoPath() {
        return photoPath;
    }

    public void setPhotoPath(String photoPath) {
        this.photoPath = photoPath;
    }
}
