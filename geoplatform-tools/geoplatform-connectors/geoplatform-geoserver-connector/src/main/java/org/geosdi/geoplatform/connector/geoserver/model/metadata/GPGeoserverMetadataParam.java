package org.geosdi.geoplatform.connector.geoserver.model.metadata;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;

import static javax.xml.bind.annotation.XmlAccessType.PROPERTY;

/**
 * @author Giuseppe La Scaleia - CNR IMAA geoSDI Group
 * @email giuseppe.lascaleia@geosdi.org
 */
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@XmlAccessorType(PROPERTY)
public class GPGeoserverMetadataParam implements IGPGeoserverMetadataParam {

    private static final long serialVersionUID = 8387455761826998501L;
    //
    private String key;
    private String value;

    /**
     * @return {@link String}
     */
    @XmlElement(name = "@key")
    @Override
    public String getKey() {
        return this.key;
    }

    /**
     * @return {@link String}
     */
    @XmlElement(name = "$")
    @Override
    public String getValue() {
        return this.value;
    }
}