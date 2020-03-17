package app.playstore.uClimb.ViewModelPresenters.login;

public class login_model {
    private String mAuth;
    private String username;
    private String pwd_hash;
    private String email;

    public login_model() {
    }

    public login_model(String mAuth, String username, String pwd_hash, String email) {
        this.mAuth = mAuth;
        this.username = username;
        this.pwd_hash = pwd_hash;
        this.email = email;
    }

    public String getmAuth() {
        return mAuth;
    }

    public void setmAuth(String mAuth) {
        this.mAuth = mAuth;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPwd_hash() {
        return pwd_hash;
    }

    public void setPwd_hash(String pwd_hash) {
        this.pwd_hash = pwd_hash;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
