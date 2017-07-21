package com.example.zhongchangwen.storybox;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentTransaction;
import android.view.View;
import android.widget.TabHost;

import com.example.zhongchangwen.storybox.Data.FragmentMode;
import com.example.zhongchangwen.storybox.base.BaseActivity;
import com.example.zhongchangwen.storybox.fragment.CloudResourcesFragment;
import com.example.zhongchangwen.storybox.fragment.MusicListFragment;

import java.util.HashMap;

public class MainActivity extends BaseActivity {
    TabManager mTabManager;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initFragment();

        if (savedInstanceState != null) {
            mTabManager.onTabChanged(savedInstanceState.getString("tab"));
        }
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putString("tab", mTabManager.getCurrentTabTag());
    }

    public void setMode(int mode){
        switch (mode){
            case FragmentMode.CLOUD:
                mTabManager.onTabChanged("cloud");
                break;
            case FragmentMode.LIST:
                mTabManager.onTabChanged("musiclist");
                break;
            default:
                break;
        }
    }

    private void initFragment(){
        mTabManager = new TabManager(this, R.id.realtabcontent);
        mTabManager.addTab(new TabSpec("cloud"),
                CloudResourcesFragment.class,null);
        mTabManager.addTab(new TabSpec("musiclist"),
                MusicListFragment.class,null);

        mTabManager.onTabChanged("cloud");
    }

    public class TabSpec{
        private String mTag;

        TabSpec (String tag){
            mTag = tag;
        }

        public String getTag(){
            return mTag;
        }
    }

    public static class TabManager{
        private final MainActivity mActivity;
        private final int mContainerId;
        private final HashMap<String, TabInfo> mTabs = new HashMap<String, TabInfo>();
        TabInfo mLastTab;

        static final class TabInfo {
            private final String tag;
            private final Class<?> clss;
            private final Bundle args;
            private Fragment fragment;

            TabInfo(String _tag, Class<?> _class, Bundle _args) {
                tag = _tag;
                clss = _class;
                args = _args;
            }
        }

        static class DummyTabFactory implements TabHost.TabContentFactory {
            private final Context mContext;

            public DummyTabFactory(Context context) {
                mContext = context;
            }

            @Override
            public View createTabContent(String tag) {
                View v = new View(mContext);
                v.setMinimumWidth(0);
                v.setMinimumHeight(0);
                return v;
            }
        }

        public TabManager(MainActivity activity , int containerId) {
            mActivity = activity;
            mContainerId = containerId;
        }

        public void addTab(TabSpec tabSpec, Class<?> clss, Bundle args) {
            //tabSpec.setContent(new DummyTabFactory(mActivity));
            String tag = tabSpec.getTag();

            TabInfo info = new TabInfo(tag, clss, args);

            // Check to see if we already have a fragment for this tab, probably
            // from a previously saved state.  If so, deactivate it, because our
            // initial state is that a tab isn't shown.
            info.fragment = mActivity.getSupportFragmentManager().findFragmentByTag(tag);
            if (info.fragment != null && !info.fragment.isDetached()) {
                FragmentTransaction ft = mActivity.getSupportFragmentManager().beginTransaction();
                ft.detach(info.fragment);
                ft.commit();
            }

            mTabs.put(tag, info);
        }

        public void onTabChanged(String tabId) {
            TabInfo newTab = mTabs.get(tabId);
            if (mLastTab != newTab)
            {
                FragmentTransaction ft = mActivity.getSupportFragmentManager().beginTransaction();
                if (mLastTab != null) {
                    if (mLastTab.fragment != null) {
                        ft.detach(mLastTab.fragment);
                    }
                }
                if (newTab != null) {
                    if (newTab.fragment == null) {
                        newTab.fragment = Fragment.instantiate(mActivity,
                                newTab.clss.getName(), newTab.args);
                        ft.add(mContainerId, newTab.fragment, newTab.tag);
                    } else {
                        ft.attach(newTab.fragment);
                    }
                }

                mLastTab = newTab;
                ft.commit();
                mActivity.getSupportFragmentManager().executePendingTransactions();
                mActivity.showContent();
            }
        }

        public String getCurrentTabTag(){
            return mLastTab.tag;
        }
    }
}
