package test.Droidlogin;

import java.util.ArrayList;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import test.Droidlogin.R;
import test.Droidlogin.Login.asynclogin;
import test.Droidlogin.library.AdminSQLiteOpenHelper;
import test.Droidlogin.library.Httppostaux;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.SystemClock;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;


public class MenuPrincipal extends Activity{
	ImageView botonconsultasaldo=null;
	ImageView botoncomprar=null;
	ImageView botonsalir=null;
	ImageView botonmapas=null;
	String user,saldo,apellido;
	TextView txt_usr;
	String bienvenida="Hola ";
	private String usuario,cedu;
	 Httppostaux post;
	    // String URL_connect="http://www.scandroidtest.site90.com/acces.php";
	    String IP_Server="192.168.1.110";//IP DE NUESTRO PC
	   
	    String URL_connect_usuario="http://"+IP_Server+"/Cinem/usuario.php";//ruta en donde estan nuestros archivos
	    boolean result_back;
	    private ProgressDialog pDialog;
	
	
	public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.menuprincipal);
        botonconsultasaldo=(ImageView)findViewById(R.id.SaldoDisponible);
        botoncomprar=(ImageView)findViewById(R.id.Comprar);
        botonsalir=(ImageView)findViewById(R.id.salir);
        botonmapas=(ImageView)findViewById(R.id.cines);
        txt_usr= (TextView) findViewById(R.id.nombre_usuario);
        Bundle extras = getIntent().getExtras();
        //Obtenemos datos enviados en el intent.
        if (extras != null) {
     	   user  = extras.getString("user");//usuario
     	  saldo =extras.getString("saldo");
     	   usuario=extras.getString("usuario");
     	  apellido=extras.getString("apellido");
     	   cedu=extras.getString("cedula");
        }else{
     	   user="error";
     	   }
        
        txt_usr.setText(bienvenida+user+"!!!");
        
        botonconsultasaldo.setOnClickListener(new View.OnClickListener(){
            
        	public void onClick(View vista){
        		 
        		//Extreamos datos de los EditText
        		
        		lanzar(vista);
        		
        		//verificamos si estan en blanco	
        		
        	}
        	});
        
       botoncomprar.setOnClickListener(new View.OnClickListener(){
            
        	public void onClick(View vista){
        		 
        		//Extreamos datos de los EditText
        		
        		lanzar_compra(vista);
        		
        	}
        	});
       
       botonmapas.setOnClickListener(new View.OnClickListener(){
           
       	public void onClick(View vista){
       		 
       		//Extreamos datos de los EditText
       		
       		lanzar_mapa(vista);
       		//verificamos si estan en blanco	
       		
       	}
       	});

      
       botonsalir.setOnClickListener(new View.OnClickListener(){
           
       	public void onClick(View vista){
       		 
       		//Extreamos datos de los EditText
       		eliminarbase();
       		//Intent intent = new Intent(Intent.ACTION_MAIN); finish();
       		onDestroy();
       		System.exit(0);
       		//verificamos si estan en blanco	
       		
       	}
       	});
      
	}
     
	public void eliminarbase(){
    	AdminSQLiteOpenHelper admin=new AdminSQLiteOpenHelper(this, "administracion", null, 1);
        SQLiteDatabase bd=admin.getWritableDatabase(); 
        admin.onDelete(bd);
        Log.e("estado de base", "eliminada");
    	
    }
	
	public void lanzar(View view) {
       Intent i = new Intent(MenuPrincipal.this, SaldoDisponible.class );
       i.putExtra("saldo",saldo);
       startActivity(i);
  }
	public void lanzar_compra(View view) {
       Intent j = new Intent(MenuPrincipal.this, Comprar.class );
       j.putExtra("usuario",usuario);
       j.putExtra("cedula",cedu);
       j.putExtra("saldo",saldo);
       j.putExtra("nombre",user);
       j.putExtra("apellido",apellido);
        startActivity(j);
  }
	public void lanzar_mapa(View view) {
	       Intent m = new Intent(MenuPrincipal.this, Mapas.class );
	       m.putExtra("usuario",usuario);
	       m.putExtra("cedula",cedu);
	        startActivity(m);
	  }
	
 
  
	 public boolean onKeyDown(int keyCode, KeyEvent event)  {
	     if (keyCode == KeyEvent.KEYCODE_BACK && event.getRepeatCount() == 0) {
	         // no hacemos nada.
	         return true;
	     }

	     return super.onKeyDown(keyCode, event);
	 }
	 
}
