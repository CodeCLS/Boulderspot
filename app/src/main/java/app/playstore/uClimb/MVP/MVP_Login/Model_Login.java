package app.playstore.uClimb.MVP.MVP_Login;

public class Model_Login {
    private String mAuth;
    private String username;
    private String pwd_hash;
    private String email;

    public Model_Login() {
    }

    public Model_Login(String mAuth, String username, String pwd_hash, String email) {
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
