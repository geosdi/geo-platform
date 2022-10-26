package org.geosdi.geoplatform.connector.geowebcache.model.seed;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import org.geosdi.geoplatform.connector.geowebcache.model.entry.IGeowebcacheParameterEntry;
import org.geosdi.geoplatform.connector.geowebcache.model.seed.operation.IGeowebcacheSeedOperationType;
import org.geosdi.geoplatform.connector.geowebcache.model.srs.IGeowebcacheSrsBean;

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
public interface IGeowebcacheSeedRequestValue extends Serializable {

    /**
     * @return {@link String}
     */
    String getName();

    /**
     * @param theName
     */
    void setName(String theName);

    /**
     * @return {@link IGeowebcacheSrsBean}
     */
    <Srs extends IGeowebcacheSrsBean> Srs getSrs();

    /**
     * @param theSrs
     */
    <Srs extends IGeowebcacheSrsBean> void setSrs(Srs theSrs);

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
    <SeedOperationType extends IGeowebcacheSeedOperationType> SeedOperationType getType();

    /**
     * @param theType
     * @param <SeedOperationType>
     */
    <SeedOperationType extends IGeowebcacheSeedOperationType> void setType(SeedOperationType theType);

    /**
     * @return {@link Integer}
     */
    Integer getThreadCount();

    /**
     * @param theThreadCount
     */
    void setThreadCount(Integer theThreadCount);

    /**
     * @return {@link Map<String,  IGeowebcacheParameterEntry >}
     */
    Map<String, List<IGeowebcacheParameterEntry>> getParameters();

    /**
     * @param theParameters
     */
    void setParameters(Map<String, List<IGeowebcacheParameterEntry>> theParameters);

    /**
     * @param param
     * @throws Exception
     */
    void addParameter(@Nonnull(when = NEVER) IGeowebcacheParameterEntry... param) throws Exception;
}