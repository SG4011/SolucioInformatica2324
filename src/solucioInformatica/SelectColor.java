package solucioInformatica;

import processing.core.PApplet;

import java.awt.*;

public class SelectColor {

    float x, y, w, h;          // Posición y dimensiones
    int[] colors;            // Valores posibles
    int selectedValue;       // Valor Seleccionado

    boolean collapsed = true;  // Plegado / Desplegado
    boolean enabled;           // Habilitado / Deshabilitado

    float lineSpace = 15;      // Espacio entre líneas

    /**
     * Constructor de la clase SelectColor
     * @param p5 Parámetro de la librería Processing que permite dibujar
     * @param colors Colores para escoger el color de un ornamento
     * @param x Coordenada X dónde se va a dibujar el desplegable
     * @param y Coordenada Y dónde se va a dibujar el desplegable
     * @param w Anchura del desplegable
     * @param h Altura del desplegable
     */
    public SelectColor(PApplet p5, int[] colors, float x, float y, float w, float h){

        this.colors = colors;
        this.selectedValue = p5.color(255);
        this.x = x;
        this.y = y;
        this.w = w;
        this.h = h;
        this.enabled = true;
        this.collapsed = true;
    }

    public boolean isEnabled(){
        return  this.enabled;
    }

    public boolean isCollapsed(){
        return this.collapsed;
    }

    public int getSelectedValue(){
        return this.selectedValue;
    }

    public void display(PApplet p5){

        p5.pushStyle();
        p5.stroke(0xFFB3DEE2); p5.strokeWeight(2);
        p5.fill(selectedValue);
        p5.rect(x, y, w, h);

        p5.fill(255);
        p5.rect(x + w - 30, y, 30, h);

        p5.fill(0); p5.stroke(0);
        p5.triangle(x + w - 25, y+5, x + w - 15, y + 25, x + w - 5 , y+5);


        if(!this.collapsed){

            p5.fill(255); p5.stroke(0);
            p5.rect(x, y+h, w, (h + lineSpace)*colors.length);

            for(int i=0; i<colors.length; i++){
                p5.fill(colors[i]); p5.noStroke();
                p5.rect(x+4, y+4 + h + (h + lineSpace)*i - 2, w -8, h + lineSpace - 8);
            }
        }
        p5.popStyle();
    }

    public void setCollapsed(boolean b){
        this.collapsed = b;
    }

    public void toggle(){
        this.collapsed = !this.collapsed;
    }


    public void update(PApplet p5){
        int option = clickedOption(p5);
        selectedValue = colors[option];
    }

    // Indica si el cursor está sobre el select
    public boolean mouseOverSelect(PApplet p5){
        if(this.collapsed){
            return (p5.mouseX >= x) &&
                    (p5.mouseX <= x + w) &&
                    (p5.mouseY >= y) &&
                    (p5.mouseY <= y + h);
        }
        else {
            return (p5.mouseX>= x) &&
                    (p5.mouseX<= x + w) &&
                    (p5.mouseY>= y) &&
                    (p5.mouseY<= y + h + (h + lineSpace)*colors.length);
        }
    }

    public int clickedOption(PApplet p5){
        int i = (int) p5.map(p5.mouseY, y + h, y + h + (h + lineSpace)*colors.length,
                0, colors.length);
        return i;
    }

}
