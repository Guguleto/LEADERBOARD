package com.guguzitha.leaderboard;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.guguzitha.leaderboard.model.LearnersHours;
import com.guguzitha.leaderboard.model.SkillIq;
import com.squareup.picasso.OkHttp3Downloader;
import com.squareup.picasso.Picasso;

import java.util.List;

public class SkillIqAdapter extends RecyclerView.Adapter<SkillIqAdapter.MyViewHolder> {
    private List<SkillIq> dataList;
    private Context context;

    public SkillIqAdapter( Context context,List<SkillIq> dataList){
        this.context = context;
        this.dataList = dataList;
    }

    public class MyViewHolder extends RecyclerView.ViewHolder  {
        private final View mView;

            TextView textV_Name;
            TextView textV_score;
            TextView textV_Country;
            TextView textV_learners_iq;
            private ImageView img;

            public MyViewHolder(@NonNull View itemView) {
                super(itemView);
                mView = itemView;

                textV_Name = mView.findViewById(R.id.name);
                textV_score = mView.findViewById(R.id.score);
                textV_Country =mView.findViewById(R.id.country);
                textV_learners_iq = mView.findViewById(R.id.learners_iq);
                img = mView.findViewById(R.id.hours_badge);
        }
    }


    @NonNull
    @Override
    public SkillIqAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View view = layoutInflater.inflate(R.layout.item_skill_iq, parent, false);

        return new MyViewHolder(view);

    }

    @Override
    public void onBindViewHolder(@NonNull SkillIqAdapter.MyViewHolder holder, int position) {

        holder.textV_Name.setText(dataList.get(position).getName());
        holder.textV_score.setText(String.valueOf(dataList.get(position).getScore()));
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


}
