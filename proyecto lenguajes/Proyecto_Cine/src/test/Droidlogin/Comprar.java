package test.Droidlogin;



import java.util.ArrayList;

import test.Droidlogin.Mapas.asynclogin;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.RadioButton;
import android.widget.SimpleCursorAdapter;
import android.widget.AdapterView.OnItemClickListener;
import android.app.Activity;
import android.content.Intent;

public class Comprar extends Activity {
	private RadioButton rb1, rb2, rb3;
	public LinearLayout ll;
	public EditText et;
	public ListView lv;
	private String usuario,cedu,saldo,nombre_cine,apellido,nombre;
	 private ArrayList  listaItems = new ArrayList();
	
		
	public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.comprar);
       
        ll = (LinearLayout)findViewById(R.id.linearLayout1);
        Bundle extras = getIntent().getExtras();
        listaItems.add("CineMark - City Mall");
        listaItems.add("CineMark - Mall del Sol");
        listaItems.add("CineMark - Mall del Sur");
        lv = new ListView(this);
        lv.setAdapter(new ArrayAdapter(this, android.R.layout.simple_list_item_1,listaItems));
        
      
        
        ll.addView(lv);

        //Obtenemos datos enviados en el intent.
        if (extras != null) {
     	   
     	   usuario=extras.getString("usuario");
     	  nombre=extras.getString("nombre");
     	   cedu=extras.getString("cedula");
     	   saldo=extras.getString("saldo");
     	  apellido=extras.getString("apellido");
     	   
        }else{
     	   usuario="error";
     	   }
        
        //lv.
             
      
       
        lv.setOnItemClickListener(new OnItemClickListener() {
        	  
			public void onItemClick(AdapterView<?> arg0, View arg1, int position,long arg3) {
				
				//Intent intent=new Intent(Proveedor_Act.this,RealizarPedido.class);
				
				nombre_cine=listaItems.get(position).toString();
            	lanzar(arg1);
            	
			
			}
        });
       
        
       
        
    }
	public void cerrar(View view) {
    	finish();
    }
	
	public void lanzar(View view) {
        Intent i = new Intent(Comprar.this, FacturaCodigo.class );
        i.putExtra("cine", nombre_cine);
        i.putExtra("usuario", usuario);
        i.putExtra("cedula", cedu);
        i.putExtra("saldo", saldo);
        i.putExtra("nombre", nombre);
        i.putExtra("apellido", apellido);
        startActivity(i);
  }

}
