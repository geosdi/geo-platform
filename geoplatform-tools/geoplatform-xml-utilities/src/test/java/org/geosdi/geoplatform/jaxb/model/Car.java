package org.geosdi.geoplatform.jaxb.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import javax.xml.bind.annotation.*;
import java.io.Serializable;
import java.util.List;

/**
 * @author Giuseppe La Scaleia - CNR IMAA geoSDI Group
 * @email giuseppe.lascaleia@geosdi.org
 */
@Getter
@Setter
@NoArgsConstructor
@ToString
@XmlRootElement(name = "Car")
@XmlAccessorType(XmlAccessType.FIELD)
public class Car implements Serializable {

    private static final long serialVersionUID = -3674922047430621165L;
    //
    private String plat;
    private String model;
    @XmlElementWrapper(name = "carParts")
    @XmlElement(name = "carPart")
    private List<CarPart> carParts;
}
