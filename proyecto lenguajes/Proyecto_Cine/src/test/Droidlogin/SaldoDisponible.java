package test.Droidlogin;

import test.Droidlogin.R;
import test.Droidlogin.clases.Usuario;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

public class SaldoDisponible extends Activity{
	String saldo_pantalla;
	String hola="Hola: ";
	TextView sald;
	
	
	
	public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.saldodisponible);
       
        //Obtenemos datos enviados en el intent.
        
        sald= (TextView) findViewById(R.id.saldo);
        Bundle extras = getIntent().getExtras();
        //Obtenemos datos enviados en el intent.
        if (extras != null) {
     	   saldo_pantalla  = extras.getString("saldo");//usuario
        }else{
     	   saldo_pantalla="error";
     	   }
        
        sald.setText("$"+saldo_pantalla);
        
    }
	public void cerrar(View view) {
    	finish();
    }

}
