package app.playstore.uClimb.Fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Locale;
import java.util.Random;

import app.playstore.uClimb.Adapters.Home.Adapter_Home_Comments;
import app.playstore.uClimb.MVP.MVP_Login.Presenter_Login;
import app.playstore.uClimb.R;

public class Fragment_Comments extends Fragment {
    private RecyclerView recyclerView;
    private EditText editText;
    private Button button_submit;
    ArrayList<String> arrayList_comments_time = new ArrayList<>();
    ArrayList<String> arrayList_comments_name = new ArrayList<>();
    ArrayList<String> arrayList_comments_id = new ArrayList<>();
    ArrayList<String> arrayList_comments_text = new ArrayList<>();
    ArrayList<String> arrayList_comments_uid = new ArrayList<>();

    private String postID;
    private Adapter_Home_Comments adapter_home_comments = new Adapter_Home_Comments(arrayList_comments_name,arrayList_comments_id,arrayList_comments_text,arrayList_comments_time,arrayList_comments_uid);


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        if (container != null){
            container.removeAllViews();
        }
        return LayoutInflater.from(getContext()).inflate(R.layout.comment_page,container,false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        Bundle bundle = getArguments();
        initViews(view);
        postComment();
        postID = bundle.getString("PostID");


        getData(view);


    }

    private void getData(@NonNull View view) {
        DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference();
        databaseReference.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                for (DataSnapshot postSnapshot: dataSnapshot.child("Posts").child(postID).child("comments").getChildren()){
                    if (postSnapshot.child("Name").exists() && postSnapshot.exists() &&postSnapshot.child("Text").exists() && postSnapshot.child("Time").exists() && postSnapshot.child("UserID").exists() ) {
                        arrayList_comments_name.add(postSnapshot.child("Name").getValue().toString());
                        arrayList_comments_id.add(postSnapshot.getKey());
                        arrayList_comments_text.add(postSnapshot.child("Text").getValue().toString());
                        arrayList_comments_time.add(postSnapshot.child("Time").getValue().toString());
                        arrayList_comments_uid.add(postSnapshot.child("UserID").getValue().toString());
                    }


                }
                setData(view);

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }

    private void setData(@NonNull View view) {

        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        recyclerView.setAdapter(adapter_home_comments);
    }

    private void postComment() {
        button_submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String edit_s = editText.getText().toString();
                if (edit_s.length() < 50 && edit_s.length() > 5){
                    SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd_HHmmss", Locale.getDefault());
                    String currentDateandTime = sdf.format(new Date());
                    Presenter_Login login_presenter = new Presenter_Login();
                    String uid = login_presenter.getUID(getContext());
                    DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference();
                    databaseReference.addListenerForSingleValueEvent(new ValueEventListener() {
                        @Override
                        public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                            String random_hash = getRandomString(10);
                            if (dataSnapshot.child("User").child(uid).child("Name").exists()) {
                                String name = dataSnapshot.child("User").child(uid).child("Name").getValue().toString();
                                databaseReference.child("Posts").child(postID).child("comments").child(random_hash).child("Name").setValue(name);
                                databaseReference.child("Posts").child(postID).child("comments").child(random_hash).child("Time").setValue(currentDateandTime);
                                databaseReference.child("Posts").child(postID).child("comments").child(random_hash).child("UserID").setValue(uid);
                                databaseReference.child("Posts").child(postID).child("comments").child(random_hash).child("Text").setValue(edit_s);
                                Toast.makeText(getContext(), "Kommentar gepostet", Toast.LENGTH_SHORT).show();
                            }



                        }

                        @Override
                        public void onCancelled(@NonNull DatabaseError databaseError) {
                            Toast.makeText(getContext(), "Fehler! Versuche es Später", Toast.LENGTH_SHORT).show();

                        }
                    });


                }
                else{
                    Toast.makeText(getContext(), "Dein Kommentar muss länger als 5 und kürzer als 50 Zeichen sein ", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    private void initViews(@NonNull View view) {
        recyclerView = view.findViewById(R.id.rec_comments);
         editText = view.findViewById(R.id.edit_comment_edit);
        button_submit = view.findViewById(R.id.edit_comment_btn);
    }
    private static final String ALLOWED_CHARACTERS ="0123456789qwertyuiopasdfghjklzxcvbnm";

    private static String getRandomString(final int sizeOfRandomString)
    {
        final Random random=new Random();
        final StringBuilder sb=new StringBuilder(sizeOfRandomString);
        for(int i=0;i<sizeOfRandomString;++i)
            sb.append(ALLOWED_CHARACTERS.charAt(random.nextInt(ALLOWED_CHARACTERS.length())));
        return sb.toString();
    }
}
