package com.young.myrecycleview;

import android.view.View;

/**
 * Created by dliu on 2016/11/7.
 */
public interface MyItemClickListener {
    //每个子的view,以及当前点击的item的位置
    void onItemClick(View v, int position);
}
