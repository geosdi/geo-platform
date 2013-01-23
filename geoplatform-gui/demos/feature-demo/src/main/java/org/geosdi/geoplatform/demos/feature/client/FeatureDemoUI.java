/*
 *  geo-platform
 *  Rich webgis framework
 *  http://geo-platform.org
 * ====================================================================
 *
 * Copyright (C) 2008-2013 geoSDI Group (CNR IMAA - Potenza - ITALY).
 *
 * This program is free software: you can redistribute it and/or modify it 
 * under the terms of the GNU General Public License as published by 
 * the Free Software Foundation, either version 3 of the License, or 
 * (at your option) any later version. This program is distributed in the 
 * hope that it will be useful, but WITHOUT ANY WARRANTY; without 
 * even the implied warranty of MERCHANTABILITY or FITNESS FOR 
 * A PARTICULAR PURPOSE. See the GNU General Public License 
 * for more details. You should have received a copy of the GNU General 
 * Public License along with this program. If not, see http://www.gnu.org/licenses/ 
 *
 * ====================================================================
 *
 * Linking this library statically or dynamically with other modules is 
 * making a combined work based on this library. Thus, the terms and 
 * conditions of the GNU General Public License cover the whole combination. 
 * 
 * As a special exception, the copyright holders of this library give you permission 
 * to link this library with independent modules to produce an executable, regardless 
 * of the license terms of these independent modules, and to copy and distribute 
 * the resulting executable under terms of your choice, provided that you also meet, 
 * for each linked independent module, the terms and conditions of the license of 
 * that module. An independent module is a module which is not derived from or 
 * based on this library. If you modify this library, you may extend this exception 
 * to your version of the library, but you are not obligated to do so. If you do not 
 * wish to do so, delete this exception statement from your version. 
 *
 */
package org.geosdi.geoplatform.demos.feature.client;

import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.user.client.rpc.AsyncCallback;
import java.util.ArrayList;
import org.geosdi.geoplatform.gui.client.config.FeatureInjector;
import org.geosdi.geoplatform.gui.client.service.WFSRemote;
import org.geosdi.geoplatform.gui.client.widget.wfs.FeatureWidget;
import org.geosdi.geoplatform.gui.configuration.map.client.geometry.BBoxClientInfo;
import org.geosdi.geoplatform.gui.configuration.message.GeoPlatformMessage;
import org.geosdi.geoplatform.gui.model.GPLayerBean;
import org.geosdi.geoplatform.gui.model.server.GPVectorLayerGrid;
import org.geosdi.geoplatform.gui.model.tree.GPStyleStringBeanModel;
import org.geosdi.geoplatform.gui.responce.LayerSchemaDTO;
import org.geosdi.geoplatform.gui.shared.GPLayerType;

/**
 *
 * @author Vincenzo Monteverde <vincenzo.monteverde@geosdi.org>
 */
public class FeatureDemoUI implements EntryPoint {

//    private static final String SCOPE = "http://localhost:8989/geoserver/wms";
    private static final String SCOPE = "http://150.146.160.92/geoserver/wms";

    @Override
    public void onModuleLoad() {
        // TODO Is it necessary?
        // Required for retrieve organization name in the save feature server operation
//        IGPAccountDetail account = new AccountDetailDummy();
//        GPAccountLogged.getInstance().setAccountDetail(account);

        FeatureInjector injector = FeatureInjector.MainInjector.getInstance();
        final FeatureWidget featureWidget = injector.getFeatureWidget();

        final GPLayerBean layer = this.toppStates();

        WFSRemote.Util.getInstance().describeFeatureType(layer.getDataSource(),
                                                         layer.getName(),
                                                         new AsyncCallback<LayerSchemaDTO>() {
            @Override
            public void onFailure(Throwable caught) {
                GeoPlatformMessage.errorMessage(
                        "DescribeFetureType Service Error",
                        "Error on WFS DescribeFeatureType request:\n" + caught.getMessage());
            }

            @Override
            public void onSuccess(LayerSchemaDTO schema) {
                assert (schema != null) : "LayerSchemaDTO is NULL.";

                featureWidget.bind(layer, schema);

                featureWidget.setClosable(false);
                featureWidget.show();
            }
        });
    }

    public GPLayerBean toppStates() {
        GPLayerBean layer = new GPVectorLayerGrid();
        layer.setDataSource(SCOPE);
        layer.setName("topp:states");
        layer.setLayerType(GPLayerType.MULTIPOLYGON);
        layer.setTitle("USA Population");
        layer.setAbstractText("This is some census data on the states.");
        layer.setCrs("EPSG:4326");
        layer.setBbox(
                new BBoxClientInfo(-124.731422, 24.955967, -66.969849, 49.371735));

        ArrayList<GPStyleStringBeanModel> styles = new ArrayList<GPStyleStringBeanModel>(3);
        GPStyleStringBeanModel polygonStyle = new GPStyleStringBeanModel();
        polygonStyle.setStyleString("polygon");
        styles.add(polygonStyle);
        GPStyleStringBeanModel pophatchStyle = new GPStyleStringBeanModel();
        pophatchStyle.setStyleString("pophatch");
        styles.add(pophatchStyle);
        GPStyleStringBeanModel populationStyle = new GPStyleStringBeanModel();
        populationStyle.setStyleString("population");
        styles.add(populationStyle);

        layer.setStyles(styles);

        return layer;
    }
}
