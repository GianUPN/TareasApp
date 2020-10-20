package com.stickstudios.peruapptask;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.res.ColorStateList;
import android.graphics.Color;
import android.support.annotation.NonNull;
import android.support.v7.recyclerview.extensions.ListAdapter;
import android.support.v7.util.DiffUtil;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.List;

public class TareasAdapter extends ListAdapter<Tarea,TareasAdapter.TareasHolder> {

    private static final String[] tipos = {"Pendiente", "Finalizado", "Postergado"};
    public OnItemClickListener listener;

    public TareasAdapter() {
        super(DIFF_CALLBACK);
    }

    private static final DiffUtil.ItemCallback<Tarea> DIFF_CALLBACK = new DiffUtil.ItemCallback<Tarea>() {
        @Override
        public boolean areItemsTheSame(@NonNull Tarea oldItem, @NonNull Tarea newItem) {
            return oldItem.getId() == newItem.getId();
        }

        @Override
        public boolean areContentsTheSame(@NonNull Tarea oldItem, @NonNull Tarea newItem) {
            return oldItem.getDescripcion().equals(newItem.getDescripcion()) &&
                    oldItem.getTitulo().equals(newItem.getTitulo()) &&
                    oldItem.getEstado() == newItem.getEstado();
        }
    };

    @NonNull
    @Override
    public TareasHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.tarea_item,viewGroup,false);
        return new TareasHolder(view);
    }

    @SuppressLint("ResourceAsColor")
    @Override
    public void onBindViewHolder(@NonNull TareasHolder holder, int i) {
        Tarea tarea_actual = getItem(i);
        holder.txttitulo.setText(tarea_actual.getTitulo());
        holder.txtdescripcion.setText(tarea_actual.getDescripcion());
        holder.txtestado.setText(tipos[tarea_actual.getEstado()]);
    }

    public Tarea getTareaAt(int posicion){
        return getItem(posicion);
    }

    class TareasHolder extends RecyclerView.ViewHolder{

        private TextView txttitulo;
        private TextView txtdescripcion;
        private TextView txtestado;
        private CardView cardview;

        public TareasHolder(View view){
            super(view);
            txttitulo = view.findViewById(R.id.txt_titulo);
            txtdescripcion = view.findViewById(R.id.txt_descripcion);
            txtestado = view.findViewById(R.id.txt_estado);
            cardview = view.findViewById(R.id.cardview);

            view.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int position = getAdapterPosition();
                    if (listener != null && position != RecyclerView.NO_POSITION) {
                        listener.onItemClick(getItem(position));
                    }
                }
            });

        }
    }

    public interface OnItemClickListener{
        void onItemClick(Tarea tarea);
    }

    public void SetOnClickListener(OnItemClickListener listener){
        this.listener = listener;
    }

}
