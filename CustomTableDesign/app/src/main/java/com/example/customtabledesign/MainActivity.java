package com.example.customtabledesign;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private static final String NAMESPACE = "http://tempuri.org/";
    TextView eT_currentdate;
    public ListView listView;
    public ListView listViewitems;
    public static List<MyList> listitems;
    public static MyAdapter adapter;
    List<ListHeader> listitems1;
    private AdapterHeader adapter1;
    String fromdate, todate, empno, appno, team, unit, caretaker, username, session, purpose, remarks, leavedetails;
    Button save;
    public JSONArray jsonArray = null;
    public static String EmpNo = "", Name = "", Team = "", Dept = "", Designation = "",
            Unit = "", Date = "";

    Toast failed, sucess;
    String s_emp, s_name, s_team, s_dept, s_desgination, s_unit, s_session, s_purpose, s_remarks, s_date;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        listView = findViewById(R.id.listitemsid);
        listViewitems = findViewById(R.id.listviewhdr);

        eT_currentdate = findViewById(R.id.currentdate);
        save = findViewById(R.id.saveid);

        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        String currentDateandTime = sdf.format(new Date());
        eT_currentdate.setText(currentDateandTime);

        listitems1 = new ArrayList<ListHeader>();
        listitems1.add(new ListHeader("Studno", "Name", "Team", "Dept", "Mobile", "City", "Leave Date", "Session", "Purpose", "Remarks"));
        adapter1 = new AdapterHeader(getApplicationContext(), (ArrayList<ListHeader>) listitems1);
        listViewitems.setAdapter(adapter1);

        LayoutInflater inflater = getLayoutInflater();
        View layout = inflater.inflate(R.layout.leave_entry_failed, (ViewGroup) findViewById(R.id.layoutid));

        failed = new Toast(getApplicationContext());
        failed.setGravity(Gravity.CENTER_VERTICAL, 0, 0);
        failed.setDuration(Toast.LENGTH_SHORT);
        failed.setView(layout);


        LayoutInflater inflater1 = getLayoutInflater();
        View layout1 = inflater1.inflate(R.layout.leave_success_msg, (ViewGroup) findViewById(R.id.layoutid));

        sucess = new Toast(getApplicationContext());
        sucess.setGravity(Gravity.CENTER_VERTICAL, 0, 0);
        sucess.setDuration(Toast.LENGTH_SHORT);
        sucess.setView(layout1);

        /*Intent intent = getIntent();

        fromdate = intent.getExtras().getString("FromDate");
        todate = intent.getExtras().getString("ToDate");
        empno = intent.getExtras().getString("Empnum");
        team = intent.getExtras().getString("Team");
        unit = intent.getExtras().getString("Unit");
        appno = intent.getExtras().getString("AppNo");
        caretaker = intent.getExtras().getString("CareTaker");
        username = intent.getExtras().getString("Username");

        (new getLeaveEntryDetails()).execute(fromdate, todate, empno, team, unit);*/


        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                for (int i = 0; i < listitems.size(); i++) {

                    MyList item = listitems.get(i);

                    if (item.session == null) {
                        Toast.makeText(MainActivity.this, "Please select session", Toast.LENGTH_SHORT).show();
                        return;
                    }
                    if (item.session.equalsIgnoreCase("select session")) {
                        Toast.makeText(MainActivity.this, "Please select session", Toast.LENGTH_SHORT).show();
                        return;
                    }
                    if (item.session.isEmpty()) {
                        Toast.makeText(MainActivity.this, "Please select session", Toast.LENGTH_SHORT).show();
                        return;
                    }
                    if (item.purpose == null) {
                        Toast.makeText(MainActivity.this, "Please select Purpose", Toast.LENGTH_SHORT).show();
                        return;
                    }
                    if (item.purpose.isEmpty()) {
                        Toast.makeText(MainActivity.this, "Please select Purpose", Toast.LENGTH_SHORT).show();
                        return;
                    }
                    if (item.purpose.equalsIgnoreCase("select purpose")) {
                        Toast.makeText(MainActivity.this, "Please select Purpose", Toast.LENGTH_SHORT).show();
                        return;
                    }
                }

                for (int i = 0; i < listitems.size(); i++) {

                    MyList item = listitems.get(i);

                    s_emp = item.studno;
                    s_name = item.name;
                    s_team = item.team;
                    s_dept = item.dept;
                    s_desgination = item.mobile;
                    s_unit = item.city;
                    s_session = item.session;
                    s_purpose = item.purpose;
                    s_remarks = item.remarks;
                    s_date = item.leavedate;

                    if (item.session == null) {
                        Toast.makeText(MainActivity.this, "Please select session", Toast.LENGTH_SHORT).show();
                    } else if (item.purpose == null) {
                        Toast.makeText(MainActivity.this, "Please select Purpose", Toast.LENGTH_SHORT).show();
                    } else {
                        DateFormat inputFormat = new SimpleDateFormat("dd MMM yyyy");
                        DateFormat outputFormat = new SimpleDateFormat("dd/MM/yyyy");
                        Date date = null;
                        try {
                            date = inputFormat.parse(s_date);
                        } catch (ParseException e) {
                            e.printStackTrace();
                        }
                        s_date = outputFormat.format(date);
                        if (s_session.equals("FullDay")) {
                            leavedetails = "1.00";
                        } else if (s_session.equals("Forenoon")) {
                            leavedetails = "0.50";
                        } else if (s_session.equals("Afternoon")) {
                            leavedetails = "0.50";
                        }

                     //   (new CheckDuplicateEntry()).execute(appno, s_emp, s_name, s_team, s_dept, s_desgination, s_unit, eT_currentdate.getText().toString(), s_date, leavedetails, caretaker, s_purpose, s_remarks, s_session, username);

                    }
                }
            }
        });
    }


    public class getLeaveEntryDetails extends AsyncTask<String, String, String> {

        private ProgressDialog progressDialog;

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            progressDialog = new ProgressDialog(MainActivity.this);
            progressDialog.setMessage("Loading, Please Wait...");
            progressDialog.show();
            progressDialog.setCanceledOnTouchOutside(false);
        }

        @Override
        protected String doInBackground(String... params) {
            String[] paras = {"datefrom", "dateto", "empno", "team", "unit"};
            String[] values = {params[0], params[1], params[2], params[3], params[4]};
            String methodname = "getLeaveDeatils";
            String URL = "http://IP/Service/Request.asmx";
            return WebService.WebServiceCall(paras, values, methodname, NAMESPACE, URL);
        }

        @Override
        protected void onPostExecute(String result) {
            super.onPostExecute(result);
            progressDialog.dismiss();

            if (result.equals("Invalid Date...Please select valid date")) {

                Toast.makeText(MainActivity.this, "Invalid date", Toast.LENGTH_SHORT).show();

            } else {

                listitems = new ArrayList<>();
                try {
                    jsonArray = new JSONArray(result);
                } catch (JSONException e) {
                    e.printStackTrace();
                }
                for (int i = 0; i < jsonArray.length(); i++) {
                    try {
                        JSONObject jsonobj = new JSONObject(jsonArray.get(i).toString());

                        EmpNo = jsonobj.getString("Empno");
                        Name = jsonobj.getString("Name");
                        Team = jsonobj.getString("Team");
                        Dept = jsonobj.getString("Dept");
                        Designation = jsonobj.getString("Designation");
                        Unit = jsonobj.getString("Unit");
                        Date = jsonobj.getString("date");

                        listitems.add(new MyList(EmpNo, Name, Team, Dept, Designation, Unit, Date, session, purpose, remarks));

                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }
                adapter = new MyAdapter(getApplicationContext(), (ArrayList<MyList>) listitems);
                listView.setAdapter(adapter);
            }
        }
    }

    public class CheckDuplicateEntry extends AsyncTask<String, String, String> {

        String c_appno, c_EmpNo, c_Name, c_Team, c_Dept, c_Designation, c_Unit, c_currentdate, c_outputDateStr, c_leavedetails, c_caretaker, c_purpose, c_remarks, c_session, c_username;

        private ProgressDialog progressDialog;

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            progressDialog = new ProgressDialog(MainActivity.this);
            progressDialog.setMessage("Loading, Please Wait...");
            progressDialog.show();
            progressDialog.setCanceledOnTouchOutside(false);
        }

        @Override
        protected String doInBackground(String... params) {
            c_appno = params[0];
            c_EmpNo = params[1];
            c_Name = params[2];
            c_Team = params[3];
            c_Dept = params[4];
            c_Designation = params[5];
            c_Unit = params[6];
            c_currentdate = params[7];
            c_outputDateStr = params[8];
            c_leavedetails = params[9];
            c_caretaker = params[10];
            c_purpose = params[11];
            c_remarks = params[12];
            c_session = params[13];
            c_username = params[14];
            String[] paras = {"appno", "leavedate"};
            String[] values = {params[0], params[8]};
            String methodname = "checkDublicateLeaveEntry";
            String URL = "http://myIP/Service/Request.asmx";
            return WebService.WebServiceCall(paras, values, methodname, NAMESPACE, URL);
        }

        @Override
        protected void onPostExecute(String result) {
            super.onPostExecute(result);
            progressDialog.dismiss();

            if (result.equals("true")) {

             //   (new insertEmpLeaveEntry()).execute(c_appno, c_EmpNo, c_Name, c_Team, c_Dept, c_Designation, c_Unit, c_currentdate, c_outputDateStr, c_leavedetails, c_caretaker, c_purpose, c_remarks, c_session, c_username);

            } else if (result.equals("false")) {

                Toast.makeText(getApplicationContext(), "Unable to validate duplicate entry", Toast.LENGTH_SHORT).show();

            }
        }
    }

    public class insertEmpLeaveEntry extends AsyncTask<String, String, String> {

        private ProgressDialog progressDialog;

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            progressDialog = new ProgressDialog(MainActivity.this);
            progressDialog.setMessage("Loading, Please Wait...");
            progressDialog.show();
            progressDialog.setCanceledOnTouchOutside(false);
        }

        @Override
        protected String doInBackground(String... params) {
            String[] paras = {"Appno", "EmpNo", "Name", "Team", "Dept", "Designation", "Unit", "RequestDate", "LeaveReqDate", "LeaveDetails", "CareTaker", "Reason", "Remarks", "Session", "UserName", "deviceid"};
            String[] values = {params[0], params[1], params[2], params[3], params[4], params[5], params[6], params[7], params[8], params[9], params[10], params[11], params[12], params[13], params[14], "deviceid"};
            String methodname = "insertLeaveRequest";
            String URL = "http://myIP/Service/Request.asmx";
            return WebService.WebServiceCall(paras, values, methodname, NAMESPACE, URL);
        }

        @Override
        protected void onPostExecute(String result) {
            super.onPostExecute(result);
            progressDialog.dismiss();

            if (result.equals("ok")) {

                sucess.show();
                Intent intent = new Intent(MainActivity.this, MainActivity.class);
                startActivity(intent);
                finish();

            } else {

                failed.show();
            }
        }
    }
}
