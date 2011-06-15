//<editor-fold defaultstate="collapsed" desc="License">
/*
 *  geo-platform
 *  Rich webgis framework
 *  http://geo-plartform.org
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
//</editor-fold>
package org.geosdi.geoplatform.services;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import org.slf4j.LoggerFactory;
import org.slf4j.Logger;

import org.geosdi.geoplatform.responce.StyleDTO;
import org.geotools.data.ows.StyleImpl;
import org.geosdi.geoplatform.core.dao.GPServerDAO;
import org.geosdi.geoplatform.core.model.GPBBox;
import org.geosdi.geoplatform.core.model.GPCababilityType;
import org.geosdi.geoplatform.core.model.GPLayerInfo;
import org.geosdi.geoplatform.core.model.GeoPlatformServer;
import org.geosdi.geoplatform.exception.IllegalParameterFault;
import org.geosdi.geoplatform.exception.ResourceNotFoundFault;
import org.geosdi.geoplatform.request.RequestById;
import org.geosdi.geoplatform.responce.ShortLayerDTO;
import org.geosdi.geoplatform.responce.RasterLayerDTO;
import org.geosdi.geoplatform.responce.ServerDTO;
import org.geotools.data.ows.CRSEnvelope;
import org.geotools.data.ows.Layer;
import org.geotools.data.ows.Service;
import org.geotools.data.ows.WMSCapabilities;
import org.geotools.data.wms.WebMapServer;
import org.geotools.ows.ServiceException;
import org.opengis.metadata.citation.Contact;
import org.opengis.metadata.citation.ResponsibleParty;
import org.opengis.util.InternationalString;

/**
 * @author Francesco Izzi - CNR IMAA - geoSDI
 * 
 */
class WMSServiceImpl {

    final private Logger logger = LoggerFactory.getLogger(WMSServiceImpl.class);
    // DAO
    private GPServerDAO serverDao;

    /**
     * @param serverDao
     *            the serverDao to set
     */
    public void setServerDao(GPServerDAO serverDao) {
        this.serverDao = serverDao;
    }

    public long insertServer(GeoPlatformServer server) {
        /** IMPORTANT TO AVOID EXCEPTION IN DB FOR UNIQUE URL SERVER **/
        GeoPlatformServer serverSearch = serverDao.findByServerUrl(server.getServerUrl());
        if (serverSearch != null) {
            return serverSearch.getId();
        }

        serverDao.persist(server);
        return server.getId();
    }

    public long updateServer(GeoPlatformServer server)
            throws ResourceNotFoundFault, IllegalParameterFault {
        GeoPlatformServer orig = serverDao.find(server.getId());
        if (orig == null) {
            throw new ResourceNotFoundFault("Server not found", server.getId());
        }
        // Update all properties
        orig.setServerUrl(server.getServerUrl());
        orig.setName(server.getName());
        orig.setTitle(server.getTitle());
        orig.setAbstractServer(server.getAbstractServer());
        orig.setContactPerson(server.getContactPerson());
        orig.setContactOrganization(server.getContactOrganization());
        orig.setServerType(server.getServerType());

        serverDao.merge(orig);
        return orig.getId();
    }

    public boolean deleteServer(long idServer)
            throws ResourceNotFoundFault, IllegalParameterFault {
        GeoPlatformServer server = serverDao.find(idServer);
        if (server == null) {
            throw new ResourceNotFoundFault("Server not found", idServer);
        }

        return serverDao.remove(server);
    }

    public GeoPlatformServer getServerDetail(long idServer)
            throws ResourceNotFoundFault {
        GeoPlatformServer server = serverDao.find(idServer);
        if (server == null) {
            throw new ResourceNotFoundFault("Server not found", idServer);
        }

        return server;
    }

    public ServerDTO getShortServer(String serverUrl)
            throws ResourceNotFoundFault {
        GeoPlatformServer server = serverDao.findByServerUrl(serverUrl);
        if (server == null) {
            throw new ResourceNotFoundFault("Server not found " + serverUrl);
        }

        return new ServerDTO(server);
    }

    public List<ServerDTO> getServers() {
        List<GeoPlatformServer> found = serverDao.findAll();
        return convertToServerList(found);
    }

    public GeoPlatformServer getServerDetailByUrl(String serverUrl)
            throws ResourceNotFoundFault {
        GeoPlatformServer server = serverDao.findByServerUrl(serverUrl);
        if (server == null) {
            throw new ResourceNotFoundFault("Server not found by URL");
        }

        return server;
    }

    public ServerDTO getCapabilities(RequestById request)
            throws ResourceNotFoundFault {
        GeoPlatformServer server = serverDao.find(request.getId());
        if (server == null) {
            throw new ResourceNotFoundFault("Server has been deleted", request.getId());
        }

        WMSCapabilities wmsCapabilities = this.getWMSCapabilities(server.getServerUrl());

        ServerDTO serverDTO = new ServerDTO(server);
        List<ShortLayerDTO> layers = convertToLayerList(
                wmsCapabilities.getLayerList(), server.getServerUrl());
        serverDTO.setLayerList(layers);

        return serverDTO;
    }

    public ServerDTO saveServer(String serverUrl)
            throws ResourceNotFoundFault {
        ServerDTO serverDTO = null;
        WMSCapabilities wmsCapabilities = this.getWMSCapabilities(serverUrl);

        // Retrieve the server by URL
        GeoPlatformServer server = serverDao.findByServerUrl(serverUrl);
        if (server == null) { // Create and Save a new Server
            Service service = wmsCapabilities.getService();
            server = this.createWMSServerFromService(serverUrl, service);
            serverDao.persist(server);
        }
        serverDTO = new ServerDTO(server);
        List<ShortLayerDTO> layers = convertToLayerList(
                wmsCapabilities.getLayerList(), serverUrl);
        serverDTO.setLayerList(layers);

        return serverDTO;
    }

    private WMSCapabilities getWMSCapabilities(String urlServer)
            throws ResourceNotFoundFault {
        URL serverURL = null;
        WebMapServer wms = null;
        WMSCapabilities cap = null;

        try {
            serverURL = new URL(urlServer);
            wms = new WebMapServer(serverURL);
            cap = wms.getCapabilities();

        } catch (MalformedURLException e) {
            // TODO Auto-generated catch block
            logger.error("MalformedURLException: " + e);
            throw new ResourceNotFoundFault("MalformedURLException ", e);
        } catch (ServiceException e) {
            // TODO Auto-generated catch block
            logger.error("ServiceException: " + e);
            throw new ResourceNotFoundFault("ServiceException ", e);
        } catch (IOException e) {
            // TODO Auto-generated catch block
            logger.error("IOException: " + e);
            throw new ResourceNotFoundFault("IOException ", e);
        }
        return cap;
    }

    // TODO Correct mapping Layer to AbstractLayerDTO (as abstract class?)
    private List<ShortLayerDTO> convertToLayerList(List<Layer> layerList, String urlServer) {
        List<ShortLayerDTO> shortLayers = new ArrayList<ShortLayerDTO>(layerList.size());

        for (Layer layer : layerList) {
            RasterLayerDTO layerDTOIth = new RasterLayerDTO();

            layerDTOIth.setUrlServer(this.getUrlServer(urlServer));
            layerDTOIth.setName(layer.getName());
            layerDTOIth.setAbstractText(layer.get_abstract());
            layerDTOIth.setTitle(layer.getTitle());

            if (layer.getLatLonBoundingBox() != null) {
                layerDTOIth.setBbox(this.createBbox(layer.getLatLonBoundingBox()));
                layerDTOIth.setSrs("EPSG:4326");
            }

            // Set LayerInfo of Raster Ith
            GPLayerInfo layerInfo = new GPLayerInfo();
            layerInfo.setQueryable(layer.isQueryable());
            if (layer.getKeywords() != null) {
                List<String> keywordList = Arrays.asList(layer.getKeywords());
                if (keywordList.size() > 0) {
                    layerInfo.setKeywords(keywordList);
                }
            }
            layerDTOIth.setLayerInfo(layerInfo);

            // Set Styles of Raster Ith
            List<StyleImpl> stylesImpl = layer.getStyles();
            logger.info("\n*** Layer \"{}\" has {} StyleImpl ***",
                    layer.getTitle(), stylesImpl.size());

            List<StyleDTO> stylesDTO = this.createStyleDTOList(stylesImpl);
            layerDTOIth.setStyleList(stylesDTO);

            shortLayers.add(layerDTOIth);
        }

        return shortLayers;
    }

    private List<ServerDTO> convertToServerList(List<GeoPlatformServer> serverList) {
        List<ServerDTO> shortServers = new ArrayList<ServerDTO>(serverList.size());
        ServerDTO serverDTOIth = null;
        for (GeoPlatformServer server : serverList) {
            serverDTOIth = new ServerDTO(server);
            shortServers.add(serverDTOIth);
        }
        return shortServers;
    }

    private GeoPlatformServer createWMSServerFromService(String serverUrl,
            Service service) {
        GeoPlatformServer newServer = new GeoPlatformServer();
        newServer.setServerUrl(serverUrl);
        newServer.setServerType(GPCababilityType.WMS);
        newServer.setName(service.getName());
        newServer.setTitle(service.getTitle());
        newServer.setAbstractServer(service.get_abstract());
        ResponsibleParty party = service.getContactInformation();
        if (party != null) {
            Contact contact = party.getContactInfo();
            if (contact != null) {
                InternationalString is = contact.getContactInstructions();
                if (is != null) {
                    newServer.setContactPerson(is.toString());
                }
            }
            InternationalString is = party.getOrganisationName();
            if (is != null) {
                newServer.setContactOrganization(is.toString());
            }
        }
        return newServer;
    }

    private List<StyleDTO> createStyleDTOList(List<StyleImpl> stylesImpl) {
        List<StyleDTO> stylesDTO = new ArrayList<StyleDTO>(stylesImpl.size());
        for (StyleImpl style : stylesImpl) {
            StyleDTO styleDTO = new StyleDTO(style);
            stylesDTO.add(styleDTO);
            logger.trace("\n*** CONVERTED StyleDTO:\n{}\n***", styleDTO);
        }
        return stylesDTO;
    }

    private GPBBox createBbox(CRSEnvelope env) {
        return new GPBBox(env.getMinX(), env.getMinY(), env.getMaxX(),
                env.getMaxY());
    }

    private String getUrlServer(String urlServer) {
        int index = -1;
        if (urlServer.contains("mapserv.exe")
                || urlServer.contains("mapserver")
                || urlServer.contains("mapserv")) {
            index = urlServer.indexOf("&");
        } else {
            index = urlServer.indexOf("?");
//            index += 1;
        }
        if (index != -1) {
            String newUrl = urlServer.substring(0, index);
            return newUrl;
        }
        return urlServer;
    }
}
