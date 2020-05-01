package app.playstore.uClimb.Fragments.custom_workout;

import android.graphics.Color;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import com.flarebit.flarebarlib.FlareBar;
import com.flarebit.flarebarlib.Flaretab;
import com.flarebit.flarebarlib.TabEventObject;

import java.util.ArrayList;

import app.playstore.uClimb.Fragments.Friends_fragment;
import app.playstore.uClimb.Fragments.Home.Home_Fragment;
import app.playstore.uClimb.Fragments.Profile.Profile_Fragment;
import app.playstore.uClimb.Fragments.Search.Search_Fragment;
import app.playstore.uClimb.R;

public class custom_workout_page extends Fragment {


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.private_custom_workout_page,container,false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        initViews(view);





    }

    private void initViews(View view) {

        final FlareBar bottomBar = view.findViewById(R.id.flarebar_custom_workout);
        bottomBar.setBarBackgroundColor(Color.parseColor("#FFFFFF"));
        ArrayList<Flaretab> tabs = new ArrayList<>();
        tabs.add(new Flaretab(getResources().getDrawable(R.drawable.inboxb),"Hangboard","#FFECB3"));
        tabs.add(new Flaretab(getResources().getDrawable(R.drawable.searchb),"Stretching","#80DEEA"));
        tabs.add(new Flaretab(getResources().getDrawable(R.drawable.phoneb),"Warmup","#B39DDB"));

        bottomBar.setTabList(tabs);
        bottomBar.attachTabs(getContext());
        bottomBar.setTabChangedListener(new TabEventObject.TabChangedListener() {
            @Override
            public void onTabChanged(LinearLayout selectedTab, int selectedIndex, int oldIndex) {
                //tabIndex starts from 0 (zero). Example : 4 tabs = last Index - 3
                if (selectedIndex == 0) {
                    Home_Fragment mFragment_Home= new Home_Fragment();
                    FragmentManager fragmentManager_search = getChildFragmentManager();
                    fragmentManager_search.beginTransaction()
                            .replace(R.id.container_fragment, mFragment_Home).commit();

                }

                if (selectedIndex == 1) {
                    Search_Fragment mFragment_Search = new Search_Fragment();
                    FragmentManager fragmentManager_search = getChildFragmentManager();
                    fragmentManager_search.beginTransaction()
                            .replace(R.id.container_fragment, mFragment_Search).commit();

                }

                if (selectedIndex == 2) {
                    Friends_fragment mFragment_Community = new Friends_fragment();
                    FragmentManager fragmentManager_community = getChildFragmentManager();
                    fragmentManager_community.beginTransaction()
                            .replace(R.id.container_fragment, mFragment_Community).commit();

                }

                if (selectedIndex == 3) {
                    app.playstore.uClimb.Fragments.Profile.Profile_Fragment mFragment_profile = new Profile_Fragment();
                    FragmentManager fragmentManager_profile = getChildFragmentManager();
                    fragmentManager_profile.beginTransaction()
                            .replace(R.id.container_fragment, mFragment_profile).commit();

                }

                //           .replace(R.id.container_fragment, mFragment).commit();
                //   setAlpha(1);
//
            }


        });




//
        //  img_home = includeView.findViewById(R.id.img_home);
        //  img_event = includeView.findViewById(R.id.img_event);
        //  img_course = includeView.findViewById(R.id.img_training);
        //  img_location = includeView.findViewById(R.id.img_location);
//
        //  line_course = includeView.findViewById(R.id.line_img_training);
        //  line_home = includeView.findViewById(R.id.line_img_home);
        //  line_location = includeView.findViewById(R.id.line_img_location);
        //  line_event = includeView.findViewById(R.id.line_img_event);




    }

}
