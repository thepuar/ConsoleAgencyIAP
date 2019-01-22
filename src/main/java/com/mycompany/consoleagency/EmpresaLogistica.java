/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.consoleagency;

import com.mycompany.consoleagency.model.EstadoPedido;
import com.mycompany.consoleagency.model.Pedido;
import com.mycompany.consoleagency.services.ControlCamiones;
import com.mycompany.consoleagency.services.Montador;
import com.mycompany.consoleagency.services.mqtt.SimpleMqttCallBack;
import com.mycompany.consoleagency.services.rabbit.SimpleRabbitMQCallBack;
import org.eclipse.paho.client.mqttv3.MqttClient;
import org.eclipse.paho.client.mqttv3.MqttException;
import org.eclipse.paho.client.mqttv3.MqttMessage;

/**
 *
 * @author thepu
 */
public class EmpresaLogistica {

    public EmpresaLogistica() {
        SimpleRabbitMQCallBack rm = new SimpleRabbitMQCallBack();
        ControlCamiones camiones = new ControlCamiones();
        while (true) {
            if (rm.getLista().size() > 0) {
                //Hay pedidos para procesar
                System.out.println("Se ha encontrado un pedido.");
                Pedido pedido = rm.getLista().remove(0);
                Montador montador = new Montador();
                camiones.addOrder(pedido);

            }
            try {
                Thread.sleep(2000);
            } catch (InterruptedException ie) {
                ie.printStackTrace();
            }
            if(camiones.getProdmq().getEstados().size()>0){
                //Hay respuestas de pedidos para procesar
                System.out.println("Se ha encontrado una respuesta.");
                EstadoPedido estado = camiones.getProdmq().getEstados().remove(0);
                System.out.println(estado.toString());
                rm.publishMessage( estado.toString());
            }
        }

    }

}
