package com.izv.agendaxml;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class Agregar extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_agregar);
		Button btCancelar=(Button) findViewById(R.id.btCancelar);
		btCancelar.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				Intent i=new Intent();
				setResult(Activity.RESULT_CANCELED, i);
				finish();				
			}
		});
	}
	
	public void agregar(View v){
		EditText et1, et2, et3;
		et1=(EditText)findViewById(R.id.etNombre);
		et2=(EditText)findViewById(R.id.etTelefono);
		et3=(EditText)findViewById(R.id.etCorreo);
		String s1, s2, s3;
		s1=et1.getText().toString().trim();
		s2=et2.getText().toString().trim();
		s3=et3.getText().toString().trim();
		if(s1.compareTo("")!=0 && s2.compareTo("")!=0 && s3.compareTo("")!=0 ){
			Contacto c=new Contacto(s1,s2,s3);
			Intent i = new Intent();
			Bundle bundle = new Bundle();
			bundle.putSerializable("contacto", c); 
			i.putExtras(bundle);
			setResult(Activity.RESULT_OK, i);
			finish();
		}else{
			tostada("Faltan Valores");
		}
	}
	
	public void tostada(String s){
		Toast.makeText(getApplicationContext(), s, Toast.LENGTH_LONG).show();
	}

}
