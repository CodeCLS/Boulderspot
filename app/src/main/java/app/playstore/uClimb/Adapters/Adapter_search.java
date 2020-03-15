package app.playstore.uClimb.Adapters;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import app.playstore.uClimb.R;
import app.playstore.uClimb.ViewModelFragments.search_presenter.Search_presenter;

public class Adapter_search extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    Context mContext;
    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        mContext = parent.getContext();
        View view;
        RecyclerView.ViewHolder viewHolder = null;

        if (viewType == 0){
            view= LayoutInflater.from(mContext).inflate(R.layout.search_bar,parent,false);
            viewHolder = new ViewHolderSearch(view);



        }
        if (viewType == 1){
            view= LayoutInflater.from(mContext).inflate(R.layout.search_custom_recyclerview,parent,false);
            viewHolder = new Recyclerview_viewholder(view);

        }
        assert viewHolder != null;
        return  viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        if (holder.getItemViewType()== 0){
            ViewHolderSearch viewHolderSearch = (ViewHolderSearch) holder;
          //  viewHolderSearch.Enter.setOnClickListener(v -> {
          //      String input =((ViewHolderSearch) holder).editText.getText().toString();
//
//
          //      //TODO enter onclick
//
//
//
          //  });


        }
        if (holder.getItemViewType() ==1){
            Recyclerview_viewholder viewHolderrec = (Recyclerview_viewholder) holder;

            Search_presenter search_presenter = new Search_presenter();
            search_presenter.setRec(holder.itemView,mContext);

        }

    }

    @Override
    public int getItemCount() {
        return 2;
    }

    @Override
    public int getItemViewType(int position) {
        int i = 0;
        if (position == 0){
            i = 0;
        }
        if (position == 1){
            i = 1;

        }
        return i;

    }

    public class ViewHolderSearch extends RecyclerView.ViewHolder{
        ImageView Enter;
        EditText editText;


        public ViewHolderSearch(@NonNull View itemView) {
            super(itemView);
            //Enter = itemView.findViewById(R.id.submit_img_search);
            //editText = itemView.findViewById(R.id.editText_search);

        }
    }
    public class Recyclerview_viewholder extends RecyclerView.ViewHolder{
        RecyclerView recyclerview;


        public Recyclerview_viewholder(@NonNull View itemView) {
            super(itemView);
            recyclerview = itemView.findViewById(R.id.rec_search_page);

        }
    }
}
