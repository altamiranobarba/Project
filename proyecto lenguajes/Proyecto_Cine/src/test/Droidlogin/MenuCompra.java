package test.Droidlogin;


import test.Droidlogin.R;
import test.Droidlogin.library.AdminSQLiteOpenHelper;

import android.app.Activity;
import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.os.Vibrator;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
public class MenuCompra extends Activity{
	ImageView bcanguil=null;
	ImageView bbebidas=null;
	ImageView bgenerar=null;
	TextView saldo_disponible=null;
	TextView valor_total=null;
	String total,nombre,apellido,sala,usuario,asiento,funcion,arreglo_base_canguil,arreglo_base_bebida,arreglo_base_hot,arreglo_base_nacho,cedu,cinenombre;
	int total_productos=0,total_bebidas_pedido=0,total_canguil_pedido=0;
	private String saldo,total_canguil,total_bebida;

	public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.menu_compra);
        bcanguil=(ImageView)findViewById(R.id.grupo_canguil);
        bbebidas=(ImageView)findViewById(R.id.grupo_bebidas);
        bgenerar=(ImageView)findViewById(R.id.genera_pedido);
        saldo_disponible=(TextView)findViewById(R.id.saldo_disponible_valor);
        valor_total=(TextView)findViewById(R.id.total_valor);
        Bundle extras = getIntent().getExtras();
        consulta();
       
        //Obtenemos datos enviados en el intent.
        if (extras != null) { 
     	   saldo=extras.getString("saldo");
     	  nombre=extras.getString("nombreuser");
     	 apellido=extras.getString("apellido");
     	sala=extras.getString("sala");
   	  	asiento=extras.getString("asiento");
   	 funcion=extras.getString("funcion");
   	 cedu=extras.getString("cedula");
   	cinenombre=extras.getString("nombrecine");
   	usuario=extras.getString("usuario");
        }else{
     	   saldo="error";
     	   }
       
        
        if(total_canguil==null){
        	total_canguil_pedido=0;
        }else{
        	
        	total_canguil_pedido=Integer.parseInt(total_canguil);
        }
        if(total_bebida==null){
        	total_bebidas_pedido=0;
        }else{
        	
        	total_bebidas_pedido=Integer.parseInt(total_bebida);
        }
        total_productos=total_productos+total_canguil_pedido+total_bebidas_pedido;
       // if(total_canguil.contains("null")==false && total_bebida.contains("null")==false){
        //	total_productos=Integer.parseInt(total_canguil) + Integer.parseInt(total_bebida);
       // }else{
        	
        //	total_productos=0;
        //}
        saldo_disponible.setText("$"+saldo);
        valor_total.setText("$"+String.valueOf(total_productos));
        bcanguil.setOnClickListener(new View.OnClickListener(){
            
        	public void onClick(View vista){
        		 
        		//Extreamos datos de los EditText
        		
        		lanzar(vista);
        		//verificamos si estan en blanco	
        		
        	}
        	});
        
       bbebidas.setOnClickListener(new View.OnClickListener(){
            
        	public void onClick(View vista){
        		 
        		//Extreamos datos de los EditText
        		
        		lanzar_bebida(vista);
        		//verificamos si estan en blanco	
        		
        	}
        	});
       
       
       
       bgenerar.setOnClickListener(new View.OnClickListener(){
           
          	public void onClick(View vista){
          		 
          		//Extreamos datos de los EditText
          		if(total_productos==0){
          			err_vacio();
          			
          		}else if(Integer.parseInt(saldo)>=total_productos){
          		generar_pedido(vista);
          		}else{
          			
          			err_saldo();
          			
          		}
          		//finish(); 
          		//verificamos si estan en blanco	
          		
          	}
          	});
	}
	
	 public void err_saldo(){
	    	Vibrator vibrator =(Vibrator) getSystemService(Context.VIBRATOR_SERVICE);
		    vibrator.vibrate(200);
		    Toast toast1 = Toast.makeText(getApplicationContext(),"Error:Tu saldo actual es insuficiente", Toast.LENGTH_SHORT);
	 	    toast1.show();    	
	    }
	 public void err_vacio(){
	    	Vibrator vibrator =(Vibrator) getSystemService(Context.VIBRATOR_SERVICE);
		    vibrator.vibrate(200);
		    Toast toast1 = Toast.makeText(getApplicationContext(),"Error:No se han realizado pedidos", Toast.LENGTH_SHORT);
	 	    toast1.show();    	
	    }
	
	public void cerrar(View view) {
    	finish();
    }
	public void lanzar(View view) {
	       Intent m = new Intent(MenuCompra.this, VentanaCanguil.class );
	      m.putExtra("saldo",saldo);
	     m.putExtra("nombreuser",nombre);
	      m.putExtra("apellido",apellido);
	      m.putExtra("sala",sala);
		     m.putExtra("asiento",asiento);
		      m.putExtra("funcion",funcion);
		      m.putExtra("cine",cinenombre);
		      m.putExtra("cedula",cedu);
		      m.putExtra("usuario",usuario);
	       startActivity(m);
	  }
	public void lanzar_bebida(View view) {
	       Intent i = new Intent(MenuCompra.this, VentanaBebidas.class );
	       		i.putExtra("saldo",saldo);
	       		i.putExtra("nombreuser",nombre);
	  	      i.putExtra("apellido",apellido);
	  	    i.putExtra("sala",sala);
		     i.putExtra("asiento",asiento);
		      i.putExtra("funcion",funcion);
		      i.putExtra("cine",cinenombre);
		      i.putExtra("cedula",cedu);
		      i.putExtra("usuario",usuario);
	       startActivity(i);
	  }
	
	public void generar_pedido(View view) {
	       Intent i = new Intent(MenuCompra.this, GenerarFactura.class );
	       		i.putExtra("saldo",saldo);
	       		i.putExtra("nombreuser",nombre);
	  	      i.putExtra("apellido",apellido);
	  	    i.putExtra("sala",sala);
		     i.putExtra("asiento",asiento);
		      i.putExtra("funcion",funcion);
		      i.putExtra("cine",cinenombre);
		      i.putExtra("cedula",cedu);
		      i.putExtra("total",String.valueOf(total_productos));
		      i.putExtra("saldo",saldo);
		     // i.putExtra("usuario",usuario);
		      i.putExtra("pedido", arreglo_base_canguil+" "+arreglo_base_hot+" "+arreglo_base_nacho+" "+arreglo_base_bebida);
	       startActivity(i);
	  }
  
	public void consulta() {
        AdminSQLiteOpenHelper admin=new AdminSQLiteOpenHelper(this, "administracion", null, 1);
        SQLiteDatabase bd=admin.getWritableDatabase();
        String dni="1";
        
        Cursor fila=bd.rawQuery("select preciocanguil,preciobebida,detallecanguil,detallehot,detallenacho,detallebebida  from pedido where id="+dni+"",null);
        if (fila.moveToFirst())
        {
        	total_canguil=fila.getString(0);
        	total_bebida=fila.getString(1);
        	arreglo_base_canguil=fila.getString(2);
        	arreglo_base_hot=fila.getString(3);
        	arreglo_base_nacho=fila.getString(4);
        	arreglo_base_bebida=fila.getString(5);
        	Log.e("valor de la base canguil en menu compra", total_canguil);
        	Log.e("valor de la base bebida en menu compra", total_bebida);
        	Log.e("string de la base canguil en menu compra", arreglo_base_canguil);
        	Log.e("string de la base bebida en menu compra", arreglo_base_bebida);
                        
        }
        else   
        bd.close();
        
    }
	
}
