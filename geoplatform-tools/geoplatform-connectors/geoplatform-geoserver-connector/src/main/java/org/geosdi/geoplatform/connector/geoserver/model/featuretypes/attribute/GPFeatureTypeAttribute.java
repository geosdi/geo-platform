package org.geosdi.geoplatform.connector.geoserver.model.featuretypes.attribute;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;

/**
 * @author Giuseppe La Scaleia - CNR IMAA geoSDI Group
 * @email giuseppe.lascaleia@geosdi.org
 */
@Getter
@Setter
@ToString
@XmlAccessorType(XmlAccessType.FIELD)
public class GPFeatureTypeAttribute implements IGPFeatureTypeAttribute {

    private static final long serialVersionUID = -8984590320238278430L;
    //
    private String name;
    private Integer minOccurs;
    private Integer maxOccurs;
    private boolean nillable;
    private String binding;
}