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
package org.geosdi.geoplatform.connector.server.request.kvp;

import com.fasterxml.jackson.annotation.JsonGetter;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

import javax.annotation.Nonnull;

import static com.google.common.base.Preconditions.checkArgument;
import static javax.annotation.meta.When.NEVER;

/**
 * @author Giuseppe La Scaleia - CNR IMAA geoSDI Group
 * @email giuseppe.lascaleia@geosdi.org
 */
@JsonPropertyOrder(value = {"key", "value"})
public abstract class WMSGetMapBaseRequestKeyValuePair<V> implements GPWMSRequestKeyValuePair<V> {

    private static final long serialVersionUID = 2531861710995929420L;
    //
    private final String key;

    /**
     * @param theKey
     */
    WMSGetMapBaseRequestKeyValuePair(@Nonnull(when = NEVER) String theKey) {
        checkArgument((theKey != null) && !(theKey.trim().isEmpty()), "The Parameter key must not be null or an empty string.");
        this.key = theKey;
    }

    /**
     * @return {@link String}
     */
    @JsonGetter(value = "key")
    @Override
    public final String toKey() {
        return this.key;
    }

    /**
     * @return {@link V}
     */
    @JsonGetter(value = "value")
    public abstract V toValue();

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        WMSGetMapBaseRequestKeyValuePair<?> that = (WMSGetMapBaseRequestKeyValuePair<?>) o;
        if (key != null ? !key.equals(that.key) : that.key != null) {
            return false;
        }
        return toValue() != null ? toValue().equals(that.toValue()) : that.toValue() == null;
    }

    @Override
    public int hashCode() {
        int result = key != null ? key.hashCode() : 0;
        result = 31 * result + (toValue() != null ? toValue().hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return (this.getClass().getSimpleName().equals("") ? "WMSGetMapBaseRequestKeyValuePair" : this.getClass().getSimpleName()) + "{" +
                "key = " + this.toKey() +
                ", value = " + ((this.toValue() != null) ? this.toValue() : "") +
                "}";
    }
}