package org.geosdi.geoplatform.connector.geoserver.model.featuretypes.attribute;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import java.util.List;

/**
 * @author Giuseppe La Scaleia - CNR IMAA geoSDI Group
 * @email giuseppe.lascaleia@geosdi.org
 */
@Setter
@Getter
@ToString
@XmlAccessorType(XmlAccessType.FIELD)
public class GPFeatureTypeAttributes implements IGPFeatureTypeAttributes {

    private static final long serialVersionUID = 4960738926410818073L;
    //
    @XmlElement(name = "attribute")
    private List<IGPFeatureTypeAttribute> values;
}