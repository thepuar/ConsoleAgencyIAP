/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.consoleagency.services;

import com.mycompany.consoleagency.App;
import com.mycompany.consoleagency.model.EstadoPedido;
import com.mycompany.consoleagency.model.GVARespuesta;
import com.mycompany.consoleagency.model.Pedido;
import java.util.ArrayList;
import java.util.List;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/**
 *
 * @author thepu
 */
public class Montador {

    String sJson;
    JSONArray ja;
    JSONObject jo;

    public Montador() {

    }

    public Pedido getPedido(String cadena) {
        Pedido elpedido;
        this.sJson = cadena;
        try{
        this.jo = new JSONObject(this.sJson);
        }catch(JSONException je){
            return null;
        }
        elpedido = new Pedido();

        elpedido.setId(jo.getInt("id"));
//        elpedido.setEmpresa(jo.getString("empresa"));
        elpedido.setFecha(jo.getString("fechaEntrega"));
        elpedido.setVolumen(jo.getInt("volumen"));
        elpedido.setDireccionalmacen(jo.getString("dirAlmacen"));
        elpedido.setDireccioncliente(jo.getString("dirCliente"));
        return elpedido;
    }

    //Le pasamos el json del trafico de valencia y crea todos los objetos de la respuesta
    public List<GVARespuesta> getGvaRespuesta(String cadena) {
        List<GVARespuesta> lista = new ArrayList<>();
        GVARespuesta respuesta = new GVARespuesta();
        this.ja = new JSONArray(cadena);
        String titulo = "";
        String mensaje = "";
        String bbox = "";
        for (int i = 0; i < ja.length(); i++) {
            titulo = ja.getJSONObject(i).getString("titulo");
            mensaje = ja.getJSONObject(i).getString("mensaje");
            bbox = ja.getJSONObject(i).getString("bbox");
            lista.add(new GVARespuesta(titulo, mensaje, bbox));

        }

        return lista;
    }
    
    public EstadoPedido getEstadoPedido(String json){
        EstadoPedido estado = new EstadoPedido();
        JSONObject jsono = new JSONObject(json);
        estado.setNombrecamion(jsono.getString("nombre"));
        estado.setEstado(jsono.getString("estado"));
        estado.setPedido(jsono.getInt("pedido"));
        estado.setPunto(jsono.getInt("punto"));
        
        return estado;
    }

}
