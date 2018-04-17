
package Grafos.desenho.color;

import java.awt.Color;

public abstract class ColorScale {
     public abstract String getName();
     
     public Color getColor(int index){
         if (index > 255) index = 255;
         if (index <= 0) index = 0;
         return colors[index];
     }

    protected java.awt.Color[] colors;
}
