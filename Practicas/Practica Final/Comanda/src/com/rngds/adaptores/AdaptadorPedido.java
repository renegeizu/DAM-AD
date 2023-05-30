package com.rngds.adaptores;

	import java.io.BufferedReader;
	import java.io.InputStreamReader;
	import java.net.URL;
	import java.util.ArrayList;
	import org.json.JSONArray;
	import org.json.JSONObject;
	import org.json.JSONTokener;
	import com.rngds.comanda.R;
	import com.rngds.pojo.Carta;
	import com.rngds.pojo.DetallePedido;
	import android.content.Context;
	import android.os.AsyncTask;
	import android.util.Log;
	import android.view.LayoutInflater;
	import android.view.View;
	import android.view.View.OnClickListener;
	import android.view.ViewGroup;
	import android.widget.ArrayAdapter;
	import android.widget.ImageButton;
	import android.widget.TextView;

public class AdaptadorPedido extends ArrayAdapter<Carta>{

	private Context contexto;
	private ArrayList<Carta> cartas;
	private ArrayList<DetallePedido> detalles;
	private long idPedido;
	
	public AdaptadorPedido(Context contexto, ArrayList<Carta> cartas, 
			ArrayList<DetallePedido> detalles, long idPedido){
		super(contexto, R.layout.detalle_pedido, cartas);
		this.idPedido=idPedido;
		this.contexto=contexto;
		this.cartas=cartas;
		this.detalles=detalles;
	}
	
	@Override
	public View getView(final int posicion, View vista, ViewGroup padre){		
		if(vista==null){
			LayoutInflater i=(LayoutInflater) contexto.getSystemService(
					Context.LAYOUT_INFLATER_SERVICE);
			vista=i.inflate(R.layout.detalle_pedido, null);
		}
		boolean existe=false;
		int pos=0;
		TextView tvNombreDetallePedido=(TextView) vista.findViewById(R.id.tvNombreDetallePedido);
		final TextView tvCantidadDetallePedido=(TextView) vista.findViewById(R.id.tvCantidadDetallePedido);
		ImageButton ibSumarDetallePedido=(ImageButton) vista.findViewById(R.id.ibSumarDetallePedido);
		ImageButton ibRestarDetallePedido=(ImageButton) vista.findViewById(R.id.ibRestarDetallePedido);
		final Carta carta=cartas.get(posicion);
		for (int i=0; i<detalles.size(); i++) {
			if(carta.getId()==detalles.get(i).getIdCarta()){
				existe=true;
				pos=i;
			}
		}
		tvNombreDetallePedido.setText(carta.getNombre());
		if(existe==true){
			tvCantidadDetallePedido.setText(detalles.get(pos).getCantidad()+"");
		}else{
			tvCantidadDetallePedido.setText(0+"");
		}
		ibSumarDetallePedido.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				int cant=Integer.parseInt(tvCantidadDetallePedido.getText().toString().trim())+1;
				if(cant==1){
					new setDetalleInsertar().execute("http://192.168.208.167:8084/aadcomanda/"
							+"controlador?op=vistadetalleinsertarjson&idPedido="+idPedido
							+"&idCarta="+carta.getId()+"&cantidad="+cant);
				}else if(cant>1){
					DetallePedido d=new DetallePedido();
					for (int i=0; i<detalles.size(); i++) {
						if(carta.getId()==detalles.get(i).getIdCarta()){
							d=detalles.get(i);
						}
					}
					new setDetalle().execute("http://192.168.208.167:8084/aadcomanda/"
							+"controlador?op=vistadetalleeditarjson&id="+d.getId()
							+"&idPedido="+idPedido+"&idCarta="+d.getIdCarta()
							+"&cantidad="+cant);
				}
				tvCantidadDetallePedido.setText(cant+"");
			}
		});
		ibRestarDetallePedido.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				int cant=Integer.parseInt(tvCantidadDetallePedido.getText().toString().trim())-1;
				if(cant==0){
					DetallePedido d=new DetallePedido();
					for (int i=0; i<detalles.size(); i++) {
						if(carta.getId()==detalles.get(i).getIdCarta()){
							d=detalles.get(i);
						}
					}
					new setDetalle().execute("http://192.168.208.167:8084/aadcomanda/"
							+"controlador?op=vistadetalleborrarjson&id="+d.getId()
							+"&idPedido="+idPedido);
				}else if(cant<0){
					cant=0;
				}else if(cant>0){
					DetallePedido d=new DetallePedido();
					for (int i=0; i<detalles.size(); i++) {
						if(carta.getId()==detalles.get(i).getIdCarta()){
							d=detalles.get(i);
						}
					}
					new setDetalle().execute("http://192.168.208.167:8084/aadcomanda/"
							+"controlador?op=vistadetalleeditarjson&id="+d.getId()
							+"&idPedido="+idPedido+"&idCarta="+d.getIdCarta()
							+"&cantidad="+cant);
				}
				tvCantidadDetallePedido.setText(cant+"");
			}
		});
		return vista;
	}
	
	private class setDetalle extends AsyncTask<String, Integer, String>{
		
		@Override
		protected String doInBackground(String... params) {
			String linea, todo="";
			try{
				URL url=new URL(params[0]);
				BufferedReader in=new BufferedReader(new InputStreamReader(url.openStream()));
				while((linea=in.readLine())!=null){
					todo+=linea;
				}
				in.close();
			}catch(Exception e){
				Log.v("Fallo", e.toString());
			}
			return todo;
		}
		
		@Override
		protected void onPostExecute(String result){
			super.onPostExecute(result);
		}
    	
    }
	
	private class setDetalleInsertar extends AsyncTask<String, Integer, String>{
		
		@Override
		protected String doInBackground(String... params) {
			String linea, todo="";
			try{
				URL url=new URL(params[0]);
				BufferedReader in=new BufferedReader(new InputStreamReader(url.openStream()));
				while((linea=in.readLine())!=null){
					todo+=linea;
				}
				in.close();
			}catch(Exception e){
				Log.v("Fallo", e.toString());
			}
			return todo;
		}
		
		@Override
		protected void onPostExecute(String result){
			super.onPostExecute(result);
			detalles.clear();
			try{
				JSONTokener tokener = new JSONTokener(result);
				JSONObject raiz = new JSONObject(tokener);
				JSONArray lista=raiz.getJSONArray("detalles");
				for (int i=0; i<lista.length(); i++) {
					JSONObject fila = lista.getJSONObject(i);
					DetallePedido detalle=new DetallePedido(fila);
					detalles.add(detalle);
				}
			}catch(Exception e){
			}
		}
    	
    }
	
}