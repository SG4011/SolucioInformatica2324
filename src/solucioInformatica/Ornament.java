package solucioInformatica;

import processing.core.PApplet;

public class Ornament {

    float x, y;
    float mida;
    int color;

    public Ornament(float x, float y, float m, int c){
        this.x = x; this.y = y;
        this.mida = m;
        this.color = c;
    }

    public void setPosicio(float x, float y){
        this.x = x; this.y = y;
    }

    public void setMida(float m){
        this.mida = m;
    }

    public void setColor(int c){
        this.color = c;
    }

    public boolean mouseOver(PApplet p5){
        return p5.mouseX >= this.x && p5.mouseX <= (this.x + this.mida) &&
                p5.mouseY >= this.y && p5.mouseY <= (this.y +  this.mida);
    }

    public void display(PApplet p5){
        p5.pushStyle();
        p5.fill(this.color);
        p5.rect(this.x, this.y, this.mida, this.mida);
        p5.popStyle();
    }
}
