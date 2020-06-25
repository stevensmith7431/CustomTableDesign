package com.example.customtabledesign;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;

public class AdapterHeader extends BaseAdapter {

    private Context mContext;
    ArrayList<ListHeader> arrayList;

    public AdapterHeader(Context mContext, ArrayList<ListHeader> arrayList) {
        this.mContext = mContext;
        this.arrayList = arrayList;
    }

    @Override
    public int getCount() {
        return arrayList.size();
    }

    @Override
    public Object getItem(int position) {
        return position;
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View view, ViewGroup parent) {

        view = LayoutInflater.from(mContext).inflate(R.layout.header_design, parent, false);
        TextView text1 = view.findViewById(R.id.text1);
        TextView text2 = view.findViewById(R.id.text2);
        TextView text3 = view.findViewById(R.id.text3);
        TextView text4 = view.findViewById(R.id.text4);
        TextView text5 = view.findViewById(R.id.text5);
        TextView text6 = view.findViewById(R.id.text6);
        TextView text7 = view.findViewById(R.id.text7);
        TextView text8 = view.findViewById(R.id.text8);
        TextView text9 = view.findViewById(R.id.text9);
        TextView text10 = view.findViewById(R.id.text10);

        final ListHeader listItem = arrayList.get(position);

        text1.setText(arrayList.get(position).getStudno());
        text2.setText(arrayList.get(position).getName());
        text3.setText(arrayList.get(position).getTeam());
        text4.setText(arrayList.get(position).getDept());
        text5.setText(arrayList.get(position).getMobile());
        text6.setText(arrayList.get(position).getCity());
        text7.setText(arrayList.get(position).getLeavedate());
        text8.setText(arrayList.get(position).getSession());
        text9.setText(arrayList.get(position).getPurpose());
        text10.setText(arrayList.get(position).getRemarks());

        return view;
    }
}
