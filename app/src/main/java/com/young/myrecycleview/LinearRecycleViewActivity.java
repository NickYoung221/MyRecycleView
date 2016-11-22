package com.young.myrecycleview;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.young.adapter.LlAdapter;
import com.young.decoration.MyLlDecoration;

import java.util.ArrayList;

import butterknife.ButterKnife;
import butterknife.InjectView;
import butterknife.OnClick;


/**
 * 修改记录后，只刷新一条；
 */
public class LinearRecycleViewActivity extends AppCompatActivity {

    @InjectView(R.id.myRecycle)
    RecyclerView myRecycle;
    @InjectView(R.id.btn_change_one)
    Button btnChangeOne;
    @InjectView(R.id.btn_change_many)
    Button btnChangeMany;
    @InjectView(R.id.btn_remove_one)
    Button btnRemoveOne;
    @InjectView(R.id.btn_remove_many)
    Button btnRemoveMany;
    @InjectView(R.id.btn_add_one)
    Button btnAddOne;
    @InjectView(R.id.btn_add_many)
    Button btnAddMany;
    private ArrayList<String> mDatas;
    LlAdapter llAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_linear_recycle_view);
        ButterKnife.inject(this);

        initData();//初始化数据

        //设置布局:第3个参数，true:设置数据源是反的；
        myRecycle.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));
        //设置adapter
        llAdapter = new LlAdapter(this, mDatas);
        myRecycle.setAdapter(llAdapter);
        //设置分隔符
        myRecycle.addItemDecoration(new MyLlDecoration(this, MyLlDecoration.VERTICAL_LIST));
        //设置动画：添加，删除的动画
        myRecycle.setItemAnimator(new DefaultItemAnimator());

        //设置点击事件
        llAdapter.setMyItemClickListener(new MyItemClickListener() {
            @Override
            public void onItemClick(View v, int position) {
                //设置点击事件
                Log.i("LinearRecycleView", "onItemClick: " + v + ",position:" + position);
            }
        });


    }


    protected void initData() {
        mDatas = new ArrayList<String>();
        for (int i = 'A'; i < 'z'; i++) {
            mDatas.add("" + (char) i);
        }
    }


    @OnClick({R.id.btn_change_one, R.id.btn_change_many, R.id.btn_remove_one, R.id.btn_remove_many, R.id.btn_add_one, R.id.btn_add_many})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btn_change_one:
                //改变一条记录:第3-5条，只刷新一条
                mDatas.set(2,"C2");
                mDatas.set(3,"D2");
                mDatas.set(4,"E2");
                mDatas.set(5,"F2");
                llAdapter.notifyItemChanged(2);//设置第2条记录改变

                break;
            case R.id.btn_change_many:
                mDatas.set(2,"C3");
                mDatas.set(3,"D3");
                mDatas.set(4,"E3");
                mDatas.set(5,"F3");
                llAdapter.notifyItemRangeChanged(2,5);
               // llAdapter.notifyI//设置第2条记录改变
                break;
            case R.id.btn_remove_one:
                //移动：从一个位置，移动到另外一个位置（position:1的记录，移动到position位4的位置）;其他内容向上移动
                llAdapter.notifyItemMoved(1,4);
                break;
            case R.id.btn_remove_many:
                //删除一条记录:从某个位置开始，删除几条
                llAdapter.notifyItemRangeRemoved(2,2);
                break;
            case R.id.btn_add_one:
                //添加一条记录
                mDatas.add(2,"add2");//在2的位置加入一条数据

                llAdapter.notifyItemInserted(2);//插入一条记录


                break;
            case R.id.btn_add_many:

                mDatas.add(2,"add2");//在2的位置加入一条数据
                mDatas.add(3,"add3");
                mDatas.add(4,"add4");

                llAdapter.notifyItemRangeInserted(2,3);
                //llAdapter.notifyItemInserted(2);//插入一条记录


                break;
        }
    }
}
