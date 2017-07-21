package com.example.zhongchangwen.storybox.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.actionbarsherlock.app.SherlockFragment;
import com.example.zhongchangwen.storybox.R;

/**
 * Created by zhongchangwen on 2017/7/20.
 */

public class CloudResourcesFragment extends SherlockFragment {

    private static CloudResourcesFragment mFragment = null;

    public CloudResourcesFragment(){
        mFragment = this;
    }
    public static CloudResourcesFragment newInstance(){
        if (mFragment == null)
            return new CloudResourcesFragment();
        else
            return mFragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_cloud_resources, container, false);
        return v;
    }
}
