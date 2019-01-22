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
import com.google.maps.model.DirectionsStep;
import com.google.maps.model.GeocodingResult;
import com.google.maps.model.LatLng;
import com.google.maps.model.TravelMode;
import com.mycompany.consoleagency.model.PuntoRutaGMaps;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author thepu
 */
public class AppMaps {

    private GeoApiContext context = null;
    GeocodingResult[] results;
    DirectionsResult dresult;
    String direccion;
    double coordAlmacen[] = {39.488165, -0.398480};

    public AppMaps() {

    }

    public LatLng getCoords(String direccion) {
        try {
            this.context = new GeoApiContext.Builder().apiKey("AIzaSyAwVHKXemyil7We_bTy2-67sBTCAVeFTcU").build();
            results = GeocodingApi.geocode(context, direccion).await();
        } catch (ApiException ae) {
            ae.printStackTrace();
        } catch (InterruptedException ex) {
            Logger.getLogger(Maps.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(Maps.class.getName()).log(Level.SEVERE, null, ex);
        }
        return new LatLng(this.getCoordLat(), this.getCoordLng());
    }

    public String getAddres(double lat, double lng) {

        try {
            this.context = new GeoApiContext.Builder().apiKey("AIzaSyAwVHKXemyil7We_bTy2-67sBTCAVeFTcU").build();
            LatLng latlng = new LatLng(lat, lng);
            results = GeocodingApi.reverseGeocode(context, latlng).await();
        } catch (ApiException ae) {
            ae.printStackTrace();
        } catch (InterruptedException ex) {
            Logger.getLogger(Maps.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(Maps.class.getName()).log(Level.SEVERE, null, ex);
        }
        return results[0].formattedAddress;

    }

    //dir1 Origen - dir2 Destino
    public void getRuta(String dir1, String dir2) {
        LatLng latlongAlmacen = this.getCoords(dir1);
        dresult = DirectionsApi.newRequest(context)
                .mode(TravelMode.DRIVING)
                .origin(latlongAlmacen)
                .destination(dir2)
                .awaitIgnoreError();

    }

    public List<PuntoRutaGMaps> getRutaPuntos(String dir1, String dir2) {
        List<PuntoRutaGMaps> punto = new ArrayList<>();
        getRuta(dir1, dir2);
        DirectionsResult dresult = this.getDirectionsResult();
        DirectionsStep[] steps = dresult.routes[0].legs[0].steps;
        long distanceTotal = 0;
        long timeTotal = 0;
        for (int i = 0; i < steps.length; i++) {
            PuntoRutaGMaps elpunto = new PuntoRutaGMaps();
            elpunto.setMetros(steps[i].distance.inMeters);
            LatLng coord = new LatLng(steps[i].startLocation.lat, steps[i].startLocation.lng);
            elpunto.setCoord(coord);
            punto.add(elpunto);

        }
        return punto;
    }

    public GeocodingResult[] getGeoResult() {
        return this.results;
    }

    public DirectionsResult getDirectionsResult() {
        return this.dresult;
    }

    public double getCoordLat() {
        return this.results[0].geometry.location.lat;
    }

    public double getCoordLng() {
        return this.results[0].geometry.location.lng;
    }

}
