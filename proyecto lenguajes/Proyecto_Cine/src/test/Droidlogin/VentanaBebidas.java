package test.Droidlogin;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import test.Droidlogin.R;
import test.Droidlogin.Login.asynclogin;
import test.Droidlogin.library.AdminSQLiteOpenHelper;

import android.widget.AdapterView.OnItemSelectedListener;
import android.app.Activity;
import android.content.ContentValues;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

public class VentanaBebidas extends Activity {
	
	private int bebida_grande_valor=5,bebida_mediano_valor=3,total=0;
	
	ImageView guardar_pedido=null;
	ImageView cancelar_pedido=null;
	ImageView vasogrande=null;
	ImageView vasomediano=null;
	
	
	TextView bebida_grande=null,bebida_mediano=null,total_texto=null;
	Spinner s ;
	
	String logo_precio="$";
	String saldo,total_canguil,total_bebida,nombre,apellido,usuario,sala,funcion,asiento,arreglo_bebida="",cola_escogida=" ",string_cola_escogida,cedu,nombrecine;
	
	public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.ventana_bebidas);
        ArrayAdapter <CharSequence> adapter =new ArrayAdapter <CharSequence> (this, android.R.layout.simple_spinner_item );
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        adapter.add("Coca Cola");
        adapter.add("Sprite");
        adapter.add("Pepsi");
        s = (Spinner) findViewById(R.id.spinner1);
        s.setAdapter(adapter);
        
        guardar_pedido=(ImageView)findViewById(R.id.boton_guardar_bebida);
        cancelar_pedido=(ImageView)findViewById(R.id.boton_cancelar_bebida);
        vasogrande=(ImageView)findViewById(R.id.vasogrande);
        vasomediano=(ImageView)findViewById(R.id.vasomediano);
        total_texto=(TextView)findViewById(R.id.total_valor_bebida);
     
        
      Bundle extras = getIntent().getExtras();
      if (extras != null) { 
     	   saldo=extras.getString("saldo");
     	  total_canguil=extras.getString("total_canguil");
     	  total_bebida=extras.getString("total_bebida");
     	 nombre=extras.getString("nombreuser");
       	apellido=extras.getString("apellido");
       	sala=extras.getString("sala");
       	asiento=extras.getString("asiento");
       	funcion=extras.getString("funcion");
       	cedu=extras.getString("cedula");
     	 nombrecine=extras.getString("cine");
     	 usuario=extras.getString("usuario");
        }else{
     	   saldo="error";
     	   }
       
        
        
    	s.setOnItemSelectedListener(new OnItemSelectedListener(){

			public void onItemSelected(AdapterView<?> arg0, View arg1,
					int arg2, long arg3) {
				// TODO Auto-generated method stub
				cola_escogida=arg0.getItemAtPosition(arg2).toString();
								
			}

			public void onNothingSelected(AdapterView<?> arg0) {
				// TODO Auto-generated method stub
				
			}
    		
    		
    		
    	});
            
        vasogrande.setOnClickListener(new View.OnClickListener(){
            
        	public void onClick(View vista){
        		 
        		//Extreamos datos de los EditText
        		
        		
        			total=bebida_grande_valor;
        			arreglo_bebida="";
        			arreglo_bebida="Grande";
            		//canguil_grande.setText(logo_precio+String.valueOf(valor_canguil_grande));
            		 total_texto.setText(logo_precio+String.valueOf(total));
        			
        		
        		//verificamos si estan en blanco	
        		
        	}
        	});
        vasomediano.setOnClickListener(new View.OnClickListener(){
            
        	public void onClick(View vista){
        		 
        		//Extreamos datos de los EditText
        		
        		
        			total=bebida_mediano_valor;
        			arreglo_bebida="";
        			arreglo_bebida="Mediano";
            		//canguil_grande.setText(logo_precio+String.valueOf(valor_canguil_grande));
            		 total_texto.setText(logo_precio+String.valueOf(total));
        			
        		//canguil_mediano.setText(logo_precio+String.valueOf(valor_canguil_mediano));
        		 
        		 
        		
        		//verificamos si estan en blanco	
        		
        	}
        	});
    
    
        
       guardar_pedido.setOnClickListener(new View.OnClickListener(){
            
        	public void onClick(View vista){
        		 
        		//Extreamos datos de los EditText
        		guardar(vista);
        		
        		lanzar(vista);
        		//verificamos si estan en blanco	
        		
        	}
        	});
        
      cancelar_pedido.setOnClickListener(new View.OnClickListener(){
           
       	public void onClick(View vista){
       		 
       		//Extreamos datos de los EditText
       		finish(); 
       		//verificamos si estan en blanco	
       		
       	}
       	});
	}
	public void lanzar(View view) {
	       Intent i = new Intent(VentanaBebidas.this, MenuCompra.class );
	      i.putExtra("total_bebida",total);
	      i.putExtra("saldo",saldo);
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
	public void guardar(View v) {
		AdminSQLiteOpenHelper admin=new AdminSQLiteOpenHelper(this, "administracion", null, 1);
        SQLiteDatabase bd=admin.getWritableDatabase();
        ContentValues registro=new ContentValues();
        String dni="1";
        registro.put("preciobebida",String.valueOf(total) );
        registro.put("detallebebida",cola_escogida + arreglo_bebida);
        int cant = bd.update("pedido", registro, "id="+dni, null);
        bd.close();
        Log.e("el valor total de bebida en ventana bebida",String.valueOf(total));
        Log.e("el string del bebida en ventana bebida",cola_escogida + arreglo_bebida);
        Log.e("cola escogida",cola_escogida);
        Log.e("tamaño escogido",arreglo_bebida);
        
        if (cant==1){
            Log.e("EStado del valor bebida", String.valueOf(cant));
        
        }
        else
        	Log.e("EStado del registro bebida", String.valueOf(cant));       
        //Toast.makeText(this, "Se cargaron los datos de la persona", Toast.LENGTH_SHORT).show();        
    }

	
}
