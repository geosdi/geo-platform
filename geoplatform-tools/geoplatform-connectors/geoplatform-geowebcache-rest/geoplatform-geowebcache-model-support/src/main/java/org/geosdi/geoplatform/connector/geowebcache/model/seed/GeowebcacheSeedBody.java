package org.geosdi.geoplatform.connector.geowebcache.model.seed;

import com.google.common.collect.Maps;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.geosdi.geoplatform.connector.geowebcache.model.seed.bounds.GPGeowebcacheBoundsBean;
import org.geosdi.geoplatform.connector.geowebcache.model.seed.bounds.GeowebcacheBoundsBean;
import org.geosdi.geoplatform.connector.geowebcache.model.seed.entry.entry.GPGeowebcacheEntryValue;
import org.geosdi.geoplatform.connector.geowebcache.model.seed.operation.GPGeowebcacheSeedOperationType;
import org.geosdi.geoplatform.connector.geowebcache.model.seed.operation.GeowebcacheSeedOperationType;
import org.geosdi.geoplatform.connector.geowebcache.model.seed.srs.GPGeowebcacheSrsBean;
import org.geosdi.geoplatform.connector.geowebcache.model.seed.srs.GeowebcacheSrsBean;

import javax.annotation.Nonnull;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.List;
import java.util.Map;

import static com.google.common.base.Preconditions.checkArgument;
import static java.util.Arrays.asList;
import static javax.annotation.meta.When.NEVER;
import static javax.xml.bind.annotation.XmlAccessType.FIELD;

/**
 * @author Giuseppe La Scaleia - CNR IMAA geoSDI Group
 * @email giuseppe.lascaleia@geosdi.org
 */
@Getter
@Setter
@ToString
@XmlAccessorType(value = FIELD)
@XmlRootElement(name = "seedRequest")
public class GeowebcacheSeedBody implements GPGeowebcacheSeedBody {

    private static final long serialVersionUID = 5970342361220683441L;
    //
    private String name;
    private String gridSetId;
    @XmlElement(name = "srs", type = GeowebcacheSrsBean.class)
    private GPGeowebcacheSrsBean srs;
    private Integer zoomStart;
    private Integer zoomStop;
    private String format;
    @XmlElement(name = "type", type = GeowebcacheSeedOperationType.class)
    private GPGeowebcacheSeedOperationType type;
    @XmlElement(name = "bounds", type = GeowebcacheBoundsBean.class)
    private GPGeowebcacheBoundsBean bounds;
    private Integer threadCount;
    @XmlElement(name = "parameters")
    private Map<String, List<GPGeowebcacheEntryValue>> parameters = Maps.newHashMap();

    /**
     * @param param
     * @throws Exception
     */
    @Override
    public void addParameter(@Nonnull(when = NEVER) GPGeowebcacheEntryValue... param) throws Exception {
        checkArgument(param != null, "The Parameter param must not be null.");
        this.parameters.put("entry", asList(param));
    }
}