package app.playstore.uClimb.MVP.MVP_Register;

public class Model_Register {
    private String uid;
    private String Username;
    private String email;
    private String pwd;

    public Model_Register(String uid, String username, String email, String pwd) {
        this.uid = uid;
        Username = username;
        this.email = email;
        this.pwd = pwd;
    }

    public Model_Register() {
    }

}
