package test.Droidlogin;


import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

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
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;

public class GenerarFactura extends Activity{
	TextView factura_nombre=null;
	TextView factura_fecha=null;
	TextView factura_sala=null;
	TextView factura_funcion=null;
	TextView factura_asiento=null;
	TextView factura_detalle=null;
	TextView factura_total=null;
	ImageView guardar_pedido=null;
	ImageView cancelar_pedido=null;
	Httppostaux post;
	private ProgressDialog pDialog;
	String cedu,nombrecine;
	String IP_Server="192.168.1.110";//IP DE NUESTRO PC
    String URL_connect="http://"+IP_Server+"/Cinem/addpedido.php";//ruta en donde estan nuestros archivos
    String URL_connect_id="http://"+IP_Server+"/Cinem/id.php";
	String nombre,apellido,saldo,sala,usuario,asiento,funcion,total,hora_temporal,pedido;
	int descuento;
	public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.pedidogenerar);
        post=new Httppostaux();
        guardar_pedido=(ImageView)findViewById(R.id.boton_guardar_registro);
        cancelar_pedido=(ImageView)findViewById(R.id.boton_cancelar_registro);
        factura_nombre= (TextView) findViewById(R.id.registro_nombre_cliente);
        factura_fecha= (TextView) findViewById(R.id.registro_fecha);
        factura_sala= (TextView) findViewById(R.id.registro_sala);
        factura_funcion= (TextView) findViewById(R.id.registro_funcion);
        factura_asiento= (TextView) findViewById(R.id.registro_asiento);
        factura_detalle= (TextView) findViewById(R.id.registro_detalle);
        factura_total= (TextView) findViewById(R.id.registro_total);
        
        Bundle extras = getIntent().getExtras();
        //Obtenemos datos enviados en el intent.
        if (extras != null) {
     	   
     	  
     	  nombre=extras.getString("nombreuser");
     	 apellido=extras.getString("apellido");
      	sala=extras.getString("sala");
       	asiento=extras.getString("asiento");
       	funcion=extras.getString("funcion");
       	total=extras.getString("total");
       	pedido=extras.getString("pedido");
       	cedu=extras.getString("cedula");
       	nombrecine=extras.getString("cine");
       	saldo=extras.getString("saldo");
       	usuario=extras.getString("usuario");
     	  
     	   
        }else{
     	   nombre="error";
     	   }
        hora_temporal=getDatePhone();
        factura_nombre.setText(nombre+" "+apellido);
       factura_fecha.setText(hora_temporal);
        factura_sala.setText(sala);
        factura_asiento.setText(asiento);
        factura_funcion.setText(funcion);
        factura_detalle.setText(pedido);
        factura_total.setText(total);
        descuento=Integer.parseInt(saldo)-Integer.parseInt(total);
        
        guardar_pedido.setOnClickListener(new View.OnClickListener(){
            
        	public void onClick(View vista){
        		 
        		//Extreamos datos de los EditText
        		
        		

        			//si pasamos esa validacion ejecutamos el asynctask pasando el usuario y clave como parametros
        			
        		new asynclogin().execute(cedu,nombre,apellido,hora_temporal,nombrecine,funcion,sala,asiento,pedido,total,String.valueOf(descuento));        		               
        			      		
        		
        		
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
	private String getDatePhone()

	{

	    Calendar cal = new GregorianCalendar();

	    Date date = cal.getTime();

	    SimpleDateFormat df = new SimpleDateFormat("dd-MM-yyyy");
	    
	    String formatteDate = df.format(date);

	    return formatteDate;

	}
	
	
	    
	   /*Valida el estado del logueo solamente necesita como parametros el usuario y passw*/
	    public boolean loginstatus(String cedula ,String nombre,String apellido,String hora, String cine,String horario,String sala,String asiento,String detalle,String total ,String desc) {
	    	int logstatus=-1;
	    	String valor;
	    	
	    	/*Creamos un ArrayList del tipo nombre valor para agregar los datos recibidos por los parametros anteriores
	    	 * y enviarlo mediante POST a nuestro sistema para relizar la validacion*/ 
	    	ArrayList<NameValuePair> postparameters2send= new ArrayList<NameValuePair>();
	    	ArrayList<NameValuePair> postparameters3send= new ArrayList<NameValuePair>();
	    	
	     	    		postparameters2send.add(new BasicNameValuePair("cedula",cedula));
			    		postparameters2send.add(new BasicNameValuePair("nombre",nombre));
			    		postparameters2send.add(new BasicNameValuePair("apellido",apellido));
			    		postparameters2send.add(new BasicNameValuePair("hora",hora));
			    		postparameters2send.add(new BasicNameValuePair("cine",cine));
			    		postparameters2send.add(new BasicNameValuePair("horario",horario));
			    		postparameters2send.add(new BasicNameValuePair("sala",sala));
			    		postparameters2send.add(new BasicNameValuePair("asiento",asiento));
			    		postparameters2send.add(new BasicNameValuePair("detalle",detalle));
			    		postparameters2send.add(new BasicNameValuePair("total",total));
			    		
			    		postparameters3send.add(new BasicNameValuePair("cedula",cedula));
			    		postparameters3send.add(new BasicNameValuePair("saldo",desc));
			   //realizamos una peticion y como respuesta obtenes un array JSON
	      		JSONArray jdata=post.getserverdata(postparameters2send, URL_connect);
	      		JSONArray jdata2=post.getserverdata(postparameters3send, URL_connect_id);
	      		

	      		/*como estamos trabajando de manera local el ida y vuelta sera casi inmediato
	      		 * para darle un poco realismo decimos que el proceso se pare por unos segundos para poder
	      		 * observar el progressdialog
	      		 * la podemos eliminar si queremos
	      		 */
			    SystemClock.sleep(950);
			    		
			    //si lo que obtuvimos no es null
			    	if (jdata!=null && jdata.length() > 0){

			    		JSONObject json_data; //creamos un objeto JSON
			    		
						try {
							json_data = jdata.getJSONObject(0); //leemos el primer segmento en nuestro caso el unico
							logstatus=json_data.getInt("logstatus");
							  
							 Log.e("loginstatus","logstatus= "+logstatus);//muestro por log que obtuvimos
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
	        	 
	        	String ced,nom,ape,hor,cin,fun,sal,asi,det,tot,des;
	            protected void onPreExecute() {
	            	//para el progress dialog
	                pDialog = new ProgressDialog(GenerarFactura.this);
	                pDialog.setMessage("Guardando....");
	                pDialog.setIndeterminate(false);
	                pDialog.setCancelable(false);
	                pDialog.show();
	            }
	     
	    		protected String doInBackground(String... params) {
	    			//obtnemos usr y pass
	    			ced=params[0];
	    			nom=params[1];
	    			ape=params[2];
	    			hor=params[3];
	    			cin=params[4];
	    			fun=params[5];
	    			sal=params[6];
	    			asi=params[7];
	    			det=params[8];
	    			tot=params[9];
	    			des=params[10];
	    			
	                
	    			//enviamos y recibimos y analizamos los datos en segundo plano.
	        		if (loginstatus(ced,nom,ape,hor,cin,fun,sal,asi,det,tot,des)==true){    		    		
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
	               
	             //  if (result.equals("ok")){
	               	finish();
	               	Toast toast1 = Toast.makeText(getApplicationContext(),"Pedido se ha realizado satisfactoriamente", Toast.LENGTH_SHORT);
	         	    toast1.show();  
	         	//   System.exit(0);
	         	 // onDestroy();
	    				Intent i=new Intent(GenerarFactura.this, Login.class);
	    				
	    				i.putExtra("user",nombre);
	    				i.putExtra("saldo",String.valueOf(descuento));
	    				i.putExtra("usuario",usuario);
	    				i.putExtra("cedula",cedu);
	    				i.putExtra("apellido", apellido);
	    				startActivity(i);
	    				
	    				
	               // }else{
	                	//err_login();
	                //}
	                
	                    									}
	    		
	            }

}
