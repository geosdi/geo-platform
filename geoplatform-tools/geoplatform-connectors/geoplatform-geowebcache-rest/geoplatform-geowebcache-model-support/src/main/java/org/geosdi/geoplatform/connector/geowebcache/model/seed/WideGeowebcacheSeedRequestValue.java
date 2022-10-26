package org.geosdi.geoplatform.connector.geowebcache.model.seed;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import org.geosdi.geoplatform.connector.geowebcache.model.entry.WideGeowebcacheParameterEntry;
import org.geosdi.geoplatform.connector.geowebcache.model.seed.operation.WideGeowebcacheSeedOperationType;
import org.geosdi.geoplatform.connector.geowebcache.model.srs.WideGeowebcacheSrsBean;

import javax.annotation.Nonnull;
import java.io.Serializable;
import java.util.List;
import java.util.Map;

import static javax.annotation.meta.When.NEVER;

/**
 * @author Giuseppe La Scaleia - CNR IMAA geoSDI Group
 * @email giuseppe.lascaleia@geosdi.org
 */
@JsonDeserialize(as = GeowebcacheSeedRequestValue.class)
public interface WideGeowebcacheSeedRequestValue extends Serializable {

    /**
     * @return {@link String}
     */
    String getName();

    /**
     * @param theName
     */
    void setName(String theName);

    /**
     * @return {@link WideGeowebcacheSrsBean}
     */
    <Srs extends WideGeowebcacheSrsBean> Srs getSrs();

    /**
     * @param theSrs
     */
    <Srs extends WideGeowebcacheSrsBean> void setSrs(Srs theSrs);

    /**
     * @return {@link Integer}
     */
    Integer getZoomStart();

    /**
     * @param theZoomStart
     */
    void setZoomStart(Integer theZoomStart);

    /**
     * @return {@link Integer}
     */
    Integer getZoomStop();

    /**
     * @param theZoomStop
     */
    void setZoomStop(Integer theZoomStop);

    /**
     * @return {@link String}
     */
    String getFormat();

    /**
     * @param theFormat
     */
    void setFormat(String theFormat);

    /**
     * @param <SeedOperationType>
     * @return {@link SeedOperationType}
     */
    <SeedOperationType extends WideGeowebcacheSeedOperationType> SeedOperationType getType();

    /**
     * @param theType
     * @param <SeedOperationType>
     */
    <SeedOperationType extends WideGeowebcacheSeedOperationType> void setType(SeedOperationType theType);

    /**
     * @return {@link Integer}
     */
    Integer getThreadCount();

    /**
     * @param theThreadCount
     */
    void setThreadCount(Integer theThreadCount);

    /**
     * @return {@link Map<String, WideGeowebcacheParameterEntry>}
     */
    Map<String, List<WideGeowebcacheParameterEntry>> getParameters();

    /**
     * @param theParameters
     */
    void setParameters(Map<String, List<WideGeowebcacheParameterEntry>> theParameters);

    /**
     * @param param
     * @throws Exception
     */
    void addParameter(@Nonnull(when = NEVER) WideGeowebcacheParameterEntry... param) throws Exception;
}