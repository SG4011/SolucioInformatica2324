package solucioInformatica;

import processing.core.PApplet;
import processing.core.PGraphics;
import processing.core.PVector;

import static processing.core.PConstants.TWO_PI;

public class OrnamentEstrella extends Ornament {

    PVector[] punts;
    int numPuntes;
    float radiIntern;
    float stepAng;

    public OrnamentEstrella(float x, float y, float m, int c, int ri, int np) {

        super(x, y, m, c);

        punts = new PVector[np];
        this.numPuntes = np;
        this.radiIntern = ri;
        this.stepAng = TWO_PI / np;

        calculaPunts();
    }

    public void calculaPunts(){
        for(int i=0; i<punts.length; i++){
            float r = (i%2==0) ? this.mida : this.radiIntern;
            float xi = (float)(this.x + r*Math.cos(stepAng*i));
            float yi = (float)(this.y + r*Math.sin(stepAng*i));
            punts[i] = new PVector(xi, yi);
        }
    }

    public void setPosicio(float x, float y){
        this.x = x; this.y = y;
        calculaPunts();
    }

    public boolean mouseOver(PApplet p5){
        p5.println(p5.dist(this.x, this.y, p5.mouseX, p5.mouseY));
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
