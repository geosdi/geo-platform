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
package org.geosdi.geoplatform.gui.client.configuration.getmap.choise.mediator.colleague;

import com.extjs.gxt.ui.client.widget.form.FieldSet;
import com.google.gwt.i18n.client.HasDirection;
import com.google.gwt.user.client.ui.RadioButton;
import javax.inject.Inject;
import org.geosdi.geoplatform.gui.client.configuration.getmap.choise.handler.GetMapSimpleHandler;
import org.geosdi.geoplatform.gui.client.configuration.getmap.choise.mediator.GetMapChoiseMediator;
import org.geosdi.geoplatform.gui.client.configuration.getmap.choise.mediator.colleague.executor.GetMapSimpleColleagueExecutor;
import org.geosdi.geoplatform.gui.client.configuration.getmap.choise.widget.simple.GetMapSimpleFieldSet;
import org.geosdi.geoplatform.gui.client.puregwt.getmap.event.DecreaseWidgetHeightEvent;
import org.geosdi.geoplatform.gui.client.widget.form.LoadWmsGetMapFromUrlWidget;

/**
 *
 * @author Giuseppe La Scaleia - CNR IMAA geoSDI Group
 * @email giuseppe.lascaleia@geosdi.org
 */
public class GetMapSimpleColleague extends GetMapChoiseColleague<FieldSet> {

    private final DecreaseWidgetHeightEvent decreaseHeight = new DecreaseWidgetHeightEvent();

    private final GetMapSimpleHandler handler;
    private RadioButton simple;

    @Inject
    private GetMapSimpleFieldSet choiseWidget;
    @Inject
    private GetMapSimpleColleagueExecutor colleagueExecutor;

    @Inject
    public GetMapSimpleColleague(GetMapChoiseMediator choiseMediator,
            GetMapSimpleHandler theHandler) {
        choiseMediator.registerChoiseColleague(this);
        this.handler = theHandler;
    }

    @Override
    public RadioButton getChoiseButton() {
        return this.simple = ((simple == null)
                ? new RadioButton("GET_MAP_CHOISE", "Simple",
                        HasDirection.Direction.LTR) {

                    {
                        super.addValueChangeHandler(handler);
                    }

                } : this.simple);
    }

    @Override
    public FieldSet getChoiseWidget() {
        return this.choiseWidget;
    }

    @Override
    public GetMapColleagueKey getChoiseColleagueKey() {
        return GetMapColleagueKey.GET_MAP_SIMPLE;
    }

    @Override
    public <E> void executeColleague(E param) {
        colleagueExecutor.executeColleague(param);
    }

    @Override
    public <R> void resetColleague(R param) {
        this.simple.setValue(Boolean.FALSE, true);
        LoadWmsGetMapFromUrlWidget.fireWmsGetMapFromUrlEvent(decreaseHeight);
        this.choiseWidget.reset();

    }

}
