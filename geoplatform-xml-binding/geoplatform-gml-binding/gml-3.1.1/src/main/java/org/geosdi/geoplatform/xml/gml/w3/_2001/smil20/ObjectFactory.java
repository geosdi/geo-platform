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
package org.geosdi.geoplatform.xml.gml.w3._2001.smil20;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlElementDecl;
import javax.xml.bind.annotation.XmlRegistry;
import javax.xml.namespace.QName;
import org.geosdi.geoplatform.xml.gml.w3._2001.smil20.language.AnimateColorType;
import org.geosdi.geoplatform.xml.gml.w3._2001.smil20.language.AnimateMotionType;
import org.geosdi.geoplatform.xml.gml.w3._2001.smil20.language.AnimateType;
import org.geosdi.geoplatform.xml.gml.w3._2001.smil20.language.SetType;

/**
 * This object contains factory methods for each 
 * Java content interface and Java element interface 
 * generated in the org.w3._2001.smil20 package. 
 * <p>An ObjectFactory allows you to programatically 
 * construct new instances of the Java representation 
 * for XML content. The Java representation of XML 
 * content can consist of schema derived interfaces 
 * and classes representing the binding of schema 
 * type definitions, element declarations and model 
 * groups.  Factory methods for each of these are 
 * provided in this class.
 * 
 */
@XmlRegistry
public class ObjectFactory {

    private final static QName _AnimateMotion_QNAME = new QName(
            "http://www.w3.org/2001/SMIL20/", "animateMotion");
    private final static QName _Animate_QNAME = new QName(
            "http://www.w3.org/2001/SMIL20/", "animate");
    private final static QName _AnimateColor_QNAME = new QName(
            "http://www.w3.org/2001/SMIL20/", "animateColor");
    private final static QName _Set_QNAME = new QName(
            "http://www.w3.org/2001/SMIL20/", "set");

    /**
     * Create a new ObjectFactory that can be used to create new instances of schema derived classes for package: org.w3._2001.smil20
     * 
     */
    public ObjectFactory() {
    }

    /**
     * Create an instance of {@link AnimateMotionPrototype }
     * 
     */
    public AnimateMotionPrototype createAnimateMotionPrototype() {
        return new AnimateMotionPrototype();
    }

    /**
     * Create an instance of {@link AnimateColorPrototype }
     * 
     */
    public AnimateColorPrototype createAnimateColorPrototype() {
        return new AnimateColorPrototype();
    }

    /**
     * Create an instance of {@link SetPrototype }
     * 
     */
    public SetPrototype createSetPrototype() {
        return new SetPrototype();
    }

    /**
     * Create an instance of {@link AnimatePrototype }
     * 
     */
    public AnimatePrototype createAnimatePrototype() {
        return new AnimatePrototype();
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link AnimateMotionType }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.w3.org/2001/SMIL20/",
                    name = "animateMotion",
                    substitutionHeadNamespace = "http://www.w3.org/2001/SMIL20/Language",
                    substitutionHeadName = "animateMotion")
    public JAXBElement<AnimateMotionType> createAnimateMotion(AnimateMotionType value) {
        return new JAXBElement<AnimateMotionType>(_AnimateMotion_QNAME,
                AnimateMotionType.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link AnimateType }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.w3.org/2001/SMIL20/",
                    name = "animate",
                    substitutionHeadNamespace = "http://www.w3.org/2001/SMIL20/Language",
                    substitutionHeadName = "animate")
    public JAXBElement<AnimateType> createAnimate(AnimateType value) {
        return new JAXBElement<AnimateType>(_Animate_QNAME, AnimateType.class,
                null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link AnimateColorType }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.w3.org/2001/SMIL20/",
                    name = "animateColor",
                    substitutionHeadNamespace = "http://www.w3.org/2001/SMIL20/Language",
                    substitutionHeadName = "animateColor")
    public JAXBElement<AnimateColorType> createAnimateColor(AnimateColorType value) {
        return new JAXBElement<AnimateColorType>(_AnimateColor_QNAME,
                AnimateColorType.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link SetType }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.w3.org/2001/SMIL20/", name = "set",
                    substitutionHeadNamespace = "http://www.w3.org/2001/SMIL20/Language",
                    substitutionHeadName = "set")
    public JAXBElement<SetType> createSet(SetType value) {
        return new JAXBElement<SetType>(_Set_QNAME, SetType.class, null, value);
    }
}
