package test.Droidlogin;
import java.util.ArrayList;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;


import test.Droidlogin.Login.asynclogin;
import test.Droidlogin.library.Httppostaux;
import android.R.string;
import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.SystemClock;
import android.os.Vibrator;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.RadioButton;
import android.widget.Toast;
import android.widget.AdapterView.OnItemClickListener;

public class Mapas extends Activity{
	private RadioButton rb3, rb1, rb2;
	public int coordenada_latitud;
	public int coordenada_longitud;
	String nombre,coord_x,coord_y;
	public String nombre_cine;
	public LinearLayout ll;
	public ListView lv;
	  Httppostaux post;
	  private ProgressDialog pDialog;
	  private ArrayList  listaItems = new ArrayList();
	  private String usuario,cedula;
	String IP_Server="192.168.1.110";//IP DE NUESTRO PC
    String URL_connect_cine="http://"+IP_Server+"/Cinem/cine.php";//ruta en donde estan nuestros archivos
	
	public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.mapas);
        ll = (LinearLayout)findViewById(R.id.linearLayout1);
        Bundle extras = getIntent().getExtras();
        listaItems.add("CineMark - City Mall");
        listaItems.add("CineMark - Mall del Sol");
        listaItems.add("CineMark - Mall del Sur");
        lv = new ListView(this);
        lv.setAdapter(new ArrayAdapter(this, android.R.layout.simple_list_item_1,listaItems));
        
      
        
        ll.addView(lv);
        if (extras != null) {
      	
      	   usuario=extras.getString("usuario");
      	   cedula=extras.getString("cedu");
         }else{
      	   usuario="error";
      	   cedula="error";
      	   }
        lv.setOnItemClickListener(new OnItemClickListener() {
      	  
			public void onItemClick(AdapterView<?> arg0, View arg1, int position,long arg3) {
				
				//Intent intent=new Intent(Proveedor_Act.this,RealizarPedido.class);
				
				nombre_cine=listaItems.get(position).toString();
            	//lanzar(v);
            	new asynclogin().execute(usuario,cedula,nombre_cine); 
			
			}
        });
        //lv.
      //  rb1 = (RadioButton)findViewById(R.id.cine1);
       //rb2 = (RadioButton)findViewById(R.id.cine2);
        //rb3 = (RadioButton)findViewById(R.id.cine3);
        
       post=new Httppostaux();
       
               
             
        
        
    }
	
	 public void err_login(){
	    	Vibrator vibrator =(Vibrator) getSystemService(Context.VIBRATOR_SERVICE);
		    vibrator.vibrate(200);
		    Toast toast1 = Toast.makeText(getApplicationContext(),"Error:Nombre de usuario o password incorrectos", Toast.LENGTH_SHORT);
	 	    toast1.show();    	
	    }
	    
	    /*Valida el estado del logueo solamente necesita como parametros el usuario y passw*/
	    public boolean loginstatus(String username ,String password ,String cinem) {
	    	int logstatus=-1;
	    	String nom,lat,longi;
	    	
	    	/*Creamos un ArrayList del tipo nombre valor para agregar los datos recibidos por los parametros anteriores
	    	 * y enviarlo mediante POST a nuestro sistema para relizar la validacion*/ 
	    	
	    	ArrayList<NameValuePair> postconsultacine= new ArrayList<NameValuePair>();
	     		
			    		
			    		postconsultacine.add(new BasicNameValuePair("usuario",username));
			    		postconsultacine.add(new BasicNameValuePair("password",password));
			    		postconsultacine.add(new BasicNameValuePair("cine",cinem));

			   //realizamos una peticion y como respuesta obtenes un array JSON
	      		
	      		JSONArray jcine=post.getserverdata(postconsultacine, URL_connect_cine);
	      		

	      		/*como estamos trabajando de manera local el ida y vuelta sera casi inmediato
	      		 * para darle un poco realismo decimos que el proceso se pare por unos segundos para poder
	      		 * observar el progressdialog
	      		 * la podemos eliminar si queremos
	      		 */
			    SystemClock.sleep(950);
			    		
			    //si lo que obtuvimos no es null
			    	if (jcine!=null && jcine.length() > 0){

			    		
			    		JSONObject json_cine;
						try {
							
							json_cine=jcine.getJSONObject(0);//leemos el primer segmento en nuestro caso el unico
							nombre=json_cine.getString("NOMBRE");
							
							coord_x=json_cine.getString("X_COORD");
							coord_y=json_cine.getString("Y_COORD");
							
							coordenada_latitud=(int)((Double.parseDouble(coord_x))*1E6);
							coordenada_longitud=(int)((Double.parseDouble(coord_y))*1E6);
							nombre_cine=nombre;
							 Log.e("loginstatus", "nombre="+nombre);
							 Log.e("loginstatus", "coordenada en x="+coord_x);
							 Log.e("loginstatus", "coordenada en y="+coord_y);
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
	    
	    public boolean checklogindata(String username ,String password ){
	    	
	        if 	(username.equals("") || password.equals("")){
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
	        	 
	        	String user,pass,cin;
	            protected void onPreExecute() {
	            	//para el progress dialog
	                pDialog = new ProgressDialog(Mapas.this);
	                pDialog.setMessage("Buscando....");
	                pDialog.setIndeterminate(false);
	                pDialog.setCancelable(false);
	                pDialog.show();
	            }
	     
	    		protected String doInBackground(String... params) {
	    			//obtnemos usr y pass
	    			user=params[0];
	    			pass=params[1];
	    			cin=params[2];
	    			
	                
	    			//enviamos y recibimos y analizamos los datos en segundo plano.
	        		if (loginstatus(user,pass,cin)==true){    		    		
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
	               
	               if (result.equals("ok")){

	    				Intent i=new Intent(Mapas.this, MapasVista.class);
	    				 i.putExtra("latitud", coordenada_latitud);
	    			        i.putExtra("longitud", coordenada_longitud);
	    			        i.putExtra("nombre", nombre_cine);
	    			        startActivity(i);
	                }else{
	                	err_login();
	                }
	                
	                    									}
	    		
	            }
	
	
	
	public void lanzar(View view) {
        Intent i = new Intent(Mapas.this, MapasVista.class );
        i.putExtra("latitud", coord_x);
        i.putExtra("longitud", coord_y);
        i.putExtra("nombre", nombre);
        startActivity(i);
  }

}
