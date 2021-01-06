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
package org.geosdi.geoplatform.gui.client.widget.wfs.buttonbar.undoredo.mediator;

import com.google.common.collect.Maps;
import org.geosdi.geoplatform.gui.client.widget.wfs.buttonbar.undoredo.button.WFSUndoRedoButton;
import org.geosdi.geoplatform.gui.client.widget.wfs.map.control.edit.undoredo.WFSUndoRedoEditSupport;

import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * @author Giuseppe La Scaleia - CNR IMAA geoSDI Group
 * @email giuseppe.lascaleia@geosdi.org
 */
public class BaseUndoRedoMediator implements WFSUndoRedoMediator {

    private static final Logger logger = Logger.getLogger("BaseUndoRedoMediator");
    //
    private final Map<String, WFSUndoRedoButton> undoRedoButtonRepo = Maps.newHashMap();
    private WFSUndoRedoEditSupport wfsUndoRedoEditSupport;

    /**
     * @param button
     * @return {@link WFSUndoRedoMediator}
     */
    @Override
    public <UndoRedo extends WFSUndoRedoButton> WFSUndoRedoMediator bind(UndoRedo button) {
        this.undoRedoButtonRepo.put(button.getItemId(), button);
        return this;
    }

    @Override
    public void undo() {
        if (this.wfsUndoRedoEditSupport != null)
            this.wfsUndoRedoEditSupport.undo();
    }

    @Override
    public void redo() {
        if (this.wfsUndoRedoEditSupport != null)
            this.wfsUndoRedoEditSupport.redo();
    }

    /**
     * @param undoRedo
     */
    @Override
    public <UndoRedo extends WFSUndoRedoEditSupport> void enableUndoRedoSupport(UndoRedo undoRedo) {
        logger.log(Level.FINE, "#################################EXECUTE enableUndoRedoSupport");
        this.wfsUndoRedoEditSupport = undoRedo;
        for (Map.Entry<String, WFSUndoRedoButton> entry : this.undoRedoButtonRepo.entrySet()) {
            entry.getValue().setEnabled(Boolean.TRUE);
        }
    }

    @Override
    public void disableUndoRedoSupport() {
        for (Map.Entry<String, WFSUndoRedoButton> entry : this.undoRedoButtonRepo.entrySet()) {
            entry.getValue().setEnabled(Boolean.FALSE);
        }
    }
}
