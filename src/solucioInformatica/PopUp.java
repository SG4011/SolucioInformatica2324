package solucioInformatica;

import processing.core.PApplet;

public class PopUp {

    // Dimensions
    float x, y, w, h;

    // Propietats
    String title, message;

    public Button bAccept;
    float buttonW = 200, buttonH = 80;
    boolean visible = true;

    // Constructor

    public PopUp(PApplet p5, String title, String message, float x, float y, float w, float h, Colors appColors){
        this.title = title;
        this.message = message;
        this.x = x; this.y = y;
        this.w = w; this.h = h;
        this.bAccept = new Button(p5, "Aceptar", x + w/2 - buttonW/2,y + h - buttonH*1.5f, buttonW, buttonH);
        this.bAccept.setColors(appColors.getColorAt(1), appColors.getColorAt(2), appColors.getColorAt(3), 155);
    }

    //Setters

    public void setTexts(String title, String message){
        this.title = title;
        this.message = message;
    }

    public void setVisible(boolean b){
        this.visible = b;
        if(!this.visible){
            this.bAccept.setActivated(false);
        }
        else {
            this.bAccept.setActivated(true);
        }
    }

    // Dibuja el PopUp

    public void display(PApplet p5){

        if(this.visible){
            float b = 40;

            p5.pushStyle();

            // Rectángulo
            p5.stroke(0); p5.strokeWeight(10);p5.fill(0xFFB3DEE2);
            p5.rect(x, y, w, h, b/2);

            p5.line(x, y + 2*b , x+w, y + 2*b);

            // Título
            p5.fill(0); p5.textSize(38); p5.textAlign(p5.LEFT);
            p5.text(title, x + b, y + 1.4f*b);

            // Mensaje
            p5.fill(0); p5.textSize(24); p5.textAlign(p5.CENTER);
            p5.text(message, x + w/2, y + 4*b);

            // Botón aceptar
            bAccept.display(p5);
            p5.popStyle();
        }
    }


}
