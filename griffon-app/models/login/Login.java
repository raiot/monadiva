package login;

import org.codehaus.griffon.runtime.core.AbstractObservable;

public class Login extends AbstractObservable {
    protected String userName;
    protected String password;
    protected String error;

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        firePropertyChange("userName", userName, this.userName = userName);
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        firePropertyChange("password", password, this.password = password);
    }

    public String getError() {
        return error;
    }

    public void setError(String error) {
        final String oldValue = this.error;
        firePropertyChange("error", oldValue, this.error = error);
    }
}
