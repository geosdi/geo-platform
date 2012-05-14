/*
 *  geo-platform
 *  Rich webgis framework
 *  http://geo-platform.org
 * ====================================================================
 *
 * Copyright (C) 2008-2011 geoSDI Group (CNR IMAA - Potenza - ITALY).
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
package org.geosdi.geoplatform.services;

import com.vividsolutions.jts.geom.Coordinate;
import com.vividsolutions.jts.geom.GeometryFactory;
import com.vividsolutions.jts.geom.LineString;
import com.vividsolutions.jts.geom.MultiLineString;
import com.vividsolutions.jts.io.ParseException;
import com.vividsolutions.jts.io.WKTReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import org.jivesoftware.smack.*;
import org.jivesoftware.smack.packet.Message;

/**
 * @author Nazzareno Sileno - CNR IMAA geoSDI Group
 * @email nazzareno.sileno@geosdi.org
 */
public class XMPPTracking implements MessageListener {

    XMPPConnection connection;

    public void login(String userName, String password) throws XMPPException {
        ConnectionConfiguration config = new ConnectionConfiguration(
                "150.145.133.75", 9090);
//                "localhost", 5222);
        connection = new XMPPConnection(config);

        connection.connect();
        connection.login(userName, password);
    }

    public void sendMessage(String message, String to) throws XMPPException {
        Chat chat = connection.getChatManager().createChat(to, this);
        chat.sendMessage(message);
    }

    public void displayBuddyList() {
        Roster roster = connection.getRoster();
        Collection<RosterEntry> entries = roster.getEntries();

        System.out.println("\n\n" + entries.size() + " buddy(ies):");
        for (RosterEntry r : entries) {
            System.out.println(r.getUser());
        }
    }

    public void disconnect() {
        connection.disconnect();
    }

    @Override
    public void processMessage(Chat chat, Message message) {
        if (message.getType() == Message.Type.chat) {
            System.out.println(chat.getParticipant() + " says: "
                    + message.getBody());
        }
    }

    public static void main(String args[]) throws XMPPException, IOException,
            InterruptedException {
        // declare variables
        XMPPTracking c = new XMPPTracking();

        // Enter your login information here
        c.login("mobile", "mobile");
        double lon = 12.46700;
        double lat = 41.88500;

        String WKT = "MULTILINESTRING((12.4201541 41.8811858,12.4196306 41.880387,12.4195909 41.880322),(12.4195909 41.880322,12.4183688 41.8807321),(12.4183688 41.8807321,12.4184461 41.8808727),(12.4184461 41.8808727,12.4176921 41.8812658),(12.4777475 41.8748904,12.4772692 41.8755831))";
        GeometryFactory geometryFactory = new GeometryFactory();

        WKTReader reader = new WKTReader(geometryFactory);
        MultiLineString mls = null;

        try {
            mls = (MultiLineString) reader.read(WKT);

            int N = mls.getNumGeometries();
            List<LineString> lineString = new ArrayList<LineString>();
            for (int i = 0; i < N; i++) {
                lineString.add((LineString) mls.getGeometryN(i));
            }
            c.displayBuddyList();
            for (int j = 0; j < lineString.size(); j++) {
                Coordinate[] coords = lineString.get(j).getCoordinateSequence().toCoordinateArray();
                for (int z = 0; z < coords.length; z++) {
                    Thread.sleep(3000);
                    System.out.println("" + coords[z].x + "," + coords[z].y);
                    c.sendMessage("" + coords[z].x + "," + coords[z].y,
                            "central@localhost");

                }
            }



        } catch (ParseException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        c.disconnect();
        System.exit(0);
    }
}
