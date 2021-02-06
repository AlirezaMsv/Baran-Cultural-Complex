package com.example.baran21;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.alirezaafkar.sundatepicker.interfaces.DateSetListener;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;

public class MeetingsActivity extends AppCompatActivity implements DateSetListener {
    AlertDialog alertDialog;
    String date;
    EditText day, month, year, note;
    Button badd, bcancel, showcalmeet;
    ArrayList<Meeting> meetinglist = new ArrayList<>();
    String responseString;
    RecyclerView recyclerView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_meetings);
        recyclerView = findViewById(R.id.meetinglst);
        getdata();
    }
//_______________________________________________________________________________________
    public void addmeeting(final View view) {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        final View v_alert = getLayoutInflater().inflate(R.layout.add_meeting_alert, null);
        day = v_alert.findViewById(R.id.alertcalday);
        month = v_alert.findViewById(R.id.alertcalmonth);
        year = v_alert.findViewById(R.id.alertcalyear);
        note = v_alert.findViewById(R.id.alertnote);
        badd = v_alert.findViewById(R.id.alertcreatemeeting);
        bcancel = v_alert.findViewById(R.id.alertcancelmeeting);
        builder.setView(v_alert);
        alertDialog = builder.create();
        alertDialog.show();
        showcalmeet = v_alert.findViewById(R.id.alertopencal);
        showcalmeet.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new com.alirezaafkar.sundatepicker.DatePicker.Builder()
                        .build(MeetingsActivity.this).show(getSupportFragmentManager(), "");
            }
        });
        bcancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                alertDialog.dismiss();
            }
        });
        badd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (TextUtils.isEmpty(year.getText().toString().trim())||TextUtils.isEmpty(day.getText().toString().trim())||TextUtils.isEmpty(month.getText().toString().trim()))
                {
                    Toast.makeText(MeetingsActivity.this, "لطفا ابتدا تاریخ درست را مشخص کنید", Toast.LENGTH_SHORT).show();
                    return;
                }
                final ProgressDialog loading = ProgressDialog.show(MeetingsActivity.this, "Logging in...", "Please wait...", false, false);
                StringRequest request = new StringRequest(Request.Method.POST, Config.addmeeting_api, new Response.Listener<String>() {
                    @Override
                    public void onResponse(String s) {
                        loading.dismiss();
                Toast.makeText(MeetingsActivity.this, s, Toast.LENGTH_SHORT).show();
                        if (s.trim().equals("ok")) {
                            //Toast.makeText(LoginActivity.this, "success", Toast.LENGTH_SHORT).show();
                            Toast.makeText(MeetingsActivity.this, "جلسه ایجاد شد", Toast.LENGTH_SHORT).show();
                            alertDialog.dismiss();
                            getdata();
                        }
                    }
                }, new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError volleyError) {
                Toast.makeText(MeetingsActivity.this, volleyError.toString(), Toast.LENGTH_SHORT).show();
//                        Toast.makeText(MeetingsActivity.this, "اتصال به سرور امکان پذیر نمی باشد." +
//                                "\n" + "مجددا امتحان کنید", Toast.LENGTH_SHORT).show();
                        loading.dismiss();
                    }
                }) {
                    @Override
                    protected Map<String, String> getParams() {
                        Map<String, String> params = new HashMap<String, String>();
                        date = year.getText().toString().trim() + "/" + month.getText().toString().trim() + "/" + day.getText().toString().trim();
                        params.put("date", date);
                        params.put("note", note.getText().toString().trim());
                        return params;
                    }

                };
                RequestQueue requestQueue =
                        Volley.newRequestQueue(MeetingsActivity.this);
                requestQueue.add(request);
            }
        });
    }
//_______________________________________________________________________________________
    @Override
    public void onDateSet(int id, @Nullable Calendar calendar, int da, int mont, int yea) {
        year.setText(yea + "");
        day.setText(da + "");
        month.setText(mont + "");
    }
    //_____________________________________________________________________________
    private void getdata()
    {
        final StringRequest request = new StringRequest
                (Request.Method.POST, Config.listmeeting_api, new Response.Listener<String>() {
                    @Override
                    public void onResponse(String s) {
                        try {
//                            Toast.makeText(MeetingsActivity.this, s, Toast.LENGTH_SHORT).show();
                            if (s.trim().equals("not"))
                            {
                                Toast.makeText(MeetingsActivity.this, "داده ای برای نمایش وجود ندارد", Toast.LENGTH_SHORT).show();
                                return;
                            }
                            showJSON(s);
                        }
                        catch (ParseException e) {
                            e.printStackTrace();
                        }
                    }
                }, new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError volleyError) {
//                        Toast.makeText(MeetingsActivity.this, volleyError.toString(), Toast.LENGTH_SHORT).show();
                        Toast.makeText(MeetingsActivity.this, "اتصال به سرور امکان پذیر نمی باشد." +
                                "\n" + "مجددا امتحان کنید", Toast.LENGTH_SHORT).show();
                    }
                }) {
            protected Map<String, String> getParams() {
                Map<String, String> params = new HashMap<String, String>();
                params.put("flag", "0");
                return params;
            }
        };
        RequestQueue requestQueue = Volley.newRequestQueue(MeetingsActivity.this);
        requestQueue.add(request);
    }
    //_________________________________________________________________________________________
    private void showJSON(String response) throws ParseException {
        meetinglist.clear();
        responseString = response;
        try {
            JSONObject jsonObject1 = new JSONObject(response);
            JSONArray result = jsonObject1.getJSONArray("result");
            for (int i = 0; i < result.length(); i++) {
                try {

                    JSONObject jsonObject = result.getJSONObject(i);
                    String date = (jsonObject.getString("date"));
                    String note = (jsonObject.getString("note"));
                    Meeting m = new Meeting(date , note);
                    meetinglist.add(m);
                }
                catch (JSONException e) {
                    e.printStackTrace();
                }
            }
            if (meetinglist.size() > 0) {
                MeetingAdapter radapter = new MeetingAdapter(meetinglist , this);
                recyclerView.setLayoutManager(new LinearLayoutManager(MeetingsActivity.this));
                recyclerView.addItemDecoration(new DividerItemDecoration(getApplicationContext() ,
                        LinearLayoutManager.VERTICAL));
                recyclerView.setAdapter(radapter);
            }
        }
        catch (JSONException e) {
            e.printStackTrace();
        }
    }
}
//_______________________________________________________________________________________
