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
//
// Questo file è stato generato dall'architettura JavaTM per XML Binding (JAXB) Reference Implementation, v2.2.7 
// Vedere <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Qualsiasi modifica a questo file andrà persa durante la ricompilazione dello schema di origine. 
// Generato il: 2015.08.25 alle 11:22:41 PM CEST 
//


package org.geosdi.geoplatform.xml.wmc.v110;

import org.geosdi.geoplatform.xml.wmc.v110.ol.*;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlElementDecl;
import javax.xml.bind.annotation.XmlRegistry;
import javax.xml.namespace.QName;


/**
 * This object contains factory methods for each
 * Java content interface and Java element interface
 * generated in the org.geosdi.geoplatform.xml.wmc.v110 package.
 * <p>An ObjectFactory allows you to programatically
 * construct new instances of the Java representation
 * for XML content. The Java representation of XML
 * content can consist of schema derived interfaces
 * and classes representing the binding of schema
 * type definitions, element declarations and model
 * groups.  Factory methods for each of these are
 * provided in this class.
 */
@XmlRegistry
public class ObjectFactory {

    private final static QName _ViewContextCollection_QNAME = new QName("http://www.opengis.net/context", "ViewContextCollection");
    private final static QName _ViewContext_QNAME = new QName("http://www.opengis.net/context", "ViewContext");

    /**
     * Create a new ObjectFactory that can be used to create new instances of schema derived classes for package: org.geosdi.geoplatform.xml.wmc.v110
     */
    public ObjectFactory() {
    }

    /**
     * Create an instance of {@link ViewContextCollectionType }
     */
    public ViewContextCollectionType createViewContextCollectionType() {
        return new ViewContextCollectionType();
    }

    /**
     * Create an instance of {@link ViewContextType }
     */
    public ViewContextType createViewContextType() {
        return new ViewContextType();
    }

    /**
     * Create an instance of {@link LayerListType }
     */
    public LayerListType createLayerListType() {
        return new LayerListType();
    }

    /**
     * Create an instance of {@link OnlineResourceType }
     */
    public OnlineResourceType createOnlineResourceType() {
        return new OnlineResourceType();
    }

    /**
     * Create an instance of {@link ViewContextReferenceType }
     */
    public ViewContextReferenceType createViewContextReferenceType() {
        return new ViewContextReferenceType();
    }

    /**
     * Create an instance of {@link GeneralType }
     */
    public GeneralType createGeneralType() {
        return new GeneralType();
    }

    /**
     * Create an instance of {@link KeywordListType }
     */
    public KeywordListType createKeywordListType() {
        return new KeywordListType();
    }

    /**
     * Create an instance of {@link URLType }
     */
    public URLType createURLType() {
        return new URLType();
    }

    /**
     * Create an instance of {@link ContextURLType }
     */
    public ContextURLType createContextURLType() {
        return new ContextURLType();
    }

    /**
     * Create an instance of {@link ExtensionType }
     */
    public ExtensionType createExtensionType() {
        return new ExtensionType();
    }

    /**
     * Create an instance of {@link DimensionType }
     */
    public DimensionType createDimensionType() {
        return new DimensionType();
    }

    /**
     * Create an instance of {@link DimensionListType }
     */
    public DimensionListType createDimensionListType() {
        return new DimensionListType();
    }

    /**
     * Create an instance of {@link WindowType }
     */
    public WindowType createWindowType() {
        return new WindowType();
    }

    /**
     * Create an instance of {@link ContactPersonPrimaryType }
     */
    public ContactPersonPrimaryType createContactPersonPrimaryType() {
        return new ContactPersonPrimaryType();
    }

    /**
     * Create an instance of {@link FormatListType }
     */
    public FormatListType createFormatListType() {
        return new FormatListType();
    }

    /**
     * Create an instance of {@link BoundingBoxType }
     */
    public BoundingBoxType createBoundingBoxType() {
        return new BoundingBoxType();
    }

    /**
     * Create an instance of {@link AddressType }
     */
    public AddressType createAddressType() {
        return new AddressType();
    }

    /**
     * Create an instance of {@link ServerType }
     */
    public ServerType createServerType() {
        return new ServerType();
    }

    /**
     * Create an instance of {@link LayerType }
     */
    public LayerType createLayerType() {
        return new LayerType();
    }

    /**
     * Create an instance of {@link SLDType }
     */
    public SLDType createSLDType() {
        return new SLDType();
    }

    /**
     * Create an instance of {@link StyleListType }
     */
    public StyleListType createStyleListType() {
        return new StyleListType();
    }

    /**
     * Create an instance of {@link ContactInformationType }
     */
    public ContactInformationType createContactInformationType() {
        return new ContactInformationType();
    }

    /**
     * Create an instance of {@link StyleType }
     */
    public StyleType createStyleType() {
        return new StyleType();
    }

    /**
     * Create an instance of {@link FormatType }
     */
    public FormatType createFormatType() {
        return new FormatType();
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link ViewContextCollectionType }{@code >}}
     */
    @XmlElementDecl(namespace = "http://www.opengis.net/context", name = "ViewContextCollection")
    public JAXBElement<ViewContextCollectionType> createViewContextCollection(ViewContextCollectionType value) {
        return new JAXBElement<ViewContextCollectionType>(_ViewContextCollection_QNAME, ViewContextCollectionType.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link ViewContextType }{@code >}}
     */
    @XmlElementDecl(namespace = "http://www.opengis.net/context", name = "ViewContext")
    public JAXBElement<ViewContextType> createViewContext(ViewContextType value) {
        return new JAXBElement<ViewContextType>(_ViewContext_QNAME, ViewContextType.class, null, value);
    }

    /** OL Section **/

    @XmlElementDecl(name = "maxExtent", namespace = "http://openlayers.org/context")
    public JAXBElement<MaxExtent> createMaxExtent(MaxExtent olMaxExtent) {
        return new JAXBElement<MaxExtent>(new QName("maxExtent"), MaxExtent.class, olMaxExtent);
    }

    @XmlElementDecl(name = "tileSize", namespace = "http://openlayers.org/context")
    public JAXBElement<TileSize> createTileSize(TileSize olTileSize) {
        return new JAXBElement<TileSize>(new QName("tileSize"), TileSize.class, olTileSize);
    }

    @XmlElementDecl(name = "transparent", namespace = "http://openlayers.org/context")
    public JAXBElement<Transparent> createTransparent(Transparent olTransparent) {
        return new JAXBElement<Transparent>(new QName("transparent"), Transparent.class, olTransparent);
    }

    @XmlElementDecl(name = "numZoomLevels", namespace = "http://openlayers.org/context")
    public JAXBElement<NumZoomLevels> createNumZoomLevels(NumZoomLevels olNumZoomLevels) {
        return new JAXBElement<NumZoomLevels>(new QName("numZoomLevels"), NumZoomLevels.class, olNumZoomLevels);
    }

    @XmlElementDecl(name = "units", namespace = "http://openlayers.org/context")
    public JAXBElement<Units> createUnits(Units olUnits) {
        return new JAXBElement<Units>(new QName("units"), Units.class, olUnits);
    }

    @XmlElementDecl(name = "isBaseLayer", namespace = "http://openlayers.org/context")
    public JAXBElement<BaseLayer> createBaseLayer(BaseLayer olBaseLayer) {
        return new JAXBElement<BaseLayer>(new QName("isBaseLayer"), BaseLayer.class, olBaseLayer);
    }

    @XmlElementDecl(name = "displayInLayerSwitcher", namespace = "http://openlayers.org/context")
    public JAXBElement<DisplayInLayerSwitcher> createDisplayInLayerSwitcher(DisplayInLayerSwitcher olDisplayInLayerSwitcher) {
        return new JAXBElement<DisplayInLayerSwitcher>(new QName("displayInLayerSwitcher"), DisplayInLayerSwitcher.class, olDisplayInLayerSwitcher);
    }

    @XmlElementDecl(name = "singleTile", namespace = "http://openlayers.org/context")
    public JAXBElement<SingleTile> createSingleTile(SingleTile olSingleTile) {
        return new JAXBElement<SingleTile>(new QName("singleTile"), SingleTile.class, olSingleTile);
    }
}
