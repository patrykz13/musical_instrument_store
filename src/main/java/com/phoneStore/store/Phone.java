package com.phoneStore.store;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.Date;

@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
public class Phone {

    @XmlAttribute(name="name")
    private String producent;

    @XmlAttribute(name="model")
    private String model;

    @XmlAttribute(name="memory")
    private Integer memory;

    @XmlAttribute(name="amount")
    private Integer amount;

    @XmlAttribute(name="date_of_production")
    private Date dateOfProduction;

    @XmlAttribute(name="photoPath")
    private String photoPath;

    @XmlAttribute(name="price")
    private Double price;


    public Phone() {
    }

    public Phone(String producent, String model,String photoPath, Integer memory, Date dateOfProduction,  Integer amount, Double price) {
        this.producent = producent;
        this.model = model;
        this.memory = memory;
        this.dateOfProduction = dateOfProduction;
        this.photoPath = photoPath;
        this.amount=amount;
        this.price=price;

    }

    public String getProducent() {
        return producent;
    }

    public void setProducent(String producent) {
        this.producent = producent;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public Integer getMemory() {
        return memory;
    }

    public void setMemory(Integer memory) {
        this.memory = memory;
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

    public Integer getAmount() {
        return amount;
    }

    public void setAmount(Integer amount) {
        this.amount = amount;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }
}
