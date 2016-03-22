package org.geosdi.geoplatform.experimental.openam.api.model.groups;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

/**
 * @author Giuseppe La Scaleia - CNR IMAA geoSDI Group
 * @email giuseppe.lascaleia@geosdi.org
 */
public class OpenAMGroupResponse implements IOpenAMGroupResponse {

    private static final long serialVersionUID = -6741552245205426270L;
    //
    @JsonProperty(value = "username")
    private String groupName;
    private String realm;
    private List<String> uniqueMember;
    private List<String> cn;
    private List<String> dn;
    @JsonProperty(value = "objectclass")
    private List<String> objectClass;
    @JsonProperty(value = "universalid")
    private List<String> universalId;

    /**
     * @return {@link String}
     */
    @Override
    public String getGroupName() {
        return this.groupName;
    }

    /**
     * @param theGroupName
     */
    @Override
    public void setGroupName(String theGroupName) {
        this.groupName = theGroupName;
    }

    /**
     * @return {@link String}
     */
    @Override
    public String getRealm() {
        return this.realm;
    }

    /**
     * @param theRealm
     */
    @Override
    public void setRealm(String theRealm) {
        this.realm = theRealm;
    }

    /**
     * @return {@link List <String>}
     */
    @Override
    public List<String> getUniqueMember() {
        return this.uniqueMember;
    }

    /**
     * @param theUniqueMember
     */
    @Override
    public void setUniqueMember(List<String> theUniqueMember) {
        this.uniqueMember = theUniqueMember;
    }

    /**
     * @return {@link List <String>}
     */
    @Override
    public List<String> getCn() {
        return this.cn;
    }

    /**
     * @param theCn
     */
    @Override
    public void setCn(List<String> theCn) {
        this.cn = theCn;
    }

    /**
     * @return {@link List <String>}
     */
    @Override
    public List<String> getDn() {
        return this.dn;
    }

    /**
     * @param theDn
     */
    @Override
    public void setDn(List<String> theDn) {
        this.dn = theDn;
    }

    /**
     * @return {@link List <String>}
     */
    @Override
    public List<String> getObjectClass() {
        return this.objectClass;
    }

    /**
     * @param theObjectClass
     */
    @Override
    public void setObjectClass(List<String> theObjectClass) {
        this.objectClass = theObjectClass;
    }

    /**
     * @return {@link List <String>}
     */
    @Override
    public List<String> getUniversalId() {
        return this.universalId;
    }

    /**
     * @param theUniversalId
     */
    @Override
    public void setUniversalId(List<String> theUniversalId) {
        this.universalId = theUniversalId;
    }

    @Override
    public String toString() {
        return getClass().getSimpleName() + " {" +
                " groupName = '" + groupName + '\'' +
                ", realm = '" + realm + '\'' +
                ", uniqueMember = " + uniqueMember +
                ", cn = " + cn +
                ", dn = " + dn +
                ", objectClass = " + objectClass +
                ", universalId = " + universalId +
                '}';
    }
}
