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
package org.geosdi.geoplatform.connector.reader.stax;

import net.jcip.annotations.ThreadSafe;
import org.geojson.Feature;
import org.geosdi.geoplatform.stax.reader.builder.GPXmlStreamReaderBuilder;

import javax.annotation.Nonnull;
import javax.xml.stream.XMLInputFactory;
import javax.xml.stream.XMLStreamReader;
import javax.xml.stream.events.XMLEvent;
import java.util.Map;

import static javax.annotation.meta.When.NEVER;

/**
 * @author Giuseppe La Scaleia - CNR IMAA geoSDI Group
 * @email giuseppe.lascaleia@geosdi.org
 */
@ThreadSafe
public class GPWMSGetFeatureInfoStaxGml3Reader extends GetFeatureGeoJsonStaxGml3Reader implements WMSGetFeatureInfoStaxGml3Reader {

    /**
     * Create a {@link GetFeatureGeoJsonStaxGml3Reader} with {@link XMLInputFactory} with these properties :
     * <p>
     *     <ul>
     *         <li>Enable {@link XMLInputFactory#IS_COALESCING} property.</li>
     *         <li>Enable {@link XMLInputFactory#IS_NAMESPACE_AWARE} property.</li>
     *         <li>Enable "http://java.sun.com/xml/stream/properties/report-cdata-event" property.</li>
     *     </ul>
     * </p>
     */
    public GPWMSGetFeatureInfoStaxGml3Reader() {
       super();
    }

    /**
     * @param theProp
     */
    public GPWMSGetFeatureInfoStaxGml3Reader(@Nonnull(when = NEVER) Map<String, Object> theProp) {
        super(theProp);
    }

    /**
     * @param theXmlStreamBuilder
     */
    public GPWMSGetFeatureInfoStaxGml3Reader(@Nonnull(when = NEVER) GPXmlStreamReaderBuilder theXmlStreamBuilder) {
        super(theXmlStreamBuilder);
    }

    /**
     * @param object
     * @return {@link GPWMSFeatureStore}
     * @throws Exception
     */
    @Override
    public GPWMSFeatureStore readAsStore(@Nonnull(when = NEVER) Object object) throws Exception {
        XMLStreamReader reader = this.acquireReader(object);
        GPWMSFeatureStore store = new GPWMSFeatureStore();
        try {
            while (reader.hasNext()) {
                int evenType = reader.getEventType();
                if (evenType == XMLEvent.START_ELEMENT) {
                    if (super.isTagName(WFS_PREFIX, FEATURE_COLLECTION_LOCAL_NAME)) {
                        this.loadTypeNames();
                    } else if (super.isTagName(GML_PREFIX, BOUNDING_BY_PREFIX)) {
                        super.goToEndTag(BOUNDING_BY_PREFIX);
                    } else if (super.isTagName(GML_PREFIX, FEATURE_MEMBERS_LOCAL_NAME)) {
                        Feature feature = new Feature();
                        this.readFeatures(feature);
                        store.addFeature(feature);
                    } else if (super.isLoadedTypeNames()) {//if (super.isFeatureTag()) {
                        Feature feature = new Feature();
                        this.readFeatureID(feature);
                        store.addFeature(feature);
                    }
                }
                reader.next();
            }
            return store;
        } finally {
            this.dispose();
        }
    }
}