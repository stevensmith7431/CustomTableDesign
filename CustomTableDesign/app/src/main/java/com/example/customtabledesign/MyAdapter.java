package com.example.customtabledesign;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

import java.util.ArrayList;

public class MyAdapter extends BaseAdapter {

    private Context mContext;
    ArrayList<MyList> arrayList;

    ArrayList<String> session_arrayList;
    ArrayAdapter<String> session_adapters;

    ArrayList<String> reason_arrayList;
    ArrayAdapter<String> reason_adapters;

    String leavedetails;
    String S_session;
    String s_purpose;
    String s_remarks;

    public MyAdapter(Context mContext,ArrayList<MyList> arrayList) {
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
    public View getView(int position, View convertView, ViewGroup parent) {
        convertView = LayoutInflater.from(mContext).inflate(R.layout.listitem, parent, false);
        TextView text1 = convertView.findViewById(R.id.text1);
        TextView text2 = convertView.findViewById(R.id.text2);
        TextView text3 = convertView.findViewById(R.id.text3);
        TextView text4 = convertView.findViewById(R.id.text4);
        TextView text5 = convertView.findViewById(R.id.text5);
        TextView text6 = convertView.findViewById(R.id.text6);
        TextView text7 = convertView.findViewById(R.id.text7);
        final Spinner session = convertView.findViewById(R.id.text8);
        final Spinner purpose = convertView.findViewById(R.id.text9);
        EditText remarks = convertView.findViewById(R.id.text10);

        final MyList listItem = arrayList.get(position);

        text1.setText(arrayList.get(position).getStudno());
        text2.setText(arrayList.get(position).getName());
        text3.setText(arrayList.get(position).getTeam());
        text4.setText(arrayList.get(position).getDept());
        text5.setText(arrayList.get(position).getMobile());
        text6.setText(arrayList.get(position).getCity());
        text7.setText(arrayList.get(position).getLeavedate());

        session_arrayList = new ArrayList<String>();
        session_arrayList.add("Select Session");
        session_arrayList.add("FullDay");
        session_arrayList.add("Forenoon");
        session_arrayList.add("Afternoon");
        session_adapters = new ArrayAdapter<String>(mContext, android.R.layout.simple_list_item_1, session_arrayList);
        session.setAdapter(session_adapters);

        reason_arrayList = new ArrayList<String>();
        reason_arrayList.add("Select Purpose");
        reason_arrayList.add("Illness");
        reason_arrayList.add("Personal Work");
        reason_arrayList.add("Marriage");
        reason_arrayList.add("Maternity");
        reason_arrayList.add("Others");
        reason_adapters = new ArrayAdapter<String>(mContext, android.R.layout.simple_list_item_1, reason_arrayList);
        purpose.setAdapter(reason_adapters);
        session.setTag(position);
        purpose.setTag(position);
        session.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

                {
                    Spinner parentRow = (Spinner) view.getParent();
                    int row = 0;
                    try {
                        String s = parentRow.getTag().toString();
                        row = Integer.parseInt(s);
                    } catch (Exception er) {
                        er.printStackTrace();
                    }

                    String selectedItem = parent.getItemAtPosition(position).toString();

                    if (selectedItem.equals("FullDay")) {
                        S_session = session.getSelectedItem().toString();
                        leavedetails = "1.00";
                    } else if (selectedItem.equals("Forenoon")) {
                        S_session = session.getSelectedItem().toString();
                        leavedetails = "0.50";
                    } else if (selectedItem.equals("Afternoon")) {
                        S_session = session.getSelectedItem().toString();
                        leavedetails = "0.50";
                    }
                    else if (selectedItem.equalsIgnoreCase("select session")) {
                        S_session = session.getSelectedItem().toString();
                        leavedetails = "0";
                    }
                    MainActivity.listitems.get(row).session = S_session;
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        purpose.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

                {
                    Spinner parentRow = (Spinner) view.getParent();
                    int row = 0;
                    try {
                        String s = parentRow.getTag().toString();
                        row = Integer.parseInt(s);
                    } catch (Exception er) {
                        er.printStackTrace();
                    }
                    String selectedItem = parent.getItemAtPosition(position).toString();

                    if (selectedItem.equals("Illness")) {

                        s_purpose = purpose.getSelectedItem().toString();

                    } else if (selectedItem.equals("Personal Work")) {

                        s_purpose = purpose.getSelectedItem().toString();

                    } else if (selectedItem.equals("Marriage")) {

                        s_purpose = purpose.getSelectedItem().toString();

                    } else if (selectedItem.equals("Maternity")) {

                        s_purpose = purpose.getSelectedItem().toString();

                    } else if (selectedItem.equals("Others")) {

                        s_purpose = purpose.getSelectedItem().toString();

                    }else if (selectedItem.equalsIgnoreCase("select purpose")) {
                        s_purpose = purpose.getSelectedItem().toString();
                    }
                    MainActivity.listitems.get(row).purpose = s_purpose;
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        remarks.setText(arrayList.get(position).getRemarks());
        s_remarks = remarks.getText().toString();
        return convertView;
    }
}
