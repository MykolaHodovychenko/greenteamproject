package com.ranpeak.ProjectX.activity.lobby.navigationFragment.homeNavFragment.tabFragment;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.ranpeak.ProjectX.R;
import com.ranpeak.ProjectX.fragment.AbstractTabFragment;

public class TookTaskFragment extends AbstractTabFragment {

    private static final int LAYOUT = R.layout.took_task_fragment;

    public static TookTaskFragment getInstance(Context context) {
        Bundle args = new Bundle();
        TookTaskFragment fragment = new TookTaskFragment();
        fragment.setArguments(args);
        fragment.setContext(context);
        fragment.setTitle(context.getString(R.string.tab_item_my_ready_answer));

        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        view = inflater.inflate(LAYOUT, container, false);
        return view;
    }

    public void setContext(Context context) {
        this.context = context;
    }

}