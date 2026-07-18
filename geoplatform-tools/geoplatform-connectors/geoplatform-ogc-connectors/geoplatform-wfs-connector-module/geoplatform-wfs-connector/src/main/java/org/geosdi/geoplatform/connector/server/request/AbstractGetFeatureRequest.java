/*
 *
 *    geo-platform
 *    Rich webgis framework
 *    http://geo-platform.org
 *   ====================================================================
 *
 *   Copyright (C) 2008-2026 geoSDI Group (CNR IMAA - Potenza - ITALY).
 *
 *   This program is free software: you can redistribute it and/or modify it
 *   under the terms of the GNU General Public License as published by
 *   the Free Software Foundation, either version 3 of the License, or
 *   (at your option) any later version. This program is distributed in the
 *   hope that it will be useful, but WITHOUT ANY WARRANTY; without
 *   even the implied warranty of MERCHANTABILITY or FITNESS FOR
 *   A PARTICULAR PURPOSE. See the GNU General Public License
 *   for more details. You should have received a copy of the GNU General
 *   Public License along with this program. If not, see http://www.gnu.org/licenses/
 *
 *   ====================================================================
 *
 *   Linking this library statically or dynamically with other modules is
 *   making a combined work based on this library. Thus, the terms and
 *   conditions of the GNU General Public License cover the whole combination.
 *
 *   As a special exception, the copyright holders of this library give you permission
 *   to link this library with independent modules to produce an executable, regardless
 *   of the license terms of these independent modules, and to copy and distribute
 *   the resulting executable under terms of your choice, provided that you also meet,
 *   for each linked independent module, the terms and conditions of the license of
 *   that module. An independent module is a module which is not derived from or
 *   based on this library. If you modify this library, you may extend this exception
 *   to your version of the library, but you are not obligated to do so. If you do not
 *   wish to do so, delete this exception statement from your version.
 */
package org.geosdi.geoplatform.connector.server.request;

import net.jcip.annotations.ThreadSafe;
import org.geosdi.geoplatform.connector.server.GPServerConnector;
import org.geosdi.geoplatform.connector.wfs.response.QueryDTO;
import org.geosdi.geoplatform.gui.shared.bean.BBox;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import javax.xml.namespace.QName;
import java.math.BigInteger;
import java.util.List;

import static java.lang.ThreadLocal.withInitial;
import static javax.annotation.meta.When.NEVER;

/**
 * Thread-safe base for the WFS {@code GetFeature} request. The configuration set through the fluent
 * {@code withXxx(...)} mutators of {@link WFSGetFeatureRequest} is kept in {@link ThreadLocal} state, so a
 * single shared instance can be configured concurrently from many threads without cross-thread clobbering;
 * the read side consumed by the {@code v110.param} classes is exposed via {@link WFSGetFeatureRequestState}.
 *
 * @author Vincenzo Monteverde <vincenzo.monteverde@geosdi.org>
 */
@ThreadSafe
public abstract class AbstractGetFeatureRequest<T, Request> extends WFSRequest<T, Request> implements WFSGetFeatureRequest<T>, WFSGetFeatureRequestState {

    private static final String DEFAULT_GEOMETRY_NAME = "the_geom";
    private static final String DEFAULT_SRS = "EPSG:4326";
    //
    protected final ThreadLocal<QName> typeName;
    protected final ThreadLocal<List<String>> featureIDs;
    protected final ThreadLocal<List<String>> propertyNames;
    protected final ThreadLocal<BBox> bBox;
    protected final ThreadLocal<String> srs;
    protected final ThreadLocal<String> resultType;
    protected final ThreadLocal<GPWFSGetFeatureOutputFormat> outputFormat;
    protected final ThreadLocal<BigInteger> maxFeatures;
    protected final ThreadLocal<QueryDTO> queryDTO;
    protected final ThreadLocal<String> cqlFilter;
    protected final ThreadLocal<String> geometryName;

    /**
     * @param server
     */
    public AbstractGetFeatureRequest(GPServerConnector server) {
        super(server);
        this.typeName = withInitial(() -> null);
        this.featureIDs = withInitial(() -> null);
        this.propertyNames = withInitial(() -> null);
        this.bBox = withInitial(() -> null);
        this.srs = withInitial(() -> null);
        this.resultType = withInitial(() -> null);
        this.outputFormat = withInitial(() -> null);
        this.maxFeatures = withInitial(() -> null);
        this.queryDTO = withInitial(() -> null);
        this.cqlFilter = withInitial(() -> null);
        this.geometryName = withInitial(() -> null);
    }

    /**
     * @param theTypeName
     * @return {@link WFSGetFeatureRequest<T>}
     */
    @Override
    public WFSGetFeatureRequest<T> withTypeName(@Nonnull(when = NEVER) QName theTypeName) {
        this.typeName.set(theTypeName);
        return self();
    }

    /**
     * @param theFeatureIDs
     * @return {@link WFSGetFeatureRequest<T>}
     */
    @Override
    public WFSGetFeatureRequest<T> withFeatureIDs(@Nullable List<String> theFeatureIDs) {
        this.featureIDs.set(theFeatureIDs);
        return self();
    }

    /**
     * @param thePropertyNames
     * @return {@link WFSGetFeatureRequest<T>}
     */
    @Override
    public WFSGetFeatureRequest<T> withPropertyNames(@Nullable List<String> thePropertyNames) {
        this.propertyNames.set(thePropertyNames);
        return self();
    }

    /**
     * @param theBBox
     * @return {@link WFSGetFeatureRequest<T>}
     */
    @Override
    public WFSGetFeatureRequest<T> withBBox(@Nullable BBox theBBox) {
        this.bBox.set(theBBox);
        return self();
    }

    /**
     * @param theGeometryName
     * @return {@link WFSGetFeatureRequest<T>}
     */
    @Override
    public WFSGetFeatureRequest<T> withGeometryName(@Nullable String theGeometryName) {
        this.geometryName.set(theGeometryName);
        return self();
    }

    /**
     * @param theSRS
     * @return {@link WFSGetFeatureRequest<T>}
     */
    @Override
    public WFSGetFeatureRequest<T> withSRS(@Nullable String theSRS) {
        this.srs.set(theSRS);
        return self();
    }

    /**
     * @param theResultType
     * @return {@link WFSGetFeatureRequest<T>}
     */
    @Override
    public WFSGetFeatureRequest<T> withResultType(@Nullable String theResultType) {
        this.resultType.set(theResultType);
        return self();
    }

    /**
     * @param theOutputFormat
     * @return {@link WFSGetFeatureRequest<T>}
     */
    @Override
    public WFSGetFeatureRequest<T> withOutputFormat(@Nullable GPWFSGetFeatureOutputFormat theOutputFormat) {
        this.outputFormat.set(theOutputFormat);
        return self();
    }

    /**
     * @param theMaxFeatures
     * @return {@link WFSGetFeatureRequest<T>}
     */
    @Override
    public WFSGetFeatureRequest<T> withMaxFeatures(@Nullable BigInteger theMaxFeatures) {
        this.maxFeatures.set(theMaxFeatures);
        return self();
    }

    /**
     * @param theQueryDTO
     * @return {@link WFSGetFeatureRequest<T>}
     */
    @Override
    public WFSGetFeatureRequest<T> withQueryDTO(@Nullable QueryDTO theQueryDTO) {
        this.queryDTO.set(theQueryDTO);
        return self();
    }

    /**
     * @param theCqlFilter
     * @return {@link WFSGetFeatureRequest<T>}
     */
    @Override
    public WFSGetFeatureRequest<T> withCqlFilter(@Nullable String theCqlFilter) {
        this.cqlFilter.set(theCqlFilter);
        return self();
    }

    /**
     * @return the value of the type name query property.
     */
    @Override
    public QName getTypeName() {
        return this.typeName.get();
    }

    /**
     * @return the value of the feature ID query property.
     */
    @Override
    public List<String> getFeatureIDs() {
        return this.featureIDs.get();
    }

    /**
     * @return {@link Boolean}
     */
    @Override
    public Boolean isSetFeatureIDs() {
        List<String> theFeatureIDs = this.featureIDs.get();
        return ((theFeatureIDs != null) && !(theFeatureIDs.isEmpty()));
    }

    /**
     * @return the Property Names to retrieve.
     */
    @Override
    public List<String> getPropertyNames() {
        return this.propertyNames.get();
    }

    /**
     * @return {@link Boolean}
     */
    @Override
    public Boolean isSetPropertyNames() {
        List<String> thePropertyNames = this.propertyNames.get();
        return ((thePropertyNames != null) && !(thePropertyNames.isEmpty()));
    }

    /**
     * @return the value of the BBox query property.
     */
    @Override
    public BBox getBBox() {
        return this.bBox.get();
    }

    /**
     * @return the value of the SRS query property.
     */
    @Override
    public String getSRS() {
        String theSRS = this.srs.get();
        if ((theSRS == null) || (theSRS.trim().isEmpty())) {
            theSRS = DEFAULT_SRS;
            this.srs.set(theSRS);
        }
        return theSRS;
    }

    /**
     * @return {@link String}
     */
    @Override
    public String getGeometryName() {
        return (isSetGeometryName() ? this.geometryName.get() : DEFAULT_GEOMETRY_NAME);
    }

    /**
     * @return {@link Boolean}
     */
    @Override
    public Boolean isSetGeometryName() {
        String theGeometryName = this.geometryName.get();
        return ((theGeometryName != null) && !(theGeometryName.trim().isEmpty()));
    }

    /**
     * @return the value of the resultType property.
     */
    @Override
    public String getResultType() {
        return this.resultType.get();
    }

    /**
     * @return the value of the outputFormat property.
     */
    @Override
    public GPWFSGetFeatureOutputFormat getOutputFormat() {
        return this.outputFormat.get();
    }

    /**
     * @return the value of the maxFeatures property.
     */
    @Override
    public BigInteger getMaxFeatures() {
        return this.maxFeatures.get();
    }

    /**
     * @return {@link QueryDTO}
     */
    @Override
    public QueryDTO getQueryDTO() {
        return this.queryDTO.get();
    }

    /**
     * @return {@link Boolean}
     */
    @Override
    public Boolean isSetQueryDTO() {
        QueryDTO theQueryDTO = this.queryDTO.get();
        return ((theQueryDTO != null) && (theQueryDTO.isSetQueryRestrictionList()));
    }

    /**
     * @return {@link String}
     */
    @Override
    public String getCqlFilter() {
        return this.cqlFilter.get();
    }

    /**
     * @return {@link Boolean}
     */
    @Override
    public Boolean isSetCqlFilter() {
        String theCqlFilter = this.cqlFilter.get();
        return ((theCqlFilter != null) && !(theCqlFilter.trim().isEmpty()));
    }

    /**
     * @return {@link WFSGetFeatureRequest<T>}
     */
    protected abstract WFSGetFeatureRequest<T> self();

    /**
     * Opt-in release of the per-thread configuration held in the {@code ThreadLocal} state.
     */
    @Override
    public void clearState() {
        this.typeName.remove();
        this.featureIDs.remove();
        this.propertyNames.remove();
        this.bBox.remove();
        this.srs.remove();
        this.resultType.remove();
        this.outputFormat.remove();
        this.maxFeatures.remove();
        this.queryDTO.remove();
        this.cqlFilter.remove();
        this.geometryName.remove();
    }

    @Override
    public String toString() {
        return this.getClass().getSimpleName()
                + " {"
                + "  typeName = " + this.typeName.get()
                + ", featureIDs = " + this.featureIDs.get()
                + ", bBox = " + this.bBox.get()
                + ", srs = " + this.srs.get()
                + ", resultType = " + this.resultType.get()
                + ", outputFormat = " + this.outputFormat.get()
                + ", maxFeatures = " + this.maxFeatures.get()
                + ", cqlFilter = " + this.cqlFilter.get()
                + ", queryDTO = " + this.queryDTO.get() + '}';
    }
}
