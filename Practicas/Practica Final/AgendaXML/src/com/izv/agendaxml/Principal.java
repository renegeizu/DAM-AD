package com.izv.agendaxml;

	import java.io.File;
	import java.io.FileInputStream;
	import java.io.FileOutputStream;
	import java.util.ArrayList;
	import java.util.Collections;
	import org.xmlpull.v1.XmlPullParser;
	import org.xmlpull.v1.XmlSerializer;
	import android.os.Bundle;
	import android.app.Activity;
	import android.content.Intent;
	import android.util.Xml;
	import android.view.Menu;
	import android.view.MenuItem;
	import android.view.View;
	import android.widget.AdapterView;
	import android.widget.ListView;
	import android.widget.Toast;
	import android.widget.AdapterView.OnItemClickListener;

public class Principal extends Activity {
	
	private static int AGREGAR=1, EDITAR=2;
	private ArrayList<Contacto> agenda;
	private Adaptador adaptador;
	public static Contacto aux=new Contacto(null,null,null);
	private ListView lv;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_principal);
		agenda=new ArrayList<Contacto>();
		leerXML();
		lv=(ListView) findViewById(R.id.lvAgenda);
		adaptador=new Adaptador(getApplicationContext(), agenda);
		lv.setAdapter(adaptador);
		lv.setOnItemClickListener(new OnItemClickListener(){

			@Override
			public void onItemClick(AdapterView<?> av, View v, int pos, long id) {
				Intent iEditar = new Intent(getApplicationContext(), Editar.class);
				startActivityForResult(iEditar, EDITAR);
			}	
		});
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		getMenuInflater().inflate(R.menu.principal, menu);
		return true;
	}
	
	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		switch(item.getItemId()){
		case R.id.action_agregar:
			Intent iAgregar=new Intent(this, Agregar.class);
			startActivityForResult(iAgregar, AGREGAR);
			return true;
		}
		return super.onOptionsItemSelected(item);
	}
	
	public void tostada(String s){
		Toast.makeText(getApplicationContext(), s, Toast.LENGTH_LONG).show();
	}
	
	@Override
	public void onActivityResult(int requestCode, int resultCode, Intent data){
		if (resultCode == Activity.RESULT_OK && requestCode == AGREGAR) {
			Contacto c = (Contacto) data.getSerializableExtra("contacto");
			tostada(c.toString());
			if(!agenda.equals(c)){
				agenda.add(c);
				Collections.sort(agenda);
				adaptador.notifyDataSetChanged();
				escribirXML();
			}
		}
		if(resultCode == Activity.RESULT_OK && requestCode == EDITAR){
			int valor=data.getIntExtra("Valor", 0);
			switch(valor){
				case 0: 
					tostada("Cancelado");
					Collections.sort(agenda);
					adaptador.notifyDataSetChanged();
					escribirXML();
					break;
				case 1:
					agenda.remove(data.getSerializableExtra("Contacto"));
					ArrayList<Contacto> auxAgenda = new ArrayList<Contacto>();;
					for(int cont=0; cont<agenda.size(); cont++){
						if(agenda.get(cont) != null){
							auxAgenda.add(agenda.get(cont));
						}
					}
					agenda.clear();
					for(int cont=0; cont<auxAgenda.size(); cont++){
						agenda.add(auxAgenda.get(cont));
					}
					Collections.sort(agenda);
					adaptador.notifyDataSetChanged();
					escribirXML();
					break;
				case 2: 
					agenda.remove(data.getSerializableExtra("Contacto2"));
					ArrayList<Contacto> auxAgenda2 = new ArrayList<Contacto>();;
					for(int cont=0; cont<agenda.size(); cont++){
						if(agenda.get(cont) != null){
							auxAgenda2.add(agenda.get(cont));
						}
					}
					agenda.clear();
					for(int cont=0; cont<auxAgenda2.size(); cont++){
						agenda.add(auxAgenda2.get(cont));
					}
					agenda.add((Contacto) data.getSerializableExtra("Contacto"));
					Collections.sort(agenda);
					adaptador.notifyDataSetChanged();
					escribirXML();
					break;
			}
		}
		if(resultCode == Activity.RESULT_CANCELED){
			tostada("Operación Cancelada");
		}
	}
	
	private void escribirXML(){
		try {
			FileOutputStream fosxml = new FileOutputStream(new File(getExternalFilesDir(null),"agenda.xml"));
			XmlSerializer docxml = Xml.newSerializer();
			docxml.setOutput(fosxml, "UTF-8");
			docxml.startDocument(null, Boolean.valueOf(true));
			docxml.setFeature("http://xmlpull.org/v1/doc/features.html#indent-output", true);
			docxml.startTag(null, "agenda");
			for(Contacto c: agenda){
				docxml.startTag(null, "contacto");
				docxml.attribute(null, "tel", c.getTelefono());
				docxml.attribute(null, "correo", c.getEmail());
				docxml.text(c.getNombre());
				docxml.endTag(null, "contacto");
			}
			docxml.endDocument();
			docxml.flush();
			fosxml.close();
		} catch (Exception e) {
		}
	}
	
	private void leerXML(){
		XmlPullParser lectorxml = Xml.newPullParser();
		try {
			lectorxml.setInput(new FileInputStream(new File(getExternalFilesDir(null),"agenda.xml")),"utf-8");
			int evento = lectorxml.getEventType();
			while (evento != XmlPullParser.END_DOCUMENT){
				if(evento == XmlPullParser.START_TAG){
					String etiqueta = lectorxml.getName();
					if(etiqueta.compareTo("contacto")==0){
							String tel=lectorxml.getAttributeValue(null, "tel");
							String correo=lectorxml.getAttributeValue(null, "correo");
							String nombre=lectorxml.nextText();
							Contacto c=new Contacto(nombre, tel, correo);
							agenda.add(c);
					}
				}
				evento = lectorxml.next();
			}
		} catch (Exception e) {
		}
	}
}