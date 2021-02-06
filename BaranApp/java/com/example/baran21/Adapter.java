package com.example.baran21;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class Adapter extends  RecyclerView.Adapter<Adapter.MyHolder>{
    private ArrayList<Client> clientlist = new ArrayList<>() ;
    private Context c;
    public Adapter(ArrayList<Client> clientlist, Context c) {
        this.clientlist = clientlist;
        this.c = c;
    }

    @NonNull
    @Override
    public MyHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(c).inflate(R.layout.row_layout , parent , false);
        return new MyHolder(v);
    }
    @Override
    public void onBindViewHolder(@NonNull MyHolder holder, int position) {
        Client client = clientlist.get(position);
        String name = client.getFirstname() + " " + client.getLastname();
        holder.txtname.setText(name);
        if (client.getRes().equals(""))
        {
            holder.rescheck.setVisibility(View.GONE);
        }
        else
        {
            holder.rescheck.setChecked(true);
        }
        Picasso.with(c).load( Config.ip_value + "baranapp/profiles/" + client.getKode()+".png").into(holder.profile);
        holder.kodemelli = client.getKode();
    }
    @Override
    public int getItemCount() {
        return clientlist.size();
    }
    class MyHolder extends RecyclerView.ViewHolder {
        TextView txtname;
        ImageView profile ;
        CheckBox rescheck;
        String kodemelli;
        public MyHolder(View itemView) {
            super(itemView);
            txtname = itemView.findViewById(R.id.rowname);
            profile = itemView.findViewById(R.id.rowimage);
            rescheck = itemView.findViewById(R.id.rowrescheck);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    ClientActivity.clientkode = kodemelli;
                    c.startActivity(new Intent(c , ClientActivity.class));
                }
            });
        }
    }
}
