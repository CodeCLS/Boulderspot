package app.playstore.boulderspot.Fragments;

import android.content.Context;
import android.os.Bundle;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;

import app.playstore.boulderspot.Adapters.Adapter_home;
import app.playstore.boulderspot.R;

public class Home_Fragment extends Fragment {

    private ArrayList<String> IMG_URL= new ArrayList<>();
    private  ArrayList<String> grade   = new ArrayList<>();
    private  ArrayList<String> name = new ArrayList<>();
    private  ArrayList<String> info= new ArrayList<>();;
    private  ArrayList<String> maker  = new ArrayList<>();
    private  ArrayList<String> video_url= new ArrayList<>();
    private  ArrayList<String> ID_User  = new ArrayList<>();
    private  ArrayList<String> ID_video = new ArrayList<>();
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.main_page, container, false);

    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        RecyclerView rec = view.findViewById(R.id.rec_home);
        Adapter_home adapter_event = new Adapter_home(IMG_URL,grade,name,info,maker,video_url,
                ID_User,ID_video,getContext());
        IMG_URL.add("url");
        grade.add("sd");
        name.add("as");
        info.add("as");
        maker.add("sda");
        video_url.add("sda");
        ID_User.add("sda");
        ID_video.add("sad");
        IMG_URL.add("url");
        grade.add("sd");
        name.add("as");
        info.add("as");
        maker.add("sda");
        video_url.add("sda");
        ID_User.add("sda");
        ID_video.add("sad");
        IMG_URL.add("url");
        grade.add("sd");
        name.add("as");
        info.add("as");
        maker.add("sda");
        video_url.add("sda");
        ID_User.add("sda");
        ID_video.add("sad");
        rec.setAdapter(adapter_event);
        rec.setLayoutManager(new LinearLayoutManager(getContext()));

  //      NotificationCompat.Builder mBuilder =
  //            new NotificationCompat.Builder(getContext())
  //                    .setSmallIcon(R.mipmap.boulderspot_logo)
  //                    .setContentTitle("My notification")
  //                    .setContentText("Hello World!");


  //    // Gets an instance of the NotificationManager service//

  //    NotificationManager mNotificationManager =

  //            (NotificationManager) getContext().getSystemService(Context.NOTIFICATION_SERVICE);

  //    // When you issue multiple notifications about the same type of event,
  //    // it’s best practice for your app to try to update an existing notification
  //    // with this new information, rather than immediately creating a new notification.
  //    // If you want to update this notification at a later date, you need to assign it an ID.
  //    // You can then use this ID whenever you issue a subsequent notification.
  //    // If the previous notification is still visible, the system will update this existing notification,
  //    // rather than create a new one. In this example, the notification’s ID is 001//



  //            mNotificationManager.notify(001, mBuilder.build());

    }
    @Override
    public void onAttach(Context context) {
        super.onAttach(context);


    }
}
