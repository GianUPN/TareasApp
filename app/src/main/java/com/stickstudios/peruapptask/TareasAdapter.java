package com.stickstudios.peruapptask;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class TareasAdapter extends RecyclerView.Adapter<TareasAdapter.TareasHolder> {

    private List<Tarea> tareaslist = new ArrayList<>();
    public OnItemClickListener listener;

    @NonNull
    @Override
    public TareasHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.tarea_item,viewGroup,false);
        return new TareasHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull TareasHolder holder, int i) {
        Tarea tarea_actual = tareaslist.get(i);
        holder.txttitulo.setText(tarea_actual.getTitulo());
        holder.txtdescripcion.setText(tarea_actual.getDescripcion());

    }

    @Override
    public int getItemCount() {
        return tareaslist.size();
    }

    public Tarea getTareaAt(int posicion){
        return tareaslist.get(posicion);
    }

    public void setList(List<Tarea> list){
        tareaslist = list;
        notifyDataSetChanged();
    }


    class TareasHolder extends RecyclerView.ViewHolder{

        private TextView txttitulo;
        private TextView txtdescripcion;

        public TareasHolder(View view){
            super(view);
            txttitulo = view.findViewById(R.id.txt_titulo);
            txtdescripcion = view.findViewById(R.id.txt_descripcion);

            view.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int position = getAdapterPosition();
                    if (listener != null && position != RecyclerView.NO_POSITION) {
                        listener.onItemClick(tareaslist.get(position));
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
