package com.instrumentStore.store;

import javax.xml.bind.annotation.*;
import java.util.LinkedList;
import java.util.List;


@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
public class Warehouse {
    @XmlElementWrapper(name="phones")
    @XmlElement(name="phone")
    private List<Phone> listOfPhones;



    public Warehouse() {
        listOfPhones=new LinkedList<Phone>();
    }

    public Warehouse(List listOfInstrument) {
        this.listOfPhones = listOfInstrument;
    }

    public List getListOfPhones() {
        return listOfPhones;
    }

    public void setListOfPhones(List listOfPhones) {
        this.listOfPhones = listOfPhones;
    }

    public Boolean addToListOfPhones(Phone phone){
        return listOfPhones.add(phone);
    }
    public Phone getItemFromListOfPhones(int id){
        return (Phone) listOfPhones.get(id);
    }
    public void remove(Phone phone){
        listOfPhones.remove(phone);
    }




}
