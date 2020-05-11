package app.playstore.uClimb.Fragments.Training;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Spinner;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import app.playstore.uClimb.Fragments.Hangboard_page;
import app.playstore.uClimb.MVP.MVP_Training.Presenter_Training;
import app.playstore.uClimb.R;
import de.hdodenhof.circleimageview.CircleImageView;

public class Log_Training_Fragment extends Fragment {
    private Presenter_Training presenter_training;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.main_log_training_page,container,false);

    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        Bundle bundle = getArguments();
        String Hang_Time = bundle.getString("Hang_Time");
        String Pause_Time = bundle.getString("Pause_Time");
        String Rest_Time= bundle.getString("Rest_Time");
        String Sets = bundle.getString("Sets");
        String Rounds = bundle.getString("Rounds");
        Presenter_Training presenter_training = new Presenter_Training(Hang_Time,Pause_Time,Rest_Time,Sets,Rounds,getContext());
        Button btn_cancel = view.findViewById(R.id.btn_cancel_log_workout);
        Button btn_log = view.findViewById(R.id.btn_log_workout);
        CircleImageView circleImageView = (CircleImageView) view.findViewById(R.id.btn_add_workout);
        Spinner spinner = view.findViewById(R.id.spinner_workouts_log_training);
        EditText info = view.findViewById(R.id.info_log_workout_edittext);
        EditText workout_add_edit = view.findViewById(R.id.edittext_workouts_log_training);
        CheckBox checkBox = view.findViewById(R.id.checkbox_log_training_public);
        presenter_training.onClickBtn(getContext(),Hang_Time,Pause_Time,Rest_Time,Sets,Rounds,btn_cancel,btn_log,circleImageView,spinner,checkBox,workout_add_edit,info);







    }
}
