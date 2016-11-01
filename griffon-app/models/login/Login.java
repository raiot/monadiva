package login;

import org.codehaus.griffon.runtime.core.AbstractObservable;

class Login extends AbstractObservable {
    private String userName;
    private String password;
    private String error;
    private boolean isValidUser;

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

    String getError() {
        return error;
    }

    void setError(String error) {
        final String oldValue = this.error;
        firePropertyChange("error", oldValue, this.error = error);
    }

    public boolean isValidUser() {
        return isValidUser;
    }

    void setIsValidUser(boolean isValidUser) {
        final boolean oldValue = this.isValidUser;
        firePropertyChange("isValidUser", oldValue, isValidUser);
    }
}
