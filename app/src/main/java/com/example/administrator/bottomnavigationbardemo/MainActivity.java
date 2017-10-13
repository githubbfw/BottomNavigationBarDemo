package com.example.administrator.bottomnavigationbardemo;

import android.os.Bundle;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.FrameLayout;
import android.widget.TextView;

import com.ashokvarma.bottomnavigation.BottomNavigationBar;
import com.ashokvarma.bottomnavigation.BottomNavigationItem;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements BottomNavigationBar.OnTabSelectedListener{


    private BottomNavigationView bottomNavigationView;
    private TextView mNavTv;


    private BottomNavigationBar bottomNavigationBar;
    private FrameLayout frameLayout;

    private ArrayList<Fragment> fragments;

//    /**
//     * 选中的Fragment的对应的位置
//     */
//    private int position;

    /**
     * 上次切换的Fragment
     */
    private Fragment mContent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        bottomNavigationView= (BottomNavigationView) findViewById(R.id.bottom_nav);
        mNavTv= (TextView) findViewById(R.id.nav_tv);


        bottomNavigationBar = (BottomNavigationBar) findViewById(R.id.bottom_navigation_bar);
        frameLayout= (FrameLayout) findViewById(R.id.layFrame);


        bottomNavigationBar.setMode(BottomNavigationBar.MODE_SHIFTING);
        bottomNavigationBar
                .setBackgroundStyle(BottomNavigationBar.BACKGROUND_STYLE_STATIC
                );
        bottomNavigationBar.addItem(new BottomNavigationItem(R.mipmap.ic_launcher, "Home").setActiveColorResource(R.color.colorAccent))
                .addItem(new BottomNavigationItem(R.mipmap.ic_launcher, "Books").setActiveColorResource(R.color.colorAccent))
                .addItem(new BottomNavigationItem(R.mipmap.ic_launcher, "Music").setActiveColorResource(R.color.colorAccent))
                .addItem(new BottomNavigationItem(R.mipmap.ic_launcher, "Movies & TV").setActiveColorResource(R.color.colorAccent))
                .addItem(new BottomNavigationItem(R.mipmap.ic_launcher, "Games").setActiveColorResource(R.color.colorAccent))
                .setFirstSelectedPosition(0)
                .initialise();

        fragments = getFragments();
        setDefaultFragment();
        bottomNavigationBar.setTabSelectedListener(this);




//        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
//            @Override
//            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
//
//                switch (item.getItemId()) {
//                    case R.id.bottom_nav_ui:
//                        mNavTv.setText(item.getTitle());
//                        break;
//                    case R.id.bottom_nav_data:
//                        mNavTv.setText(item.getTitle());
//                        break;
//                    case R.id.bottom_nav_service:
//                        mNavTv.setText(item.getTitle());
//                        break;
//                    case R.id.bottom_nav_net:
//                        mNavTv.setText(item.getTitle());
//                        break;
//                    case R.id.bottom_nav_media:
//                        mNavTv.setText(item.getTitle());
//                        break;
//                }
//
//
//
//                return true;
//            }
//        });


    }
    /**
     * 设置默认的
     */
    private void setDefaultFragment() {
        FragmentManager fm = getSupportFragmentManager();
        FragmentTransaction transaction = fm.beginTransaction();
        transaction.add(R.id.layFrame, HomeFragment.newInstance("Home"));
        transaction.commit();
    }

    private ArrayList<Fragment> getFragments() {
        ArrayList<Fragment> fragments = new ArrayList<>();
        fragments.add(HomeFragment.newInstance("Home"));
        fragments.add(BookFragment.newInstance("Books"));
        fragments.add(MusicFragment.newInstance("Music"));
        fragments.add(TvFragment.newInstance("Movies & TV"));
        fragments.add(GameFragment.newInstance("Games"));
        return fragments;
    }


    @Override
    public void onTabSelected(int position) {

        if (fragments != null) {
            if (position < fragments.size()) {
                FragmentManager fm = getSupportFragmentManager();
                FragmentTransaction ft = fm.beginTransaction();
                //当前的fragment
                Fragment from = fm.findFragmentById(R.id.layFrame);

                //点击即将跳转的fragment
                Fragment to = fragments.get(position);
                if (to.isAdded()) {
                   ft.hide(from).show(to);
                } else {
                   ft.hide(from).add(R.id.layFrame,to);
                    if (to.isHidden()) {
                        ft.show(to);
                        Log.d("----------------","被隐藏了");
                        Log.d("----------------","为什么这个没有添加到GitHub上去");

                    }
                }
//                else {
//                    // 隐藏当前的fragment，add下一个到Activity中
//                    ft.hide(from).add(R.id.layFrame, fragment);
//                    if (fragment.isHidden()) {
//                        ft.show(fragment);
//                        Logger.d("被隐藏了");
//                    }
//                }


                ft.commitAllowingStateLoss();
            }
        }





    }

    @Override
    public void onTabUnselected(int position) {

        if (fragments != null) {
            if (position < fragments.size()) {
                FragmentManager fm = getSupportFragmentManager();
                FragmentTransaction ft = fm.beginTransaction();
                Fragment fragment = fragments.get(position);
                ft.hide(fragment);
                ft.commitAllowingStateLoss();
            }
        }


    }

    @Override
    public void onTabReselected(int position) {

    }

    private Fragment getFragment(int position){
        Fragment fragment = fragments.get(position);
        return fragment;
    }


    /**
     *
     * @param from 刚显示的Fragment,马上就要被隐藏了
     * @param to 马上要切换到的Fragment，一会要显示
     */

    private void switchFrament(Fragment from,Fragment to) {
        if(from != to){
            mContent = to;
            FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
            //才切换
            //判断有没有被添加
            if(!to.isAdded()){
                //to没有被添加
                //from隐藏
                if(from != null){
                    ft.hide(from);
                }
                //添加to
                if(to != null){
                    ft.add(R.id.layFrame,to).commit();
                }
            }else{
                //to已经被添加
                // from隐藏
                if(from != null){
                    ft.hide(from);
                }
                //显示to
                if(to != null){
                    ft.show(to).commit();
                }
            }
        }

    }




}
