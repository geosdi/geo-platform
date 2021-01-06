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
package org.geosdi.geoplatform.connector.reader.stax;

import lombok.Getter;
import lombok.ToString;
import org.geojson.Feature;

import javax.annotation.Nonnull;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static com.google.common.base.Preconditions.checkArgument;
import static java.util.stream.Collectors.toList;
import static java.util.stream.Stream.of;
import static javax.annotation.meta.When.NEVER;

/**
 * @author Giuseppe La Scaleia - CNR IMAA geoSDI Group
 * @email giuseppe.lascaleia@geosdi.org
 */
@Getter
@ToString
public abstract class WMSFeatureStore<K extends Object> implements GPStaxFeatureStore<K> {

    private static final long serialVersionUID = 7246174437706791817L;
    //
    private final K key;
    private final Map<K, List<Feature>> store = new HashMap<>();

    /**
     * @param theKey
     */
    protected WMSFeatureStore(@Nonnull(when = NEVER) K theKey) {
        checkArgument(theKey != null, "The Parameter key must not be null.");
        this.key = theKey;
    }

    /**
     * @param feature
     * @throws Exception
     */
    @Override
    public void addFeature(@Nonnull(when = NEVER) Feature feature) throws Exception {
        checkArgument(feature != null, "The Parameter feature must not be null.");
        K featureKey = (K) feature.getProperties().get(this.key);
        if(this.store.containsKey(featureKey)) {
            List<Feature> features = this.store.get(featureKey);
            features.add(feature);
        } else {
            this.store.put(featureKey, of(feature).collect(toList()));
        }
        feature.getProperties().remove(this.key);
    }

    /**
     * @param theKey
     * @return {@link List<Feature>}
     * @throws Exception
     */
    @Override
    public List<Feature> getFeaturesByKey(@Nonnull(when = NEVER) K theKey) throws Exception {
        checkArgument(theKey != null, "The Parameter key must not be null.");
        return this.store.get(theKey);
    }
}