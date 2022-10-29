package org.geosdi.geoplatform.connector.geowebcache.model.seed;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import org.geosdi.geoplatform.connector.geowebcache.model.seed.bounds.GPGeowebcacheBoundsBean;
import org.geosdi.geoplatform.connector.geowebcache.model.seed.entry.entry.GPGeowebcacheEntryValue;
import org.geosdi.geoplatform.connector.geowebcache.model.seed.operation.GPGeowebcacheSeedOperationType;
import org.geosdi.geoplatform.connector.geowebcache.model.seed.srs.GPGeowebcacheSrsBean;

import javax.annotation.Nonnull;
import java.io.Serializable;
import java.util.List;
import java.util.Map;

import static javax.annotation.meta.When.NEVER;

/**
 * @author Giuseppe La Scaleia - CNR IMAA geoSDI Group
 * @email giuseppe.lascaleia@geosdi.org
 */
@JsonDeserialize(as = GeowebcacheSeedBody.class)
public interface GPGeowebcacheSeedBody extends Serializable {

    /**
     * @return {@link String}
     */
    String getName();

    /**
     * @param theName
     */
    void setName(String theName);

    /**
     * @param theGridSetId
     */
    void setGridSetId(String theGridSetId);

    /**
     * @return {@link String}
     */
    String getGridSetId();

    /**
     * @return {@link GPGeowebcacheSrsBean}
     */
    <Srs extends GPGeowebcacheSrsBean> Srs getSrs();

    /**
     * @param theSrs
     */
    <Srs extends GPGeowebcacheSrsBean> void setSrs(Srs theSrs);

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
    <SeedOperationType extends GPGeowebcacheSeedOperationType> SeedOperationType getType();

    /**
     * @param theType
     * @param <SeedOperationType>
     */
    <SeedOperationType extends GPGeowebcacheSeedOperationType> void setType(SeedOperationType theType);

    /**
     * @param bounds
     */
    <Bounds extends GPGeowebcacheBoundsBean>void setBounds(Bounds bounds);

    /**
     *
     * @return {@link  Bounds}
     * @param <Bounds>
     */
    <Bounds extends GPGeowebcacheBoundsBean>Bounds getBounds();

    /**
     * @return {@link Integer}
     */
    Integer getThreadCount();

    /**
     * @param theThreadCount
     */
    void setThreadCount(Integer theThreadCount);

    /**
     * @return {@link Map<String, GPGeowebcacheEntryValue>}
     */
    Map<String, List<GPGeowebcacheEntryValue>> getParameters();

    /**
     * @param theParameters
     */
    void setParameters(Map<String, List<GPGeowebcacheEntryValue>> theParameters);

    /**
     * @param param
     * @throws Exception
     */
    void addParameter(@Nonnull(when = NEVER) GPGeowebcacheEntryValue... param) throws Exception;
}