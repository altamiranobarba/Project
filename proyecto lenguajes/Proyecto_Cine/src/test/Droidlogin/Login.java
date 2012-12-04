package test.Droidlogin;
/*CREADO POR SEBASTIAN CIPOLAT*/
import java.util.ArrayList;
import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import test.Droidlogin.R.string;
import test.Droidlogin.clases.Usuario;
import test.Droidlogin.library.AdminSQLiteOpenHelper;
import test.Droidlogin.library.Httppostaux;
import android.app.Activity;
import android.app.ProgressDialog;
import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.SystemClock;
import android.os.Vibrator;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class Login extends Activity {
    /** Called when the activity is first created. */
    
    EditText user;
    EditText pass;
    Button blogin;
    TextView registrar;
    Httppostaux post;
    // String URL_connect="http://www.scandroidtest.site90.com/acces.php";
    String IP_Server="192.168.1.110";//IP DE NUESTRO PC
    String URL_connect="http://"+IP_Server+"/Cinem/acces.php";//ruta en donde estan nuestros archivos
    String URL_connect_usuario="http://"+IP_Server+"/Cinem/usuario.php";//ruta en donde estan nuestros archivos
    boolean result_back;
    private ProgressDialog pDialog;
    private String usuario,cedu;
    String nombre,apellido,saldo;
    
    public Login(){
    	
    	
    }
    
    @Override
    public void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        post=new Httppostaux();
        
        user= (EditText) findViewById(R.id.edusuario);
        pass= (EditText) findViewById(R.id.edpassword);
        blogin= (Button) findViewById(R.id.Blogin);
        
        
        eliminarbase();
        //Login button action
        blogin.setOnClickListener(new View.OnClickListener(){
       
        	public void onClick(View view){
        		 
        		//Extreamos datos de los EditText
        		String usuario=user.getText().toString();
        		String passw=pass.getText().toString();
        		
        		//verificamos si estan en blanco
        		if( checklogindata( usuario , passw )==true){

        			//si pasamos esa validacion ejecutamos el asynctask pasando el usuario y clave como parametros
        			
        		new asynclogin().execute(usuario,passw);        		               
        			      		
        		
        		}else{
        			//si detecto un error en la primera validacion vibrar y mostrar un Toast con un mensaje de error.
        			err_login();
        		}
        		
        	}
        													});
        
        
                
    }
    
    //vibra y muestra un Toast
    public void err_login(){
    	Vibrator vibrator =(Vibrator) getSystemService(Context.VIBRATOR_SERVICE);
	    vibrator.vibrate(200);
	    Toast toast1 = Toast.makeText(getApplicationContext(),"Error:Nombre de usuario o password incorrectos", Toast.LENGTH_SHORT);
 	    toast1.show();
 	    eliminarbase();
    }
    
    
    /*Valida el estado del logueo solamente necesita como parametros el usuario y passw*/
    public boolean loginstatus(String username ,String password ) {
    	int logstatus=-1;
    	String nom,ape,sald;
    	
    	/*Creamos un ArrayList del tipo nombre valor para agregar los datos recibidos por los parametros anteriores
    	 * y enviarlo mediante POST a nuestro sistema para relizar la validacion*/ 
    	ArrayList<NameValuePair> postparameters2send= new ArrayList<NameValuePair>();
    	ArrayList<NameValuePair> postconsultausuario= new ArrayList<NameValuePair>();
     		
		    		postparameters2send.add(new BasicNameValuePair("usuario",username));
		    		postparameters2send.add(new BasicNameValuePair("password",password));
		    		postconsultausuario.add(new BasicNameValuePair("usuario",username));
		    		postconsultausuario.add(new BasicNameValuePair("password",password));

		   //realizamos una peticion y como respuesta obtenes un array JSON
      		JSONArray jdata=post.getserverdata(postparameters2send, URL_connect);
      		JSONArray jusuario=post.getserverdata(postconsultausuario, URL_connect_usuario);
      		

      		/*como estamos trabajando de manera local el ida y vuelta sera casi inmediato
      		 * para darle un poco realismo decimos que el proceso se pare por unos segundos para poder
      		 * observar el progressdialog
      		 * la podemos eliminar si queremos
      		 */
		    SystemClock.sleep(950);
		    		
		    //si lo que obtuvimos no es null
		    	if (jdata!=null && jdata.length() > 0){

		    		JSONObject json_data; //creamos un objeto JSON
		    		JSONObject json_usuario;
					try {
						json_data = jdata.getJSONObject(0); //leemos el primer segmento en nuestro caso el unico
						json_usuario=jusuario.getJSONObject(0);
						nombre=json_usuario.getString("NOMBRE");
						usuario=json_usuario.getString("USER");
						cedu=json_usuario.getString("CEDULA");
						
						apellido=json_usuario.getString("APELLIDO");
						saldo=json_usuario.getString("SALDO");
						
						 logstatus=json_data.getInt("logstatus");//accedemos al valor 
						 Log.e("loginstatus","logstatus= "+logstatus);//muestro por log que obtuvimos
						 Log.e("loginstatus", "nombre="+nombre);
						 Log.e("loginstatus", "apellido="+apellido);
						 Log.e("loginstatus", "saldo="+saldo);
						 Log.e("loginstatus", "user="+usuario);
						 Log.e("loginstatus", "cedula="+cedu);
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
    
          
    //validamos si no hay ningun campo en blanco
    public boolean checklogindata(String username ,String password ){
    	
    if 	(username.equals("") || password.equals("")){
    	Log.e("Login ui", "checklogindata user or pass error");
    return false;
    
    }else{
    	
    	return true;
    }
    
}  
    
    public void iniciarbase() {
        AdminSQLiteOpenHelper admin=new AdminSQLiteOpenHelper(this, "administracion", null, 1);
        SQLiteDatabase bd=admin.getWritableDatabase(); 
        admin.onCreate(bd);
        ContentValues registro=new ContentValues();
        registro.put("id",1 );
        registro.put("preciocanguil","0" );
        registro.put("preciobebida","0" );
        registro.put("detallecanguil"," " );
        registro.put("detallehot"," " );
        registro.put("detallenacho"," " );
        registro.put("detallebebida"," " );
        bd.insert("pedido", null, registro);
        bd.close();       
        Log.e("estado general de la base", "ok");        
    }
    
    
    public void eliminarbase(){
    	AdminSQLiteOpenHelper admin=new AdminSQLiteOpenHelper(this, "administracion", null, 1);
        SQLiteDatabase bd=admin.getWritableDatabase(); 
        admin.onDelete(bd);
        Log.e("estado de base", "eliminada");
    	
    }
    
/*		CLASE ASYNCTASK
 * 
 * usaremos esta para poder mostrar el dialogo de progreso mientras enviamos y obtenemos los datos
 * podria hacerse lo mismo sin usar esto pero si el tiempo de respuesta es demasiado lo que podria ocurrir    
 * si la conexion es lenta o el servidor tarda en responder la aplicacion sera inestable.
 * ademas observariamos el mensaje de que la app no responde.     
 */
    
    class asynclogin extends AsyncTask< String, String, String > {
    	 
    	String user,pass;
        protected void onPreExecute() {
        	//para el progress dialog
            pDialog = new ProgressDialog(Login.this);
            pDialog.setMessage("Autenticando....");
            pDialog.setIndeterminate(false);
            pDialog.setCancelable(false);
            pDialog.show();
        }
 
		protected String doInBackground(String... params) {
			//obtnemos usr y pass
			user=params[0];
			pass=params[1];
            
			//enviamos y recibimos y analizamos los datos en segundo plano.
    		if (loginstatus(user,pass)==true){    		    		
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
           
           if (result.equals("ok") && nombre!=null){
        	   iniciarbase();
				Intent i=new Intent(Login.this, MenuPrincipal.class);
				i.putExtra("user",nombre);
				i.putExtra("saldo",saldo);
				i.putExtra("usuario",usuario);
				i.putExtra("cedula",cedu);
				i.putExtra("apellido", apellido);
				startActivity(i); 
				
            }else{
            	err_login();
            }
            
                									}
		
        }
 
    }
    

   
     
    //-----------------------------------------------------------------------
    
    
 

