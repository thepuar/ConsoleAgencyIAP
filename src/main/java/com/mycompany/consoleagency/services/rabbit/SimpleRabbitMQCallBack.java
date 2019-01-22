/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.consoleagency.services.rabbit;

import com.mycompany.consoleagency.model.Pedido;
import com.mycompany.consoleagency.services.Montador;
import com.rabbitmq.client.AMQP;
import com.rabbitmq.client.BuiltinExchangeType;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;
import com.rabbitmq.client.Consumer;
import com.rabbitmq.client.DefaultConsumer;
import com.rabbitmq.client.Envelope;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeoutException;

/**
 *
 * @author thepu
 */
public class SimpleRabbitMQCallBack {

    private final static String NOMBRE_EXCHANGE = "pedidos";
    private final static String NOMBRE_QUEUE_ESTADO = "estadopedidos";
    Channel channel;
    Connection connection;
    ConnectionFactory factory;
    List<Pedido> lista;

    public SimpleRabbitMQCallBack() {
        System.out.println("Conectando a cola RabbitMQ");
        lista = new ArrayList<>();
        factory = new ConnectionFactory();
        factory.setHost("192.168.1.138");
        try {
            connection = factory.newConnection();

            channel = connection.createChannel();
            channel.queueDeclare(NOMBRE_QUEUE_ESTADO,false,false,false,null);
            channel.exchangeDeclare(NOMBRE_EXCHANGE, BuiltinExchangeType.TOPIC);

            String COLA_CONSUME = channel.queueDeclare().getQueue();

            channel.queueBind(COLA_CONSUME, NOMBRE_EXCHANGE, "DHL");
            channel.queueBind(COLA_CONSUME, NOMBRE_EXCHANGE, "CORREOS");
            channel.basicConsume(COLA_CONSUME, true, consumer);

        } catch (TimeoutException toe) {
            toe.printStackTrace();
        } catch (IOException ioe) {
            ioe.printStackTrace();
        }

    }
    
    public void publishMessage( String mensaje){
        try{
        this.channel.basicPublish("", NOMBRE_QUEUE_ESTADO  , null, mensaje.getBytes());
        }catch(IOException io){io.printStackTrace();}
        
    }

    public List<Pedido> getLista() {
        return this.lista;

    }

    Consumer consumer = new DefaultConsumer(channel) {

        @Override
        public void handleDelivery(String consumerTag, Envelope envelope, AMQP.BasicProperties properties, byte[] body) throws IOException {
            String mensaje = new String(body, "UTF-8");
            Montador montador = new Montador();
            lista.add(montador.getPedido(mensaje));
            System.out.println("Se ha añadido un pedido a la lista de Rabbit");
        }
    };

}
