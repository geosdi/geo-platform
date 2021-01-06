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
package org.geosdi.geoplatform.xml.gml.w3._2001.smil20.language;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAnyAttribute;
import javax.xml.bind.annotation.XmlAnyElement;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlID;
import javax.xml.bind.annotation.XmlIDREF;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlType;
import javax.xml.bind.annotation.adapters.CollapsedStringAdapter;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;
import javax.xml.namespace.QName;
import org.geosdi.geoplatform.xml.gml.w3._2001.smil20.AnimateColorPrototype;
import org.geosdi.geoplatform.xml.gml.w3._2001.smil20.FillDefaultType;
import org.geosdi.geoplatform.xml.gml.w3._2001.smil20.FillTimingAttrsType;
import org.geosdi.geoplatform.xml.gml.w3._2001.smil20.RestartDefaultType;
import org.geosdi.geoplatform.xml.gml.w3._2001.smil20.RestartTimingType;
import org.geosdi.geoplatform.xml.gml.w3._2001.smil20.SyncBehaviorDefaultType;
import org.geosdi.geoplatform.xml.gml.w3._2001.smil20.SyncBehaviorType;
import org.w3c.dom.Element;

/**
 * <p>Java class for animateColorType complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="animateColorType">
 *   &lt;complexContent>
 *     &lt;extension base="{http://www.w3.org/2001/SMIL20/}animateColorPrototype">
 *       &lt;choice maxOccurs="unbounded" minOccurs="0">
 *         &lt;any processContents='lax' namespace='##other'/>
 *       &lt;/choice>
 *       &lt;attGroup ref="{http://www.w3.org/2001/SMIL20/}animTargetAttrs"/>
 *       &lt;attGroup ref="{http://www.w3.org/2001/SMIL20/}animModeAttrs"/>
 *       &lt;attGroup ref="{http://www.w3.org/2001/SMIL20/Language}TimingAttrs"/>
 *       &lt;attGroup ref="{http://www.w3.org/2001/SMIL20/Language}CoreAttrs"/>
 *       &lt;attGroup ref="{http://www.w3.org/2001/SMIL20/}skipContentAttrs"/>
 *       &lt;anyAttribute/>
 *     &lt;/extension>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "animateColorType", propOrder = {
    "any"
})
public class AnimateColorType extends AnimateColorPrototype {

    @XmlAnyElement(lax = true)
    private List<Object> any;
    @XmlAttribute(name = "targetElement")
    @XmlIDREF
    @XmlSchemaType(name = "IDREF")
    protected Object targetElement;
    @XmlAttribute(name = "calcMode")
    protected String calcMode;
    @XmlAttribute(name = "restartDefault")
    protected RestartDefaultType restartDefault;
    @XmlAttribute(name = "syncBehavior")
    protected SyncBehaviorType syncBehavior;
    @XmlAttribute(name = "syncTolerance")
    protected String syncTolerance;
    @XmlAttribute(name = "repeatDur")
    protected String repeatDur;
    @XmlAttribute(name = "repeatCount")
    protected BigDecimal repeatCount;
    @XmlAttribute(name = "begin")
    protected String begin;
    @XmlAttribute(name = "end")
    protected String end;
    @XmlAttribute(name = "min")
    protected String min;
    @XmlAttribute(name = "max")
    protected String max;
    @XmlAttribute(name = "dur")
    protected String dur;
    @XmlAttribute(name = "repeat")
    @XmlSchemaType(name = "nonNegativeInteger")
    protected BigInteger repeat;
    @XmlAttribute(name = "fill")
    protected FillTimingAttrsType fill;
    @XmlAttribute(name = "syncBehaviorDefault")
    protected SyncBehaviorDefaultType syncBehaviorDefault;
    @XmlAttribute(name = "syncToleranceDefault")
    protected String syncToleranceDefault;
    @XmlAttribute(name = "fillDefault")
    protected FillDefaultType fillDefault;
    @XmlAttribute(name = "restart")
    protected RestartTimingType restart;
    @XmlAttribute(name = "id")
    @XmlJavaTypeAdapter(CollapsedStringAdapter.class)
    @XmlID
    @XmlSchemaType(name = "ID")
    protected String id;
    @XmlAttribute(name = "class")
    protected String clazz;
    @XmlAttribute(name = "lang",
                  namespace = "http://www.w3.org/XML/1998/namespace")
    @XmlJavaTypeAdapter(CollapsedStringAdapter.class)
    @XmlSchemaType(name = "language")
    protected String lang;
    @XmlAttribute(name = "alt")
    protected String alt;
    @XmlAttribute(name = "longdesc")
    @XmlSchemaType(name = "anyURI")
    protected String longdesc;
    @XmlAttribute(name = "skip-content")
    protected Boolean skipContent;
    @XmlAnyAttribute
    private Map<QName, String> otherAttributes = new HashMap<QName, String>();

    public AnimateColorType() {
    }

    /**
     * @param any the any to set
     */
    public void setAny(List<Object> any) {
        this.any = any;
    }

    /**
     * Gets the value of the any property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the any property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getAny().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link Object }
     * {@link Element }
     * 
     * 
     */
    public List<Object> getAny() {
        if (any == null) {
            setAny(new ArrayList<Object>());
        }
        return this.any;
    }

    /**
     * Gets the value of the targetElement property.
     * 
     * @return
     *     possible object is
     *     {@link Object }
     *     
     */
    public Object getTargetElement() {
        return targetElement;
    }

    /**
     * Sets the value of the targetElement property.
     * 
     * @param value
     *     allowed object is
     *     {@link Object }
     *     
     */
    public void setTargetElement(Object value) {
        this.targetElement = value;
    }

    /**
     * Gets the value of the calcMode property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCalcMode() {
        if (calcMode == null) {
            return "linear";
        } else {
            return calcMode;
        }
    }

    /**
     * Sets the value of the calcMode property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCalcMode(String value) {
        this.calcMode = value;
    }

    /**
     * Gets the value of the restartDefault property.
     * 
     * @return
     *     possible object is
     *     {@link RestartDefaultType }
     *     
     */
    public RestartDefaultType getRestartDefault() {
        if (restartDefault == null) {
            return RestartDefaultType.INHERIT;
        } else {
            return restartDefault;
        }
    }

    /**
     * Sets the value of the restartDefault property.
     * 
     * @param value
     *     allowed object is
     *     {@link RestartDefaultType }
     *     
     */
    public void setRestartDefault(RestartDefaultType value) {
        this.restartDefault = value;
    }

    /**
     * Gets the value of the syncBehavior property.
     * 
     * @return
     *     possible object is
     *     {@link SyncBehaviorType }
     *     
     */
    public SyncBehaviorType getSyncBehavior() {
        if (syncBehavior == null) {
            return SyncBehaviorType.DEFAULT;
        } else {
            return syncBehavior;
        }
    }

    /**
     * Sets the value of the syncBehavior property.
     * 
     * @param value
     *     allowed object is
     *     {@link SyncBehaviorType }
     *     
     */
    public void setSyncBehavior(SyncBehaviorType value) {
        this.syncBehavior = value;
    }

    /**
     * Gets the value of the syncTolerance property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getSyncTolerance() {
        return syncTolerance;
    }

    /**
     * Sets the value of the syncTolerance property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setSyncTolerance(String value) {
        this.syncTolerance = value;
    }

    /**
     * Gets the value of the repeatDur property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getRepeatDur() {
        return repeatDur;
    }

    /**
     * Sets the value of the repeatDur property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setRepeatDur(String value) {
        this.repeatDur = value;
    }

    /**
     * Gets the value of the repeatCount property.
     * 
     * @return
     *     possible object is
     *     {@link BigDecimal }
     *     
     */
    public BigDecimal getRepeatCount() {
        return repeatCount;
    }

    /**
     * Sets the value of the repeatCount property.
     * 
     * @param value
     *     allowed object is
     *     {@link BigDecimal }
     *     
     */
    public void setRepeatCount(BigDecimal value) {
        this.repeatCount = value;
    }

    /**
     * Gets the value of the begin property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getBegin() {
        return begin;
    }

    /**
     * Sets the value of the begin property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setBegin(String value) {
        this.begin = value;
    }

    /**
     * Gets the value of the end property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getEnd() {
        return end;
    }

    /**
     * Sets the value of the end property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setEnd(String value) {
        this.end = value;
    }

    /**
     * Gets the value of the min property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getMin() {
        return min;
    }

    /**
     * Sets the value of the min property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setMin(String value) {
        this.min = value;
    }

    /**
     * Gets the value of the max property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getMax() {
        return max;
    }

    /**
     * Sets the value of the max property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setMax(String value) {
        this.max = value;
    }

    /**
     * Gets the value of the dur property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getDur() {
        return dur;
    }

    /**
     * Sets the value of the dur property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setDur(String value) {
        this.dur = value;
    }

    /**
     * Gets the value of the repeat property.
     * 
     * @return
     *     possible object is
     *     {@link BigInteger }
     *     
     */
    public BigInteger getRepeat() {
        return repeat;
    }

    /**
     * Sets the value of the repeat property.
     * 
     * @param value
     *     allowed object is
     *     {@link BigInteger }
     *     
     */
    public void setRepeat(BigInteger value) {
        this.repeat = value;
    }

    /**
     * Gets the value of the fill property.
     * 
     * @return
     *     possible object is
     *     {@link FillTimingAttrsType }
     *     
     */
    public FillTimingAttrsType getFill() {
        if (fill == null) {
            return FillTimingAttrsType.DEFAULT;
        } else {
            return fill;
        }
    }

    /**
     * Sets the value of the fill property.
     * 
     * @param value
     *     allowed object is
     *     {@link FillTimingAttrsType }
     *     
     */
    public void setFill(FillTimingAttrsType value) {
        this.fill = value;
    }

    /**
     * Gets the value of the syncBehaviorDefault property.
     * 
     * @return
     *     possible object is
     *     {@link SyncBehaviorDefaultType }
     *     
     */
    public SyncBehaviorDefaultType getSyncBehaviorDefault() {
        if (syncBehaviorDefault == null) {
            return SyncBehaviorDefaultType.INHERIT;
        } else {
            return syncBehaviorDefault;
        }
    }

    /**
     * Sets the value of the syncBehaviorDefault property.
     * 
     * @param value
     *     allowed object is
     *     {@link SyncBehaviorDefaultType }
     *     
     */
    public void setSyncBehaviorDefault(SyncBehaviorDefaultType value) {
        this.syncBehaviorDefault = value;
    }

    /**
     * Gets the value of the syncToleranceDefault property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getSyncToleranceDefault() {
        if (syncToleranceDefault == null) {
            return "inherit";
        } else {
            return syncToleranceDefault;
        }
    }

    /**
     * Sets the value of the syncToleranceDefault property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setSyncToleranceDefault(String value) {
        this.syncToleranceDefault = value;
    }

    /**
     * Gets the value of the fillDefault property.
     * 
     * @return
     *     possible object is
     *     {@link FillDefaultType }
     *     
     */
    public FillDefaultType getFillDefault() {
        if (fillDefault == null) {
            return FillDefaultType.INHERIT;
        } else {
            return fillDefault;
        }
    }

    /**
     * Sets the value of the fillDefault property.
     * 
     * @param value
     *     allowed object is
     *     {@link FillDefaultType }
     *     
     */
    public void setFillDefault(FillDefaultType value) {
        this.fillDefault = value;
    }

    /**
     * Gets the value of the restart property.
     * 
     * @return
     *     possible object is
     *     {@link RestartTimingType }
     *     
     */
    public RestartTimingType getRestart() {
        if (restart == null) {
            return RestartTimingType.DEFAULT;
        } else {
            return restart;
        }
    }

    /**
     * Sets the value of the restart property.
     * 
     * @param value
     *     allowed object is
     *     {@link RestartTimingType }
     *     
     */
    public void setRestart(RestartTimingType value) {
        this.restart = value;
    }

    /**
     * Gets the value of the id property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getId() {
        return id;
    }

    /**
     * Sets the value of the id property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setId(String value) {
        this.id = value;
    }

    /**
     * Gets the value of the clazz property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getClazz() {
        return clazz;
    }

    /**
     * Sets the value of the clazz property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setClazz(String value) {
        this.clazz = value;
    }

    /**
     * Gets the value of the lang property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getLang() {
        return lang;
    }

    /**
     * Sets the value of the lang property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setLang(String value) {
        this.lang = value;
    }

    /**
     * Gets the value of the alt property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getAlt() {
        return alt;
    }

    /**
     * Sets the value of the alt property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setAlt(String value) {
        this.alt = value;
    }

    /**
     * Gets the value of the longdesc property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getLongdesc() {
        return longdesc;
    }

    /**
     * Sets the value of the longdesc property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setLongdesc(String value) {
        this.longdesc = value;
    }

    /**
     * Gets the value of the skipContent property.
     * 
     * @return
     *     possible object is
     *     {@link Boolean }
     *     
     */
    public boolean isSkipContent() {
        if (skipContent == null) {
            return true;
        } else {
            return skipContent;
        }
    }

    /**
     * Sets the value of the skipContent property.
     * 
     * @param value
     *     allowed object is
     *     {@link Boolean }
     *     
     */
    public void setSkipContent(Boolean value) {
        this.skipContent = value;
    }

    /**
     * Gets a map that contains attributes that aren't bound to any typed property on this class.
     * 
     * <p>
     * the map is keyed by the name of the attribute and 
     * the value is the string value of the attribute.
     * 
     * the map returned by this method is live, and you can add new attribute
     * by updating the map directly. Because of this design, there's no setter.
     * 
     * 
     * @return
     *     always non-null
     */
    public Map<QName, String> getOtherAttributes() {
        return otherAttributes;
    }

    @Override
    public String toString() {
        return "AnimateColorType{ " + "any = " + any + ", targetElement = "
                + targetElement + ", calcMode = " + calcMode
                + ", restartDefault = " + restartDefault + ", syncBehavior = "
                + syncBehavior + ", syncTolerance = " + syncTolerance
                + ", repeatDur = " + repeatDur + ", repeatCount = "
                + repeatCount + ", begin = " + begin + ", end = " + end
                + ", min = " + min + ", max = " + max + ", dur = " + dur
                + ", repeat = " + repeat + ", fill = " + fill
                + ", syncBehaviorDefault = " + syncBehaviorDefault
                + ", syncToleranceDefault = " + syncToleranceDefault
                + ", fillDefault = " + fillDefault + ", restart = "
                + restart + ", id = " + id + ", clazz = " + clazz
                + ", lang = " + lang + ", alt = " + alt + ", longdesc = "
                + longdesc + ", skipContent = " + skipContent
                + ", otherAttributes = " + otherAttributes + '}';
    }
}
