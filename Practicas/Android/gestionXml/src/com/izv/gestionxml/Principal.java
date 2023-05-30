package com.izv.gestionxml;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;
import org.xmlpull.v1.XmlSerializer;

import android.os.Bundle;
import android.app.Activity;
import android.util.Xml;
import android.view.Menu;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

public class Principal extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_principal);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		getMenuInflater().inflate(R.menu.principal, menu);
		return true;
	}
	
	public void Escribir(View v){
		try {
			FileOutputStream fosxml = new FileOutputStream(new File(getExternalFilesDir(null),"Contactos.xml"));
			XmlSerializer docxml = Xml.newSerializer();
			docxml.setOutput(fosxml, "UTF-8");
			docxml.startDocument(null, Boolean.valueOf(true));
			docxml.setFeature("http://xmlpull.org/v1/doc/features.html#indent-output", true);
			docxml.startTag(null, "Agenda");
			docxml.startTag(null, "Contacto");
			docxml.attribute(null, "Nombre", "valor1");
			docxml.attribute(null, "Telefono", "valor2");
			docxml.attribute(null, "Email", "valor3");
			docxml.endTag(null, "nodo");
			docxml.endDocument();
			docxml.flush();
			fosxml.close();
		} catch (Exception e) {
		}
	}
	
	public void Leer(View v){
		try {
			XmlPullParser lectorxml = Xml.newPullParser();
			lectorxml.setInput(new FileInputStream(new File(getExternalFilesDir(null),"Contactos.xml")),"utf-8");
			int evento = lectorxml.getEventType();
			String atrib1 = null, atrib2=null, atrib3=null;
			String etiqueta=null;
			while (evento!=XmlPullParser.END_DOCUMENT){
				if(evento==XmlPullParser.START_TAG){
					etiqueta = lectorxml.getName();
					if(etiqueta.compareTo("nodo")==0){
						atrib1=lectorxml.getAttributeValue(null, "Nombre");
						atrib2=lectorxml.getAttributeValue(null, "Telefono");
						atrib3=lectorxml.getAttributeValue(null, "Email");
					}
				}

				evento = lectorxml.next();
				TextView tv=(TextView) findViewById(R.id.tvXML);
			}
		} catch (Exception e) {
			TextView tv=(TextView) findViewById(R.id.tvXML);
			tv.setText("Fallo");
		}
	}

}