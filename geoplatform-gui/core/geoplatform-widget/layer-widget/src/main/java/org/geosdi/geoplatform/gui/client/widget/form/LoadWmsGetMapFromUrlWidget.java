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
package org.geosdi.geoplatform.gui.client.widget.form;

import com.extjs.gxt.ui.client.Style.HorizontalAlignment;
import com.extjs.gxt.ui.client.event.ButtonEvent;
import com.extjs.gxt.ui.client.event.SelectionListener;
import com.extjs.gxt.ui.client.widget.button.Button;
import com.extjs.gxt.ui.client.widget.layout.MarginData;
import com.extjs.gxt.ui.client.widget.treepanel.TreePanel;
import com.google.gwt.event.shared.HandlerRegistration;
import com.google.gwt.user.client.ui.AbstractImagePrototype;
import org.geosdi.geoplatform.gui.client.BasicWidgetResources;
import org.geosdi.geoplatform.gui.client.LayerResources;
import org.geosdi.geoplatform.gui.client.config.WmsGetMapInjector;
import org.geosdi.geoplatform.gui.client.configuration.getmap.choise.mediator.GetMapChoiseMediator;
import org.geosdi.geoplatform.gui.client.i18n.LayerModuleConstants;
import org.geosdi.geoplatform.gui.client.i18n.buttons.ButtonsConstants;
import org.geosdi.geoplatform.gui.client.model.RasterTreeNode;
import org.geosdi.geoplatform.gui.client.widget.expander.GPLayerExpander;
import org.geosdi.geoplatform.gui.client.widget.tree.form.GPTreeFormWidget;
import org.geosdi.geoplatform.gui.model.tree.GPBeanTreeModel;
import org.geosdi.geoplatform.gui.puregwt.GPEventBus;
import org.geosdi.geoplatform.gui.puregwt.GPEventBusImpl;
import org.geosdi.geoplatform.gui.shared.util.GPSharedUtils;

/**
 *
 * @author Giuseppe La Scaleia - CNR IMAA geoSDI Group
 * @email giuseppe.lascaleia@geosdi.org
 *
 * @author Vincenzo Monteverde <vincenzo.monteverde@geosdi.org>
 */
public class LoadWmsGetMapFromUrlWidget extends GPTreeFormWidget<RasterTreeNode>
        implements LoadWmsGetMap {

    static {
        choiseMediator = WmsGetMapInjector.MainInjector.getInstance().getGetMapChoiseMediator();
    }

    static final GetMapChoiseMediator choiseMediator;
    private static final int BASE_WINDOW_HEIGHT = 140;
    private static final GPEventBus bus = new GPEventBusImpl();
    //
    private Button save;
    private Button cancel;
    private final GPLayerExpander expander;

    public LoadWmsGetMapFromUrlWidget(TreePanel<GPBeanTreeModel> theTree) {
        super(theTree, true);
        choiseMediator.bindWidget(this);
        this.expander = new GPLayerExpander(theTree, this);
        addWmsGetMapFromUrlHandler();
    }

    @Override
    public void addComponentToForm() {
        this.formPanel.add(choiseMediator.getGroupChoiseWidget(),
                new MarginData(5, 5, 5, 40));

        this.saveStatus = new WmsUrlStatus();
        this.saveStatus.setAutoWidth(true);

        this.formPanel.getButtonBar().add(this.saveStatus);
        this.formPanel.setButtonAlign(HorizontalAlignment.RIGHT);

        this.save = new Button(ButtonsConstants.INSTANCE.addText(),
                AbstractImagePrototype.create(LayerResources.ICONS.addRasterLayer()),
                new SelectionListener<ButtonEvent>() {

                    @Override
                    public void componentSelected(ButtonEvent ce) {
                        execute();
                    }

                });

        save.setEnabled(false);
        this.formPanel.addButton(save);

        this.cancel = new Button(ButtonsConstants.INSTANCE.cancelText(),
                AbstractImagePrototype.create(BasicWidgetResources.ICONS.cancel()),
                new SelectionListener<ButtonEvent>() {

                    @Override
                    public void componentSelected(ButtonEvent ce) {
                        reset();
                    }

                });

        this.formPanel.addButton(cancel);
    }

    @Override
    public void initSize() {
        setHeadingHtml(
                LayerModuleConstants.INSTANCE.LoadWmsGetMapFromUrlWidget_headingText());
        setSize(340, BASE_WINDOW_HEIGHT);
    }

    @Override
    public void initSizeFormPanel() {
        this.formPanel.setHeaderVisible(false);
        this.formPanel.setSize(290, 100);
    }

    @Override
    public void execute() {
        this.saveStatus.setBusy(LayerModuleConstants.INSTANCE.
                LoadWmsGetMapFromUrlWidget_statusAddingWMSText());

        choiseMediator.executeColleague(null);
    }

    @Override
    public void reset() {
        disableStatus();
        choiseMediator.resetColleague(null);
    }

    public void showForm() {
        this.expander.checkNodeState();
    }

    @Override
    public final HandlerRegistration addWmsGetMapFromUrlHandler() {
        return bus.addHandler(TYPE, this);
    }

    @Override
    public void correctStatus() {
        save.enable();
        setStatus(
                WmsUrlStatus.EnumWmsUrlStatus.STATUS_CHECKED.getValue(),
                WmsUrlStatus.EnumWmsUrlStatus.STATUS_MESSAGE_CHECKED.getValue());
    }

    @Override
    public void incorrectStatus(String statusMessage) {
        save.disable();
        if (!GPSharedUtils.isNotEmpty(statusMessage)) {
            statusMessage = WmsUrlStatus.EnumWmsUrlStatus.STATUS_MESSAGE_NOT_CHECKED.getValue();
        }
        setStatus(
                WmsUrlStatus.EnumWmsUrlStatus.STATUS_NO_CHECKED.getValue(),
                statusMessage);
    }

    @Override
    public void disableStatus() {
        this.save.disable();
        this.saveStatus.clearStatus("");
    }

    public static void fireWmsGetMapFromUrlEvent(WmsGetMapFromUrlEvent event) {
        bus.fireEvent(event);
    }

    @Override
    public void increaseWidgetHeight(int height) {
        this.formPanel.setHeight(height);
        super.setHeight(height);
        super.layout(true);
    }

    @Override
    public void resetWidgetHeight() {
        this.formPanel.setHeight(100);
        super.setHeight(BASE_WINDOW_HEIGHT);
        super.layout(true);
    }

    @Override
    public void hideWidget() {
        super.hide();
    }

    @Override
    protected final void notifyShow() {
        super.notifyShow();
        setSize(340, BASE_WINDOW_HEIGHT);
    }

}
