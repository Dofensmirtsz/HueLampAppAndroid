package com.avans.huelampapp.util;

import com.avans.huelampapp.data.model.Light;

import java.util.ArrayList;

import io.fabianterhorst.isometric.Color;
import io.fabianterhorst.isometric.IsometricView;
import io.fabianterhorst.isometric.Point;
import io.fabianterhorst.isometric.Shape;
import io.fabianterhorst.isometric.shapes.Octahedron;
import io.fabianterhorst.isometric.shapes.Prism;
import io.fabianterhorst.isometric.shapes.Pyramid;

public final class HueUtil {

    //todo clean up code to use the LIGHTBULB array
    public static final Point LIGHTBULB[] = new Point[]{
            new Point(0, 1, 0), //base
            new Point(0, 1, 0),
            new Point(0, 1, 0),
            new Point(0, 1, 0),
            new Point(0, 1, 0),
            new Point(0, 1, 0),
            new Point(0, 1, 0)
    };

    public static int getColor(Light light) {
        //hue and saturation are null when the light type is LUX (white only)
        float[] color = new float[]{
                light.getState().getHue() != null ? (light.getState().getHue() / 65536.0f) * 360.0f : 0.0f,
                light.getState().getSaturation() != null ? light.getState().getSaturation() / 254.0f : 0.0f,
                light.getState().getBrightness() != null ? light.getState().getBrightness() / 254.0f : 254.0f
        };
        return android.graphics.Color.HSVToColor(color);
    }

    public static void createIsometricLight(IsometricView isometricView, int c, boolean status) {
        io.fabianterhorst.isometric.Color color = getColor(c);

        Pyramid base = new Pyramid(new Point(1, 1, 0));
        Prism bulb = new Prism(new Point(1, 1, 1));

        isometricView.add(base.scale(new Point(1, 1, 0), 0.6, 0.6,0.3), new Color(200, 200, 200));
        isometricView.add(bulb.translate(0,0,-0.25), color);

        if (status) {
            ArrayList<Shape> glow = new ArrayList<>();
            glow.add(new Octahedron(new Point(1,1,2)).scale(new Point(1.5,1.5,2.2), 0.5)); //top
            glow.add(new Octahedron(new Point(1,2,1)).scale(new Point(1.5,2.5,1), 0.5)); //back
            glow.add(new Octahedron(new Point(1,0,1)).scale(new Point(1.5,0.5,1), 0.5)); //front
            glow.add(new Octahedron(new Point(0,1,1)).scale(new Point(0.5,1.5,1), 0.5)); //right
            glow.add(new Octahedron(new Point(2,1,1)).scale(new Point(2.5,1.5,1), 0.5)); //left

            for(Shape shape : glow) {
                isometricView.add(shape, color);
            }
        }
    }

    public static Color getColor(int color) {
        int r = android.graphics.Color.red(color);
        int g = android.graphics.Color.green(color);
        int b = android.graphics.Color.blue(color);
        return new Color(r, g, b);
    }
}
