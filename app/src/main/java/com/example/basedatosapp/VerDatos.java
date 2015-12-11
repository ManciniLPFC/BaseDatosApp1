package com.example.basedatosapp;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.animation.BounceInterpolator;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class VerDatos  extends Activity implements OnClickListener{

	private EditText txtNombre;
	private EditText txtTipo;
	//private EditText txtStock;
	//private EditText txtPrecio;
	
	private Button btnAnterior;
	private Button btnSiguiente;
	private Button btnNuevo;
	private Button btnModificar;
	private Button btnEliminar;
	
	private BDSqlLite BaseDatos;
	
	String Nombre,Tipo;
	int Id_;
	//Double Precio;
	
	private int contador=0;
	Productos[] productos;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {		
		super.onCreate(savedInstanceState);
		setContentView(R.layout.datos);
		
		txtNombre=(EditText) findViewById(R.id.editText_NombreDatos);
		txtTipo=(EditText) findViewById(R.id.editText_TipoDatos);
		//txtStock=(EditText) findViewById(R.id.editText_StockDatos);
		//txtPrecio=(EditText) findViewById(R.id.editText_PrecioDatos);
		
		btnSiguiente=(Button) findViewById(R.id.btnSiguiente);
		btnAnterior=(Button) findViewById(R.id.btnAnterior);
		btnEliminar=(Button) findViewById(R.id.btnEliminar);
		btnModificar=(Button) findViewById(R.id.btnModificar);
		btnNuevo=(Button) findViewById(R.id.btnNuevo);
		
		btnSiguiente.setOnClickListener(this);
		btnAnterior.setOnClickListener(this);
		btnEliminar.setOnClickListener(this);
		btnModificar.setOnClickListener(this);
		btnNuevo.setOnClickListener(this);
		
		BaseDatos=new BDSqlLite(getApplicationContext());
		
		productos=BaseDatos.ConsultarProductos();
		
		if(productos.length>0){
		
			txtNombre.setText(productos[0].getNombre());
			txtTipo.setText(productos[0].getTipo());
			//txtStock.setText(String.valueOf(productos[0].getStock()));
			//txtPrecio.setText(String.valueOf(productos[0].getPrecio()));
			contador++;
			
			btnAnterior.setEnabled(true);
			btnSiguiente.setEnabled(true);
			btnEliminar.setEnabled(true);
			btnModificar.setEnabled(true);
			
		}
		else{
			btnAnterior.setEnabled(false);
			btnSiguiente.setEnabled(false);
			btnEliminar.setEnabled(false);
			btnModificar.setEnabled(false);
			
			Toast.makeText(VerDatos.this, "No hay datos que mostrar", Toast.LENGTH_SHORT).show();
			
		}
		
		

	}

	@Override
	public void onClick(View v) {
		
		switch(v.getId()){
		case R.id.btnNuevo:
			Intent mostarpantalla=new Intent(this,MainActivity.class);
			startActivity(mostarpantalla);
			finish();
			
			break;
			
		case R.id.btnAnterior:
			
			if(productos.length>0){
				if(contador>=0){
					contador--;
					if(contador<0){
						contador++;
					}
					
					txtNombre.setText(productos[contador].getNombre());
					txtTipo.setText(productos[contador].getTipo());
					//txtStock.setText(String.valueOf(productos[0].getStock()));
					//txtPrecio.setText(String.valueOf(productos[contador].getPrecio()));
					
					
				}
				
			}
			
			
			break;
			
	   case R.id.btnSiguiente:
		   
		   if(productos.length>0){
				if(contador<productos.length){
					contador++;
					if(contador==productos.length){
						contador--;
					}
					
					txtNombre.setText(productos[contador].getNombre());
					txtTipo.setText(productos[contador].getTipo());

					
					
				}
				
			}
			
			break;
	   case R.id.btnModificar:
			Id_=productos[contador].getId();
			Nombre=txtNombre.getText().toString();
			Tipo=txtTipo.getText().toString();

			BaseDatos.ModificarProducto(Id_, Nombre, Tipo);
			productos=BaseDatos.ConsultarProductos();
			
			Toast.makeText(VerDatos.this, "Alumno modificado con exito...", Toast.LENGTH_SHORT).show();
			
		   
			break;
			
	   case R.id.btnEliminar:
		   
		   if(productos.length==1){
			   contador=0;
		   }
		   Id_=productos[contador].getId();
		   
		   BaseDatos.EliminarProducto(Id_);
		   
		   contador=0;
		   
		   Toast.makeText(VerDatos.this, "Alumno Eliminado con exito...", Toast.LENGTH_SHORT).show();
			
		   productos=BaseDatos.ConsultarProductos();
		   
		   
		   if(productos.length>0){
			   txtNombre.setText(productos[contador].getNombre());
				txtTipo.setText(productos[contador].getTipo());

				
		   }
		   else{
			    mostarpantalla=new Intent(this,MainActivity.class);
				startActivity(mostarpantalla);
				finish();
			   
		   }
		   
			break;
		
		}
		
	}
	
	
	

}
