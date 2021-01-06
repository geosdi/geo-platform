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

import com.google.gwt.inject.client.AbstractGinModule;
import org.geosdi.geoplatform.gui.client.action.wfs.toolbar.EditFeatureAction;
import org.geosdi.geoplatform.gui.client.action.wfs.toolbar.EraseFeatureAction;
import org.geosdi.geoplatform.gui.client.action.wfs.toolbar.GetFeatureAction;
import org.geosdi.geoplatform.gui.client.puregwt.wfs.event.InjectGetFeatureModelEvent;
import org.geosdi.geoplatform.gui.client.widget.wfs.map.control.edit.WFSLineFeatureControl;
import org.geosdi.geoplatform.gui.client.widget.wfs.map.control.edit.WFSPointFeatureControl;
import org.geosdi.geoplatform.gui.client.widget.wfs.map.control.edit.WFSPolygonFeatureControl;
import org.geosdi.geoplatform.gui.client.widget.wfs.map.control.erase.WFSEraseFeatureControl;
import org.geosdi.geoplatform.gui.client.widget.wfs.map.control.getfeature.WFSGetFeatureControl;
import org.geosdi.geoplatform.gui.client.widget.wfs.map.control.modify.WFSModifyFeatureControl;
import org.geosdi.geoplatform.gui.client.widget.wfs.map.control.repository.SimpleEditFeatureRepository;
import org.geosdi.geoplatform.gui.client.widget.wfs.map.control.repository.WFSEditFeatureRepository;
import org.geosdi.geoplatform.gui.client.widget.wfs.map.dispatcher.FeatureDispatcher;
import org.geosdi.geoplatform.gui.client.widget.wfs.map.dispatcher.WFSFeatureDispatcher;
import org.geosdi.geoplatform.gui.client.widget.wfs.map.dispatcher.executor.*;
import org.geosdi.geoplatform.gui.client.widget.wfs.map.mediator.WFSBaseMapMediator;
import org.geosdi.geoplatform.gui.client.widget.wfs.map.mediator.colleague.WFSEditFeatureColleague;
import org.geosdi.geoplatform.gui.client.widget.wfs.map.mediator.colleague.WFSEraseFeatureColleague;
import org.geosdi.geoplatform.gui.client.widget.wfs.map.mediator.colleague.WFSGetFeatureColleague;
import org.geosdi.geoplatform.gui.client.widget.wfs.map.mediator.colleague.WFSModifyFeatureColleague;
import org.geosdi.geoplatform.gui.client.widget.wfs.toolbar.EditingToolBarDialog;
import org.geosdi.geoplatform.gui.client.widget.wfs.toolbar.button.observer.EditToolbarBaseObserver;
import org.geosdi.geoplatform.gui.client.widget.wfs.toolbar.button.observer.WFSToolbarObserver;

import javax.inject.Singleton;

/**
 * @author Giuseppe La Scaleia - CNR IMAA geoSDI Group
 * @email giuseppe.lascaleia@geosdi.org
 */
public class FeatureInjectorToolbar extends AbstractGinModule {

    @Override
    protected void configure() {
        bind(EditingToolBarDialog.class).in(Singleton.class);
        bind(WFSToolbarObserver.class).to(EditToolbarBaseObserver.class).in(Singleton.class);
        bind(WFSBaseMapMediator.class).in(Singleton.class);
        bind(WFSEditFeatureColleague.class).asEagerSingleton();
        bind(WFSPointFeatureControl.class).asEagerSingleton();
        bind(WFSLineFeatureControl.class).asEagerSingleton();
        bind(WFSPolygonFeatureControl.class).asEagerSingleton();
        bind(WFSGetFeatureColleague.class).asEagerSingleton();
        bind(WFSGetFeatureControl.class).in(Singleton.class);
        bind(WFSModifyFeatureColleague.class).asEagerSingleton();
        bind(WFSModifyFeatureControl.class).in(Singleton.class);
        bind(WFSEraseFeatureColleague.class).asEagerSingleton();
        bind(WFSEraseFeatureControl.class).in(Singleton.class);
        bind(WFSEditFeatureRepository.class).to(SimpleEditFeatureRepository.class).in(Singleton.class);
        bind(EditFeatureAction.class).in(Singleton.class);
        bind(GetFeatureAction.class).in(Singleton.class);
        bind(EraseFeatureAction.class).in(Singleton.class);
        bind(InjectGetFeatureModelEvent.class).in(Singleton.class);
        bind(FeatureDispatcher.class).to(WFSFeatureDispatcher.class).asEagerSingleton();
        bind(IWFSUpdateGeometryExecutor.class).to(WFSUpdateGeometryExecutor.class).in(Singleton.class);
        bind(IWFSEraseFeatureExecutor.class).to(WFSEraseFeatureExecutor.class).in(Singleton.class);
        bind(IWFSInsertFeatureExecutor.class).to(WFSInsertFeatureExecutor.class).in(Singleton.class);
    }
}