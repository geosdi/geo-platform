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
package org.geosdi.geoplatform.gui.client.model.memento.save;

import com.extjs.gxt.ui.client.Registry;
import com.google.common.collect.Maps;
import com.google.gwt.core.client.GWT;
import org.geosdi.geoplatform.gui.action.ISave;
import org.geosdi.geoplatform.gui.client.command.memento.basic.SendSharedProjectNotificationRequest;
import org.geosdi.geoplatform.gui.client.command.memento.basic.SendSharedProjectNotificationResponse;
import org.geosdi.geoplatform.gui.client.model.memento.puregwt.event.PeekCacheEvent;
import org.geosdi.geoplatform.gui.client.model.memento.save.storage.AbstractMementoOriginalProperties;
import org.geosdi.geoplatform.gui.command.api.ClientCommandDispatcher;
import org.geosdi.geoplatform.gui.command.api.GPClientCommand;
import org.geosdi.geoplatform.gui.configuration.users.options.member.UserSessionEnum;
import org.geosdi.geoplatform.gui.model.memento.GPCache;
import org.geosdi.geoplatform.gui.model.memento.IMemento;
import org.geosdi.geoplatform.gui.model.project.IGPClientProject;
import org.geosdi.geoplatform.gui.model.tree.GPBeanTreeModel;
import org.geosdi.geoplatform.gui.model.tree.LayerEvents;
import org.geosdi.geoplatform.gui.puregwt.layers.LayerHandlerManager;
import org.geosdi.geoplatform.gui.puregwt.xmpp.XMPPHandlerManager;
import org.geosdi.geoplatform.gui.puregwt.xmpp.handler.IXMPPNotifyMessageHandler;
import org.geosdi.geoplatform.gui.shared.XMPPSubjectEnum;

/**
 * @author Nazzareno Sileno - CNR IMAA geoSDI Group
 * @email nazzareno.sileno@geosdi.org
 */
public class GPMementoSaveShared extends GPCache<IMemento<ISave>> implements
        IMementoSave, IXMPPNotifyMessageHandler {

    private static final long serialVersionUID = -5458269761345444182L;
    //
    private final PeekCacheEvent peekCacheEvent;
    private ObservableGPLayerSaveCache observable;
    private boolean isAddedToXMPPHandler;

    public GPMementoSaveShared(ObservableGPLayerSaveCache observable, PeekCacheEvent peekCacheEvent) {
        this.observable = observable;
        this.peekCacheEvent = peekCacheEvent;
        this.observable.notifyObservers(LayerEvents.SAVE_CACHE_EMPTY);
        if (!isAddedToXMPPHandler) {
            XMPPHandlerManager.addHandler(TYPE, this);
            this.isAddedToXMPPHandler = Boolean.TRUE;
        }
    }

    @Override
    public AbstractMementoOriginalProperties copyOriginalProperties(GPBeanTreeModel element) {
        AbstractMementoOriginalProperties memento = MementoSaveBuilder.generateMementoOriginalProperties(element);
        memento.copyOriginalProperties(element);
        return memento;
    }

    @Override
    public void putOriginalPropertiesInCache(AbstractMementoOriginalProperties memento) {
        boolean treeInitialized = (Boolean) Registry.get(UserSessionEnum.TREE_LOADED.name());
        if (treeInitialized) {
            super.add(memento);
            if (super.size() == 1) {
                LayerHandlerManager.fireEvent(peekCacheEvent);
            }
        }
    }

    @Override
    public boolean add(IMemento<ISave> memento) {
        boolean treeInitialized = (Boolean) Registry.get(UserSessionEnum.TREE_LOADED.name());
        boolean condition = false;
        if (treeInitialized) {
            condition = super.add(memento);
            if (condition && super.size() == 1) {
                LayerHandlerManager.fireEvent(peekCacheEvent);
            }
        }
        return condition;
    }

    @Override
    public IMemento<ISave> poll() {
        IMemento<ISave> memento = super.poll();
        return memento;
    }

    @Override
    public void clear() {
        super.clear();
    }

    /**
     * @see super.peek()
     */
    @Override
    public IMemento<ISave> peek() {
        return super.peek();
    }

    @Override
    public boolean remove(Object o) {
        //TODO: fare in modo che il memento dia tutte le proprietà necessarie
        //per inviare il messaggio di notifica per lo share
        this.sendXMPPNotification();
        boolean operation = super.remove(o);
        return operation;
    }

    @Override
    public boolean isEmpty() {
        boolean result = true;
        if (!super.isEmpty()) {
            result = false;
        }
        return result;
    }

    @Override
    public void cleanOperationsRefToDeletedElement(GPBeanTreeModel gpBeanTreeModel) {
    }

    @Override
    public ObservableGPLayerSaveCache getObservable() {
        return this.observable;
    }

    @Override
    public void setObservable(ObservableGPLayerSaveCache observable) {
        this.observable = observable;
    }

    @Override
    public void notifyXMPPMessage() {
        this.sendXMPPNotification();
    }

    protected final void sendXMPPNotification() {
        IGPClientProject project = Registry.get(UserSessionEnum.CURRENT_PROJECT_ON_TREE.name());
        final SendSharedProjectNotificationRequest sharedProjectNotificationRequest = GWT.
                <SendSharedProjectNotificationRequest>create(SendSharedProjectNotificationRequest.class);

        sharedProjectNotificationRequest.setProjectId(project.getId());
        sharedProjectNotificationRequest.setAttributesMap(Maps.<String, String>newHashMap());
        sharedProjectNotificationRequest.setSubject(XMPPSubjectEnum.RELOAD_TREE);
        sharedProjectNotificationRequest.setText("Reload proj");

        ClientCommandDispatcher.getInstance().execute(
                new GPClientCommand<SendSharedProjectNotificationResponse>() {

                    private static final long serialVersionUID = -4111650393368278288L;

                    {
                        super.setCommandRequest(sharedProjectNotificationRequest);
                    }

                    @Override
                    public void onCommandSuccess(
                            SendSharedProjectNotificationResponse response) {
                                System.out.println("Send shared project notification On success");
                            }

                            @Override
                            public void onCommandFailure(Throwable exception) {
                                System.out.println("Send shared project notification On Fail");
                            }

                });
    }

}
