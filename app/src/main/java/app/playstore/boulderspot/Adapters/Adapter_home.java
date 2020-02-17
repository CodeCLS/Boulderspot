package app.playstore.boulderspot.Adapters;

import android.content.Context;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.RecyclerView;
import androidx.transition.Scene;
import androidx.transition.TransitionManager;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
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

import app.playstore.boulderspot.Fragments.Training_list_fragment;
import app.playstore.boulderspot.Fragments.video_upload_fragment;
import app.playstore.boulderspot.R;

public class Adapter_home extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private static final String TAG = "adapter_home";
    private ArrayList<String> IMG_URL;
    private  ArrayList<String> grade;
    private  ArrayList<String> place;
    private  ArrayList<String> name;
    private  ArrayList<String> info;
    private  ArrayList<String> video_url;
    private int clicked = 1;
    private  ArrayList<String> ID_User;
    private  ArrayList<String> post;

    private  ArrayList<String> likes;
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

    public Adapter_home(ArrayList<String> IMG_URL, ArrayList<String> grade, ArrayList<String> place,
                        ArrayList<String> name, ArrayList<String> info,
                        ArrayList<String> video_url, ArrayList<String> ID_User
                        , ArrayList<String> likes,ArrayList<String> post, Context mContext) {
        this.IMG_URL = IMG_URL;
        this.grade = grade;
        this.place = place;
        this.name = name;
        this.info = info;
        this.video_url = video_url;
        this.ID_User = ID_User;
        this.likes = likes;
        this.post = post;
        this.mContext = mContext;
        Log.d(TAG , "context:" + mContext);

    }

    @Override
    public int getItemViewType(int position) {
        if (position == 0){
            Log.d("Adapter_home","POSITION0: "+position);
            return 0;
        }
        else{
            Log.d("Adapter_home","POSITION1: "+position);

            return 1;
        }
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = null;
        RecyclerView.ViewHolder viewHolder = null;


            if (i== 1){
                view = LayoutInflater.from(viewGroup.getContext())
                        .inflate(R.layout.custom_home, viewGroup, false);
               viewHolder= new ViewHolder1(view);
            }
            if (i==0){
                view = LayoutInflater.from(viewGroup.getContext())
                        .inflate(R.layout.training_rec_item, viewGroup, false);
                return new ViewHolder2(view);
             }

        mContext = viewGroup.getContext();
        mAuth = FirebaseAuth.getInstance();
        return viewHolder;

    }

    @Override
    public void onBindViewHolder(@NonNull final RecyclerView.ViewHolder viewHolder,int i) {
        i = i -1;

        // Read from the database
        switch (viewHolder.getItemViewType()) {
            case 0:
                ViewHolder2 viewHolder2 = (ViewHolder2) viewHolder;
                viewHolder2.rec_item_layout.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Training_list_fragment mFragment = new Training_list_fragment();
                        FragmentManager fragmentManager = ((AppCompatActivity)mContext).getSupportFragmentManager();
                        fragmentManager.beginTransaction().addToBackStack("Fragment_training").setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN)
                                .replace(R.id.container_fragment, mFragment).commit();


                    }
                });



                break;

            case 1:
                Log.d(TAG,"number_adapter:" + i);
                ViewHolder1 viewHolder1 = (ViewHolder1)viewHolder;
                //liking_event(viewHolder1, i);
                //viewHolder1.liked.setText(likes.get(i));
                setWidgets(viewHolder1, i);
                pause_play_video(viewHolder1);
                //profile_pic_click_event(viewHolder1);
                break;
        }



    }



    private void setWidgets(@NonNull ViewHolder1 viewHolder, int i) {
        Log.d(TAG,"grade_txt" + i + "   .. " + grade.size() + "... " + grade.get(i));
        viewHolder.info_txt.setText(info.get(i));
        Picasso.get().load(IMG_URL.get(i)).fit().into(viewHolder.IMG_img_profile_pic);
        viewHolder.video_view.setVideoPath(video_url.get(i));
        viewHolder.video_view.seekTo(2);
        viewHolder.name_txt.setText(name.get(i));
    }

    private void liking_event(@NonNull ViewHolder1 viewHolder, int i) {
        setting_and_changing_boolean_for_liking(i);
        clicking_like_text(viewHolder, i);
    }

    private void setting_and_changing_boolean_for_liking(int i) {
        Log.d(TAG,"Post:"+post);
        Log.d(TAG,"UID"+ID_User);

        auth = mAuth.getUid();
        UID = ID_User.get(i);
        String_post = post.get(i);

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

    private void clicking_like_text(@NonNull ViewHolder1 viewHolder, final int i) {
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
            myRef.child(UID).child(post.get(i)).child("Likes").child(auth).setValue(auth);


        }
    }

    private void pause_play_video(@NonNull final ViewHolder1 viewHolder) {
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
        return IMG_URL.size() + 1;
    }

    class ViewHolder1 extends RecyclerView.ViewHolder {
        TextView grade_txt;
        TextView name_txt;
        de.hdodenhof.circleimageview.CircleImageView IMG_img_profile_pic;
        TextView info_txt;
        VideoView video_view;
        //TextView maker_txt;
        //  ImageView full_screen;
        TextView liked;




        ViewHolder1(View view) {
            super(view);
             name_txt = (TextView) view.findViewById(R.id.name_profile_custom_home);
            IMG_img_profile_pic =  view.findViewById(R.id.profile_image);
                info_txt = (TextView) view.findViewById(R.id.info_view_home);
            video_view = view.findViewById(R.id.myvideoview);







        }
    }

    class ViewHolder2 extends RecyclerView.ViewHolder {
       LinearLayout rec_item_layout;




        ViewHolder2(View view) {
            super(view);
            // grade_txt = (TextView) view.findViewById(R.id.grade_txt);
            // name_txt = (TextView) view.findViewById(R.id.username_txt);
            rec_item_layout = view.findViewById(R.id.rec_training_item_layout);
            //     info_txt = (TextView) view.findViewById(R.id.info_txt);
            //maker_txt = (TextView) view.findViewById(R.id.maker_txt);
            //  full_screen = (ImageView) view.findViewById(R.id.full_view_img_custom);








        }
    }


}
