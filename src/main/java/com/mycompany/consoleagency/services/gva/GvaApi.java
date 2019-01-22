/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.consoleagency.services.gva;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.ProtocolException;
import java.net.URL;

/**
 *
 * @author thepu
 */
public class GvaApi {

    String laurl = "http://mapas.valencia.es/lanzadera/gps/trafico/"; //{lat}/{lon};
    String userAgent = "Mozilla/5.0 (Windows NT 10.0; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/55.0.2883.87 Safari/537.36";
    String authorization = "Basic aW5mb3JtYXR1cHY6dHlBMGJxVWU=";
    HttpURLConnection con;

    public GvaApi() {

    }

    public String getEstado(long lat, long lng) {

        String concat=lat+"/"+lng;
        //String concat = "-2/-2";
        try {
            URL url = new URL(this.laurl + "" + concat);
            con = (HttpURLConnection) url.openConnection();
        } catch (IOException ioe) {
            ioe.printStackTrace();
        }

        try {

            con.setRequestMethod("GET");
        } catch (ProtocolException pe) {
            pe.printStackTrace();
        }
        con.setRequestProperty("authorization", this.authorization);
        con.setRequestProperty("user-agent", this.userAgent);

        //Procesar la respuesta
        String inputLine;
        StringBuffer response = new StringBuffer();

        try {
            BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream()));
            while ((inputLine = in.readLine()) != null) {
                response.append(inputLine);

            }
            in.close();
        } catch (IOException ioe) {
            ioe.printStackTrace();
        }
        System.out.println(response.toString());
        return response.toString();

    }
}
