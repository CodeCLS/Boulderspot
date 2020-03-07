package app.playstore.uClimb.ViewModelFragments;

import android.os.Bundle;
import android.view.View;

import java.util.ArrayList;

public class current_user_presenter {
    private Model_current_user model_current_user;
    private Data data;
    private ArrayList<String> array_name = new ArrayList();
    private ArrayList<String> array_source_img = new ArrayList();
    private ArrayList<String> array_source = new ArrayList();
    private ArrayList<String> array_info = new ArrayList();
    private ArrayList<String> array_place = new ArrayList();
    private ArrayList<String> array_user_id = new ArrayList();
    private ArrayList<String> array_post_id = new ArrayList();
    private ArrayList<String> array_type = new ArrayList();
    private ArrayList<String> array_seen_by = new ArrayList();
    private ArrayList<String> array_likes = new ArrayList();
    private ArrayList<String> array_time = new ArrayList();


    public current_user_presenter(Data data) {
        this.model_current_user = new Model_current_user();
        this.data = data;

    }


    public Bundle get_current_user_pack_minimal(){
        String mAuth = model_current_user.getmAuth_current_user();
        String name = model_current_user.getName_current_user();
        String Source_img = model_current_user.getImg_profile_source_current_user();
        String info = model_current_user.getInfo_current_user();

        Bundle bundle = new Bundle();
        bundle.putString("mAuth",mAuth);
        bundle.putString("Name" , name);
        bundle.putString("Source_img", Source_img);
        bundle.putString("Info" , info);
        return bundle;



    }
    public void set_current_user_data(String mAuth, String name, String Source_img, String info, String city, String subscription, ArrayList<String> following, ArrayList<String> follower,String height){
        Model_current_user model_current_user = new Model_current_user(mAuth,city,Source_img,following,follower,subscription,info,name,height);
        Bundle bundle = new Bundle();
        bundle.putString("mAuth",mAuth);
        bundle.putString("Name" , name);
        bundle.putString("Source_img", Source_img);
        bundle.putString("Info" , info);
        bundle.putString("City" , city);
        bundle.putString("Subscription",subscription);
        bundle.putStringArrayList("Following" , following);
        bundle.putStringArrayList("Follower", following);
        bundle.putString("Height" , height);
        data.sendData(bundle);


    }



    public interface Data{
        void sendData(Bundle bundle);
    }
}
