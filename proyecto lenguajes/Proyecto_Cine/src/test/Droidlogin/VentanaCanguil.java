package test.Droidlogin;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import test.Droidlogin.R;
import test.Droidlogin.Login.asynclogin;
import test.Droidlogin.library.AdminSQLiteOpenHelper;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.ContentValues;
import android.content.Intent;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;
import android.content.DialogInterface;
import android.database.sqlite.SQLiteDatabase;
import android.app.AlertDialog;

public class VentanaCanguil extends Activity{
	private int valor_canguil_grande=5,valor_canguil_mediano=3,valor_hot_pollo=3,valor_hot_res=4,valor_nachos=4,total=0;
	ImageView canguilgrande=null;
	ImageView canguilmediano=null;
	ImageView hotpollo=null;
	ImageView hotres=null;
	ImageView fotonacho=null;
	ImageView guardar_pedido=null;
	ImageView cancelar_pedido=null;
	
	private int total_categoria_canguil=0,total_categoria_hot=0,total_categoria_nacho=0;
	
	
	TextView canguil_grande=null,canguil_mediano=null,hot_pollo=null,hot_res=null,nacho=null,total_pedido=null;
	
	String logo_precio="$";
	String saldo,total_canguil,total_bebida,nombre,usuario,apellido,sala,funcion,asiento,arreglo_canguil="",arreglo_hot="",arreglo_nacho="",arreglo_total="",cedu,nombrecine;
	 
	
	public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.ventana_canguil);
        
        guardar_pedido=(ImageView)findViewById(R.id.boton_guardar_pedido);
        cancelar_pedido=(ImageView)findViewById(R.id.boton_cancelar_pedido);
        canguilgrande=(ImageView)findViewById(R.id.canguilgrandefoto);
        canguilmediano=(ImageView)findViewById(R.id.canguilmedianofoto);
        hotpollo=(ImageView)findViewById(R.id.hotpollofoto);
        hotres=(ImageView)findViewById(R.id.hotresfoto);
        fotonacho=(ImageView)findViewById(R.id.nachofoto);
        total_pedido=(TextView)findViewById(R.id.total_pedido_canguil);
        Bundle extras = getIntent().getExtras();
        if (extras != null) { 
      	   saldo=extras.getString("saldo");
      	 nombre=extras.getString("nombreuser");
      	apellido=extras.getString("apellido");
      	sala=extras.getString("sala");
      	asiento=extras.getString("asiento");
      	funcion=extras.getString("funcion");
      	 cedu=extras.getString("cedula");
      	 nombrecine=extras.getString("cine");
      	 usuario=extras.getString("usuario");
      	  //total_canguil=extras.getString("total_canguil");
      	  //total_bebida=extras.getString("total_bebida");
         }else{
      	   saldo="error";
      	   }
        
      
    	total=total_categoria_canguil+total_categoria_nacho+total_categoria_hot;
        
         canguilgrande.setOnClickListener(new View.OnClickListener(){
            
        	public void onClick(View vista){
        		 
        		//Extreamos datos de los EditText
        		
        		
        		//	tmp_rb1=valor_canguil_grande;
        			total_categoria_canguil=valor_canguil_grande;
        			arreglo_canguil="";
        			arreglo_canguil="Canguil Grande";
        		//	total=total+valor_canguil_grande;
        		//canguil_grande.setText(logo_precio+String.valueOf(valor_canguil_grande));
        		 total_pedido.setText(logo_precio+String.valueOf(total_categoria_canguil+total_categoria_hot+total_categoria_nacho));
        		
        		//else if(rb1.isChecked()==false && rb2.isChecked()==true){
        			//total=total-valor_canguil_grande;
        			
        			//total_pedido.setText(logo_precio+String.valueOf(total));
        	//	}
        		//verificamos si estan en blanco	
        		
        	}
        	});
        canguilmediano.setOnClickListener(new View.OnClickListener(){
            
        	public void onClick(View vista){
        		 
        		//Extreamos datos de los EditText
        		
        			total_categoria_canguil=valor_canguil_mediano;
        			arreglo_canguil="";
        			arreglo_canguil="Canguil Mediano";
        			//total=total+valor_canguil_mediano;
        		//canguil_mediano.setText(logo_precio+String.valueOf(valor_canguil_mediano));
        		 total_pedido.setText(logo_precio+String.valueOf(total_categoria_canguil+total_categoria_hot+total_categoria_nacho));
        		 
        		//else if(rb2.isChecked()==false && rb1.isChecked()==true){
        			// total=total-valor_canguil_mediano;
             		//canguil_mediano.setText(logo_precio+String.valueOf(valor_canguil_mediano));
             		// total_pedido.setText(logo_precio+String.valueOf(total));
        			 
        			 
        		 //}
        		
        		//verificamos si estan en blanco	
        		
        	}
        	});
       hotpollo.setOnClickListener(new View.OnClickListener(){
    
	public void onClick(View vista){
		
		
			total_categoria_hot=valor_hot_pollo;
			arreglo_hot="";
			arreglo_hot="Hot Dog Pollo";
			//total=total+valor_hot_pollo;
		//canguil_mediano.setText(logo_precio+String.valueOf(valor_canguil_mediano));
		 total_pedido.setText(logo_precio+String.valueOf(total_categoria_canguil+total_categoria_hot+total_categoria_nacho));
		 
		//else{
			// total=total-valor_hot_pollo;
     		//canguil_mediano.setText(logo_precio+String.valueOf(valor_canguil_mediano));
     		 //total_pedido.setText(logo_precio+String.valueOf(total));
			 
			 
		 //}
		//Extreamos datos de los EditText
	     // hot_pollo.setText(logo_precio+String.valueOf(valor_hot_pollo));
	      
		//verificamos si estan en blanco	
		
	}
	});
        hotres.setOnClickListener(new View.OnClickListener(){
    
	public void onClick(View vista){
		 
		
			total_categoria_hot=valor_hot_res;
			arreglo_hot="";
			arreglo_hot="Hot Dog Res";
		//	total=total+valor_hot_res;
		//canguil_mediano.setText(logo_precio+String.valueOf(valor_canguil_mediano));
		 total_pedido.setText(logo_precio+String.valueOf(total_categoria_canguil+total_categoria_hot+total_categoria_nacho));
		 
		//else{
			// total=total-valor_hot_res;
     		//canguil_mediano.setText(logo_precio+String.valueOf(valor_canguil_mediano));
     		// total_pedido.setText(logo_precio+String.valueOf(total));
			 
			 
		 //}
		//Extreamos datos de los EditText
		//hot_res.setText(logo_precio+String.valueOf(valor_hot_res));
		 
		//verificamos si estan en blanco	
		
	}
	});
        
        fotonacho.setOnClickListener(new View.OnClickListener(){
            
        	public void onClick(View vista){
        		 
        		
        			total_categoria_nacho=valor_nachos;
        			arreglo_nacho="";
        			arreglo_nacho="Nachos";
        		//	total=total+valor_hot_res;
        		//canguil_mediano.setText(logo_precio+String.valueOf(valor_canguil_mediano));
        		 total_pedido.setText(logo_precio+String.valueOf(total_categoria_canguil+total_categoria_hot+total_categoria_nacho));
        		
        		//else{
        			// total=total-valor_hot_res;
             		//canguil_mediano.setText(logo_precio+String.valueOf(valor_canguil_mediano));
             		// total_pedido.setText(logo_precio+String.valueOf(total));
        			 
        			 
        		 //}
        		//Extreamos datos de los EditText
        		//hot_res.setText(logo_precio+String.valueOf(valor_hot_res));
        		 
        		//verificamos si estan en blanco	
        		
        	}
        	});
                
       guardar_pedido.setOnClickListener(new View.OnClickListener(){
            
        	public void onClick(View vista){
        		
        		 
        		 guardar(vista);
        		lanzar(vista);
        		
        	}
        	});
        
      cancelar_pedido.setOnClickListener(new View.OnClickListener(){
           
       	public void onClick(View vista){
       		 
       		//Extreamos datos de los EditText
       		arreglo_total="";
       		total=0;
       		finish(); 
       		//verificamos si estan en blanco	
       		
       	}
       	});
	}
	public void lanzar(View view) {
	       Intent i = new Intent(VentanaCanguil.this, MenuCompra.class );
	       i.putExtra("total_canguil",total);
	       i.putExtra("saldo", saldo);
	       i.putExtra("sala", sala);
	       i.putExtra("asiento", asiento);
	       i.putExtra("funcion", funcion);
	       i.putExtra("nombreuser", nombre);
	       i.putExtra("apellido", apellido);
	       i.putExtra("cedula", cedu);
	       i.putExtra("nombrecine", nombrecine);
	       i.putExtra("usuario", usuario);
	       startActivity(i);
	  }
     
	 public void guardar(View view) {
			 AdminSQLiteOpenHelper admin=new AdminSQLiteOpenHelper(this, "administracion", null, 1);
	        SQLiteDatabase bd=admin.getWritableDatabase();
	        ContentValues registro=new ContentValues();
	        String dni="1";
	        registro.put("preciocanguil",String.valueOf(total_categoria_canguil+total_categoria_hot+total_categoria_nacho) );
	        registro.put("detallecanguil",arreglo_canguil );
	        registro.put("detallehot",arreglo_hot );
	        registro.put("detallenacho",arreglo_nacho );
	        int cant = bd.update("pedido", registro, "id="+dni, null);
	        bd.close();
	        Log.e("el valor total del canguil en ventana canguil",String.valueOf(total_categoria_canguil+total_categoria_hot+total_categoria_nacho));
	        Log.e("el string del canguil es guardado en la base",arreglo_total);
	        Log.e("canguil escogido",arreglo_canguil);
	        Log.e("hot dog escogido",arreglo_hot);
	        Log.e("nacho escogido",arreglo_nacho);
	        if (cant==1)
	            Log.e("EStado del valor canguil", String.valueOf(cant));
	        	        else
	        	Log.e("EStado del registro canguil", String.valueOf(cant));       
	        //Toast.makeText(this, "Se cargaron los datos de la persona", Toast.LENGTH_SHORT).show();     
	    }
	 
	 }
