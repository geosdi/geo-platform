package org.geosdi.geoplatform.connector.geowebcache.model.seed;

import com.google.common.collect.Maps;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.geosdi.geoplatform.connector.geowebcache.model.entry.WideGeowebcacheParameterEntry;
import org.geosdi.geoplatform.connector.geowebcache.model.seed.operation.GeowebcacheSeedOperationType;
import org.geosdi.geoplatform.connector.geowebcache.model.seed.operation.WideGeowebcacheSeedOperationType;
import org.geosdi.geoplatform.connector.geowebcache.model.srs.GeowebcacheSrsBean;
import org.geosdi.geoplatform.connector.geowebcache.model.srs.WideGeowebcacheSrsBean;

import javax.annotation.Nonnull;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
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
public class GeowebcacheSeedRequestValue implements WideGeowebcacheSeedRequestValue {

    private static final long serialVersionUID = 5970342361220683441L;
    //
    private String name;
    @XmlElement(name = "srs", type = GeowebcacheSrsBean.class)
    private WideGeowebcacheSrsBean srs;
    private Integer zoomStart;
    private Integer zoomStop;
    private String format;
    @XmlElement(name = "type", type = GeowebcacheSeedOperationType.class)
    private WideGeowebcacheSeedOperationType type;
    private Integer threadCount;
    private Map<String, List<WideGeowebcacheParameterEntry>> parameters = Maps.newHashMap();

    /**
     * @param param
     * @throws Exception
     */
    @Override
    public void addParameter(@Nonnull(when = NEVER) WideGeowebcacheParameterEntry... param) throws Exception {
        checkArgument(param != null, "The Parameter param must not be null.");
        this.parameters.put("entry", asList(param));
    }
}