package com.izv.agendaxml;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class Editar extends Activity {

	
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_editar);
		try{
			EditText et11=(EditText)findViewById(R.id.etENombre);
			EditText et22=(EditText)findViewById(R.id.etETelefono);
			EditText et33=(EditText)findViewById(R.id.etECorreo);
			et11.setText(Principal.aux.getNombre().toString());
			et22.setText(Principal.aux.getTelefono().toString());
			et33.setText(Principal.aux.getEmail().toString());
		}catch(Exception e){
			tostada("Error en la Seleccion");
		}
		Button borrar=(Button) findViewById(R.id.btEBorrar);
		borrar.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				Intent i = new Intent();
				Bundle bundle = new Bundle();
				bundle.putSerializable("Contacto", Principal.aux); 
				bundle.putInt("Valor", 1);
				i.putExtras(bundle);
				setResult(Activity.RESULT_OK, i);
				finish();
			}
		});
		Button editar=(Button) findViewById(R.id.btEGuardar);
		editar.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				EditText et11=(EditText)findViewById(R.id.etENombre);
				EditText et22=(EditText)findViewById(R.id.etETelefono);
				EditText et33=(EditText)findViewById(R.id.etECorreo);
				Contacto auxiliar=new Contacto(et11.getText().toString(),
						et22.getText().toString(),et33.getText().toString());
				Intent i = new Intent();
				Bundle bundle = new Bundle();
				bundle.putSerializable("Contacto", auxiliar); 
				bundle.putSerializable("Contacto2", Principal.aux);
				bundle.putInt("Valor", 2);
				i.putExtras(bundle);
				setResult(Activity.RESULT_OK, i);
				finish();
			}
		});
		Button cancelar=(Button) findViewById(R.id.btECancelar);
		cancelar.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				Intent i=new Intent();
				setResult(Activity.RESULT_CANCELED, i);
				finish();				
			}
		});
	}
	
	public void tostada(String s){
		Toast.makeText(getApplicationContext(), s, Toast.LENGTH_LONG).show();
	}

}
