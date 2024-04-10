package solucioInformatica;

import processing.core.PApplet;
import processing.core.PGraphics;
import processing.core.PVector;

import static processing.core.PConstants.TWO_PI;

public class StarOrnament extends Ornament {

    PVector[] points;
    int numTips;
    float InternRadius;
    float stepAng;

    public StarOrnament(float x, float y, float m, int c, int ri, int np) {

        super(x, y, m, c);

        points = new PVector[np];
        this.numTips = np;
        this.InternRadius = ri;
        this.stepAng = TWO_PI / np;

        calculatePoints();
    }

    public void calculatePoints(){
        for(int i = 0; i< points.length; i++){
            float r = (i%2==0) ? this.size : this.InternRadius;
            float xi = (float)(this.x + r*Math.cos(stepAng*i));
            float yi = (float)(this.y + r*Math.sin(stepAng*i));
            points[i] = new PVector(xi, yi);
        }
    }

    public void setPosition(float x, float y){
        this.x = x; this.y = y;
        calculatePoints();
    }

    public boolean mouseOver(PApplet p5){
        p5.println(p5.dist(this.x, this.y, p5.mouseX, p5.mouseY));
        return p5.dist(this.x, this.y, p5.mouseX, p5.mouseY) < this.size;
    }

    public void display(PApplet p5){
        p5.pushStyle();
        p5.fill(this.color);
        p5.beginShape();
        for(int i = 0; i< points.length; i++) {
            p5.vertex(this.points[i].x, this.points[i].y);
        }
        p5.endShape(p5.CLOSE);
        p5.popStyle();
    }

    public void display(PGraphics p5){
        p5.pushStyle();
        p5.fill(this.color);
        p5.beginShape();
        for(int i = 0; i< points.length; i++) {
            p5.vertex(this.points[i].x, this.points[i].y);
        }
        p5.endShape(p5.CLOSE);
        p5.popStyle();
    }
}
