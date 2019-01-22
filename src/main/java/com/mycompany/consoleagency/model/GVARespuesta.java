/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.consoleagency.model;

import com.google.maps.model.LatLng;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author thepu
 */
public class GVARespuesta {

    String titulo;
    String mensaje;
    String estado;
    List<LatLng> polygon;

    public GVARespuesta(String titulo, String mensaje, List<LatLng> polygon) {
        this.titulo = titulo;

        this.mensaje = mensaje;
        this.polygon = polygon;
    }

    public GVARespuesta(String titulo, String mensaje, String bbox) {
        this.titulo = titulo;
        this.mensaje = mensaje;
        if (this.titulo.equals("SIN_RESULTADO")) {
            this.estado = "Fluido";
        } else if (this.titulo.equals("TRÁFICO")) {
            //Capturandoe el estado del trafico
            String[] estadotoken = this.mensaje.split(" ");
            String estadoaux = estadotoken[estadotoken.length - 1];
            estadoaux = estadoaux.replace("(", mensaje);
            estadoaux = estadoaux.replace(")", mensaje);

            //Creando lat y long del poligono
            bbox = bbox.replace("POLYGON((", "");
            bbox = bbox.replace("))", "");
            bbox = bbox.replace(",", " ");
            String[] coords = bbox.split(" ");
            this.polygon = new ArrayList<>();
            for (int i = 0; i < coords.length; i = i + 2) {
                Double lng = Double.parseDouble(coords[0].trim());
                Double lat = Double.parseDouble(coords[1].trim());
                this.polygon.add(new LatLng(lat, lng));
            }
        }
        else{
            this.estado = "Fluido";
        }

    }

    public GVARespuesta() {

    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getMensaje() {
        return mensaje;
    }

    public void setMensaje(String mensaje) {
        this.mensaje = mensaje;
    }

    public List<LatLng> getPolygon() {
        return polygon;
    }

    public void setPolygon(List<LatLng> polygon) {
        this.polygon = polygon;
    }

}
