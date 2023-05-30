package com.izv.basedatosagenda.db;

	import com.izv.basedatosagenda.R;
import android.content.Context;
import android.database.Cursor;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CursorAdapter;
import android.widget.TextView;


public class Adaptador extends CursorAdapter {
	
	public Adaptador (Context co, Cursor cu) {
		super(co, cu, true);
	}

	@Override
	public void bindView(View v, Context co, Cursor cu) {
		TextView tvNombre=(TextView) v.findViewById(R.id.tvNombre);
		TextView tvTelefono=(TextView) v.findViewById(R.id.tvTelefono);
		TextView tvCorreo=(TextView) v.findViewById(R.id.tvCorreo);
		tvNombre.setText(cu.getString(1));
		tvTelefono.setText(cu.getString(2));
		tvCorreo.setText(cu.getString(3));
	}

	@Override
	public View newView(Context co, Cursor cu, ViewGroup vg) {
		LayoutInflater i = LayoutInflater.from(vg.getContext());
		View v = i.inflate(R.layout.detalle, vg, false);
		return v;
	}

}