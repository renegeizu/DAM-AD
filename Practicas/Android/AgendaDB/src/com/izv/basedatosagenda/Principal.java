package com.izv.basedatosagenda;

	import java.util.List;
	import android.os.Bundle;
	import android.app.Activity;
	import android.database.Cursor;
	import android.view.Menu;
	import android.widget.EditText;
	import android.widget.ListView;
	import android.widget.TextView;
	import android.widget.Toast;
	import com.izv.basedatosagenda.db.Adaptador;
	import com.izv.basedatosagenda.db.GestionAgenda;
	import com.izv.basedatosagenda.pojo.Agenda;

public class Principal extends Activity {

	private GestionAgenda gestorAgenda;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.principal);
		gestorAgenda = new GestionAgenda(getApplicationContext());
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		getMenuInflater().inflate(R.menu.principal, menu);
		return true;
	}

	@Override
	public void onResume() {
		gestorAgenda.open();
		super.onResume();
		/*for(int i=0; i<10; i++){
			Agenda ag=new Agenda(0, "Luffy", "987654321"+i, "One@piece.com");
			gestorAgenda.insert(ag); 
		}*/
		//mostrar();
		Cursor c=gestorAgenda.getCursor();
		Adaptador a=new Adaptador(getApplicationContext(), c);
		ListView lv=(ListView) findViewById(R.id.lvAgenda);
		lv.setAdapter(a);
	}

	public void mostrar(){
		TextView tv=(TextView) findViewById(R.id.tvNombre);
		tv.setText("");
		List<Agenda> res=gestorAgenda.select("");
		for(Agenda a: res){
			tv.append(a.toString()+"\n"+"\n");
		}
	}

	@Override
	public void onPause() {
		super.onPause();
		gestorAgenda.close();
	}

	public void borrar(){

		EditText et=(EditText) findViewById(R.id.etId);
		String id=et.getText().toString();
		//int r=gestorAgenda.delete(Long.parseLong(id));
		int r=gestorAgenda.delete(new Agenda(Long.parseLong(id),"","",""));
		if(r>0){
			tostada("Elemento Borrado");
			mostrar();
		}else{
			tostada("Elemento No Borrado");
		}
	}

	public void borrar2(){
		EditText et=(EditText) findViewById(R.id.etId);
		String id=et.getText().toString();
		Agenda a=gestorAgenda.getRow(Long.parseLong(id));
		int r=gestorAgenda.delete(a.getNombre(), a.getTelefono());
		if(r>0){
			tostada("Elemento Borrado");
			mostrar();
		}else{
			tostada("Elemento No Borrado");
		}
	}

	public void cambio(){
		EditText et=(EditText) findViewById(R.id.etId);
		EditText et2=(EditText) findViewById(R.id.editText1);
		String id=et.getText().toString();
		Agenda a=gestorAgenda.getRow(Long.parseLong(id));
		a.setNombre(et2.getText().toString());
		int r=gestorAgenda.update(a);
		if(r>0){
			tostada("Elemento Borrado");
			mostrar();
		}else{
			tostada("Elemento No Borrado");
		}
	}

	public void tostada(String s){
		Toast.makeText(getApplicationContext(), s, Toast.LENGTH_SHORT).show();
	}
	
}
