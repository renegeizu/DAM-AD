package com.izv.archivostexto;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

import android.os.Bundle;
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
   
    public void guardar(View v){
    	EditText et=(EditText) findViewById(R.id.etTexto);
    	String s=et.getText().toString()+'\n';
    	Log.v("MiLog", s);
    	File f=new File(getFilesDir(),"archivo.txt");
    	try{
	    	FileWriter fw=new FileWriter(f, true);
	    	fw.write(s+"\n");
	    	fw.write(f.getAbsolutePath());
	    	fw.flush();
	    	fw.close();
    	}catch(IOException e){
    		Log.v("MiLog", "Excepcion: "+e.toString());
    	}
    }
    
    public void leer(View v) throws FileNotFoundException{
    	File f = new File(getFilesDir(), "archivo.txt");
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
    	TextView tv=(TextView)findViewById(R.id.tvLeet);
    	tv.setText(s);
    }
    /*
     * Otra Forma
     * 
     public void leer(View v) throws FileNotFoundException{
    	File f = new File(getFilesDir(), "archivo.txt");
    	BufferedReader br = new BufferedReader(new FileReader(f));
    	String linea="";
    	StringBuilder s=new String Builder();
    	try {
    		while ((linea = br.readLine()) != null) {
    			texto.append(linea);
    			texto.append("\n");
    		}
    		br.close();
    		TextView tv=(TextView)findViewById(R.id.tvLeet);
    		tv.setText(s)
		} catch (IOException e) {
			Log.v("MiLog", "Excepcion: "+e.toString());
		}
    }
     *
     */
}
