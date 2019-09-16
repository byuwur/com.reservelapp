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

public class AdaptadorReservar extends RecyclerView.Adapter<AdaptadorReservar.ReservarViewHolder> {

    private ArrayList<HolderCanchasReservar> listareservar;
    private onItemClickListener mlistener;

    public interface onItemClickListener{
        void onItemClick (int position);
    }

    public void setonItemClickListener(onItemClickListener listener){
        mlistener = listener;
    }

    public AdaptadorReservar(ArrayList<HolderCanchasReservar> listareservar){
        this.listareservar = listareservar;
    }

    @NonNull
    @Override
    public ReservarViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(com.APC.Reserv.R.layout.item_reservar,null,false);
        return new ReservarViewHolder(view, mlistener);
    }

    @Override
    public void onBindViewHolder(@NonNull final ReservarViewHolder holder, int position) {
        holder.reservarnombre.setText(listareservar.get(position).getNombre());
        holder.reservarid.setText(listareservar.get(position).getId());
        holder.reservarvalor.setText(listareservar.get(position).getValor());
        holder.reservardireccion.setText(listareservar.get(position).getDireccion());
        holder.reservarciudad.setText(listareservar.get(position).getCiudad());
        holder.reservardias.setText(listareservar.get(position).getDias());
        holder.reservarhorario.setText(listareservar.get(position).getHorario());
        //load image
        Picasso.get().load(listareservar.get(position).getImg())
                //.networkPolicy(NetworkPolicy.NO_CACHE).memoryPolicy(MemoryPolicy.NO_CACHE)
                .fit().centerCrop()
                .into(holder.reservarimg, new Callback() {
            @Override
            public void onSuccess() {
                Log.d("Carga","Cargada");
            }
            @Override
            public void onError(Exception e) {
                Log.d("Carga","Error al cargar");
                holder.reservarimg.setImageResource(com.APC.Reserv.R.drawable.no_image);
                //Toast.makeText(ctx, "Ocurrieron errores al cargar algunas imágenes.",Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    public int getItemCount() {
        return listareservar.size();
    }

    public class ReservarViewHolder extends RecyclerView.ViewHolder {
        TextView reservarnombre,reservarid, reservarvalor,reservardireccion,reservarciudad,reservardias,reservarhorario;
        ImageView reservarimg;

        private ReservarViewHolder(View itemView, final onItemClickListener listener) {
            super(itemView);
            reservarnombre= itemView.findViewById(com.APC.Reserv.R.id.reservarnombre);
            reservarid = itemView.findViewById(com.APC.Reserv.R.id.reservarid);
            reservarvalor = itemView.findViewById(com.APC.Reserv.R.id.reservarvalor);
            reservardireccion= itemView.findViewById(com.APC.Reserv.R.id.reservardireccion);
            reservarciudad= itemView.findViewById(com.APC.Reserv.R.id.reservarciudad);
            reservardias= itemView.findViewById(com.APC.Reserv.R.id.reservardias);
            reservarhorario= itemView.findViewById(com.APC.Reserv.R.id.reservarhorario);
            reservarimg= itemView .findViewById(com.APC.Reserv.R.id.reservarimg);

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