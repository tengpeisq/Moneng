package com.example.moral;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.widget.FrameLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import com.example.moral.fragment.LiveFragment;
import com.example.moral.fragment.MessageFragment;
import com.example.moral.fragment.MyFragment;
import com.example.moral.fragment.QueryFragment;

import butterknife.Bind;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity {


    @Bind(R.id.fl_content)
    FrameLayout flContent;
    @Bind(R.id.rb_message)
    RadioButton rbMessage;
    @Bind(R.id.rb_live)
    RadioButton rbLive;
    @Bind(R.id.rb_query)
    RadioButton rbQuery;
    @Bind(R.id.rb_me)
    RadioButton rbMe;
    @Bind(R.id.rg)
    RadioGroup rg;
    private MessageFragment messageFragment;
    private LiveFragment liveFragment;
    private QueryFragment queryFragment;
    private MyFragment myFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        if (messageFragment == null) {
            messageFragment = new MessageFragment();
        }
        if (liveFragment == null) {
            liveFragment = new LiveFragment();
        }
        if (queryFragment == null) {
            queryFragment = new QueryFragment();
        }
        if (myFragment == null) {
            myFragment = new MyFragment();
        }
        rg.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                Fragment fragment=null;
                switch (checkedId){
                    case R.id.rb_message:
                        fragment=messageFragment;
                        break;
                    case R.id.rb_live:
                        fragment=liveFragment;
                        break;
                    case R.id.rb_query:
                        fragment=queryFragment;
                        break;
                    case R.id.rb_me:
                        fragment=myFragment;
                        break;
                }
                switchFragment(fragment);
            }
        });
        rg.check(R.id.rb_message);
    }

    private void switchFragment(Fragment fragment) {
        FragmentManager manager=getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = manager.beginTransaction();
        fragmentTransaction.replace(R.id.fl_content,fragment).commit();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        ButterKnife.unbind(this);
    }
}
