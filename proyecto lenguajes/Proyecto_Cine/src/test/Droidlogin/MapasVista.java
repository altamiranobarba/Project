package test.Droidlogin;

import java.util.List;


import com.google.android.maps.GeoPoint;
import com.google.android.maps.MapActivity;
import com.google.android.maps.MapController;
import com.google.android.maps.MapView;
import com.google.android.maps.OverlayItem;

import android.graphics.drawable.Drawable;
import android.os.Bundle;

import test.Droidlogin.R.string;
import test.Droidlogin.library.*;

public class MapasVista extends MapActivity{
	private MapView mapa = null;
	private int latitudeE6; 
			//(int) (-2.187035 * 1E6);
	private int longitudeE6 ; 
			//(int) (-79.879317 * 1E6);
	private CharSequence nombre_cine;
	
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.mapa_vista);
        Bundle extras = getIntent().getExtras();
        if (extras != null) {
      	   latitudeE6  = extras.getInt("latitud");//usuario
      	   longitudeE6 =extras.getInt("longitud");
      	   nombre_cine=extras.getCharSequence("nombre");
         }else{
        	 latitudeE6  = 0;//usuario
        	   longitudeE6 =0;
      	   }
        
        //Obtenemos una referencia al control MapView
        mapa = (MapView)findViewById(R.id.mapa);
        
        //Mostramos los controles de zoom sobre el mapa
        mapa.setBuiltInZoomControls(true);
        List mapOverlays = mapa.getOverlays();
        Drawable drawable = this.getResources().getDrawable(R.drawable.mar);
		MyOverlay itemizedOverlay = new MyOverlay(drawable, this);
 
		GeoPoint point = new GeoPoint(latitudeE6, longitudeE6);
		OverlayItem overlayitem = new OverlayItem(point, "Este es",(String) nombre_cine);
 
		itemizedOverlay.addOverlay(overlayitem);
		mapOverlays.add(itemizedOverlay);
 
		MapController mapController = mapa.getController();
 
		mapController.animateTo(point);
		mapController.setZoom(17);
		
    }
    
    @Override
    protected boolean isRouteDisplayed() {
    	return false;
    }

}
