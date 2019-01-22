/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.consoleagency;

import java.util.Scanner;

/**
 *
 * @author thepu
 */
public class Menu {

    Scanner teclado = new Scanner(System.in);

    public Menu() {

    }

    public int menuInicio() {
        int opc = -1;
        while (opc == -1) {
            System.out.println("Elige una opción:");
            System.out.println("1.- Arrancar la aplicación de transporte");
            System.out.println("2.- GoogleMaps API & GvaAPI");
            opc = teclado.nextInt();
            if(comprobarRango(opc,1,2))
                return opc;
            else opc = -1;
        }
        return -1;
    }
    
    public int menuGoogle(){
         int opc = -1;
        while (opc == -1) {
            System.out.println("Elige una opción:");
            System.out.println("1.- Coordenadas de una dirección");
            System.out.println("2.- Crear una ruta");
            System.out.println("3.- Trafico en Valencia");
            
            opc = teclado.nextInt();
            if(comprobarRango(opc,1,3))
                return opc;
            else opc = -1;
        }
        return -1;
        
        
    }
    
  public String menuDireccion(){
      teclado.nextLine();
      System.out.println("Introduce una dirección:");
      return teclado.nextLine();
  }

    public boolean comprobarRango(int opcion, int valuemin, int valuemax) {
        if (opcion >= valuemin && opcion <= valuemax) {
            return true;
        }
        return false;
    }

}
