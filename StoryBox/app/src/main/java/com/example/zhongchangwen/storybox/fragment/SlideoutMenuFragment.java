package com.example.zhongchangwen.storybox.fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.zhongchangwen.storybox.Data.FragmentMode;
import com.example.zhongchangwen.storybox.MainActivity;
import com.example.zhongchangwen.storybox.R;

/**
 * Created by zhongchangwen on 2017/7/19.
 */

public class SlideoutMenuFragment extends Fragment implements View.OnClickListener {

    private static SlideoutMenuFragment mSlideoutMenuFragment = null;
    private MainActivity mActivity;

    private View mCloudBtnLayout;
    private View mDeviceListBtnLayout;

    public SlideoutMenuFragment() {
        mSlideoutMenuFragment = this;
    }

    public static SlideoutMenuFragment getInstance() {
        if (mSlideoutMenuFragment == null) {
            return new SlideoutMenuFragment();
        }
        return mSlideoutMenuFragment;
    }

    @Override
    public void onClick(View v) {
        setMenuUnselected();
        v.setSelected(true);
        int mode = FragmentMode.CLOUD;
        switch (v.getId()) {
            case R.id.cloudBtnLayout:
                mode = FragmentMode.CLOUD;
                break;
            case R.id.listBtnLayout:
                mode = FragmentMode.LIST;
                break;
            default:
                break;
        }
        mActivity.setMode(mode);
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mActivity = (MainActivity) getActivity();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.main_left_fragment, container, false);
        mCloudBtnLayout = view.findViewById(R.id.cloudBtnLayout);
        mCloudBtnLayout.setOnClickListener(this);

        mDeviceListBtnLayout = view.findViewById(R.id.listBtnLayout);
        mDeviceListBtnLayout.setOnClickListener(this);

        return view;
    }

    private void setMenuUnselected() {
        mCloudBtnLayout.setSelected(false);
        mDeviceListBtnLayout.setSelected(false);
    }
}
