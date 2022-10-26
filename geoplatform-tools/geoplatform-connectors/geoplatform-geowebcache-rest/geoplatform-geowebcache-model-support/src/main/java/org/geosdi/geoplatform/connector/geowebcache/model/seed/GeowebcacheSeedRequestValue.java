package org.geosdi.geoplatform.connector.geowebcache.model.seed;

import com.google.common.collect.Maps;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.geosdi.geoplatform.connector.geowebcache.model.entry.IGeowebcacheParameterEntry;
import org.geosdi.geoplatform.connector.geowebcache.model.seed.operation.GeowebcacheSeedOperationType;
import org.geosdi.geoplatform.connector.geowebcache.model.seed.operation.IGeowebcacheSeedOperationType;
import org.geosdi.geoplatform.connector.geowebcache.model.srs.GeowebcacheSrsBean;
import org.geosdi.geoplatform.connector.geowebcache.model.srs.IGeowebcacheSrsBean;

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
public class GeowebcacheSeedRequestValue implements IGeowebcacheSeedRequestValue {

    private static final long serialVersionUID = 5970342361220683441L;
    //
    private String name;
    @XmlElement(name = "srs", type = GeowebcacheSrsBean.class)
    private IGeowebcacheSrsBean srs;
    private Integer zoomStart;
    private Integer zoomStop;
    private String format;
    @XmlElement(name = "type", type = GeowebcacheSeedOperationType.class)
    private IGeowebcacheSeedOperationType type;
    private Integer threadCount;
    private Map<String, List<IGeowebcacheParameterEntry>> parameters = Maps.newHashMap();

    /**
     * @param param
     * @throws Exception
     */
    @Override
    public void addParameter(@Nonnull(when = NEVER) IGeowebcacheParameterEntry... param) throws Exception {
        checkArgument(param != null, "The Parameter param must not be null.");
        this.parameters.put("entry", asList(param));
    }
}