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
@XmlRootElement(name = "AttributeStore")
@XmlAccessorType(XmlAccessType.FIELD)
public class AttributeStore implements Serializable {

    private static final long serialVersionUID = -2601597403686322914L;
    //
    @XmlElementWrapper(name = "attributes")
    @XmlElement(name = "attribute")
    private List<Attribute> attributes;
}
