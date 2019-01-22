/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.consoleagency.services.mqtt;

import com.mycompany.consoleagency.model.EstadoPedido;
import com.mycompany.consoleagency.model.PedidoCamion;
import com.mycompany.consoleagency.services.Montador;
import java.util.ArrayList;
import java.util.List;
import org.eclipse.paho.client.mqttv3.IMqttDeliveryToken;
import org.eclipse.paho.client.mqttv3.MqttCallback;
import org.eclipse.paho.client.mqttv3.MqttClient;
import org.eclipse.paho.client.mqttv3.MqttException;
import org.eclipse.paho.client.mqttv3.MqttMessage;

/**
 *
 * @author thepu
 */
public class SimpleMqttCallBack implements MqttCallback {
    List<EstadoPedido> milista ;

    public SimpleMqttCallBack(List<EstadoPedido> lista) {
        milista = lista;
    }

   
    

    
    public void connectionLost(Throwable throwable) {
        System.out.println("Connection to MQTT broker lost!");
    }

    public void messageArrived(String s, MqttMessage mqttMessage) throws Exception {
       // System.out.println("Message received:\n\t" + new String(mqttMessage.getPayload()));
        String payload = new String(mqttMessage.getPayload());
        Montador mont = new Montador();
        EstadoPedido estado = mont.getEstadoPedido(payload);
        milista.add(estado);
        
    }

    public void deliveryComplete(IMqttDeliveryToken iMqttDeliveryToken) {
        // not used in this example
    }

}
