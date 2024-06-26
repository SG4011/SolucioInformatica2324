package solucioInformatica;

import processing.core.PApplet;
import processing.core.PGraphics;

public class RoundOrnament extends Ornament {

    public RoundOrnament(float x, float y, float m, int c) {
        super(x, y, m, c);
    }

    public boolean mouseOver(PApplet p5){
        return p5.dist(this.x, this.y, p5.mouseX, p5.mouseY)< (this.size /2);
    }

    public void display(PApplet p5){
        p5.pushStyle();
        p5.fill(this.color);
        p5.circle(this.x, this.y, this.size *2);
        p5.popStyle();
    }

    public void display(PGraphics p5){
        p5.pushStyle();
        p5.fill(this.color);
        p5.circle(this.x, this.y, this.size *2);
        p5.popStyle();
    }
}
