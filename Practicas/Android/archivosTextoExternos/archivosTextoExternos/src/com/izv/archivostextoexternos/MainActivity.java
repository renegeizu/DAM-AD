package com.izv.archivostextoexternos;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import android.os.Bundle;
import android.os.Environment;
import android.app.Activity;
import android.util.Log;
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
    
    public boolean isModificable() {
    	String state = Environment.getExternalStorageState();
    	if (Environment.MEDIA_MOUNTED.equals(state)) {
    		return true;
    	}
    	return false;
    }
    
    public void guardar(View v){ 
    	EditText et=(EditText) findViewById(R.id.etTexto);
    	TextView tv=(TextView)findViewById(R.id.tvVisualizar);
    	if(isModificable()){
	    	String s=et.getText().toString()+'\n';
	    	Log.v("MiLog", s);
	    	File f=new File(getExternalFilesDir(Environment.DIRECTORY_MUSIC),"Archivo.txt");
	    	try{
		    	FileWriter fw=new FileWriter(f, true);
		    	long libre=f.getFreeSpace(), total=f.getTotalSpace(), porcentaje=(100*libre)/total;
		    	String porcent=porcentaje+"%";
		    	fw.write("Mensaje: "+s+"\n"+"Ruta: "+f.getAbsolutePath()+"\n"+
		    			 "Pocentaje Memoria Libre: "+porcent+"\n");
		    	fw.flush();
		    	fw.close();
	    	}catch(IOException e){
	    		Log.v("MiLog", "Excepcion: "+e.toString());
	    	}
    	}else{
    		tv.setText("No hay memoria externa montada");
    	}
    }
    public void leer(View v) throws FileNotFoundException{
    	TextView tv=(TextView)findViewById(R.id.tvVisualizar);
    	File f = new File(getExternalFilesDir(Environment.DIRECTORY_MUSIC), "archivo.txt");
    	BufferedReader br = new BufferedReader(new FileReader(f));
    	String linea="", s="";
    	try {
    		while ((linea = br.readLine()) != null) {
    			s=s+linea+"\n";
    		}
    		br.close();
		} catch (IOException e) {
			Log.v("MiLog", "Excepcion: "+e.toString());
		}
    	tv.setText(s);
    }
}
