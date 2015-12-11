package com.example.basedatosapp;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Switch;
import android.widget.Toast;

public class MainActivity extends Activity implements OnClickListener{

	private EditText txtNombre;
	private EditText txtTipo;
	//private EditText txtStock;
	//private EditText txtPrecio;
	
	private Button btnGuardar;
	private Button btnVerProductos;
	
	private BDSqlLite BaseDatos;
	
	String Nombre,Tipo;
	int Id;
	//Double Precio;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		txtNombre=(EditText) findViewById(R.id.editText_Nombre);
		txtTipo=(EditText) findViewById(R.id.editText_Tipo);
		//txtStock=(EditText) findViewById(R.id.editText_Stock);
		//txtPrecio=(EditText) findViewById(R.id.editText_Precio);
		
		btnGuardar=(Button) findViewById(R.id.btnGuardar_);
		btnVerProductos=(Button) findViewById(R.id.btnVerDatos);
		
		btnGuardar.setOnClickListener(this);
		btnVerProductos.setOnClickListener(this);
		
		BaseDatos=new BDSqlLite(getApplicationContext());
		
		
		
	}

	
	private void Limpiar(){
		txtNombre.setText("");
		txtTipo.setText("");
		//txtStock.setText("");
		//txtPrecio.setText("");
	}
	
	
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

	@Override
	public void onClick(View v) {
		
		switch (v.getId()){
		case R.id.btnGuardar_:
			
			Nombre=txtNombre.getText().toString();
			Tipo=txtTipo.getText().toString();
			//Stock=Integer.valueOf(txtStock.getText().toString());
			//Precio=Double.valueOf(txtPrecio.getText().toString());
			
			BaseDatos.InsertarProductos(Nombre, Tipo);
			
			Toast.makeText(MainActivity.this, "Alumno---->" +Nombre+ " <------ Ingresado satisfactoriamente", Toast.LENGTH_SHORT).show();
			
			Limpiar();
			
			break;
			
	    case R.id.btnVerDatos:
			Intent mostrarPantalla=new Intent(this,VerDatos.class);
			startActivity(mostrarPantalla);
			finish();
	    	
	    	
		break;
		
		}
		
	}
	
	
	

}
