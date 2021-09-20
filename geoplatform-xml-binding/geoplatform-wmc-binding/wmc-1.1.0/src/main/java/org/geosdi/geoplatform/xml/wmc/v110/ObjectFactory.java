//
// Questo file è stato generato dall'architettura JavaTM per XML Binding (JAXB) Reference Implementation, v2.3.0 
// Vedere <a href="https://javaee.github.io/jaxb-v2/">https://javaee.github.io/jaxb-v2/</a> 
// Qualsiasi modifica a questo file andrà persa durante la ricompilazione dello schema di origine. 
// Generato il: 2021.09.20 alle 09:33:43 AM CEST 
//


package org.geosdi.geoplatform.xml.wmc.v110;

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
     * Create an instance of {@link ViewContextReferenceType }
     */
    public ViewContextReferenceType createViewContextReferenceType() {
        return new ViewContextReferenceType();
    }

    /**
     * Create an instance of {@link ContextURLType }
     */
    public ContextURLType createContextURLType() {
        return new ContextURLType();
    }

    /**
     * Create an instance of {@link OnlineResourceType }
     */
    public OnlineResourceType createOnlineResourceType() {
        return new OnlineResourceType();
    }

    /**
     * Create an instance of {@link GeneralType }
     */
    public GeneralType createGeneralType() {
        return new GeneralType();
    }

    /**
     * Create an instance of {@link WindowType }
     */
    public WindowType createWindowType() {
        return new WindowType();
    }

    /**
     * Create an instance of {@link BoundingBoxType }
     */
    public BoundingBoxType createBoundingBoxType() {
        return new BoundingBoxType();
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
     * Create an instance of {@link ContactInformationType }
     */
    public ContactInformationType createContactInformationType() {
        return new ContactInformationType();
    }

    /**
     * Create an instance of {@link ContactPersonPrimaryType }
     */
    public ContactPersonPrimaryType createContactPersonPrimaryType() {
        return new ContactPersonPrimaryType();
    }

    /**
     * Create an instance of {@link AddressType }
     */
    public AddressType createAddressType() {
        return new AddressType();
    }

    /**
     * Create an instance of {@link LayerListType }
     */
    public LayerListType createLayerListType() {
        return new LayerListType();
    }

    /**
     * Create an instance of {@link LayerType }
     */
    public LayerType createLayerType() {
        return new LayerType();
    }

    /**
     * Create an instance of {@link ServerType }
     */
    public ServerType createServerType() {
        return new ServerType();
    }

    /**
     * Create an instance of {@link FormatListType }
     */
    public FormatListType createFormatListType() {
        return new FormatListType();
    }

    /**
     * Create an instance of {@link FormatType }
     */
    public FormatType createFormatType() {
        return new FormatType();
    }

    /**
     * Create an instance of {@link StyleListType }
     */
    public StyleListType createStyleListType() {
        return new StyleListType();
    }

    /**
     * Create an instance of {@link StyleType }
     */
    public StyleType createStyleType() {
        return new StyleType();
    }

    /**
     * Create an instance of {@link SLDType }
     */
    public SLDType createSLDType() {
        return new SLDType();
    }

    /**
     * Create an instance of {@link DimensionListType }
     */
    public DimensionListType createDimensionListType() {
        return new DimensionListType();
    }

    /**
     * Create an instance of {@link DimensionType }
     */
    public DimensionType createDimensionType() {
        return new DimensionType();
    }

    /**
     * Create an instance of {@link ExtensionType }
     */
    public ExtensionType createExtensionType() {
        return new ExtensionType();
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link ViewContextCollectionType }{@code >}
     *
     * @param value Java instance representing xml element's value.
     * @return the new instance of {@link JAXBElement }{@code <}{@link ViewContextCollectionType }{@code >}
     */
    @XmlElementDecl(namespace = "http://www.opengis.net/context", name = "ViewContextCollection")
    public JAXBElement<ViewContextCollectionType> createViewContextCollection(ViewContextCollectionType value) {
        return new JAXBElement<ViewContextCollectionType>(_ViewContextCollection_QNAME, ViewContextCollectionType.class,
                null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link ViewContextType }{@code >}
     *
     * @param value Java instance representing xml element's value.
     * @return the new instance of {@link JAXBElement }{@code <}{@link ViewContextType }{@code >}
     */
    @XmlElementDecl(namespace = "http://www.opengis.net/context", name = "ViewContext")
    public JAXBElement<ViewContextType> createViewContext(ViewContextType value) {
        return new JAXBElement<ViewContextType>(_ViewContext_QNAME, ViewContextType.class, null, value);
    }

}
