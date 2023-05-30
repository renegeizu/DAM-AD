package com.izv.convertirjson;

	import java.io.InputStream;
	import java.net.URL;
	import org.json.JSONArray;
	import org.json.JSONException;
	import org.json.JSONObject;
	import org.json.JSONTokener;
	import com.squareup.picasso.Picasso;
	import android.os.AsyncTask;
	import android.os.Bundle;
	import android.view.View;
	import android.view.View.OnClickListener;
	import android.widget.ImageButton;
	import android.widget.ImageView;
	import android.widget.TextView;
	import android.app.Activity;
	import android.graphics.Bitmap;
	import android.graphics.BitmapFactory;

public class Principal extends Activity {
	
	private ImageView imagen;
	private TextView descripcion;
	private ImageButton ib2;
	private ImageButton ib1;
	private Zapato zapato;
	private JSONArray lista;
	private int contador=0;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.principal);
		imagen=(ImageView)findViewById(R.id.imageView1);
		descripcion=(TextView)findViewById(R.id.textView1);
		ib2=(ImageButton)findViewById(R.id.imageButton2);
		ib2.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				contador--;
				if(contador<=lista.length()){
					contador=0;
				}
				posicionarDatos(contador);
			}
		});
		ib1=(ImageButton)findViewById(R.id.imageButton1);
		ib1.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				contador++;
				if(contador>=lista.length()){
					contador=lista.length()-1;
				}
				posicionarDatos(contador);
			}
		});
		posicionarDatos(contador);
	}
	
	public String obtenerJSON(){
		return "{"+"\"zapatos\""+":"+"["+"{\"descr\":"+"\"zapato de hombre dustin\","
				 +"\"url\"  :"+"\"http://sgfm.elcorteingles.es/SGFM/dctm/MEDIA01/201310/29/00103389100060_39_1__230x291.jpg\"},"
				 +"{\"descr\":"+"\"zapato de hombre joven\","+"\"url\"  :"
				 +"\"http://sgfm.elcorteingles.es/SGFM/dctm/MEDIA01/201306/19/00103369100148_40_1__230x291.jpg\"}"
				 +"]};";
	}
	
	public JSONArray manipularJSON(String todo) throws JSONException{
		JSONTokener tokener = new JSONTokener(todo);
		JSONObject raiz = new JSONObject(tokener);
		JSONArray lista=raiz.getJSONArray("zapatos");
		return lista;
	}
	
	 /*private class RecogerImagen extends AsyncTask<String, Integer, Bitmap>{

			@Override
			protected Bitmap doInBackground(String... params) {
				Bitmap bitmap = null;
				try{
					bitmap = BitmapFactory.decodeStream((InputStream)
							new URL(zapato.getUrlImagen()).getContent());
				}catch(Exception e){
				}
				return bitmap;
			}
			
			@Override
			protected void onPostExecute(Bitmap bitmap){
				super.onPostExecute(bitmap);
				imagen.setImageBitmap(bitmap);
			}
	    	
	   }*/
	 
	 private void posicionarDatos(int cont){
		 try {
				lista=manipularJSON(obtenerJSON());
				JSONObject fila = lista.getJSONObject(cont);
				zapato=new Zapato(fila);
				descripcion.setText(zapato.getDescripcion());
				Picasso.with(this).load(zapato.getUrlImagen()).into(imagen);
				//RecogerImagen hilo=new RecogerImagen();
				//hilo.execute(zapato.getUrlImagen());
			} catch (Exception e) {
				e.printStackTrace();
			}
	 }
	    
}