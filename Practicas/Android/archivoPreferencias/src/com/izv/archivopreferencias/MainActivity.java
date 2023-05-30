package com.izv.archivopreferencias;

import java.io.IOException;

import android.os.Bundle;
import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.view.Menu;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }
    
    public void guardar(View v) throws IOException{
    	EditText et1=(EditText) findViewById(R.id.etNombre);
    	EditText et2=(EditText) findViewById(R.id.etDatos);
    	SharedPreferences pc=getSharedPreferences ("Preferencias",Context.MODE_PRIVATE);
    	Editor ed = pc.edit();
    	ed.putString(et1.getText().toString().trim(), et2.getText().toString().trim());
    	ed.commit();
    }
    
    public void obtener(View v){
    	SharedPreferences pc=getSharedPreferences ("Preferencias",Context.MODE_PRIVATE);
    	EditText et1=(EditText) findViewById(R.id.etNombre);
    	TextView tv=(TextView) findViewById(R.id.tvSalida);
    	tv.setText(pc.getString(et1.getText().toString().trim(),"No hay valor o no existe"));
    }
    
}
