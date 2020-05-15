package app.playstore.uClimb.MVP.MVP_Friends;

import java.util.ArrayList;

public class Model_Friends {
    private ArrayList<String> friends_id = new ArrayList<>();
    private ArrayList<String> friends_name = new ArrayList<>();
    private ArrayList<String> friends_img_url = new ArrayList<>();

    public Model_Friends(ArrayList<String> friends_id, ArrayList<String> friends_name, ArrayList<String> friends_img_url) {
        this.friends_id = friends_id;
        this.friends_name = friends_name;
        this.friends_img_url = friends_img_url;
    }

    public Model_Friends() {
    }
}
