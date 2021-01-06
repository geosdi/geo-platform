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
package org.geosdi.geoplatform.gui.client.widget.wfs.map.control.edit;

import org.geosdi.geoplatform.gui.client.editor.map.control.DrawEditorLineFeature;
import org.geosdi.geoplatform.gui.client.model.binder.ILayerSchemaBinder;
import org.geosdi.geoplatform.gui.client.puregwt.wfs.event.EnableUndoRedoSupportEvent;
import org.geosdi.geoplatform.gui.client.puregwt.wfs.event.ShowAttributesWindowEvent;
import org.geosdi.geoplatform.gui.client.puregwt.wfs.handler.WFSUndoRedoMediatorHandlerSupport;
import org.geosdi.geoplatform.gui.client.widget.wfs.builder.feature.FeatureAttributesWindowBuilder;
import org.geosdi.geoplatform.gui.client.widget.wfs.map.control.edit.undoredo.WFSUndoRedoEditSupport;
import org.geosdi.geoplatform.gui.client.widget.wfs.map.control.repository.WFSEditFeatureRepository;
import org.gwtopenmaps.openlayers.client.control.DrawFeature;
import org.gwtopenmaps.openlayers.client.feature.VectorFeature;
import org.gwtopenmaps.openlayers.client.layer.Vector;

import javax.inject.Inject;
import java.util.logging.Logger;

import static org.geosdi.geoplatform.gui.client.puregwt.wfs.handler.WFSUndoRedoMediatorHandlerSupport.DISABLE_UNDO_REDO_SUPPORT_EVENT;

/**
 * @author Giuseppe La Scaleia - CNR IMAA geoSDI Group
 * @email giuseppe.lascaleia@geosdi.org
 */
public class WFSLineFeatureControl extends DrawEditorLineFeature implements
        WFSEditFeatureControl, WFSUndoRedoEditSupport {

    private static final Logger logger = Logger.getLogger("WFSLineFeatureControl");
    //
    @Inject
    private ShowAttributesWindowEvent event;
    private VectorFeature lastFeatureInserted;

    @Inject
    public WFSLineFeatureControl(Vector vector, ILayerSchemaBinder layerSchemaBinder,
                                 WFSEditFeatureRepository editFeatureRepository) {
        super(vector, true, layerSchemaBinder);
        editFeatureRepository.bindWFSEditFeatureControl(this);
    }

    @Override
    protected void manageAddedFeature(VectorFeature vf) {
        this.lastFeatureInserted = vf;
        this.event.setEditorSource(this);
        FeatureAttributesWindowBuilder.fireAttributesWindowEvent(event);
    }

    @Override
    public void activate() {
        super.activateControl();
        WFSUndoRedoMediatorHandlerSupport.fireUndoRedoEvent(new EnableUndoRedoSupportEvent(this));
    }

    @Override
    public void deactivate() {
        super.deactivateControl();
        WFSUndoRedoMediatorHandlerSupport.fireUndoRedoEvent(DISABLE_UNDO_REDO_SUPPORT_EVENT);
    }

    @Override
    public void redo() {
        if (super.isEnabled())
            this.drawEditorControl.redo();
    }

    @Override
    public void undo() {
        if (super.isEnabled())
            this.drawEditorControl.undo();
    }

    @Override
    public String[] getEditFeatureKeys() {
        return new String[]{"LineString", "MultiLineString", "LinearRing"};
    }

    @Override
    public DrawFeature getEditFeatureControl() {
        return super.getControl();
    }

    @Override
    public void resetWFSEditing() {
        if (lastFeatureInserted != null) {
            this.vector.removeFeature(lastFeatureInserted);
        }
    }

    @Override
    public String buildWktGeometry() {
        if (lastFeatureInserted == null) {
            throw new IllegalStateException("The Last Feature added is null.");
        }

        return this.wktGeometryBuilder.buildWktGeometry(lastFeatureInserted);
    }

}
