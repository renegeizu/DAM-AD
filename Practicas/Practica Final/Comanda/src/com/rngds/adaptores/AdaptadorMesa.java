package com.rngds.adaptores;

	import com.rngds.comanda.R;
	import com.rngds.comanda.Ventana_Pedido;
	import com.rngds.pojo.Mesa;
	import android.content.Context;
	import android.content.Intent;
	import android.database.Cursor;
	import android.os.Bundle;
	import android.view.LayoutInflater;
	import android.view.View;
	import android.view.ViewGroup;
	import android.view.View.OnClickListener;
	import android.widget.Button;
	import android.widget.CursorAdapter;

public class AdaptadorMesa extends CursorAdapter{
	
private Context ctx;
	
	public AdaptadorMesa (Context co, Cursor cu) {
		super(co, cu, true);
		ctx=co;
	}

	@Override
	public void bindView(View v, Context co, Cursor cu) {
		Button bt=(Button) v.findViewById(R.id.btDetalleMesa);
		bt.setText(cu.getString(2));
		String nombre=cu.getString(2);
		int id=cu.getInt(1);
		final Mesa m=new Mesa(id, nombre);
		bt.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				Intent i = new Intent(ctx,Ventana_Pedido.class);
				Bundle b = new Bundle();
				b.putSerializable("Mesa", m);
				i.putExtras(b);
				ctx.startActivity(i);
			}
		});
	}

	@Override
	public View newView(Context co, Cursor cu, ViewGroup vg) {
		LayoutInflater i = LayoutInflater.from(vg.getContext());
		View v = i.inflate(R.layout.detalle_mesa, vg, false);
		return v;
	}

}