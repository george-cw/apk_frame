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

public class MusicListFragment extends SherlockFragment {

    private static MusicListFragment mFragment = null;

    public MusicListFragment(){
        mFragment = this;
    }
    public static MusicListFragment newInstance(){
        if (mFragment == null)
            return new MusicListFragment();
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
        View v = inflater.inflate(R.layout.fragment_music_list, container, false);
        return v;
    }
}
