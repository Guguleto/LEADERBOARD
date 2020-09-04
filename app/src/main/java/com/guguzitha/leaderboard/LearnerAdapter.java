package com.guguzitha.leaderboard;

import android.content.Context;
import android.media.Image;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.guguzitha.leaderboard.model.LearnersHours;
import com.squareup.picasso.OkHttp3Downloader;
import com.squareup.picasso.Picasso;

import java.util.List;

public class LearnerAdapter extends RecyclerView.Adapter<LearnerAdapter.MyViewHolder> {
    private List<LearnersHours> dataList;
    private Context context;

    public LearnerAdapter( Context context,List<LearnersHours> dataList){
        this.context = context;
        this.dataList = dataList;
    }





    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View view = layoutInflater.inflate(R.layout.item_learner_hours, parent, false);
        return new MyViewHolder(view);

    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        holder.textV_Name.setText(dataList.get(position).getName());
        holder.textV_Hours.setText(dataList.get(position).getHours());
        holder.textV_Country.setText(dataList.get(position).getCountry());


        Picasso.Builder builder = new Picasso.Builder(context);
        builder.downloader(new OkHttp3Downloader(context));
        builder.build().load(dataList.get(position).getBadgeUrl())
                .placeholder(R.drawable.ic_launcher_background)
                .error(R.drawable.ic_launcher_background)
                .into(holder.img);

    }

    @Override
    public int getItemCount() {
        return dataList.size();
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder{
        private final View mView;

        TextView textV_Name;
        TextView textV_Hours;
        TextView textV_Country;
        private ImageView img;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            mView = itemView;

            textV_Name = mView.findViewById(R.id.name);
            textV_Hours = mView.findViewById(R.id.hours);
            textV_Country =mView.findViewById(R.id.country);
            img = mView.findViewById(R.id.hours_badge);
        }
    }
}
