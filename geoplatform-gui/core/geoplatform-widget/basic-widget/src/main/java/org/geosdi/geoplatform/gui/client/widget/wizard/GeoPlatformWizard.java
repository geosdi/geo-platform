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
package org.geosdi.geoplatform.gui.client.widget.wizard;

import com.extjs.gxt.ui.client.event.ButtonEvent;
import com.extjs.gxt.ui.client.event.SelectionListener;
import com.extjs.gxt.ui.client.widget.Component;
import com.extjs.gxt.ui.client.widget.ContentPanel;
import com.extjs.gxt.ui.client.widget.Html;
import com.extjs.gxt.ui.client.widget.button.Button;
import com.extjs.gxt.ui.client.widget.layout.LayoutData;
import com.google.common.collect.Lists;
import com.google.gwt.user.client.ui.Widget;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.geosdi.geoplatform.gui.client.i18n.buttons.ButtonsConstants;
import org.geosdi.geoplatform.gui.client.widget.GeoPlatformWindow;

/**
 * @author Nazzareno Sileno - CNR IMAA geoSDI Group
 * @email nazzareno.sileno@geosdi.org
 */
public abstract class GeoPlatformWizard extends GeoPlatformWindow {

    protected final static Logger logger = Logger.getLogger("");
    private final static String WIZARD_ID = "wizardID";
    //
    private IWizardCommitAction commitAction;
    //
    private Button nextButton;
    private Button previuosButton;
    private Button commitButton = new Button(
            ButtonsConstants.INSTANCE.saveText());
    private List<GeoPlatformWizardPanel> wizardPanelsStack = Lists.
            <GeoPlatformWizardPanel>newArrayList();
    protected ContentPanel contentPanel;
    protected boolean closeForCommit;

    public GeoPlatformWizard(boolean lazy, String commitButtonName,
            IWizardCommitAction commitAction,
            List<GeoPlatformWizardPanel> wizardPanelsStack) {
        super(lazy);
        this.commitAction = commitAction;
        this.prepareWizardPanels(wizardPanelsStack.toArray(
                new GeoPlatformWizardPanel[]{}));
        this.wizardPanelsStack = wizardPanelsStack;
        this.commitButton.setHtml(commitButtonName);
    }

    public GeoPlatformWizard(boolean lazy, String commitButtonName,
            IWizardCommitAction finalAction) {
        super(lazy);
        this.commitAction = finalAction;
        this.commitButton.setHtml(commitButtonName);
    }

    private void prepareWizardPanels(GeoPlatformWizardPanel... panels) {
        for (GeoPlatformWizardPanel panel : panels) {
            panel.setId(WIZARD_ID);
        }
    }

    public void addPanel(GeoPlatformWizardPanel contentPanelWizard) {
        contentPanelWizard.setId(WIZARD_ID);
        this.wizardPanelsStack.add(contentPanelWizard);
    }

    @Override
    public final void addComponent() {
        logger.log(Level.FINEST, "Executing add Component on GeoPlatformWizard");
        this.contentPanel = new ContentPanel();
        this.contentPanel.setHeaderVisible(Boolean.FALSE);
        super.add(this.contentPanel);
    }

    protected void addButtons() {
        logger.log(Level.FINEST, "Executing addButtons on GeoPlatformWizard");
        this.previuosButton = new Button(ButtonsConstants.INSTANCE.prevText(),
                new SelectionListener<ButtonEvent>() {

                    @Override
                    public void componentSelected(ButtonEvent ce) {
                        Component component = contentPanel.getItemByItemId(
                                WIZARD_ID);
                        logger.info("Wizard prev button Component: " + component);
                        if (component != null) {
                            GeoPlatformWizardPanel panel = (GeoPlatformWizardPanel) component;
                            int indexPrevElement = wizardPanelsStack.indexOf(
                                    panel);
                            logger.info("Wizard indexPrevElement: " + indexPrevElement);
                            if (indexPrevElement > 0) {
                                GeoPlatformWizardPanel panelToDispay = wizardPanelsStack.get(
                                        --indexPrevElement);
                                placeWizardPanel(panelToDispay);
                                if (indexPrevElement == 0) {
                                    previuosButton.disable();
                                }
                                nextButton.enable();
                            }
                        }
                    }

                });
        this.previuosButton.disable();
        logger.log(Level.FINEST, "Created button previous on GeoPlatformWizard");
        this.nextButton = new Button(ButtonsConstants.INSTANCE.nextText(),
                new SelectionListener<ButtonEvent>() {

                    @Override
                    public void componentSelected(ButtonEvent ce) {
                        Component component = contentPanel.getItemByItemId(
                                WIZARD_ID);
                        if (component != null && ((GeoPlatformWizardPanel) component).isNextEnabled()) {
                            if (component instanceof GeoPlatformWizardOptionPanel) {
                                GeoPlatformWizardOptionPanel panel = (GeoPlatformWizardOptionPanel) component;
                                panel.onNextAction();
                                int currentPanelIndex = wizardPanelsStack.indexOf(panel);
                                wizardPanelsStack = wizardPanelsStack.subList(0, currentPanelIndex + 1);
                                prepareWizardPanels(panel.getNextPanels().toArray(
                                                new GeoPlatformWizardPanel[]{}));
                                wizardPanelsStack.addAll(panel.getNextPanels());
                                commitAction = panel.getCommitAction();
                            }

                            GeoPlatformWizardPanel panel = (GeoPlatformWizardPanel) component;
                            panel.onNextAction();
                            int indexPrevElement = wizardPanelsStack.indexOf(
                                    panel);
                            if (indexPrevElement < wizardPanelsStack.size() - 1) {
                                GeoPlatformWizardPanel panelToDispay = wizardPanelsStack.get(
                                        ++indexPrevElement);
                                placeWizardPanel(panelToDispay);
                                if (indexPrevElement == wizardPanelsStack.size() - 1
                                && !(panelToDispay instanceof GeoPlatformWizardOptionPanel)) {
                                    nextButton.disable();
                                }
                                previuosButton.enable();
                            }
                        }
                    }

                });
        logger.log(Level.FINEST, "Created button next on GeoPlatformWizard");
        this.commitButton.addSelectionListener(
                new SelectionListener<ButtonEvent>() {

                    @Override
                    public void componentSelected(ButtonEvent ce) {
                        GeoPlatformWizardPanel panel = (GeoPlatformWizardPanel) contentPanel.getItemByItemId(
                                WIZARD_ID);
                        if (panel != null && panel.executeCommitAction(
                                commitAction)) {
                            closeWindow(true);
                        }
                    }

                });
        logger.log(Level.FINEST, "Created commit next on GeoPlatformWizard");
        super.getButtonBar().add(this.previuosButton);
        super.getButtonBar().add(this.nextButton);
        super.getButtonBar().add(this.commitButton);

        logger.log(Level.FINEST, "Finished addButtons on GeoPlatformWizard");
    }

    private void placeWizardPanel(GeoPlatformWizardPanel panelToDisplay) {
        logger.log(Level.FINEST, "Placing wizardPanel on GeoPlatformWizard");
        this.removeWizardScreen();
        contentPanel.add(panelToDisplay.asWidget());
        panelToDisplay.onPreviewWizard();
        contentPanel.layout(Boolean.TRUE);
        logger.log(Level.FINEST,
                "Panel header: " + panelToDisplay.getHeadingHtml());
        logger.log(Level.FINEST,
                "Panel displayed is final: " + panelToDisplay.isCommitAction());
        if (panelToDisplay.isCommitAction()) {
            this.commitButton.enable();
        } else {
            this.commitButton.disable();
        }
        logger.log(Level.FINEST, "End Placing wizardPanel on GeoPlatformWizard");
    }

    private void removeWizardScreen() {
        logger.log(Level.FINEST, "Removing wizardScreen on GeoPlatformWizard");
        Component component = contentPanel.getItemByItemId(WIZARD_ID);
        if (component != null) {
            GeoPlatformWizardPanel panel = (GeoPlatformWizardPanel) component;
            contentPanel.remove(panel);
            logger.log(Level.INFO,
                    "Removed wizardScreen: on GeoPlatformWizard");
        } else {
            logger.log(Level.WARNING, "Component to remove not found on wizardScreen GeoPlatformWizard");
        }
    }

    @Override
    public void finalizeInitOperations() {
        logger.log(Level.FINEST,
                "Executing finalize Init Operations on GeoPlatformWizard");
        super.finalizeInitOperations();
        this.addButtons();
        this.showFirstWizardScreen();
        logger.log(Level.FINEST,
                "Finalized Init Operations on GeoPlatformWizard");
    }

    protected void showFirstWizardScreen() {
        logger.log(Level.FINEST, "Show first Wizard Screen on GeoPlatformWizard");
        if (this.wizardPanelsStack != null && !this.wizardPanelsStack.isEmpty()) {
            this.placeWizardPanel(this.wizardPanelsStack.get(0));
            this.nextButton.enable();
            this.previuosButton.disable();
            logger.log(Level.FINEST,
                    "After placing first panel on GeoPlatformWizard");
        }
    }

    @Override
    public void show() {
        logger.log(Level.FINEST, "Before Show on GeoPlatformWizard");
        super.show();
        this.showFirstWizardScreen();
        logger.log(Level.FINEST, "After Show on GeoPlatformWizard");
        this.closeForCommit = true;
    }

    @Override
    public void reset() {
        logger.log(Level.FINEST, "Executing reset on GeoPlatformWizard");
        super.reset();
        removeWizardScreen();
        for (GeoPlatformWizardPanel panel : this.wizardPanelsStack) {
            panel.reset(this.closeForCommit);
        }
    }

    public IWizardCommitAction getCommitAction() {
        return commitAction;
    }

    @Override
    public final boolean add(Widget widget) {
//        return super.add(widget);
        return false;
    }

    @Override
    public final boolean add(Widget widget, LayoutData layoutData) {
//        return super.add(widget, layoutData);
        return false;
    }

    @Override
    public final Html addText(String text) {
//        return super.addText(text);
        return new Html();
    }

    public abstract void closeWindow(boolean theCloseForCommit);

}
