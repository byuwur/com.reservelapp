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

public class AdaptadorFavoritos extends RecyclerView.Adapter<AdaptadorFavoritos.FavoritosViewHolder> {

    private ArrayList<HolderCanchasFavoritos> listaFavoritos;
    private onItemClickListener mlistener;

    public interface onItemClickListener{
        void onItemClick (int position);
    }

    public void setonItemClickListener(onItemClickListener listener){
        mlistener = listener;
    }

    public AdaptadorFavoritos(ArrayList<HolderCanchasFavoritos> listaFavoritos) {
        this.listaFavoritos = listaFavoritos;
    }

    @NonNull
    @Override
    public FavoritosViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(com.APC.Reserv.R.layout.item_favoritos,null,false);
        return new FavoritosViewHolder(view, mlistener);
    }

    @Override
    public void onBindViewHolder(@NonNull final FavoritosViewHolder holder, int position) {
        holder.Favoritosnombre.setText(listaFavoritos.get(position).getNombre());
        holder.Favoritosid.setText(listaFavoritos.get(position).getId());
        holder.Favoritosvalor.setText(listaFavoritos.get(position).getValor());
        holder.Favoritosdireccion.setText(listaFavoritos.get(position).getDireccion());
        holder.Favoritosciudad.setText(listaFavoritos.get(position).getCiudad());
        holder.Favoritosdias.setText(listaFavoritos.get(position).getDias());
        holder.Favoritoshorario.setText(listaFavoritos.get(position).getHorario());
        //load image
        Picasso.get().load(listaFavoritos.get(position).getImg())
                //.networkPolicy(NetworkPolicy.NO_CACHE).memoryPolicy(MemoryPolicy.NO_CACHE)
                .fit().centerCrop()
                .into(holder.Favoritosimg, new Callback() {
            @Override
            public void onSuccess() {
                Log.d("Carga","Cargada");
            }
            @Override
            public void onError(Exception e) {
                Log.d("Carga","Error al cargar");
                holder.Favoritosimg.setImageResource(com.APC.Reserv.R.drawable.no_image);
                //Toast.makeText(ctx, "Ocurrieron errores al cargar algunas imágenes.",Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    public int getItemCount() {
        return listaFavoritos.size();
    }

    public class FavoritosViewHolder extends RecyclerView.ViewHolder {

        TextView Favoritosnombre,Favoritosid, Favoritosvalor,Favoritosdireccion,Favoritosciudad,Favoritosdias,Favoritoshorario;
        ImageView Favoritosimg;

        private FavoritosViewHolder(View itemView, final onItemClickListener listener) {
            super(itemView);
            Favoritosnombre= itemView.findViewById(com.APC.Reserv.R.id.favoritosnombre);
            Favoritosid = itemView.findViewById(com.APC.Reserv.R.id.favoritosid);
            Favoritosvalor = itemView.findViewById(com.APC.Reserv.R.id.favoritosvalor);
            Favoritosdireccion= itemView.findViewById(com.APC.Reserv.R.id.favoritosdireccion);
            Favoritosciudad= itemView.findViewById(com.APC.Reserv.R.id.favoritosciudad);
            Favoritosdias= itemView.findViewById(com.APC.Reserv.R.id.favoritosdias);
            Favoritoshorario= itemView.findViewById(com.APC.Reserv.R.id.favoritoshorario);
            Favoritosimg= itemView .findViewById(com.APC.Reserv.R.id.favoritosimg);

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