package org.geosdi.geoplatform.jaxb.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.io.Serializable;

/**
 * @author Giuseppe La Scaleia - CNR IMAA geoSDI Group
 * @email giuseppe.lascaleia@geosdi.org
 */
@Getter
@Setter
@NoArgsConstructor
@ToString
@XmlRootElement(name = "Attribute")
@XmlAccessorType(XmlAccessType.FIELD)
public class Attribute implements Serializable {

    private static final long serialVersionUID = -5201486007919812553L;
    //
    @XmlElement(name = "name")
    private String attributeName;
    @XmlElement(name = "type")
    private String attributeType;
}
