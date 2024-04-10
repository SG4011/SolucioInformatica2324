package solucioInformatica;

import processing.core.PApplet;
import processing.core.PGraphics;

public class Ornament {

    float x, y;
    float size;
    int color;

    public Ornament(float x, float y, float m, int c){
        this.x = x; this.y = y;
        this.size = m;
        this.color = c;
    }

    public void setPosition(float x, float y){
        this.x = x; this.y = y;
    }

    public void setSize(float m){
        this.size = m;
    }

    public void setColor(int c){
        this.color = c;
    }

    public boolean mouseOver(PApplet p5){
        return p5.mouseX >= this.x && p5.mouseX <= (this.x + this.size) &&
                p5.mouseY >= this.y && p5.mouseY <= (this.y +  this.size);
    }

    public void display(PApplet p5){
        p5.pushStyle();
        p5.fill(this.color);
        p5.rect(this.x, this.y, this.size, this.size);
        p5.popStyle();
    }

    public void display(PGraphics p5){
        p5.pushStyle();
        p5.stroke(0);
        p5.strokeWeight(2);
        p5.fill(this.color);
        p5.rect(this.x, this.y, this.size, this.size);
        p5.popStyle();
    }
}
