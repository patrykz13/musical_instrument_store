package com.instrumentStore.store;

import java.util.LinkedList;
import java.util.List;

public class Warehouse {
    private List listOfInstrument;

    public Warehouse() {
        listOfInstrument=new LinkedList<Instrument>();
    }

    public Warehouse(List listOfInstrument) {
        this.listOfInstrument = listOfInstrument;
    }
}
