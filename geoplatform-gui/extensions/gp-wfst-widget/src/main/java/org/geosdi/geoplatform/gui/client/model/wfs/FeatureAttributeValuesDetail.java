/**
 * geo-platform
 * Rich webgis framework
 * http://geo-platform.org
 * ====================================================================
 * <p>
 * Copyright (C) 2008-2018 geoSDI Group (CNR IMAA - Potenza - ITALY).
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
package org.geosdi.geoplatform.gui.client.model.wfs;

import org.geosdi.geoplatform.gui.model.GeoPlatformBeanModel;
import org.gwtopenmaps.openlayers.client.feature.VectorFeature;

import java.util.Map;

/**
 *
 * @author Vincenzo Monteverde <vincenzo.monteverde@geosdi.org>
 */
public abstract class FeatureAttributeValuesDetail extends GeoPlatformBeanModel {

    private static final long serialVersionUID = 3816298502884381217L;
    //
    private Map<String, String> attributes;

    public FeatureAttributeValuesDetail(Map<String, String> attributes) {
        assert (attributes != null) : "attributes must not be null.";
        for (Map.Entry<String, String> att : attributes.entrySet()) {
            this.set(att.getKey(), att.getValue());
        }
        this.attributes = attributes;
    }

    public String getValue(String attributeName) {
        assert (attributeName != null) : "attributeName must not be null.";
        return super.get(attributeName);
    }

    public String setValue(String attributeName, String attributeValue) {
        assert (attributeName != null) : "attributeName must not be null.";
        assert (attributeValue != null) : "attributeValue must not be null.";
        return super.set(attributeName, attributeValue);
    }

    public Map<String, String> getAttributes() {
        return attributes;
    }

    protected abstract VectorFeature buildFeature();

    @Override
    public String toString() {
        StringBuilder str = new StringBuilder("AttributeDetail {");
        for (String name : super.getProperties().keySet()) {
            str.append(", ").append(name).append(" = ").append(this.get(name).toString());
        }
        return str.append('}').toString();
    }
}
