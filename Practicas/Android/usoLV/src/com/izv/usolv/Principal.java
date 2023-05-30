package com.izv.usolv;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;

import org.xmlpull.v1.XmlSerializer;

import com.izv.usolv.util.AdaptadorContacto;
import com.izv.usolv.util.Contacto;
import android.os.Bundle;
import android.os.Environment;
import android.app.Activity;
import android.util.Log;
import android.util.Xml;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;
import android.widget.Toast;

public class Principal extends Activity {
	
	private ArrayList <Contacto> lista;
	private AdaptadorContacto adco;
	private ListView lv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_principal);
        lista=new ArrayList<Contacto>();
        Contacto c;
        c=new Contacto("Kirito","958123456","Beater@aincrad.es");
        lista.add(c);
        c=new Contacto("Ace","958987654","Portgas@ace.com");
        lista.add(c);
        c=new Contacto("Luffy","695123456","Monkey@onepiece.com");
        lista.add(c);
        c=new Contacto("Lidia","695987654","Lidia@delvalle.es");
        lista.add(c);
        c=new Contacto("Eren","675123456","Jaeger@kyojin.es");
        lista.add(c);
        c=new Contacto("Ueki","675987654","Justicia@ley.com");
        lista.add(c);
        adco=new AdaptadorContacto(this, lista);
        lv=(ListView) findViewById(R.id.lvLista);
        lv.setAdapter(adco);
        //Para que se pueda pulsar sobre cada contacto
        lv.setOnItemClickListener(new OnItemClickListener(){

			@Override
			public void onItemClick(AdapterView<?> av, View v, int pos,long id) {
				Toast.makeText(getApplicationContext(), "Hola "+adco.getItem(pos).getNombre().toString(), Toast.LENGTH_SHORT).show();
			}	
		});
    }
    
    public boolean onOptionsItemSelected(MenuItem item) {
    	switch (item.getItemId()) {
    		case R.id.agregar_contacto:
    			Contacto c=new Contacto("Levi","666777666","Capitan@exploradores.es");
    			adco.add(c);
    			adco.notifyDataSetChanged();
    			return true;
    	}
    	return super.onOptionsItemSelected(item);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.principal, menu);
        return true;
    }
    
}