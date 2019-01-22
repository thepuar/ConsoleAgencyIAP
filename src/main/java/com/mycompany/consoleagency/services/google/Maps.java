/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.consoleagency.services.google;

import com.google.maps.DirectionsApi;
import com.google.maps.GeoApiContext;
import com.google.maps.GeocodingApi;
import com.google.maps.errors.ApiException;
import com.google.maps.model.DirectionsResult;
import com.google.maps.model.GeocodingResult;
import com.google.maps.model.LatLng;
import com.google.maps.model.TravelMode;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author thepu
 */
public class Maps {

    private GeoApiContext context = null;
    GeocodingResult[] results ;
    DirectionsResult dresult;
    String direccion = "Calle rio nervion 11 9, 46025";
    double coordAlmacen[] = {39.488165, -0.398480};
    public Maps() {
        try{
        this.context = new GeoApiContext.Builder().apiKey("AIzaSyAwVHKXemyil7We_bTy2-67sBTCAVeFTcU").build();
        results = GeocodingApi.geocode(context,direccion ).await();
              //  "1600 Amphitheatre Parkway Mountain View, CA 94043").await();
               
        }catch(ApiException ae){ae.printStackTrace();} catch (InterruptedException ex) {
            Logger.getLogger(Maps.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(Maps.class.getName()).log(Level.SEVERE, null, ex);
        }
        LatLng latlongAlmacen = new LatLng(coordAlmacen[0], coordAlmacen[1]);
        dresult = DirectionsApi.newRequest(context)
                .mode(TravelMode.DRIVING)
                .origin(latlongAlmacen)
                .destination(direccion)
                .awaitIgnoreError();
        

    }
    
    public GeocodingResult[] getGeoResult(){return this.results;}
    
    public DirectionsResult getDirectionsResult(){return this.dresult;}
}
