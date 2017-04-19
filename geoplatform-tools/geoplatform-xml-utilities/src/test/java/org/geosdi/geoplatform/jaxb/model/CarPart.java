package org.geosdi.geoplatform.jaxb.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
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
@XmlRootElement(name = "CarPart")
@XmlAccessorType(XmlAccessType.FIELD)
public class CarPart implements Serializable {

    private static final long serialVersionUID = -4820968017757045937L;
    //
    private String partName;
}

