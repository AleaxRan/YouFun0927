package com.atguigu.youfun0927.activity;

import android.os.Bundle;
import android.os.Handler;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.view.KeyEvent;
import android.widget.ImageButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.atguigu.youfun0927.R;
import com.atguigu.youfun0927.base.Basefragment;
import com.atguigu.youfun0927.fragment.ClassifyFragment;
import com.atguigu.youfun0927.fragment.HomeFragment;
import com.atguigu.youfun0927.fragment.InspirationFragment;
import com.atguigu.youfun0927.fragment.LeftmenuFragment;
import com.atguigu.youfun0927.fragment.SettingFragment;
import com.atguigu.youfun0927.fragment.ShoppingbagFragment;
import com.atguigu.youfun0927.utils.DensityUtil;
import com.jeremyfeinstein.slidingmenu.lib.SlidingMenu;
import com.jeremyfeinstein.slidingmenu.lib.app.SlidingFragmentActivity;

import java.util.ArrayList;

public class MainActivity extends SlidingFragmentActivity {

    private RadioGroup rg_main;
    private ArrayList<Basefragment> basefragmentArrayList;

    //每个fragment的位置
    private int fPosition = 0;

    private Fragment mContent;
    private FragmentTransaction ft;
    private ImageButton ib_menu;
    private TextView tv_title;
    private ImageButton ib_search;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        findView();

        //initTitlebar();

        //添加侧滑菜单
        initSlidingMenu();

        //把5个Basefragment的子类添加到集合当中
        initFragment();

        //radioGroup的监听
        setListener();

        initleftFragment();


    }

//    private void initTitlebar() {
//        ib_menu.setVisibility(View.VISIBLE);
//        ib_menu.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//
//                MainActivity.this.getSlidingMenu().toggle();
//
//            }
//        });
//
//        ib_search.setVisibility(View.VISIBLE);
//
//    }

    private void findView() {
        rg_main = (RadioGroup) findViewById(R.id.rg_main);


        tv_title = (TextView) findViewById(R.id.tv_title);
        ib_menu = (ImageButton) findViewById(R.id.ib_menu);
        ib_search = (ImageButton) findViewById(R.id.ib_search);
    }

    private void initleftFragment() {

        ft.replace(R.id.fl_leftmenu, new LeftmenuFragment());
    }

    //添加侧滑菜单
    private void initSlidingMenu() {
        //2.设置左侧菜单
        setBehindContentView(R.layout.activity_leftmenu);

        SlidingMenu slidingMenu = getSlidingMenu();
        //4.设置显示的模式：左侧菜单+主页，左侧菜单+主页面+右侧菜单；主页面+右侧菜单
        slidingMenu.setMode(SlidingMenu.LEFT);

        //设置滑动模式
        slidingMenu.setTouchModeAbove(SlidingMenu.TOUCHMODE_MARGIN);

        //6.设置主页占据的宽度
        slidingMenu.setBehindOffset(DensityUtil.dip2px(MainActivity.this,100));

    }

    //radiogroup的监听
    private void setListener() {
        rg_main.setOnCheckedChangeListener(new MyOnCheckedChangeListener());

        rg_main.check(R.id.rb_home);

    }

    class MyOnCheckedChangeListener implements RadioGroup.OnCheckedChangeListener {

        private Fragment toFragment;

        @Override
        public void onCheckedChanged(RadioGroup group, int checkedId) {
            switch (checkedId){

                case R.id.rb_home://0
                    fPosition=0;
                    break;
                case R.id.rb_classify://1
                    fPosition=1;
                    break;
                case R.id.rb_inspiration://2
                    fPosition=2;
                    break;
                case R.id.rb_shoppingbag:
                    fPosition=3;
                    break;
                case R.id.rb_setting:
                    fPosition=4;
                    break;

            }

            //根据位置从集合中取对应的Fragment
            if(basefragmentArrayList!=null && basefragmentArrayList.size()>0) {
                toFragment = basefragmentArrayList.get(fPosition);
            }

            switchFragment(mContent,toFragment);

        }

    }

    //选择哪一个fragment的方法
    private void switchFragment(Fragment fromFragment , Fragment toFragment) {
        if(mContent != toFragment) {
            mContent = toFragment;

            if(toFragment != null) {

                ft = getSupportFragmentManager().beginTransaction();


                if(!toFragment.isAdded()) {
                    //如果没有添加
                    //隐藏fromFragment
                    if (fromFragment != null) {
                        ft.hide(fromFragment);
                    }
                    //添加toFragment
                    ft.add(R.id.fl_content, toFragment).commit();

                }else{

                    //如果添加了
                    //也得先隐藏fromfragment
                    if(fromFragment!=null) {
                        ft.hide(fromFragment);
                    }
                    //不是添加了只是显示
                    ft.show(toFragment).commit();

                }
            }
        }
    }




    private void initFragment() {
        basefragmentArrayList = new ArrayList<>();
        basefragmentArrayList.add(new HomeFragment());
        basefragmentArrayList.add(new ClassifyFragment());
        basefragmentArrayList.add(new InspirationFragment());
        basefragmentArrayList.add(new ShoppingbagFragment());
        basefragmentArrayList.add(new SettingFragment());
    }

    //连按两次推出
    private boolean isExit = false;
    Handler handler = new Handler();

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if(keyCode == KeyEvent.KEYCODE_BACK) {
            if(fPosition!=0) {
                rg_main.check(R.id.rb_home);

                return true;
            }else if(!isExit){
                isExit = true;
                Toast.makeText(MainActivity.this, "再点一次退出", Toast.LENGTH_SHORT).show();
                handler.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        isExit = false;
                    }
                },2000);

                return true;

            }

        }

        return super.onKeyDown(keyCode, event);
    }

}
