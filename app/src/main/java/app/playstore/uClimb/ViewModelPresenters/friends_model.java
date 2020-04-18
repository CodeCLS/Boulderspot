package app.playstore.uClimb.ViewModelPresenters;

import java.util.ArrayList;

public class friends_model {
    private ArrayList<String> friends_id = new ArrayList<>();
    private ArrayList<String> friends_name = new ArrayList<>();
    private ArrayList<String> friends_img_url = new ArrayList<>();

    public friends_model(ArrayList<String> friends_id, ArrayList<String> friends_name, ArrayList<String> friends_img_url) {
        this.friends_id = friends_id;
        this.friends_name = friends_name;
        this.friends_img_url = friends_img_url;
    }

    public friends_model() {
    }
}
