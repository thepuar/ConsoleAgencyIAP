/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.consoleagency;

import com.google.maps.model.DirectionsResult;
import com.google.maps.model.DirectionsStep;
import com.google.maps.model.GeocodingResult;
import com.mycompany.consoleagency.model.Pedido;
import com.mycompany.consoleagency.services.google.AppMaps;
import com.mycompany.consoleagency.services.gva.GvaApi;
import com.mycompany.consoleagency.services.rabbit.SimpleRabbitMQCallBack;
import com.mycompany.consoleagency.util.CoordinateConversion;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author thepu
 */
public class App {

    public static void main(String[] args) {
        Menu elmenu = new Menu();
        int opcion = elmenu.menuInicio();
        switch (opcion) {
            case 1:
                Aplicacion();
                break;
            case 2:
                MiMaps();
                break;
            case 0:
                System.exit(0);
                break;
        }
    }

    public static void Aplicacion() {
        EmpresaLogistica empresa = new EmpresaLogistica();
    }

    public static void MiMaps() {
        Menu elmenu = new Menu();
        int opc = elmenu.menuGoogle();
        AppMaps gmaps = new AppMaps();
        GvaApi gva = new GvaApi();
        switch (opc) {
            case 1:
                String direccion = elmenu.menuDireccion();
                gmaps.getCoords(direccion);
                System.out.println("Coordenadas Lat: " + gmaps.getCoordLat() + ", " + gmaps.getCoordLng());
                CoordinateConversion coordconv = new CoordinateConversion();
                System.out.println("DIRECCIONUTM " + coordconv.latLon2UTM(gmaps.getCoordLat(), gmaps.getCoordLng()));
                Double lat = gmaps.getCoordLat()*1000000;
                Double lng =  gmaps.getCoordLng()* 1000000;
                gva.getEstado(lat.longValue(),lng.longValue());
                break;
            case 2:
                String direccionOrigen = elmenu.menuDireccion();
                String direccionDestino = elmenu.menuDireccion();
                gmaps.getRuta(direccionOrigen, direccionDestino);
                DirectionsResult dresult = gmaps.getDirectionsResult();
                System.out.println("Puntos intermedios : " + dresult.routes[0].legs[0].steps.length);
                DirectionsStep[] steps = dresult.routes[0].legs[0].steps;
                long distanceTotal = 0;
                long timeTotal = 0;

                for (int i = 0; i < steps.length; i++) {
                    long daux = steps[i].distance.inMeters;
                    long time = steps[i].duration.inSeconds;
                    double timeh = (double) time / (double) 3600;
                    double distkm = (double) daux / (double) 1000;
                    System.out.println("DIRECCION: " + gmaps.getAddres(steps[i].startLocation.lat, steps[i].startLocation.lng));
                    System.out.println("Distancia: " + i + ": " + daux + " metros / Duracion: " + time + " seg / Velocidad: " + distkm / timeh + "km/h");
                    gva.getEstado((long) steps[i].startLocation.lat * 1000000, (long) steps[i].startLocation.lng * 1000000);

                    distanceTotal += daux;
                    timeTotal += time;
                }
                System.out.println("Distancia total " + distanceTotal + " - Tiempo total " + timeTotal);
                break;
               
            case 3: 
                 gva.getEstado(-2,-2);
                break;
        }
    }
}
