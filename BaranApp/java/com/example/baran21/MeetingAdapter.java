package com.example.baran21;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.security.KeyStore;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;

public class MeetingAdapter extends  RecyclerView.Adapter<MeetingAdapter.MyHolder> {
    private ArrayList<Meeting> meetinglist = new ArrayList<>() ;

    public MeetingAdapter(ArrayList<Meeting> meetinglist, Context c) {
        this.meetinglist = meetinglist;
        this.c = c;
    }
    private Context c;

    @NonNull
    @Override
    public MyHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(c).inflate(R.layout.listmeetingrow , parent , false);
        return new MyHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull MyHolder holder, int position) {
        Meeting meeting = meetinglist.get(position);
        String date = meeting.getDate();
        String not  = meeting.getNote();
        holder.date.setText(date);
        holder.note.setText(not);
        Calendar c = Calendar.getInstance();
        SimpleDateFormat format = new SimpleDateFormat("yyyy,MM,dd");
        String formattedDate = format.format(c.getTime());
        int[] PersianDateInt = gregorian_to_jalali(Integer.parseInt(formattedDate.substring(0, 4)),
                Integer.parseInt(formattedDate.substring(5, 7)),
                Integer.parseInt(formattedDate.substring(8, 10))
        );
        String todaysdate = PersianDateInt[0] +"/" + PersianDateInt[1] + "/" + PersianDateInt[2];
        if (todaysdate.equals(date))
        {
            holder.imageView.setImageResource(R.color.greenmeeting);
            holder.flag=1;
        }
        else
        {
            holder.imageView.setImageResource(R.color.redmeeting);
        }
    }

    @Override
    public int getItemCount() {
        return meetinglist.size();
    }

    class MyHolder extends RecyclerView.ViewHolder {
        TextView date;
        TextView note ;
        ImageView imageView;
        int flag=0;
        public MyHolder(View itemView) {
            super(itemView);
            date = itemView.findViewById(R.id.datemeetingrow);
            note = itemView.findViewById(R.id.notemeetingrow);
            imageView = itemView.findViewById(R.id.meetingrowimg);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    RollcallActivity.date= date.getText().toString().trim();
                    if (flag==1)
                    {
                        RollcallActivity.istoday=1;
                    }
                    else
                    {
                        RollcallActivity.istoday=0;
                        Toast.makeText(c, "شما قادر به حضور و غیاب برای این جلسه نیستید و فقط میتوانید لیست کسانی که در این جلسه حاضر یا غایب بوده اند را ببینید", Toast.LENGTH_SHORT).show();
                    }
                    c.startActivity(new Intent(c , RollcallActivity.class));
                }
            });
        }
    }
    //_____________________________________________________________
    public static int[] gregorian_to_jalali(int gy, int gm, int gd){
        int[] g_d_m = {0,31,59,90,120,151,181,212,243,273,304,334};
        int jy;
        if(gy>1600){
            jy=979;
            gy-=1600;
        }else{
            jy=0;
            gy-=621;
        }
        int gy2 = (gm > 2)?(gy + 1):gy;
        int days = (365 * gy) + ((int)((gy2 + 3) / 4)) - ((int)((gy2 + 99) / 100)) + ((int)((gy2 + 399) / 400)) - 80 + gd + g_d_m[gm - 1];
        jy += 33 * ((int)(days / 12053));
        days %= 12053;
        jy += 4 * ((int)(days / 1461));
        days %= 1461;
        if(days > 365){
            jy+=(int)((days-1)/365);
            days=(days-1)%365;
        }
        int jm = (days < 186)?1 + (int)(days / 31):7 + (int)((days - 186) / 30);
        int jd = 1 + ((days < 186)?(days % 31):((days - 186) % 30));
        int[] out = {jy,jm,jd};
        return out;
    }
}
