/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.consoleagency.services;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.google.maps.model.LatLng;
import com.mycompany.consoleagency.model.GVARespuesta;
import com.mycompany.consoleagency.model.Pedido;
import com.mycompany.consoleagency.model.PedidoCamion;
import com.mycompany.consoleagency.model.PuntoRuta;
import com.mycompany.consoleagency.model.PuntoRutaGMaps;
import com.mycompany.consoleagency.services.google.AppMaps;
import com.mycompany.consoleagency.services.gva.GvaApi;
import com.mycompany.consoleagency.services.mqtt.ProductorMQTT;
import com.mycompany.consoleagency.services.mqtt.SimpleMqttCallBack;
import com.sun.org.apache.bcel.internal.generic.AALOAD;
import java.lang.reflect.Type;
import java.util.List;
import org.eclipse.paho.client.mqttv3.MqttClient;
import org.eclipse.paho.client.mqttv3.MqttException;
import org.eclipse.paho.client.mqttv3.MqttMessage;

/**
 *
 * @author thepu
 */
public class ControlCamiones {

    private int id = 0;
    ProductorMQTT prodmq;

    public ControlCamiones() {
         this.prodmq = new ProductorMQTT();

        //Enviar mensaje 
    }

    public void addOrder(Pedido pedido) {

        //Crear los puntos intermedios
        PedidoCamion pcamion = new PedidoCamion(id++, pedido.getVolumen());
        AppMaps maps = new AppMaps();
        List<PuntoRutaGMaps> puntos = maps.getRutaPuntos(pedido.getDireccionalmacen(), pedido.getDireccioncliente());
        GvaApi gva = new GvaApi();
        String jsongva = gva.getEstado(-2, -2);
        Montador montador = new Montador();
        List<GVARespuesta> gvaresponse = montador.getGvaRespuesta(jsongva);

        for (int i = 0; i < puntos.size(); i++) {
            int id = i;
            double[] coord = {puntos.get(i).getCoord().lat, puntos.get(i).getCoord().lng};

            //CALCULAR EL ESTADO
            int estado = 0;

            PuntoRuta pr = new PuntoRuta(id, coord, puntos.get(i).getMetros(), estado);
            pcamion.addPunto(pr);
        }
        System.out.println("Tamaño de la orden "+pcamion.getPuntos().size());
        
        //JSON
        Gson gson = new Gson();
        Type type = new TypeToken<PedidoCamion>() {}.getType();
        String json = gson.toJson(pcamion,type);
        //json = json.replace("\"","\\\"");
        //System.out.println("JSON: "+json);
        
        //Publicar orden
        System.out.println("Publicando orden en MQTT - camiones");
        prodmq.publicarOrdenJson(json);
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public ProductorMQTT getProdmq() {
        return prodmq;
    }

    public void setProdmq(ProductorMQTT prodmq) {
        this.prodmq = prodmq;
    }
    

}
