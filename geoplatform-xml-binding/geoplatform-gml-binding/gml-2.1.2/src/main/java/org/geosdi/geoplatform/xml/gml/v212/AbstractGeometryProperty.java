//
// Questo file è stato generato dall'architettura JavaTM per XML Binding (JAXB) Reference Implementation, v2.2.7 
// Vedere <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Qualsiasi modifica a questo file andrà persa durante la ricompilazione dello schema di origine. 
// Generato il: 2015.08.25 alle 10:30:08 PM CEST 
//


package org.geosdi.geoplatform.xml.gml.v212;

import javax.xml.bind.JAXBElement;
import javax.xml.namespace.QName;

public class AbstractGeometryProperty
    extends JAXBElement<GeometryAssociationType>
{

    protected final static QName NAME = new QName("http://www.opengis.net/gml", "_geometryProperty");

    public AbstractGeometryProperty(GeometryAssociationType value) {
        super(NAME, ((Class) GeometryAssociationType.class), null, value);
    }

    public AbstractGeometryProperty() {
        super(NAME, ((Class) GeometryAssociationType.class), null, null);
    }

}
