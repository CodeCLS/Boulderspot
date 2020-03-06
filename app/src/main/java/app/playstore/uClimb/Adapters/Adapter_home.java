package app.playstore.uClimb.Adapters;

import android.content.Context;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.VideoView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

import app.playstore.uClimb.Fragments.Training_list_fragment;
import app.playstore.uClimb.R;

public class Adapter_home extends RecyclerView.Adapter<Adapter_home.ViewHolder> {
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
    private static final String TAG = "adapter_home";


    private  ArrayList<String> likes;
    private int clicked = 0;
    private String auth ;
    private String UID ;
    private String String_post;
    private Boolean exists;
    private FirebaseAuth mAuth;
    public static final int layout_one = 1;
    public static final int layout_two = 2;

    private FirebaseDatabase database = FirebaseDatabase.getInstance();
    private final DatabaseReference myRef = database.getReference("Posts");

    private Context mContext;

    public Adapter_home(ArrayList<String> array_time, ArrayList<String> array_name, ArrayList<String> array_source_img, ArrayList<String> array_source, ArrayList<String> array_info, ArrayList<String> array_place, ArrayList<String> array_user_id, ArrayList<String> array_post_id, ArrayList<String> array_type, ArrayList<String> array_likes) {
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



    @NonNull
    @Override
    public Adapter_home.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        mContext = viewGroup.getContext();
        mContext = viewGroup.getContext();
        mAuth = FirebaseAuth.getInstance();




        View view = LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.custom_home, viewGroup, false);
        mContext = viewGroup.getContext();
        return new ViewHolder(view);









    }

    @Override
    public void onBindViewHolder(@NonNull final Adapter_home.ViewHolder viewHolder,int i) {
        i = i -1;
        Log.d("adapter_home" , "context_home" + mContext);
        if (array_type.get(i)== "video"){
        }

        // Read from the database

              //  adapter_stretch.ViewHolder1 viewHolder2 = (ViewHolder2) viewHolder;
              //  viewHolder2.rec_item_layout.setOnClickListener(new View.OnClickListener() {
              //      @Override
              //      public void onClick(View v) {
              //          Training_list_fragment mFragment = new Training_list_fragment();
              //          Log.d("adapter_home" , "context_home2" + mContext);
//
              //          FragmentManager fragmentManager = ((AppCompatActivity)mContext).getSupportFragmentManager();
              //          fragmentManager.beginTransaction().addToBackStack("Fragment_training").setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN)
              //                  .replace(R.id.container_fragment, mFragment).commit();
//

           //
           //     Log.d(TAG,"number_adapter:" + i);
           //     //liking_event(viewHolder1, i);
           //     //viewHolder1.liked.setText(likes.get(i));
           //     setWidgets(viewHolder1, i);
                pause_play_video(ViewHolder,i);
             //   //profile_pic_click_event(viewHolder1);





    }



    private void setWidgets(@NonNull ViewHolder viewHolder, int i) {
        viewHolder.info_txt.setText(array_info.get(i));
        Picasso.get().load(array_source_img.get(i)).fit().centerCrop().into(viewHolder.IMG_img_profile_pic);
        viewHolder.video_view.setVideoPath(array_source.get(i));
        viewHolder.video_view.seekTo(2);
        viewHolder.name_txt.setText(array_name.get(i));
    }

    private void liking_event(@NonNull ViewHolder viewHolder, int i) {
        setting_and_changing_boolean_for_liking(i);
        clicking_like_text(viewHolder, i);
    }

    private void setting_and_changing_boolean_for_liking(int i) {

        auth = mAuth.getUid();
        UID = array_user_id.get(i);
        String_post = array_post_id.get(i);

        myRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                // This method is called once with the initial value and again
                // whenever data at this location is updated.

                exists = dataSnapshot.child(UID).child(String_post).child("Likes").child(auth).exists();
                Log.d(TAG,"exists2: " +exists);

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                // Failed to read value
                Log.w(TAG, "Failed to read value.", error.toException());
            }
        });
    }

    private void clicking_like_text(@NonNull ViewHolder viewHolder, final int i) {
        viewHolder.liked.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {



                testingliked(UID,String_post,auth,myRef,i);










            }
        });
    }

    private void testingliked( String UID, String string_post, String auth, DatabaseReference myRef, int i) {
        if (exists){

            myRef.child(UID).child(string_post).child("Likes").child(auth).getRef().removeValue();


        }
        if (!exists){
            myRef.child(UID).child(array_post_id.get(i)).child("Likes").child(auth).setValue(auth);


        }
    }

    private void pause_play_video(@NonNull final ViewHolder viewHolder) {
        viewHolder.video_view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (clicked == 1){
                    Log.d(TAG,"clicked_first");
                    viewHolder.video_view.start();
                    clicked = 0;
                }
                else{
                    viewHolder.video_view.pause();
                    clicked= 1;
                    Log.d(TAG,"clicked_second");


                }



            }
        });
    }

    @Override
    public int getItemCount() {
        return array_source.size() + 1;
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        TextView grade_txt;
        TextView name_txt;
        ImageView img_view;
        de.hdodenhof.circleimageview.CircleImageView IMG_img_profile_pic;
        TextView info_txt;
        VideoView video_view;
        //TextView maker_txt;
        //  ImageView full_screen;
        TextView liked;




        ViewHolder(View view) {
            super(view);
            name_txt = (TextView) view.findViewById(R.id.name_profile_custom_home);
            IMG_img_profile_pic =  view.findViewById(R.id.profile_img_custom_home);
            info_txt = (TextView) view.findViewById(R.id.txt_info_custom_home);
            video_view = view.findViewById(R.id.video_custom_home);
            img_view = view.findViewById(R.id.img_view_custom_home);








        }
    }




}

