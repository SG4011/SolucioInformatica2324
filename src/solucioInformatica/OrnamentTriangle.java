package solucioInformatica;

import processing.core.PApplet;
import processing.core.PGraphics;
import processing.core.PVector;

import static processing.core.PConstants.TWO_PI;

public class OrnamentTriangle extends Ornament {

    PVector[] punts;
    float stepAng;

    public OrnamentTriangle(float x, float y, float m, int c) {

        super(x, y, m, c);

        this.punts = new PVector[3];
        this.stepAng =  TWO_PI / 3;
        calculaPunts();
    }

    public void calculaPunts(){
        for(int i=0; i<this.punts.length; i++){
            float xi = (float)(this.x + this.mida*Math.cos(this.stepAng*i));
            float yi = (float)(this.y + this.mida*Math.sin(this.stepAng*i));
            this.punts[i] = new PVector(xi, yi);
        }
    }

    public void setPosicio(float x, float y){
        this.x = x; this.y = y;
        calculaPunts();
    }

    public boolean mouseOver(PApplet p5){
        return p5.dist(this.x, this.y, p5.mouseX, p5.mouseY) < this.mida;
    }

    public void display(PApplet p5){
        p5.pushStyle();
        p5.fill(this.color);
        p5.beginShape();
        for(int i=0; i< punts.length; i++) {
            p5.vertex(this.punts[i].x, this.punts[i].y);
        }
        p5.endShape(p5.CLOSE);
        p5.popStyle();
    }

    public void display(PGraphics p5){
        p5.pushStyle();
        p5.fill(this.color);
        p5.beginShape();
        for(int i=0; i< punts.length; i++) {
            p5.vertex(this.punts[i].x, this.punts[i].y);
        }
        p5.endShape(p5.CLOSE);
        p5.popStyle();
    }
}
