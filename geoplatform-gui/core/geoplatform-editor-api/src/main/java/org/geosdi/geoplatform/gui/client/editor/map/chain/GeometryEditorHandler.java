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
package org.geosdi.geoplatform.gui.client.editor.map.chain;

import org.geosdi.geoplatform.gui.client.editor.map.control.ModifyEditorFeature;
import org.gwtopenmaps.openlayers.client.feature.VectorFeature;
import org.gwtopenmaps.openlayers.client.layer.Vector;

/**
 *
 * @author Giuseppe La Scaleia - CNR IMAA geoSDI Group
 * @email giuseppe.lascaleia@geosdi.org
 */
public abstract class GeometryEditorHandler {

    protected ModifyEditorFeature modifyEditorControl;
    private GeometryEditorHandler successor;

    public GeometryEditorHandler(ModifyEditorFeature theModifyEditorControl) {
        this.modifyEditorControl = theModifyEditorControl;
    }

    public void setSuperiorEditorHandler(GeometryEditorHandler theSuperior) {
        this.successor = theSuperior;
    }

    public abstract void geometryRequest(VectorFeature feature, Vector vector);

    protected void forwardGeometryRequest(VectorFeature feature, Vector vector) {
        if (successor != null) {
            successor.geometryRequest(feature, vector);
        }
    }

    public VectorFeature getSelectedFeature() {
        return modifyEditorControl.getSelectedFeature();
    }

    protected abstract boolean checkModifications(VectorFeature feature);

    protected abstract void showConfirmMessage(final VectorFeature feature,
            final Vector vector);

    protected abstract void manageUpdatedFeature(VectorFeature modifiedFeature,
            VectorFeature oldFeature);

}
