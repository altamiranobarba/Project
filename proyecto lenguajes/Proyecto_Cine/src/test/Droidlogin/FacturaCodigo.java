package test.Droidlogin;

import java.util.ArrayList;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import test.Droidlogin.Login.asynclogin;
import test.Droidlogin.library.Httppostaux;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.SystemClock;
import android.os.Vibrator;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;
import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;


public class FacturaCodigo extends Activity {
	ImageView botoncodigo=null;
	EditText codigo=null;
	String usuario,passw;
	String cod;
	String hora,sala,asiento,saldo,nombre,apellido;
	 Httppostaux post;
	  private ProgressDialog pDialog;
	  private String cedu,user,cinenombre;
	String IP_Server="192.168.1.110";//IP DE NUESTRO PC
   String URL_connect_id="http://"+IP_Server+"/Cinem/factura.php";//ruta en donde estan nuestros archivos
   
	public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.codigo_factura);
        post=new Httppostaux();
        
        botoncodigo= (ImageView) findViewById(R.id.botonok);
        codigo= (EditText) findViewById(R.id.caja_codigo);
        Bundle extras = getIntent().getExtras();
        //Obtenemos datos enviados en el intent.
        if (extras != null) {
     	   
     	   user=extras.getString("usuario");
     	  nombre=extras.getString("nombre");
     	 apellido=extras.getString("apellido");
     	   cedu=extras.getString("cedula");
     	   cinenombre=extras.getString("cine");
     	   saldo=extras.getString("saldo");
        }else{
     	   usuario="error";
     	   }
        
        botoncodigo.setOnClickListener(new View.OnClickListener(){
            
        	public void onClick(View view){
        		//Extreamos datos de los EditText
        		String cod=codigo.getText().toString();
        	
        		
        		//verificamos si estan en blanco
        		if( checklogindata( cod )==true){

        			//si pasamos esa validacion ejecutamos el asynctask pasando el usuario y clave como parametros
        			
        		new asynclogin().execute(user,cedu,cod);        		               
        			      		
        		
        		}else{
        			//si detecto un error en la primera validacion vibrar y mostrar un Toast con un mensaje de error.
        			err_login();
        		}
        		
        		
        	}
	});
             
               
    }
	
	public void err_login(){
    	Vibrator vibrator =(Vibrator) getSystemService(Context.VIBRATOR_SERVICE);
	    vibrator.vibrate(200);
	    Toast toast1 = Toast.makeText(getApplicationContext(),"Error:Codigo de factura incorrecto...", Toast.LENGTH_SHORT);
 	    toast1.show();    	
    }
	
    /*Valida el estado del logueo solamente necesita como parametros el usuario y passw*/
    public boolean loginstatus(String username ,String password ,String id) {
    	int logstatus=-1;
    	
    	
    	/*Creamos un ArrayList del tipo nombre valor para agregar los datos recibidos por los parametros anteriores
    	 * y enviarlo mediante POST a nuestro sistema para relizar la validacion*/ 
    	
    	ArrayList<NameValuePair> postconsultafactura= new ArrayList<NameValuePair>();
     		
		    		
		    		postconsultafactura.add(new BasicNameValuePair("usuario",username));
		    		postconsultafactura.add(new BasicNameValuePair("password",password));
		    		postconsultafactura.add(new BasicNameValuePair("id",id));

		   //realizamos una peticion y como respuesta obtenes un array JSON
      		
      		JSONArray jid=post.getserverdata(postconsultafactura, URL_connect_id);
      		

      		/*como estamos trabajando de manera local el ida y vuelta sera casi inmediato
      		 * para darle un poco realismo decimos que el proceso se pare por unos segundos para poder
      		 * observar el progressdialog
      		 * la podemos eliminar si queremos
      		 */
		    SystemClock.sleep(950);
		    		
		    //si lo que obtuvimos no es null
		    	if (jid!=null && jid.length() > 0){

		    		
		    		JSONObject json_factura;
					try {
						
						json_factura=jid.getJSONObject(0);//leemos el primer segmento en nuestro caso el unico
						hora=json_factura.getString("HORARIO_FUNCION");
						
						sala=json_factura.getString("SALA_FUNCION");
						asiento=json_factura.getString("ASIENTO_FUNCION");
						
						//coordenada_latitud=(int)((Double.parseDouble(coord_x))*1E6);
						//coordenada_longitud=(int)((Double.parseDouble(coord_y))*1E6);
						//nombre_cine=nombre;
						 Log.e("loginstatus", "hora="+hora);
						Log.e("loginstatus", "sala="+sala);
						 Log.e("loginstatus", "asiento="+asiento);
					} catch (JSONException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}		            
		             
					//validamos el valor obtenido
		    		 if (logstatus==0){// [{"logstatus":"0"}] 
		    			 Log.e("loginstatus ", "invalido");
		    			 return false;
		    		 }
		    		 else{// [{"logstatus":"1"}]
		    			 Log.e("loginstatus ", "valido");
		    			 return true;
		    		 }
		    		 
			  }else{	//json obtenido invalido verificar parte WEB.
		    			 Log.e("JSON  ", "ERROR");
			    		return false;
			  }
    	
    }
    
    public boolean checklogindata(String codigo ){
    	
        if 	(codigo.equals("")){
        	Log.e("Login ui", "checklogindata user or pass error");
        return false;
        
        }else{
        	
        	return true;
        }
        
    }
    
    /*		CLASE ASYNCTASK
     * 
     * usaremos esta para poder mostrar el dialogo de progreso mientras enviamos y obtenemos los datos
     * podria hacerse lo mismo sin usar esto pero si el tiempo de respuesta es demasiado lo que podria ocurrir    
     * si la conexion es lenta o el servidor tarda en responder la aplicacion sera inestable.
     * ademas observariamos el mensaje de que la app no responde.     
     */
        
        class asynclogin extends AsyncTask< String, String, String > {
        	 
        	String user,pass,ide;
            protected void onPreExecute() {
            	//para el progress dialog
                pDialog = new ProgressDialog(FacturaCodigo.this);
                pDialog.setMessage("Buscando....");
                pDialog.setIndeterminate(false);
                pDialog.setCancelable(false);
                pDialog.show();
            }
     
    		protected String doInBackground(String... params) {
    			//obtnemos usr y pass
    			user=params[0];
    			pass=params[1];
    			ide=params[2];
    			
                
    			//enviamos y recibimos y analizamos los datos en segundo plano.
        		if (loginstatus(user,pass,ide)==true){    		    		
        			return "ok"; //login valido
        		}else{    		
        			return "err"; //login invalido     	          	  
        		}
            	
    		}
           
    		/*Una vez terminado doInBackground segun lo que halla ocurrido 
    		pasamos a la sig. activity
    		o mostramos error*/
            protected void onPostExecute(String result) {

               pDialog.dismiss();//ocultamos progess dialog.
               Log.e("onPostExecute=",""+result);
               
               if (result.equals("ok") && hora!=null){

    				Intent i=new Intent(FacturaCodigo.this, MenuCompra.class);
    				// i.putExtra("latitud", coordenada_latitud);
    				i.putExtra("saldo", saldo);
    				i.putExtra("nombreuser", nombre);
    				i.putExtra("apellido", apellido);
    			       i.putExtra("nombrecine", cinenombre);
    			       i.putExtra("sala", sala);
       				i.putExtra("funcion", hora);
       			       i.putExtra("asiento",asiento);
       			    i.putExtra("cedula",cedu);
       			 i.putExtra("usuario",user);
    			        startActivity(i);
                }else{
                	err_login();
                }
                
                    									}
    		
            }
        
	public void cerrar(View view) {
    	finish();
    }
	
}
