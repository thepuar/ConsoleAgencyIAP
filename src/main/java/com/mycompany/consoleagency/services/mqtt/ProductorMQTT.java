/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.consoleagency.services.mqtt;

import com.mycompany.consoleagency.model.EstadoPedido;
import com.mycompany.consoleagency.model.PedidoCamion;
import java.util.ArrayList;
import java.util.List;
import org.eclipse.paho.client.mqttv3.MqttClient;
import org.eclipse.paho.client.mqttv3.MqttException;
import org.eclipse.paho.client.mqttv3.MqttMessage;

/**
 *
 * @author thepu
 */
public class ProductorMQTT {

    MqttClient client;
     List<EstadoPedido> estados = new ArrayList<>();

    public ProductorMQTT() {
        try {
            System.out.println("Conectando a cola MQTT");
            client = new MqttClient("tcp://192.168.1.138:1883", MqttClient.generateClientId());
            client.setCallback(new SimpleMqttCallBack(estados));
            client.connect();
            client.subscribe("respcamiones");

        } catch (MqttException mq) {
            mq.printStackTrace();
        }

    }

    //Enviar json a la cola camiones
    public void publicarOrden(PedidoCamion pedido) {
        try {
            MqttMessage message = new MqttMessage();
            message.setPayload("Hello world from Java".getBytes());
            client.publish("camiones", message);
        } catch (MqttException mq) {
            mq.printStackTrace();
        }

    }

    public void publicarOrdenJson(String json) {
        try {
            MqttMessage message = new MqttMessage();
            message.setPayload(json.getBytes());
            client.publish("camiones", message);
        } catch (MqttException mq) {
            mq.printStackTrace();
        }
    }

    public MqttClient getClient() {
        return client;
    }

    public void setClient(MqttClient client) {
        this.client = client;
    }

    public List<EstadoPedido> getEstados() {
        return estados;
    }

    public void setEstados(List<EstadoPedido> estados) {
        this.estados = estados;
    }

    
}
