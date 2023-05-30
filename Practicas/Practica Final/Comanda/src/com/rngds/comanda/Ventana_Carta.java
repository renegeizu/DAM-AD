package com.rngds.comanda;

	import java.io.BufferedReader;
	import java.io.InputStreamReader;
	import java.net.URL;
	import org.json.JSONArray;
	import org.json.JSONObject;
	import org.json.JSONTokener;
	import com.rngds.adaptores.AdaptadorCarta;
	import com.rngds.bd.GestionCarta;
	import com.rngds.pojo.Carta;
	import android.os.AsyncTask;
	import android.os.Bundle;
	import android.view.Menu;
	import android.view.MenuItem;
	import android.widget.GridView;
	import android.widget.Toast;
	import android.app.Activity;
	import android.database.Cursor;

public class Ventana_Carta extends Activity {
	
	private GestionCarta gestorCarta;
	private AdaptadorCarta adapCarta;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.ventana_carta);
		gestorCarta = new GestionCarta(this);
	}
	
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		getMenuInflater().inflate(R.menu.ventana_carta, menu);
		return true;
	}
	
	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		switch(item.getItemId()){
		case R.id.action_actualizar_carta:
	    	new getCarta().execute("http://192.168.208.167:8084/aadcomanda/" +
	    			"controlador?op=vistacartajson");
			return true;
		}
		return super.onOptionsItemSelected(item);
	}
	
	@Override
	public void onResume() {
		gestorCarta.open();
		super.onResume();
		Cursor c=gestorCarta.getCursor();
		adapCarta=new AdaptadorCarta(this, c);
		GridView gv=(GridView) findViewById(R.id.gvCarta);
		gv.setAdapter(adapCarta);
		gestorCarta.close();
	}

	private class getCarta extends AsyncTask<String, Integer, String>{
		
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
			}
			return todo;
		}
		
		@Override
		protected void onPostExecute(String result){
			super.onPostExecute(result);
			gestorCarta.open();
			gestorCarta.delete();
			JSONTokener tokener = new JSONTokener(result);
			try{
				JSONObject raiz = new JSONObject(tokener);
				JSONArray lista=raiz.getJSONArray("cartas");
				for (int i = 0; i < lista.length(); i++) {
					JSONObject fila = lista.getJSONObject(i);
					Carta carta=new Carta(fila);
					gestorCarta.insert(carta);
				}
			}catch(Exception e){
			}
			adapCarta.changeCursor(gestorCarta.getCursor());
			gestorCarta.close();
			Toast.makeText(getApplicationContext(), "Carta Actualizada", Toast.LENGTH_LONG).show();
		}
    	
    }

}