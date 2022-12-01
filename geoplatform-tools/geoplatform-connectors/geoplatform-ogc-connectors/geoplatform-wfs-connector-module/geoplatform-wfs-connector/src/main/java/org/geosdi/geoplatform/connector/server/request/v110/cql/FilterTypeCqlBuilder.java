/**
 * geo-platform
 * Rich webgis framework
 * http://geo-platform.org
 * ====================================================================
 * <p>
 * Copyright (C) 2008-2022 geoSDI Group (CNR IMAA - Potenza - ITALY).
 * <p>
 * This program is free software: you can redistribute it and/or modify it
 * under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version. This program is distributed in the
 * hope that it will be useful, but WITHOUT ANY WARRANTY; without
 * even the implied warranty of MERCHANTABILITY or FITNESS FOR
 * A PARTICULAR PURPOSE. See the GNU General Public License
 * for more details. You should have received a copy of the GNU General
 * Public License along with this program. If not, see http://www.gnu.org/licenses/
 * <p>
 * ====================================================================
 * <p>
 * Linking this library statically or dynamically with other modules is
 * making a combined work based on this library. Thus, the terms and
 * conditions of the GNU General Public License cover the whole combination.
 * <p>
 * As a special exception, the copyright holders of this library give you permission
 * to link this library with independent modules to produce an executable, regardless
 * of the license terms of these independent modules, and to copy and distribute
 * the resulting executable under terms of your choice, provided that you also meet,
 * for each linked independent module, the terms and conditions of the license of
 * that module. An independent module is a module which is not derived from or
 * based on this library. If you modify this library, you may extend this exception
 * to your version of the library, but you are not obligated to do so. If you do not
 * wish to do so, delete this exception statement from your version.
 */
package org.geosdi.geoplatform.connector.server.request.v110.cql;

import org.geosdi.geoplatform.jaxb.pool.GPJAXBContextBuilderPool;
import org.geosdi.geoplatform.xml.filter.v110.FilterType;
import org.geotools.filter.text.cql2.CQL;
import org.geotools.xml.Configuration;
import org.geotools.xml.Encoder;
import org.opengis.filter.Filter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.annotation.Nullable;
import java.io.ByteArrayOutputStream;
import java.io.StringReader;

import static org.geosdi.geoplatform.jaxb.pool.GPJAXBContextBuilderPool.jaxbContextBuilderPool;
import static org.geotools.factory.CommonFactoryFinder.getFilterFactory2;
import static org.geotools.factory.GeoTools.getDefaultHints;

/**
 * @author Giuseppe La Scaleia - CNR IMAA geoSDI Group
 * @email giuseppe.lascaleia@geosdi.org
 */
class FilterTypeCqlBuilder implements GPFilterTypeCqlBuilder {

    private static final Logger logger = LoggerFactory.getLogger(FilterTypeCqlBuilder.class);
    //
    private static final GPJAXBContextBuilderPool jaxbContextBuilder = jaxbContextBuilderPool();
    //
    private String cqlFilter;

    FilterTypeCqlBuilder() {
    }

    /**
     * @param theCqlFilter
     * @return {@link GPFilterTypeCqlBuilder}
     */
    @Override
    public GPFilterTypeCqlBuilder withCqlFilter(@Nullable String theCqlFilter) {
        this.cqlFilter = theCqlFilter;
        return this;
    }

    /**
     * @return {@link FilterType}
     */
    @Override
    public FilterType build() {
        return (((this.cqlFilter != null) && !(this.cqlFilter.trim().isEmpty())) ? this.internalBuild() : new FilterType());
    }

    /**
     * @return {@link FilterType}
     */
    protected final FilterType internalBuild() {
        try {
            Filter filter = CQL.toFilter(this.cqlFilter, getFilterFactory2(getDefaultHints()));
            Configuration configuration = new org.geotools.filter.v1_0.OGCConfiguration();
            Encoder encoder = new Encoder(configuration);
            encoder.setIndenting(true);
            encoder.setIndentSize(4);
            ByteArrayOutputStream stream = new ByteArrayOutputStream();
            encoder.encode(filter, org.geotools.filter.v1_0.OGC.Filter, stream);
            return jaxbContextBuilder.unmarshal(new StringReader(stream.toString()), FilterType.class);
        } catch (Exception ex) {
            ex.printStackTrace();
            logger.error("##################{} error parse cqlFilter : {}\n", this, this.cqlFilter);
        }
        return new FilterType();
    }
}