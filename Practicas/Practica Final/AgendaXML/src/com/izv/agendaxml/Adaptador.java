package com.izv.agendaxml;

	import java.util.ArrayList;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

public class Adaptador extends ArrayAdapter<Contacto>{
	
	private Context c;
	private ArrayList<Contacto> l;
	
	public Adaptador(Context c, ArrayList<Contacto> l){
		super(c, R.layout.detalle, l);
		this.c=c;
		this.l=l;
	}
	
	@Override
	public View getView(int posicion, View vista, ViewGroup padre){
		if(vista==null){
			LayoutInflater i=(LayoutInflater) c.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
			vista=i.inflate(R.layout.detalle, null);
		}
		TextView tv1=(TextView) vista.findViewById(R.id.tvNombre);
		TextView tv2=(TextView) vista.findViewById(R.id.tvTelefono);
		TextView tv3=(TextView) vista.findViewById(R.id.tvCorreo);
		Contacto c=l.get(posicion);
		tv1.setText(c.getNombre());
		tv2.setText(c.getTelefono());
		tv3.setText(c.getEmail());
		return vista;
	}
	
}
