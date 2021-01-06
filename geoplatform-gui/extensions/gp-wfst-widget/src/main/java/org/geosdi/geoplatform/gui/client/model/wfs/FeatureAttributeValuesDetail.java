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
package org.geosdi.geoplatform.gui.client.model.wfs;

import org.geosdi.geoplatform.gui.model.GeoPlatformBeanModel;
import org.gwtopenmaps.openlayers.client.feature.VectorFeature;

import java.util.Map;

import static com.google.common.base.Preconditions.checkArgument;

/**
 * @author Vincenzo Monteverde <vincenzo.monteverde@geosdi.org>
 */
public abstract class FeatureAttributeValuesDetail extends GeoPlatformBeanModel {

    private static final long serialVersionUID = 3816298502884381217L;
    //
    private Map<String, String> attributes;

    /**
     * @param theAttributes
     */
    public FeatureAttributeValuesDetail(Map<String, String> theAttributes) {
        checkArgument(theAttributes != null, "The Parameter attributes must not be null.");
        for (Map.Entry<String, String> att : theAttributes.entrySet()) {
            this.set(att.getKey(), ((att.getValue() != null) ? att.getValue() : ""));
        }
        this.attributes = theAttributes;
    }

    /**
     * @param theAttributeName
     * @return {@link String}
     */
    public String getValue(String theAttributeName) {
        checkArgument(theAttributeName != null, "The Parameter attributeName must not be null.");
        return super.get(theAttributeName);
    }

    /**
     * @param theAttributeName
     * @param theAttributeValue
     * @return
     */
    public String setValue(String theAttributeName, String theAttributeValue) {
        checkArgument(theAttributeName != null, "The Parameter attributeName must not be null.");
        checkArgument(theAttributeValue != null, "The Parameter attributeValue must not be null.");
        return super.set(theAttributeName, theAttributeValue);
    }

    /**
     * @return {@link Map<String, String>}
     */
    public Map<String, String> getAttributes() {
        return attributes;
    }

    /**
     * @return {@link VectorFeature}
     */
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