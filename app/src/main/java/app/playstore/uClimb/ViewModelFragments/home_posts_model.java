package app.playstore.uClimb.ViewModelFragments;

import java.util.ArrayList;

public class home_posts_model {
    private ArrayList<String> array_time = new ArrayList();
    private ArrayList<String> array_name = new ArrayList();
    private ArrayList<String> array_source_img = new ArrayList();
    private ArrayList<String> array_source = new ArrayList();
    private ArrayList<String> array_info = new ArrayList();
    private ArrayList<String> array_place = new ArrayList();
    private ArrayList<String> array_user_id = new ArrayList();
    private ArrayList<String> array_post_id = new ArrayList();
    private ArrayList<String> array_type = new ArrayList();
    private ArrayList<String> array_likes = new ArrayList();

    public home_posts_model(ArrayList<String> array_time, ArrayList<String> array_name, ArrayList<String> array_source_img, ArrayList<String> array_source, ArrayList<String> array_info, ArrayList<String> array_place, ArrayList<String> array_user_id, ArrayList<String> array_post_id, ArrayList<String> array_type, ArrayList<String> array_likes) {
        this.array_time = array_time;
        this.array_name = array_name;
        this.array_source_img = array_source_img;
        this.array_source = array_source;
        this.array_info = array_info;
        this.array_place = array_place;
        this.array_user_id = array_user_id;
        this.array_post_id = array_post_id;
        this.array_type = array_type;
        this.array_likes = array_likes;
    }

    public home_posts_model() {
    }

    public ArrayList<String> getArray_time() {
        return array_time;
    }

    public void setArray_time(ArrayList<String> array_time) {
        this.array_time = array_time;
    }

    public ArrayList<String> getArray_name() {
        return array_name;
    }

    public void setArray_name(ArrayList<String> array_name) {
        this.array_name = array_name;
    }

    public ArrayList<String> getArray_source_img() {
        return array_source_img;
    }

    public void setArray_source_img(ArrayList<String> array_source_img) {
        this.array_source_img = array_source_img;
    }

    public ArrayList<String> getArray_source() {
        return array_source;
    }

    public void setArray_source(ArrayList<String> array_source) {
        this.array_source = array_source;
    }

    public ArrayList<String> getArray_info() {
        return array_info;
    }

    public void setArray_info(ArrayList<String> array_info) {
        this.array_info = array_info;
    }

    public ArrayList<String> getArray_place() {
        return array_place;
    }

    public void setArray_place(ArrayList<String> array_place) {
        this.array_place = array_place;
    }

    public ArrayList<String> getArray_user_id() {
        return array_user_id;
    }

    public void setArray_user_id(ArrayList<String> array_user_id) {
        this.array_user_id = array_user_id;
    }

    public ArrayList<String> getArray_post_id() {
        return array_post_id;
    }

    public void setArray_post_id(ArrayList<String> array_post_id) {
        this.array_post_id = array_post_id;
    }

    public ArrayList<String> getArray_type() {
        return array_type;
    }

    public void setArray_type(ArrayList<String> array_type) {
        this.array_type = array_type;
    }



    public ArrayList<String> getArray_likes() {
        return array_likes;
    }

    public void setArray_likes(ArrayList<String> array_likes) {
        this.array_likes = array_likes;
    }
}