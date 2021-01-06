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
package org.geosdi.geoplatform.gui.client.config;

import com.extjs.gxt.ui.client.widget.button.ButtonBar;
import com.extjs.gxt.ui.client.widget.form.FieldSet;
import com.extjs.gxt.ui.client.widget.form.FormPanel;
import com.extjs.gxt.ui.client.widget.form.SimpleComboBox;
import com.extjs.gxt.ui.client.widget.layout.BorderLayout;
import com.extjs.gxt.ui.client.widget.menu.Menu;
import com.google.gwt.inject.client.AbstractGinModule;
import org.geosdi.geoplatform.gui.client.action.menu.edit.responsibility.DescribeFeatureTypeHandler;
import org.geosdi.geoplatform.gui.client.action.menu.edit.responsibility.LayerTypeHandlerManager;
import org.geosdi.geoplatform.gui.client.action.menu.edit.responsibility.RasterTypeHandler;
import org.geosdi.geoplatform.gui.client.action.menu.edit.responsibility.schema.ConcreteLayerSchemaHandler;
import org.geosdi.geoplatform.gui.client.action.menu.edit.responsibility.schema.LayerSchemaHandlerManager;
import org.geosdi.geoplatform.gui.client.action.menu.edit.responsibility.schema.LayerSchemaParserHandler;
import org.geosdi.geoplatform.gui.client.command.wfst.feature.EraseFeatureRequest;
import org.geosdi.geoplatform.gui.client.command.wfst.feature.InsertFeatureRequest;
import org.geosdi.geoplatform.gui.client.command.wfst.feature.UpdateFeatureGeometryRequest;
import org.geosdi.geoplatform.gui.client.config.annotation.FeatureAttributeConditionFieldList;
import org.geosdi.geoplatform.gui.client.config.annotation.MatchComboField;
import org.geosdi.geoplatform.gui.client.config.annotation.geocoding.WFSGeocodingFieldSet;
import org.geosdi.geoplatform.gui.client.config.annotation.geocoding.WFSGeocodingFormPanel;
import org.geosdi.geoplatform.gui.client.config.annotation.geocoding.WFSGeocodingTextField;
import org.geosdi.geoplatform.gui.client.config.annotation.geocoding.WFSLocationFieldSet;
import org.geosdi.geoplatform.gui.client.config.annotation.tree.WFSLayerTree;
import org.geosdi.geoplatform.gui.client.config.annotation.tree.WFSLayerTreeStore;
import org.geosdi.geoplatform.gui.client.config.annotation.tree.WFSTreeLeafMenu;
import org.geosdi.geoplatform.gui.client.config.provider.*;
import org.geosdi.geoplatform.gui.client.config.provider.fieldset.GeocodingFieldsetProvider;
import org.geosdi.geoplatform.gui.client.config.provider.form.GeocodingFormPanelProvider;
import org.geosdi.geoplatform.gui.client.config.provider.fieldset.LocationFieldsetProvider;
import org.geosdi.geoplatform.gui.client.config.provider.layout.BorderLayoutProvider;
import org.geosdi.geoplatform.gui.client.config.provider.text.GeocodingTextFieldProvider;
import org.geosdi.geoplatform.gui.client.config.provider.tree.LayerTreePanelProvider;
import org.geosdi.geoplatform.gui.client.config.provider.tree.LayerTreeStoreProvider;
import org.geosdi.geoplatform.gui.client.config.provider.tree.WFSRootLayerTreeNodeProvider;
import org.geosdi.geoplatform.gui.client.config.provider.tree.menu.WFSTreeLeafMenuProvider;
import org.geosdi.geoplatform.gui.client.config.provider.window.buttonbar.FeatureWidgetBar;
import org.geosdi.geoplatform.gui.client.model.tree.WFSRootLayerTreeNode;
import org.geosdi.geoplatform.gui.client.widget.map.control.GotoXYWidget;
import org.geosdi.geoplatform.gui.client.widget.tree.GPTreePanel;
import org.geosdi.geoplatform.gui.client.widget.tree.GPTreeStore;
import org.geosdi.geoplatform.gui.client.widget.wfs.dispatcher.DescribeFeatureDispatcher;
import org.geosdi.geoplatform.gui.client.widget.wfs.dispatcher.GPDescribeFeatureDispatcher;
import org.geosdi.geoplatform.gui.client.widget.wfs.dispatcher.WFSDispatcherProgressBar;
import org.geosdi.geoplatform.gui.client.widget.wfs.map.listener.FeatureSelectListener;
import org.geosdi.geoplatform.gui.client.widget.wfs.map.listener.FeatureUnSelectListener;
import org.geosdi.geoplatform.gui.configuration.GPSecureStringTextField;
import org.gwtopenmaps.openlayers.client.LonLat;
import org.gwtopenmaps.openlayers.client.MapWidget;
import org.gwtopenmaps.openlayers.client.Style;
import org.gwtopenmaps.openlayers.client.layer.Vector;
import org.gwtopenmaps.openlayers.client.protocol.WFSProtocolCRUDOptions;

import javax.inject.Singleton;
import java.util.List;

/**
 * @author Giuseppe La Scaleia - CNR IMAA geoSDI Group
 * @email giuseppe.lascaleia@geosdi.org
 */
public class FeatureInjectorProvider extends AbstractGinModule {

    @Override
    protected void configure() {
        bind(MapWidget.class).toProvider(MapWidgetProvider.class).in(Singleton.class);
        bind(LayerTypeHandlerManager.class).toProvider(LayerTypeHandlerManagerProvider.class).in(Singleton.class);
        bind(DescribeFeatureTypeHandler.class).toProvider(DescribeFeatureTypeHandlerProvider.class).in(Singleton.class);
        bind(RasterTypeHandler.class).toProvider(RasterTypeHandlerProvider.class).in(Singleton.class);
        bind(DescribeFeatureDispatcher.class).to(GPDescribeFeatureDispatcher.class).in(Singleton.class);
        bind(LayerSchemaParserHandler.class).to(ConcreteLayerSchemaHandler.class).in(Singleton.class);
        bind(LayerSchemaHandlerManager.class).toProvider(LayerSchemaHandlerManagerProvider.class).in(Singleton.class);
        bind(Style.class).toProvider(VectorStyleProvider.class).in(Singleton.class);
        bind(Vector.class).toProvider(VectorLayerProvider.class).in(Singleton.class);
        bind(FeatureSelectListener.class).toProvider(FeatureSelectListenerProvider.class).in(Singleton.class);
        bind(FeatureUnSelectListener.class).toProvider(FeatureUnSelectListenerProvider.class).in(Singleton.class);
        bind(LonLat.class).toProvider(FeatureLonLatItalyProvider.class).in(Singleton.class);
        bind(BorderLayout.class).toProvider(BorderLayoutProvider.class).in(Singleton.class);
        bind(WFSDispatcherProgressBar.class).toProvider(WFSDispatcherProgressBarProvider.class).in(Singleton.class);
        bind(UpdateFeatureGeometryRequest.class).toProvider(UpdateFeatureGeometryRequestProvider.class).in(Singleton.class);
        bind(InsertFeatureRequest.class).toProvider(InsertFeatureRequestProvider.class).in(Singleton.class);
        bind(EraseFeatureRequest.class).toProvider(EraseFeatureRequestProvider.class).in(Singleton.class);
        bind(WFSProtocolCRUDOptions.class).toProvider(FeatureProtocolCRUDOptionsProvider.class).in(Singleton.class);
        bind(GotoXYWidget.class).toProvider(WFSGotoXYWigetProvider.class).in(Singleton.class);
        bind(GPTreePanel.class).annotatedWith(WFSLayerTree.class).toProvider(LayerTreePanelProvider.class).in(Singleton.class);
        bind(GPTreeStore.class).annotatedWith(WFSLayerTreeStore.class).toProvider(LayerTreeStoreProvider.class).in(Singleton.class);
        bind(WFSRootLayerTreeNode.class).toProvider(WFSRootLayerTreeNodeProvider.class).in(Singleton.class);
        bind(ButtonBar.class).toProvider(FeatureWidgetBar.class).in(Singleton.class);
        bind(List.class).annotatedWith(FeatureAttributeConditionFieldList.class).toProvider(FeatureAttributeConditionFieldListProvider.class).in(Singleton.class);
        bind(SimpleComboBox.class).annotatedWith(MatchComboField.class).toProvider(MatchComboFieldProvider.class).in(Singleton.class);
        bind(Menu.class).annotatedWith(WFSTreeLeafMenu.class).toProvider(WFSTreeLeafMenuProvider.class).in(Singleton.class);
        bind(GPSecureStringTextField.class).annotatedWith(WFSGeocodingTextField.class).toProvider(GeocodingTextFieldProvider.class).in(Singleton.class);
        bind(FieldSet.class).annotatedWith(WFSGeocodingFieldSet.class).toProvider(GeocodingFieldsetProvider.class).in(Singleton.class);
        bind(FormPanel.class).annotatedWith(WFSGeocodingFormPanel.class).toProvider(GeocodingFormPanelProvider.class).in(Singleton.class);
        bind(FieldSet.class).annotatedWith(WFSLocationFieldSet.class).toProvider(LocationFieldsetProvider.class).in(Singleton.class);
    }
}