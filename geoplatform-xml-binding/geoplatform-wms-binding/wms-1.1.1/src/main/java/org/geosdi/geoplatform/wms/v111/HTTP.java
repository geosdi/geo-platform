//
// Questo file è stato generato dall'architettura JavaTM per XML Binding (JAXB) Reference Implementation, v2.2.11 
// Vedere <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Qualsiasi modifica a questo file andrà persa durante la ricompilazione dello schema di origine. 
// Generato il: 2019.01.24 alle 07:06:40 PM CET 
//


package org.geosdi.geoplatform.wms.v111;

import org.jvnet.jaxb2_commons.lang.JAXBToStringStrategy;
import org.jvnet.jaxb2_commons.lang.ToString2;
import org.jvnet.jaxb2_commons.lang.ToStringStrategy2;
import org.jvnet.jaxb2_commons.locator.ObjectLocator;

import javax.xml.bind.annotation.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;


/**
 *
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "", propOrder = {
        "getOrPost"
})
@XmlRootElement(name = "HTTP")
public class HTTP implements Serializable, ToString2 {

    private final static long serialVersionUID = 1L;
    @XmlElements({
            @XmlElement(name = "Get", required = true, type = Get.class),
            @XmlElement(name = "Post", required = true, type = Post.class)
    })
    protected List<Serializable> getOrPost;

    /**
     * Gets the value of the getOrPost property.
     *
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the getOrPost property.
     *
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getGetOrPost().add(newItem);
     * </pre>
     *
     *
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link Get }
     * {@link Post }
     */
    public List<Serializable> getGetOrPost() {
        if (getOrPost == null) {
            getOrPost = new ArrayList<Serializable>();
        }
        return this.getOrPost;
    }

    public String toString() {
        final ToStringStrategy2 strategy = JAXBToStringStrategy.INSTANCE2;
        final StringBuilder buffer = new StringBuilder();
        append(null, buffer, strategy);
        return buffer.toString();
    }

    public StringBuilder append(ObjectLocator locator, StringBuilder buffer, ToStringStrategy2 strategy) {
        strategy.appendStart(locator, this, buffer);
        appendFields(locator, buffer, strategy);
        strategy.appendEnd(locator, this, buffer);
        return buffer;
    }

    public StringBuilder appendFields(ObjectLocator locator, StringBuilder buffer, ToStringStrategy2 strategy) {
        {
            List<Serializable> theGetOrPost;
            theGetOrPost = (((this.getOrPost != null) && (!this.getOrPost.isEmpty())) ? this.getGetOrPost() : null);
            strategy.appendField(locator, this, "getOrPost", buffer, theGetOrPost, ((this.getOrPost != null) && (!this.getOrPost.isEmpty())));
        }
        return buffer;
    }

    public void setGetOrPost(List<Serializable> value) {
        this.getOrPost = null;
        if (value != null) {
            List<Serializable> draftl = this.getGetOrPost();
            draftl.addAll(value);
        }
    }

}
