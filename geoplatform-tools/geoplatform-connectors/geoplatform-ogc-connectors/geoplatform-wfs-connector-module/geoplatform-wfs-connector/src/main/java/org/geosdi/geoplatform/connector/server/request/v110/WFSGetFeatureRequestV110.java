/**
 *
 *    geo-platform
 *    Rich webgis framework
 *    http://geo-platform.org
 *   ====================================================================
 *
 *   Copyright (C) 2008-2021 geoSDI Group (CNR IMAA - Potenza - ITALY).
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
package org.geosdi.geoplatform.connector.server.request.v110;

import org.geosdi.geoplatform.connector.server.GPServerConnector;
import org.geosdi.geoplatform.connector.server.request.AbstractGetFeatureRequest;
import org.geosdi.geoplatform.gui.shared.bean.BBox;
import org.geosdi.geoplatform.xml.filter.v110.BBOXType;
import org.geosdi.geoplatform.xml.filter.v110.FilterType;
import org.geosdi.geoplatform.xml.filter.v110.GmlObjectIdType;
import org.geosdi.geoplatform.xml.filter.v110.PropertyNameType;
import org.geosdi.geoplatform.xml.gml.v311.DirectPositionType;
import org.geosdi.geoplatform.xml.gml.v311.EnvelopeType;
import org.geosdi.geoplatform.xml.wfs.v110.FeatureCollectionType;
import org.geosdi.geoplatform.xml.wfs.v110.GetFeatureType;
import org.geosdi.geoplatform.xml.wfs.v110.QueryType;

import javax.annotation.Nonnull;
import javax.xml.bind.JAXBElement;

import static com.google.common.base.Preconditions.checkArgument;
import static io.reactivex.rxjava3.core.Observable.fromIterable;
import static java.util.Arrays.asList;
import static java.util.stream.Collectors.toList;
import static javax.annotation.meta.When.NEVER;
import static org.geosdi.geoplatform.connector.server.request.v110.query.responsibility.ILogicOperatorHandler.WFSQueryRestrictionsBuilder.builder;
import static org.geosdi.geoplatform.xml.wfs.v110.ResultTypeType.RESULTS;
import static org.geosdi.geoplatform.xml.wfs.v110.ResultTypeType.fromValue;

/**
 * @author Giuseppe La Scaleia - <giuseppe.lascaleia@geosdi.org>
 * @author Vincenzo Monteverde <vincenzo.monteverde@geosdi.org>
 */
public class WFSGetFeatureRequestV110 extends AbstractGetFeatureRequest<FeatureCollectionType, GetFeatureType> {

    private static final org.geosdi.geoplatform.xml.filter.v110.ObjectFactory filterFactory = new org.geosdi.geoplatform.xml.filter.v110.ObjectFactory();
    private static final org.geosdi.geoplatform.xml.gml.v311.ObjectFactory gmlFactory = new org.geosdi.geoplatform.xml.gml.v311.ObjectFactory();

    /**
     * @param server
     */
    public WFSGetFeatureRequestV110(@Nonnull(when = NEVER) GPServerConnector server) {
        super(server);
    }

    /**
     * @return {@link GetFeatureType}
     * @throws Exception
     */
    @Override
    protected GetFeatureType createRequest() throws Exception {
        checkArgument(this.typeName != null, "The Parameter typeName must not be null.");
        GetFeatureType request = new GetFeatureType();
        QueryType query = new QueryType();
        query.setTypeName(asList(typeName));
        request.getQuery().add(query);
        if (super.isSetFeatureIDs()) {
            FilterType filter = new FilterType();
            filter.setId(featureIDs
                    .stream()
                    .filter(featureID -> (featureID != null) && !(featureID.trim().isEmpty()))
                    .map(featureID -> filterFactory.createGmlObjectId(new GmlObjectIdType(featureID)))
                    .collect(toList()));
            query.setFilter(filter);
        }
        if (super.isSetPropertyNames()) {
            fromIterable(this.propertyNames)
                    .filter(v -> ((v != null) && !(v.trim().isEmpty())))
                    .doOnComplete(() -> logger.debug("################### propertyNames processed."))
                    .subscribe(v -> query.getPropertyNameOrXlinkPropertyNameOrFunction().add(v), e -> e.printStackTrace());
        }
        if (srs != null) {
            query.setSrsName(srs);
        }
        if (bBox != null) {
            FilterType filter = query.getFilter();
            if (filter == null) {
                filter = new FilterType();
                query.setFilter(filter);
            }
            filter.setSpatialOps(this.createAreaOperator(bBox));
        }
        if (super.isSetQueryDTO()) {
            FilterType filterType = query.getFilter();
            if (filterType == null) {
                filterType = new FilterType();
                query.setFilter(filterType);
            }
            builder().withFilterType(filterType).withQueryDTO(queryDTO).build();
        }
        request.setResultType(resultType != null ? fromValue(resultType) : RESULTS);
        request.setOutputFormat(outputFormat != null ? outputFormat : "text/xml; subtype=gml/3.1.1");
        if (maxFeatures != null) {
            request.setMaxFeatures(maxFeatures);
        }
        return request;
    }

    /**
     * @param bbox
     * @return {@link JAXBElement<BBOXType>}
     */
    private JAXBElement<BBOXType> createAreaOperator(BBox bbox) {
        logger.debug("#######################BBOX : {}\n.", bbox);
        BBOXType bBoxType = new BBOXType();
        PropertyNameType propertyNameType = new PropertyNameType();
        propertyNameType.setContent(asList(super.getGeometryName()));
        bBoxType.setPropertyName(propertyNameType);
        EnvelopeType envelope = this.createEnvelope(bbox);
        if (srs != null) {
            envelope.setSrsName(srs);
        }
        bBoxType.setEnvelope(gmlFactory.createEnvelope(envelope));
        return filterFactory.createBBOX(bBoxType);
    }

    /**
     * @param bbox
     * @return {@link EnvelopeType}
     */
    private EnvelopeType createEnvelope(BBox bbox) {
        EnvelopeType envelope = new EnvelopeType();
        DirectPositionType lower = new DirectPositionType();
        lower.setValue(asList(bbox.getMinX(), bbox.getMinY()));
        envelope.setLowerCorner(lower);
        DirectPositionType upper = new DirectPositionType();
        upper.setValue(asList(bbox.getMaxX(), bbox.getMaxY()));
        envelope.setUpperCorner(upper);
        return envelope;
    }
}
