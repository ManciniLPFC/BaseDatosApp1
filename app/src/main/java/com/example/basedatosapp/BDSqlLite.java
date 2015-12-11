package com.example.basedatosapp;

import static android.provider.BaseColumns._ID;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteDatabase.CursorFactory;
import android.database.sqlite.SQLiteOpenHelper;

public class BDSqlLite extends SQLiteOpenHelper {
     
	private static int version=1;
	private static String nombreBd="MiBaseDatos";
	private static CursorFactory factory=null;
	
	
	public BDSqlLite(Context context) {
		super(context, nombreBd, factory, version);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void onCreate(SQLiteDatabase db) {
		// TODO Auto-generated method stub
		String query="CREATE TABLE Producto ("+_ID+" INTEGER PRIMARY KEY AUTOINCREMENT,"+
											"Nombre TEXT NOT NULL, "+
											"Tipo TEXT NOT NULL)";

		
		db.execSQL(query);
		
	}

	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		// TODO Auto-generated method stub
		
	}

	
	public  void InsertarProductos(String Nombre, String Tipo){
	
		SQLiteDatabase db=getWritableDatabase();
		
		if(db!=null){
		ContentValues valores=new ContentValues();
		
		valores.put("Nombre", Nombre);
		valores.put("Tipo", Tipo);
		//valores.put("Stock", Stock);
		//valores.put("Precio", Precio);
		
		db.insert("Producto", null, valores);
		
		db.close();
			
		}
		
	}
	
	
	public void ModificarProducto(int id,String Nombre, String Tipo){
	
		SQLiteDatabase db=getWritableDatabase();
		
		ContentValues valores=new ContentValues();
		
		valores.put("Nombre", Nombre);
		valores.put("Tipo", Tipo);
		//valores.put("Stock", Stock);
		//valores.put("Precio", Precio);
		
		
		db.update("Producto",valores, "_ID="+id, null);
		db.close();
	}
	
	
	public void EliminarProducto(int id){
		SQLiteDatabase db=getWritableDatabase();
		db.delete("Producto", "_ID="+id, null);
		db.close();
	}

	
	public Productos[] ConsultarProductos(){
		SQLiteDatabase db=getReadableDatabase();
		String sql="SELECT * FROM Producto";
		Productos[] listaProductos=null;
		
		Cursor c=db.rawQuery(sql, null);
		
		listaProductos=new Productos[c.getCount()];
		int i=0;
		if(c.moveToFirst()){
		
			do{
				Productos oProductos=new Productos();
				oProductos.setId(c.getInt(0));
				oProductos.setNombre(c.getString(1));
				oProductos.setTipo(c.getString(2));
				//oProductos.setStock(c.getInt(3));
				//oProductos.setPrecio(c.getDouble(4));

				listaProductos[i]=oProductos;
				i++;
				
				
			}while(c.moveToNext());
			
		}
		c.close();
		db.close();
		
		return listaProductos;
		
		
	}


}
