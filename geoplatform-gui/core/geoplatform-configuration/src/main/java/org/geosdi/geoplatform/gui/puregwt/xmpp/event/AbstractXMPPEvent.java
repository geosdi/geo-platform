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
package org.geosdi.geoplatform.gui.puregwt.xmpp.event;

import com.google.common.collect.Maps;
import com.google.gwt.event.shared.EventHandler;
import com.google.gwt.event.shared.GwtEvent;
import java.util.Map;
import org.geosdi.geoplatform.gui.puregwt.xmpp.XMPPEventRepository;

/**
 * @author Nazzareno Sileno - CNR IMAA geoSDI Group
 * @email nazzareno.sileno@geosdi.org
 */
public abstract class AbstractXMPPEvent<T extends EventHandler> extends GwtEvent<T> {

    private final String messageSubject;
    private String messageBody;
    private Map<String, String> attributes;

    public AbstractXMPPEvent(String messageSubject) {
        this.messageSubject = messageSubject;
        this.attributes = Maps.<String, String>newHashMap();
        XMPPEventRepository.putEventMapping(messageSubject, this);
    }

    public String getMessageSubject() {
        return messageSubject;
    }

    public String getMessageBody() {
        return messageBody;
    }

    public void setMessageBody(String messageBody) {
        this.messageBody = messageBody;
    }

    public final String getMessageAttribute(final String attributeName) {
        return attributes.get(attributeName);
    }

    public Map<String, String> getMessageAttributes() {
        return attributes;
    }

    public boolean hasMessageAttribute(final String attributeName) {
        return attributes.containsKey(attributeName);
    }

    public final void setMessageAttribute(final String attributeName,
            final String attributeValue) {
        attributes.put(attributeName, attributeValue);
    }

    public void setAttributes(Map<String, String> attributes) {
        this.attributes = attributes;
    }
}
