package com.rngds.comanda;

	import java.io.BufferedReader;
	import java.io.InputStreamReader;
	import java.net.URL;
	import org.json.JSONArray;
	import org.json.JSONObject;
	import org.json.JSONTokener;
	import com.rngds.adaptores.AdaptadorMesa;
	import com.rngds.bd.GestionMesa;
	import com.rngds.pojo.Mesa;
	import android.os.AsyncTask;
	import android.os.Bundle;
	import android.app.Activity;
	import android.content.Intent;
	import android.database.Cursor;
	import android.view.Menu;
	import android.view.MenuItem;
	import android.widget.GridView;
	import android.widget.Toast;

public class Ventana_Mesa extends Activity {
	
	private GestionMesa gestorMesa;
	private AdaptadorMesa adapMesa;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.ventana_mesa);
		gestorMesa = new GestionMesa(this);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		getMenuInflater().inflate(R.menu.ventana_mesa, menu);
		return true;
	}
	
	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		switch(item.getItemId()){
		case R.id.action_visualizar_carta:
			Intent i = new Intent(this, Ventana_Carta.class);
			startActivity(i);
			return true;
		case R.id.action_actualizar:
	    	new getMesas().execute("http://192.168.208.167:8084/aadcomanda/" +
	    			"controlador?op=vistamesajson");
			return true;
		}
		return super.onOptionsItemSelected(item);
	}
	
	@Override
	public void onResume() {
		gestorMesa.open();
		super.onResume();
		Cursor c=gestorMesa.getCursor();
		adapMesa=new AdaptadorMesa(this, c);
		GridView gv=(GridView) findViewById(R.id.gvMesas);
		gv.setAdapter(adapMesa);
		gestorMesa.close();
	}

	private class getMesas extends AsyncTask<String, Integer, String>{
		
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
				return e.toString();
			}
			return todo;
		}
		
		@Override
		protected void onPostExecute(String result){
			super.onPostExecute(result);
			gestorMesa.open();
			gestorMesa.delete();
			JSONTokener tokener = new JSONTokener(result);
			try{
				JSONObject raiz = new JSONObject(tokener);
				JSONArray lista=raiz.getJSONArray("mesas");
				for (int i = 0; i < lista.length(); i++) {
					JSONObject fila = lista.getJSONObject(i);
					Mesa mesa=new Mesa(fila);
					gestorMesa.insert(mesa);
				}
			}catch(Exception e){
			}
			adapMesa.changeCursor(gestorMesa.getCursor());
			gestorMesa.close();
			Toast.makeText(getApplicationContext(), "Mesas Actualizadas", Toast.LENGTH_LONG).show();
		}
    	
    }

}
