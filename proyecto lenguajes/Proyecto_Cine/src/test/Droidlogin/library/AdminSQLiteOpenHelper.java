package test.Droidlogin.library;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteDatabase.CursorFactory;
import android.database.sqlite.SQLiteOpenHelper;

public class AdminSQLiteOpenHelper extends SQLiteOpenHelper{

	public AdminSQLiteOpenHelper(Context context, String nombre, CursorFactory factory, int version) {
		super(context, nombre, factory, version);
	}
	
	@Override
	public void onCreate(SQLiteDatabase arg0) {
		// TODO Auto-generated method stub
		arg0.execSQL("create table pedido(id integer primary key, preciocanguil text, preciobebida text,detallecanguil text,detallehot text,detallenacho text,detallebebida text)");
		
	}
	
	public void onDelete(SQLiteDatabase arg0) {
		// TODO Auto-generated method stub
		arg0.execSQL("drop table if exists pedido");
			
	}

	@Override
	public void onUpgrade(SQLiteDatabase arg0, int arg1, int arg2) {
		// TODO Auto-generated method stub
		arg0.execSQL("drop table if exists pedido");
		arg0.execSQL("create table pedido(id integer primary key, preciocanguil text, preciobebida text,detallecanguil text,detallehot text,detallenacho textdetallebebida text)");
				
	}

}
