package com.example.testing1201;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class MyAdapter extends RecyclerView.Adapter<MyAdapter.ViewHolder> {
    View view;
    private Context mContext;
    List<Data> data=new ArrayList<>();
    private int position=0;
    private boolean focusOnOne=false;
    public MyAdapter(Context context) {
        this.mContext = context;
    }

    public void setData(List<Data> data){
        this.data=data;
        notifyDataSetChanged();
    }

    public void setFocusOnOne(boolean focusOnOne){
        this.focusOnOne=focusOnOne;

    }

    public void  setFocusPosition(int position,boolean focusOnOne){
        this.position=position;
        this.focusOnOne=focusOnOne;
        notifyDataSetChanged();
    }

    public void  setChangePosition(int position,boolean focusOnOne,int updownleftright){
        if (updownleftright==1){

            this.position=position;
            this.focusOnOne=focusOnOne;
            notifyDataSetChanged();
        }


    }

    public void cleanFocus(){
        view.clearFocus();
    }
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent,
                                         int viewType) {
         view = LayoutInflater
                .from(mContext)
                .inflate(R.layout.cell_post, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        Data post = data.get(position);
        holder.tvContent.setText(post.getText());
    }

    @Override
    public int getItemCount() {
        return data.size();
    }


    @Override
    public void onViewAttachedToWindow(@NonNull ViewHolder holder) {
        super.onViewAttachedToWindow(holder);
        Log.d("7777777777", "onViewAttachedToWindow: "+holder.getAdapterPosition()+"ttttttt"+focusOnOne);
//        if (holder.getAdapterPosition()==0 && (focusOnOne)){
//            holder.itemView.requestFocus();
//        }
        if (holder.getAdapterPosition()==position && (focusOnOne)){
            holder.itemView.requestFocus();
        }
    }


    class ViewHolder extends RecyclerView.ViewHolder {


        public TextView tvContent;
        public Button btnLike;


        public ViewHolder(View itemView) {
            super(itemView);
            tvContent=itemView.findViewById(R.id.tttt);
            btnLike=itemView.findViewById(R.id.img);
        }
    }
}