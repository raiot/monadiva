package shared.users;

import griffon.core.artifact.GriffonModel;
import griffon.metadata.ArtifactProviderFor;
import org.codehaus.griffon.runtime.core.artifact.AbstractGriffonModel;

@ArtifactProviderFor(GriffonModel.class)
public class User extends AbstractGriffonModel {
    private String name;
    private String lastName;
    private UserPrivilege privilege;
    private String address;
    private String phone;
    private char[] password;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public UserPrivilege getPrivilege() {
        return privilege;
    }

    public void setPrivilege(UserPrivilege privilege) {
        this.privilege = privilege;
    }

    public void setPassword(char[] password) {
        this.password = password;
    }

    public char[] getPassword() {
        return password;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }
}
