package com.APC.Reserv;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Callback;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class AdaptadorReservas extends RecyclerView.Adapter<AdaptadorReservas.ReservasViewHolder> {

    private ArrayList<HolderCanchasReservas> listareservas;
    private onItemClickListener mlistener;

    public interface onItemClickListener{
        void onItemClick (int position);
    }

    public void setonItemClickListener(onItemClickListener listener){
        mlistener = listener;
    }

    public AdaptadorReservas(ArrayList<HolderCanchasReservas> listareservas){
        this.listareservas = listareservas;
    }

    @NonNull
    @Override
    public ReservasViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(com.APC.Reserv.R.layout.item_reservas,null,false);
        return new ReservasViewHolder(view, mlistener);
    }

    @Override
    public void onBindViewHolder(@NonNull final ReservasViewHolder holder, int position) {
        holder.reservasnombre.setText(listareservas.get(position).getNombre());
        holder.reservasidc.setText(listareservas.get(position).getIdC());
        holder.reservasdia.setText(listareservas.get(position).getDia());
        holder.reservashora.setText(listareservas.get(position).getHora());
        holder.reservasidr.setText(listareservas.get(position).getIdR());
        holder.reservasdireccion.setText(listareservas.get(position).getDireccion());
        holder.reservasciudad.setText(listareservas.get(position).getCiudad());
        //load image
        Picasso.get().load(listareservas.get(position).getImg())
                //.networkPolicy(NetworkPolicy.NO_CACHE).memoryPolicy(MemoryPolicy.NO_CACHE)
                .fit().centerCrop()
                .into(holder.reservasimg, new Callback() {
            @Override
            public void onSuccess() {
                Log.d("Carga","Cargada");
            }
            @Override
            public void onError(Exception e) {
                Log.d("Carga","Error al cargar");
                holder.reservasimg.setImageResource(com.APC.Reserv.R.drawable.no_image);
                //Toast.makeText(ctx, "Ocurrieron errores al cargar algunas imágenes.",Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    public int getItemCount() {
        return listareservas.size();
    }

    public class ReservasViewHolder extends RecyclerView.ViewHolder {

        TextView reservasnombre,reservasdireccion,reservasciudad,reservasdia,reservashora, reservasidc, reservasidr;
        ImageView reservasimg;

        private ReservasViewHolder(View itemView, final onItemClickListener listener) {
            super(itemView);
            reservasnombre= itemView.findViewById(com.APC.Reserv.R.id.reservasnombre);
            reservasidc= itemView.findViewById(com.APC.Reserv.R.id.reservasidc);
            reservasdia= itemView.findViewById(com.APC.Reserv.R.id.reservasdia);
            reservashora= itemView.findViewById(com.APC.Reserv.R.id.reservashora);
            reservasidr= itemView.findViewById(com.APC.Reserv.R.id.reservasidr);
            reservasdireccion= itemView.findViewById(com.APC.Reserv.R.id.reservasdireccion);
            reservasciudad= itemView.findViewById(com.APC.Reserv.R.id.reservasciudad);
            reservasimg=  itemView.findViewById(com.APC.Reserv.R.id.reservasimg);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (listener != null){
                        int position = getAdapterPosition();
                        if (position != RecyclerView.NO_POSITION){
                            listener.onItemClick(position);
                        }
                    }
                }
            });
        }
    }
}