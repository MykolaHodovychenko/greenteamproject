package com.ranpeak.ProjectX.activity.lobby.forGuestUsers.fragments;

import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.baoyz.widget.PullRefreshLayout;
import com.ranpeak.ProjectX.R;
import com.ranpeak.ProjectX.activity.interfaces.Activity;
import com.ranpeak.ProjectX.activity.lobby.commands.TaskNavigator;
import com.ranpeak.ProjectX.activity.lobby.forAuthorizedUsers.navigationFragment.tasksNavFragment.adapter.TaskListAdapter;
import com.ranpeak.ProjectX.activity.lobby.viewModel.TaskViewModel;
import com.ranpeak.ProjectX.dto.TaskDTO;

import java.util.ArrayList;
import java.util.List;

public class FragmentTasks extends Fragment implements Activity, TaskNavigator {

     View view;
     private RecyclerView recyclerView;
     private TaskListAdapter taskListAdapter;
     private List<TaskDTO> data = new ArrayList<>();
     private ArrayList<String> imageUrls = new ArrayList<>();
     private TaskViewModel taskViewModel;
     PullRefreshLayout pullRefreshLayout;

     public FragmentTasks() {
     }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
         view = inflater.inflate(R.layout.fargment_lobby_list_tasks, container, false);

         taskViewModel = new TaskViewModel(getContext());
        taskViewModel.setNavigator(this);

        findViewById();
        typeRefresh();
        onListener();
        initImageBitmaps();

        setupAdapter();
        getTasks();

        return view;
    }

    private void typeRefresh() {
        pullRefreshLayout.setRefreshStyle(PullRefreshLayout.STYLE_SMARTISAN);
    }

    @Override
    public void findViewById() {
         pullRefreshLayout = view.findViewById(R.id.refresh_tasks);
         recyclerView = view.findViewById(R.id.recycler_tasks);
    }

    @Override
    public void onListener() {
         pullRefreshLayout.setOnRefreshListener(() -> {
             final Handler handler = new Handler();
             handler.postDelayed(() -> {
                 getTasks();
                 pullRefreshLayout.setRefreshing(false);
             }, 1000);
         });



    }

    @Override
    public void handleError(Throwable throwable) {
        Toast.makeText(getContext(),"Tasks don`t upload",Toast.LENGTH_LONG).show();
    }

    @Override
    public void getDataInAdapter(List<TaskDTO> taskDTOS) {
         data.clear();
         data.addAll(taskDTOS);
         taskListAdapter.notifyDataSetChanged();
    }

    private void setupAdapter(){
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        taskListAdapter = new TaskListAdapter(data,imageUrls,recyclerView,getActivity());
        recyclerView.setAdapter(taskListAdapter);
    }

    private void getTasks(){
         taskViewModel.getTasksFromServer();
    }

    private void initImageBitmaps() {
        imageUrls.add("https://cdn.fishki.net/upload/post/2017/03/19/2245758/01-beautiful-white-cat-imagescar-wallpaper.jpg");
        imageUrls.add("https://usionline.com/wp-content/uploads/2016/02/12-4.jpg");
        imageUrls.add("http://bm.img.com.ua/nxs/img/prikol/images/large/1/2/308321_879390.jpg");
        imageUrls.add("http://bm.img.com.ua/nxs/img/prikol/images/large/1/2/308321_879389.jpg");
        imageUrls.add("http://www.radionetplus.ru/uploads/posts/2013-05/1369460621_panda-26.jpg");
        imageUrls.add("http://v.img.com.ua/b/1100x999999/1/fc/409a3eebc81a4d8dc4a2437cbe07afc1.jpg");
        imageUrls.add("http://ztb.kz/media/imperavi/59cb70c479d20.jpg");
        imageUrls.add("https://bryansktoday.ru/uploads/common/dcbf021231e742e6_XL.jpg");
        imageUrls.add("https://ki.ill.in.ua/m/670x450/24227758.jpg");
        imageUrls.add("https://cs9.pikabu.ru/post_img/2017/10/06/7/1507289738144386744.jpg");
        imageUrls.add("https://placepic.ru/uploads/posts/2014-03/1396234652_podborka-realnyh-pacanov-2.jpg");
        imageUrls.add("http://2queens.ru/Uploads/Yelizaveta/%D1%80%D0%B5%D0%B0%D0%BB%D1%8C%D0%BD%D1%8B%D0%B9%20%D0%BF%D0%B0%D1%86%D0%B0%D0%BD%20%D1%80%D0%B6%D0%B0%D0%B2%D1%8B%D0%B9.jpg");
        imageUrls.add("http://bidla.net/uploads/posts/2017-06/thumbs/1496943807_urodru20170608ku7564.jpeg");
        imageUrls.add("https://www.baikal-daily.ru/upload/resize_cache/iblock/ca6/600_500_1/vovan.png");
        imageUrls.add("https://www.vokrug.tv/pic/person/b/3/6/d/b36d3d2f4c263fc18eba1a464eb942d2.jpeg");
        imageUrls.add("https://i.mycdn.me/image?id=877079192648&t=35&plc=WEB&tkn=*85PLfcQAXU8Glv9V8-xzIyJxZF4");
    }
}


