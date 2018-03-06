package com.instrumentStore.store;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import java.io.File;

/**
 * <p>Xml parsing class.</p>
 *
 * @author Patryk Zdral
 * @version $Id: $Id
 */
public class XmlParser {

    /**
     * <p>function reading from xml file</p>
     *
     * @param path a {@link String} object.
     * @return a {@link com.instrumentStore.store.Warehouse} object.
     */
    public Warehouse readFromXMLFile(String path){
        File file = new File(path);
        try {
            JAXBContext jaxbContext = JAXBContext.newInstance(Warehouse.class);

            Unmarshaller jaxbUnmarshaller = jaxbContext.createUnmarshaller();
            Warehouse warehouse = (Warehouse) jaxbUnmarshaller.unmarshal(file);
            return warehouse;
        } catch (JAXBException e) {
            e.printStackTrace();
            return null;
        }
    }

    /**
     * <p>function saving to xml file.</p>
     *
     * @param warehouse a {@link com.instrumentStore.store.Warehouse} object.} object.
     * @param p a {@link String} object.
     */
    public void saveToXMLFile(Warehouse warehouse,String p){
        JAXBContext jaxbContext = null;
        try {
            jaxbContext = JAXBContext.newInstance(Warehouse.class);
            Marshaller jaxbMarshaller = jaxbContext.createMarshaller();
            jaxbMarshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
            //ZAPIS DO PLIKU
            jaxbMarshaller.marshal(warehouse, new File(p+".xml"));
        } catch (JAXBException e) {
            e.printStackTrace();
        }

    }





}
