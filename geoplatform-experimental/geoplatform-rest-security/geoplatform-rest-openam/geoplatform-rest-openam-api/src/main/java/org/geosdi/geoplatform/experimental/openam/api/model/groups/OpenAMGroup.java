package org.geosdi.geoplatform.experimental.openam.api.model.groups;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

/**
 * @author Giuseppe La Scaleia - CNR IMAA geoSDI Group
 * @email giuseppe.lascaleia@geosdi.org
 */
public class OpenAMGroup implements IOpenAMGroup {

    private static final long serialVersionUID = 400374974056913834L;
    //
    @JsonProperty(value = "username")
    private String groupName;
    @JsonProperty(value = "uniquemember")
    private List<String> uniqueMember;

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
        this.uniqueMember.add("uid=francesco.izzi@gmail.com,ou=people,dc=iam,dc=sirape,dc=it");
    }

    @Override
    public String toString() {
        return getClass().getSimpleName() + " {" +
                " groupName = '" + groupName + '\'' +
                ", uniqueMember = " + uniqueMember +
                '}';
    }
}
