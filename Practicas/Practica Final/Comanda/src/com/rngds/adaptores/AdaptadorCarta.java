package com.rngds.adaptores;

	import com.rngds.comanda.R;
	import android.content.Context;
	import android.database.Cursor;
	import android.view.LayoutInflater;
	import android.view.View;
	import android.view.ViewGroup;
	import android.widget.CursorAdapter;
	import android.widget.TextView;

public class AdaptadorCarta extends CursorAdapter {
	
	public AdaptadorCarta (Context co, Cursor cu) {
		super(co, cu, true);
	}
	
	@Override
	public void bindView(View v, Context co, Cursor cu) {
		TextView tvNombre=(TextView) v.findViewById(R.id.tvNombreDetalleCarta);
		TextView tvPrecio=(TextView) v.findViewById(R.id.tvPrecioDetalleCarta);
		tvNombre.setText(cu.getString(2));
		tvPrecio.setText(cu.getFloat(3)+"");
	}

	@Override
	public View newView(Context co, Cursor cu, ViewGroup vg) {
		LayoutInflater i = LayoutInflater.from(vg.getContext());
		View v = i.inflate(R.layout.detalle_carta, vg, false);
		return v;
	}
	
}