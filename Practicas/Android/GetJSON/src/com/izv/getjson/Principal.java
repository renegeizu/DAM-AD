package com.izv.getjson;

	import java.io.BufferedReader;
	import java.io.InputStreamReader;
	import java.net.URL;
	import org.json.JSONArray;
	import org.json.JSONObject;
	import org.json.JSONTokener;
	import android.os.AsyncTask;
	import android.os.Bundle;
	import android.app.Activity;
	import android.view.Menu;
	import android.view.View;
	import android.widget.TextView;

public class Principal extends Activity {
	
	private TextView tv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_principal);
        tv=(TextView) findViewById(R.id.tvJson);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.principal, menu);
        return true;
    }
    
    public void json(View v){
    	HebraJson hj=new HebraJson();
    	hj.execute("http://192.168.208.166:8084/aadcomanda/controlador?op=vistamesajson");
    }
    
    public void insertar(View v){
    	HebraInsertar hi=new HebraInsertar();
    	hi.execute("http://192.168.208.166:8084/aadcomanda/controlador?op=opinsertarmesa&mesa=nueva");
    }
    
    private class HebraJson extends AsyncTask<String, Integer, String>{

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
			//tv.setText(result);
			JSONTokener tokener = new JSONTokener(result);
			String todo="";
			try{
				JSONObject raiz = new JSONObject(tokener);
				JSONArray lista=raiz.getJSONArray("mesas");
				for (int i = 0; i < lista.length(); i++) {
					JSONObject fila = lista.getJSONObject(i);
					Mesa mesa=new Mesa(fila);
					todo+=mesa.toString()+"\n";
				}
				tv.setText(todo);
			}catch(Exception e){
				tv.setText(e.toString());
				e.printStackTrace();
			}
		}
    	
    }
    
    private class HebraInsertar extends AsyncTask<String, Integer, String>{

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
  			tv.setText(result);

  		}
      	
      }
    
}
