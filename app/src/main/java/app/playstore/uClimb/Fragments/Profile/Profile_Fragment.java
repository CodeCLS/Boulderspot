package app.playstore.uClimb.Fragments.Profile;

import android.graphics.Color;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import com.anychart.AnyChart;
import com.anychart.AnyChartView;
import com.anychart.chart.common.dataentry.DataEntry;
import com.anychart.chart.common.dataentry.ValueDataEntry;
import com.anychart.charts.Pie;
import com.flarebit.flarebarlib.FlareBar;
import com.flarebit.flarebarlib.Flaretab;
import com.flarebit.flarebarlib.TabEventObject;

import java.util.ArrayList;
import java.util.List;

import app.playstore.uClimb.Fragments.Community_Fragment;
import app.playstore.uClimb.Fragments.Home.Home_Fragment;
import app.playstore.uClimb.Fragments.Search.Search_Fragment;
import app.playstore.uClimb.R;

public class Profile_Fragment extends Fragment {
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.profile_page,container,false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        Toast.makeText(getContext(), "Hey", Toast.LENGTH_SHORT).show();
        initViews(view);
        Pie pie = AnyChart.pie();

        List<DataEntry> data = new ArrayList<>();
        data.add(new ValueDataEntry("John", 10000));
        data.add(new ValueDataEntry("Jake", 12000));
        data.add(new ValueDataEntry("Peter", 18000));

        pie.data(data);

        AnyChartView anyChartView = (AnyChartView) view.findViewById(R.id.profile_statistics_chart);
        anyChartView.setChart(pie);


    }



    private void initViews(View view) {









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
