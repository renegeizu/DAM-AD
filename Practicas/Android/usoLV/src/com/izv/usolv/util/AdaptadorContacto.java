package com.izv.usolv.util;

import java.util.ArrayList;
import com.izv.usolv.R;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

public class AdaptadorContacto extends ArrayAdapter <Contacto> {
	
	private Context contexto;
	private ArrayList<Contacto> lista;
	
	public AdaptadorContacto(Context c, ArrayList<Contacto> l){
		super(c,R.layout.detalle,l);
		this.contexto=c;
		this.lista=l;
	}
	
	@Override
	public View getView(int posicion, View vista, ViewGroup padre){
		//Dibujar Linea
		//Metiendo hasta el siguiente comentario en if(vista==null){}
		//Reutiliza los elementos, entonces no alterna el uso de detalle y detalle2
		LayoutInflater i=(LayoutInflater) contexto.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
		//Un detalle distinto segun si la linea es par o impar
		if(posicion%2==0){
			vista=i.inflate(R.layout.detalle, null);
		}else{
			vista=i.inflate(R.layout.detalle2,null);
		}
		//Rellenar las Lineas
		Contacto ct=lista.get(posicion);
		TextView tvContacto=(TextView) vista.findViewById(R.id.tvContacto);
		TextView tvTelefono=(TextView) vista.findViewById(R.id.tvTelefono);
		TextView tvCorreo=(TextView) vista.findViewById(R.id.tvCorreo);
		tvContacto.setText(ct.getNombre());
		tvTelefono.setText(ct.getTelefono());
		tvCorreo.setText(ct.getCorreo());
		return vista;
	}
	
}