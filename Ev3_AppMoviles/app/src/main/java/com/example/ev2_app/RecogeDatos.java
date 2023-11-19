package com.example.ev2_app;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;

public class RecogeDatos extends BaseAdapter{
    private Context context;
    private ArrayList<UserModel> userModelArrayList;

    public RecogeDatos(Context context, ArrayList<UserModel> userModelArrayList) {

        this.context = context;
        this.userModelArrayList = userModelArrayList;
    }


    @Override
    public int getCount() {
        return userModelArrayList.size();
    }

    @Override
    public Object getItem(int position) {
        return userModelArrayList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder;

        if (convertView == null) {
            holder = new ViewHolder();
            LayoutInflater inflater = (LayoutInflater) context
                    .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = inflater.inflate(R.layout.activity_recoger_datos, null, true);

            holder.recogerFecha = (TextView) convertView.findViewById(R.id.rdFecha);
            holder.recogerHora = (TextView) convertView.findViewById(R.id.rdHora);
            holder.recogerLab = (TextView) convertView.findViewById(R.id.rdLab);
            holder.recogeNombre = (TextView) convertView.findViewById(R.id.rdNombre);
            holder.recogerRut = (TextView) convertView.findViewById(R.id.rdRut);
            holder.recogeDescription = (TextView) convertView.findViewById(R.id.rdDescription);



            convertView.setTag(holder);
        }else {
            // the getTag returns the viewHolder object set as a tag to the view
            holder = (ViewHolder)convertView.getTag();
        }

        holder.recogerFecha.setText("Fecha: "+userModelArrayList.get(position).getFecha());
        holder.recogerHora.setText("Hora: "+userModelArrayList.get(position).getHora());
        holder.recogerLab.setText("Laboratorio: "+userModelArrayList.get(position).getLaboratorio());
        holder.recogeNombre.setText("Nombre: "+userModelArrayList.get(position).getNombre());
        holder.recogerRut.setText("RUT:"+userModelArrayList.get(position).getRut());
        holder.recogeDescription.setText("Descripcion: "+userModelArrayList.get(position).getDescription());

        return convertView;
    }

    private class ViewHolder {

        protected TextView recogerFecha, recogerHora, recogerLab, recogeNombre, recogerRut, recogeDescription;
    }
}
