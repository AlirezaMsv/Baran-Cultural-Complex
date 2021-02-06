package com.example.baran21;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.provider.MediaStore;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class RoolCallAdapter extends  RecyclerView.Adapter<RoolCallAdapter.MyHolder> {
    private ArrayList<Client> clientlist = new ArrayList<>() ;
    private Context c;

    public RoolCallAdapter(ArrayList<Client> clientlist, Context c) {
        this.clientlist = clientlist;
        this.c = c;
    }

    @NonNull
    @Override
    public MyHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(c).inflate(R.layout.rollcallrowlayout , parent , false);
        return new RoolCallAdapter.MyHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull MyHolder holder, int position) {
        Client client = clientlist.get(position);
        String name = client.getFirstname() + " " + client.getLastname();
        holder.txtname.setText(name);
        holder.kodemelli = client.getKode();
        holder.present = client.getRes();
        final String flag = holder.flag+"";
        final String kodemelli = client.getKode();
        Picasso.with(c).load("http://192.168.43.250/baranapp/profiles/" + client.getKode() + ".png").into(holder.profile);
        /*holder.done.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final ProgressDialog loading = ProgressDialog.show(c, "Registering...", "Please wait...", false, false);
                StringRequest request = new StringRequest(Request.Method.POST, Config.donerollcall_api, new Response.Listener<String>() {
                    @Override
                    public void onResponse(String s) {
                        loading.dismiss();
                        Toast.makeText(c, s, Toast.LENGTH_SHORT).show();
                        if (s.trim().equals("ok"))
                        {
                            Toast.makeText(c, "ثبت شد", Toast.LENGTH_SHORT).show();
                            RollcallActivity activity = RollcallActivity.getInstance();
                            activity.getdatatoday();
                            Toast.makeText(activity, flag, Toast.LENGTH_SHORT).show();
                        }
                    }
                }, new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError volleyError) {
                Toast.makeText(c, volleyError.toString(), Toast.LENGTH_SHORT).show();
//                        Toast.makeText(c, "اتصال به سرور امکان پذیر نمی باشد." +
//                                "\n" + "مجددا امتحان کنید", Toast.LENGTH_SHORT).show();
                        loading.dismiss();
                    }
                }) {
                    @Override
                    protected Map<String, String> getParams() {
                        Map<String, String> params = new HashMap<String, String>();
                        params.put("kodemelli",kodemelli);
                        params.put("date", RollcallActivity.date);
                        params.put("flag",flag);
                        return params;
                    }

                };
                RequestQueue requestQueue =
                        Volley.newRequestQueue(c);
                requestQueue.add(request);
            }
        });*/
        if (holder.present.equals("0"))
        {
            holder.pre.setVisibility(View.GONE);
            holder.app.setClickable(false);
            holder.app.setChecked(true);
            holder.done.setVisibility(View.GONE);
        }
        else if (holder.present.equals("1"))
        {
            holder.app.setVisibility(View.GONE);
            holder.pre.setClickable(false);
            holder.pre .setChecked(true);
            holder.done.setVisibility(View.GONE);
        }
    }

    @Override
    public int getItemCount() {
        return clientlist.size();
    }

    class MyHolder extends RecyclerView.ViewHolder {
        TextView txtname;
        String kodemelli;
        ImageView profile ;
        RadioGroup radioGroup;
        RadioButton  pre ,app;
        Button done;
        String present;
        int flag;
        public MyHolder(final View itemView) {
            super(itemView);
            txtname = itemView.findViewById(R.id.txtcallrollrow);
            profile = itemView.findViewById(R.id.imgprofilerollcallrow);
            done = itemView.findViewById(R.id.rollcalldonerow);
            radioGroup = itemView.findViewById(R.id.rgrollcall);
            pre = itemView.findViewById(R.id.rowpresent);
            app = itemView.findViewById(R.id.rowappsent);
            radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
                @Override
                public void onCheckedChanged(RadioGroup group, int checkedId) {
                    if (pre.isChecked())
                    {
                        flag=1;
                        done.setVisibility(View.VISIBLE);
                    }
                    else if(app.isChecked())
                    {
                        flag=0;
                        done.setVisibility(View.VISIBLE);
                    }
                }
            });
            done.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    final ProgressDialog loading = ProgressDialog.show(c, "Registering...", "Please wait...", false, false);
                    StringRequest request = new StringRequest(Request.Method.POST, Config.donerollcall_api, new Response.Listener<String>() {
                        @Override
                        public void onResponse(String s) {
                            loading.dismiss();
                            Toast.makeText(c, s, Toast.LENGTH_SHORT).show();
                            if (s.trim().equals("ok"))
                            {
                                Toast.makeText(c, "ثبت شد", Toast.LENGTH_SHORT).show();
                                RollcallActivity activity = RollcallActivity.getInstance();
                                activity.getdatatoday();
                            }
                        }
                    }, new Response.ErrorListener() {
                        @Override
                        public void onErrorResponse(VolleyError volleyError) {
//                            Toast.makeText(c, volleyError.toString(), Toast.LENGTH_SHORT).show();
                        Toast.makeText(c, "اتصال به سرور امکان پذیر نمی باشد." +
                                "\n" + "مجددا امتحان کنید", Toast.LENGTH_SHORT).show();
                            loading.dismiss();
                        }
                    }) {
                        @Override
                        protected Map<String, String> getParams() {
                            Map<String, String> params = new HashMap<String, String>();
                            params.put("kodemelli",kodemelli);
                            params.put("date", RollcallActivity.date);
                            params.put("flag",flag+"");
                            return params;
                        }
                    };
                    RequestQueue requestQueue =
                            Volley.newRequestQueue(c);
                    requestQueue.add(request);
                }
            });
        }
    }
}
