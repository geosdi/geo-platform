package org.geosdi.geoplatform.connector.geowebcache.model.seed.entry.coordinates;

import com.google.common.collect.Lists;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.geosdi.geoplatform.connector.geowebcache.model.seed.entry.coordinates.GPGeowebcacheCoordinatesEntry;

import javax.xml.bind.annotation.XmlElement;
import java.util.List;

import static java.util.Arrays.asList;

/**
 * @author Giuseppe La Scaleia - CNR IMAA geoSDI Group
 * @email giuseppe.lascaleia@geosdi.org
 */
@ToString
@Getter
@Setter
public class GeowebcacheCoordinatesEntry implements GPGeowebcacheCoordinatesEntry {

    private static final long serialVersionUID = -8741112827617099689L;
    //
    @XmlElement(name = "double")
    private List<Double> numbers = Lists.newArrayList();

    /**
     * @param numbers
     */
    public void addNumbers(Double... numbers) {
        this.numbers = asList(numbers);
    }
}
