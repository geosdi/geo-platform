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
package org.geosdi.geoplatform.gui.client.i18n;

import com.google.gwt.core.client.GWT;
import com.google.gwt.i18n.client.Constants;
import com.google.gwt.i18n.client.Constants.DefaultStringValue;

/**
 * @author Nazzareno Sileno - CNR IMAA geoSDI Group
 * @email nazzareno.sileno@geosdi.org
 */
public interface MementoPersistenceConstants extends Constants {

    public static final MementoPersistenceConstants INSTANCE =
            GWT.create(MementoPersistenceConstants.class);

    /**
     * start SaveLayersPropertiesAction
     */
    @DefaultStringValue("Problems on saving the new layer properties")
    String SaveLayersPropertiesAction_errorSaveLayerPropertiesBodyText();

    @DefaultStringValue("Save Layer Properties Error")
    String SaveLayersPropertiesAction_errorSaveLayerPropertiesTitleText();

    @DefaultStringValue(
            "Save Layer Properties Operation completed successfully.")
    String SaveLayersPropertiesAction_statusSaveLayerSuccessText();

    /**
     * start SaveFoldersPropertiesAction
     */
    @DefaultStringValue("Save Folder Properties Error")
    String SaveFoldersPropertiesAction_errorSaveFolderTitleText();

    @DefaultStringValue("Problems on saving the new folder properties")
    String SaveFoldersPropertiesAction_errorSaveFolderBodyText();

    @DefaultStringValue(
            "Save Folder Properties Operation completed successfully.")
    String SaveFoldersPropertiesAction_statusSaveSuccessText();

    /**
     * start MementoSaveOperations
     */
    @DefaultStringValue("Save Layers Error")
    String MementoSaveOperations_errorSavingfLayersTitleText();

    /**
     * start MementoSaveCacheManager
     */
    @DefaultStringValue("There are unsaved operations on tree.\n"
            + "Do you want to save your changes before proceed?")
    String MementoSaveCacheManager_unsavedOperationMessageText();

    @DefaultStringValue("Unsaved Operations")
    String MementoSaveCacheManager_unsavedOperationsText();

    @DefaultStringValue("Unsaved Operations on Tree.")
    String MementoSaveCacheManager_statusUnsavedOperationsText();

    @DefaultStringValue("Save Folder Drag&Drop Operation Error")
    String GPDNDListener_saveFolderDDErrorTitleText();

    @DefaultStringValue(
            "Problems on saving the new tree state after folder drag&drop operation")
    String GPDNDListener_saveFolderDDErrorBodyText();

    @DefaultStringValue("Folder Drag&Drop operation saved successfully.")
    String GPDNDListener_statusSaveFolderDDSuccessText();

    /**
     * start GPDNDListener
     */
    @DefaultStringValue("Layer Drag&Drop operation saved successfully.")
    String GPDNDListener_statusSaveLayerDDSuccessText();

    @DefaultStringValue("Save Layer Drag&Drop Operation Error")
    String GPDNDListener_saveLayerDDErrorTitleText();

    @DefaultStringValue(
            "Problems on saving the new tree state after layer drag&drop operation")
    String GPDNDListener_saveLayerDDErrorBodyText();

}
