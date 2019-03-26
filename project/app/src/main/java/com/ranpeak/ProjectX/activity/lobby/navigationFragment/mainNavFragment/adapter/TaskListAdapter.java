package com.ranpeak.ProjectX.activity.lobby.navigationFragment.mainNavFragment.adapter;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.StrictMode;
import android.support.annotation.NonNull;
import android.support.v7.widget.CardView;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.ranpeak.ProjectX.R;
import com.ranpeak.ProjectX.activity.TaskActivity;
import com.ranpeak.ProjectX.activity.lobby.navigationFragment.mainNavFragment.ILoadMore;
import com.ranpeak.ProjectX.dto.TaskDTO;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class TaskListAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private final int VIEW_TYPE_ITEM = 0, VIEW_TYPE_LOADING = 1;
    private ILoadMore loadMore;
    private boolean isLoading;
    private int visibleThreshold = 5;
    private int lastVisibleItem, totalItemCount;
    private List<TaskDTO> data;
    private ArrayList<String> images = new ArrayList<>();
    private Context context;
    private Activity activity;

    public TaskListAdapter(List<TaskDTO> data, ArrayList<String> images, RecyclerView recyclerView, Activity activity) {
        this.data = data;
        this.images = images;
        this.activity = activity;

        final LinearLayoutManager linearLayoutManager = (LinearLayoutManager) recyclerView.getLayoutManager();
        recyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
                totalItemCount = linearLayoutManager.getItemCount();
                lastVisibleItem = linearLayoutManager.findLastVisibleItemPosition();
                if (!isLoading && totalItemCount <= (lastVisibleItem + visibleThreshold)) {
                    if (loadMore != null) {
                        loadMore.onLoadMore();
                        isLoading = true;
                    }

                }
            }
        });
    }


    @Override
    public int getItemViewType(int position) {
        return data.get(position) == null ? VIEW_TYPE_LOADING:VIEW_TYPE_ITEM;
    }

    public void setLoadMore(ILoadMore loadMore) {
        this.loadMore = loadMore;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        if(viewType == VIEW_TYPE_ITEM){
            View view = LayoutInflater.from(activity).inflate(R.layout.task_item, parent, false);
            return new TaskViewHolder(view);
        }else if(viewType == VIEW_TYPE_LOADING){
            View view = LayoutInflater.from(activity).inflate(R.layout.item_loading, parent, false);
            return new LoadingViewHolder(view);
        }

        return null;
    }


    private static String getRandomChestItem(ArrayList<String> images) {
        return images.get(new Random().nextInt(images.size()));
    }


    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {

        if(holder instanceof TaskViewHolder){
            TaskDTO item = data.get(position);
            TaskViewHolder viewHolder = (TaskViewHolder) holder;

            viewHolder.headline.setText(item.getHeadLine());
            viewHolder.subject.setText(item.getSubject());
            viewHolder.date.setText(item.getDateStart());
            viewHolder.price.setText(String.valueOf(item.getPrice()));
            StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
            StrictMode.setThreadPolicy(policy);


            URL url = null;
            try {
                url = new URL(getRandomChestItem(images));
                Bitmap bmp = BitmapFactory.decodeStream(url.openConnection().getInputStream());
                viewHolder.profile_user.setImageBitmap(bmp);
            } catch (java.io.IOException e) {
                e.printStackTrace();
            }

        }else if(holder instanceof LoadingViewHolder){
            LoadingViewHolder loadingViewHolder = (LoadingViewHolder) holder;
            loadingViewHolder.progressBar.setIndeterminate(true);
        }

//        TaskDTO item = data.get(position);
//        holder.headline.setText(item.getHeadLine());
//        holder.subject.setText(item.getSubject());
//        holder.date.setText(item.getDateStart());
//        holder.price.setText(String.valueOf(item.getPrice()));



//        Glide.with(context)
//                .asBitmap()
//                .load(images.get(position))
//                .into(holder.profile_user);
    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    public void setLoaded() {
        isLoading = false;
    }

    public void setData(List<TaskDTO> data) {
        this.data = data;
    }



    public class TaskViewHolder extends RecyclerView.ViewHolder implements com.ranpeak.ProjectX.activity.interfaces.Activity {
        CardView cardView;
        TextView headline;
        TextView date;
        TextView subject;
        ImageView profile_user;
        TextView price;

        public TaskViewHolder(View itemView) {
            super(itemView);
            findViewById();
            onListener();

        }

        @Override
        public void findViewById() {
            cardView = itemView.findViewById(R.id.cardView);
            headline = itemView.findViewById(R.id.headline);
            subject = itemView.findViewById(R.id.subject);
            date = itemView.findViewById(R.id.date);
            profile_user = itemView.findViewById(R.id.profile_user);
            price = itemView.findViewById(R.id.price);
        }

        @Override
        public void onListener() {
            cardView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Toast.makeText(v.getContext(), "Open task", Toast.LENGTH_LONG).show();
                    v.getContext().startActivity(new Intent(v.getContext(), TaskActivity.class));
                }
            });

        }
    }



    private class LoadingViewHolder extends RecyclerView.ViewHolder{

        public ProgressBar progressBar;

        public LoadingViewHolder(View itemView) {
            super(itemView);
            progressBar = itemView.findViewById(R.id.progress_bar_loading);
        }
    }

}
