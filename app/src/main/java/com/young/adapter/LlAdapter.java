package com.young.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.util.SparseArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.young.myrecycleview.LinearRecycleViewActivity;
import com.young.myrecycleview.MyItemClickListener;
import com.young.myrecycleview.R;

import java.util.List;

/**
 * Created by young on 2016/10/19.
 * 每个item的点击事件不一致
 */
public class LlAdapter extends RecyclerView.Adapter {

    Context context;
    List<String> datas;
    MyItemClickListener myItemClickListener;
    public LlAdapter(Context context, List<String> datas){
        this.context=context;
        this.datas=datas;

    }

    //创建viewholder
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        //如果是复用的view，不会再次执行onCreateViewHolder
        Log.i("LlAdapter", "onCreateViewHolder: ");
        View view= LayoutInflater.from(context).inflate(R.layout.ll_recycle_item,parent,false);
        //设置根布局的点击事件
        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.i("LlAdapterClick", "onClick: 进入点击事件1");
                if(myItemClickListener!=null) {
                    Log.i("LlAdapterClick", "onClick: 进入点击事件");
                    //回调自己定义的接口方法
                    myItemClickListener.onItemClick(v, (int) v.getTag());
                }
            }
        });

        return new MyLinearViewHolder(view);
    }

    //viewholder中控件赋值
    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {

        //一直调用:从第14条开始，复用第一条的viewholder中数据
        MyLinearViewHolder myLinearViewHolder= (MyLinearViewHolder) holder;
        //Log.i("LlAdapter", "onBindViewHolder: "+position+",tv:"+myLinearViewHolder.tv);
        //控件赋值
        myLinearViewHolder.tv.setText(datas.get(position));

        //获取viewholder对应的view,设置view的tag；
        myLinearViewHolder.itemView.setTag(position);
    }


    //总共的Item的个数
    @Override
    public int getItemCount() {
        return datas.size();
    }

    //保存显示的控件对象
    class MyLinearViewHolder extends RecyclerView.ViewHolder{

        //item中的控件
        TextView tv;

        //传一个view：每个item对应的view
        public MyLinearViewHolder(View itemView) {
            super(itemView);
            Log.i("LlAdapter", "MyLinearViewHolder: "+itemView);
            //构造方法中控件赋值
            tv= (TextView) itemView.findViewById(R.id.id_num);

        }


    }

    //赋值给接口
    public void setMyItemClickListener(MyItemClickListener myItemClickListener){
        this.myItemClickListener=myItemClickListener;
    }
}
