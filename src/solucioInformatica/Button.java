package solucioInformatica;

import processing.core.PApplet;
public class Button {
    // Propietats d'un botó:

    float x, y, w, h;  // Posición (x, y) y dimensiones (w, h)
    int fillColor, strokeColor; // Colores del botón (fill / stroke).
    int fillColorOn, fillColorOut;  // Colores del botón (activo / inactivo).
    String textButton;  // Texto
    boolean activated;  // Estado del botón (activo / inactivo).

    // Constructor
    public Button(PApplet p5, String text, float x, float y, float w, float h){
        this.textButton = text;
        this.x = x;
        this.y = y;
        this.w = w;
        this.h = h;
        this.activated = true;
    }

    // Setters

    public void setActivated(boolean b){
        this.activated = b;
    }

    public void setTextButton(String t){ this.textButton = t; }

    public void setColors(int cFill, int cStroke, int cSobre, int cDesactivated){

        this.fillColor = cFill;
        this.strokeColor = cStroke;
        this.fillColorOn = cSobre;
        this.fillColorOut = cDesactivated;
    }

    // Dibuja el botón
    public void display(PApplet p5){
        p5.pushStyle();
        if(!activated){
            p5.fill(fillColorOut);  // Color desabilitado
        }
        else if(mouseOnButton(p5)){
            p5.fill(fillColorOn);      // Color cuando ratón encima
        }
        else{
            p5.fill(fillColor);          // Color activo pero ratón fuera
        }
        p5.stroke(strokeColor); p5.strokeWeight(2);        //Color y grosor del contorno
        p5.rect(this.x, this.y, this.w, this.h, 10);    // Rectángulo del botón

        // Text (color, alineación y medidas)
        p5.fill(0); p5.textAlign(p5.CENTER); p5.textSize(20);
        p5.text(textButton, this.x + this.w/2, this.y + this.h/2 + 10);
        p5.popStyle();
    }

    // Indica si el cursor está sobre el botón
    public boolean mouseOnButton(PApplet p5){
        return (p5.mouseX >= this.x) && (p5.mouseX <= this.x + this.w) &&
                (p5.mouseY >= this.y) && (p5.mouseY <= this.y + this.h);
    }

    // Indica si es necesario poner el cursor en HAND
    public boolean updateCursor(PApplet p5){
        return mouseOnButton(p5) && activated;
    }
}
