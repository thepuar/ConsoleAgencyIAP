/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.consoleagency.util;

import com.google.maps.model.LatLng;
import java.util.List;

/**
 *
 * @author thepu
 */
public class Boundary {

    private final Point[] points; // Points making up the boundary

    public Boundary(List<LatLng> puntos) {
        points = new Point[puntos.size()];
        for (int i = 0; i < points.length; i++) {
            points[i] = new Point(puntos.get(i).lat, puntos.get(i).lng);
        }

    }

    
    public boolean contains(Point test) {
        int i;
        int j;
        boolean result = false;
        for (i = 0, j = points.length - 1; i < points.length; j = i++) {
            if ((points[i].y > test.y) != (points[j].y > test.y)
                    && (test.x < (points[j].x - points[i].x) * (test.y - points[i].y) / (points[j].y - points[i].y) + points[i].x)) {
                result = !result;
            }
        }
        return result;
    }

}
