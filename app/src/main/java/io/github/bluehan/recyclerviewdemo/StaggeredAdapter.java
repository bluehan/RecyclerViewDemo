package io.github.bluehan.recyclerviewdemo;

import android.content.Context;
import android.view.ViewGroup;

import java.util.ArrayList;

public class StaggeredAdapter extends SimpleAdapter {

    private ArrayList<Integer> mHeights;

    public StaggeredAdapter(Context context, ArrayList<String> datas) {
        super(context, datas);
        mHeights = new ArrayList<>();
        for (int i = 0; i < mDatas.size(); i++) {
            mHeights.add((int) (200 + 400 * Math.random()));
        }
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        ViewGroup.LayoutParams lp = holder.itemView.getLayoutParams();
        lp.height = mHeights.get(position);
        holder.itemView.setLayoutParams(lp);
        holder.tv.setText(mDatas.get(position));
        setListener(holder);
    }
}

