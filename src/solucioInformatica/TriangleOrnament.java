package solucioInformatica;

import processing.core.PApplet;
import processing.core.PGraphics;
import processing.core.PVector;

import static processing.core.PConstants.TWO_PI;

public class TriangleOrnament extends Ornament {

    PVector[] points;
    float stepAng;

    public TriangleOrnament(float x, float y, float m, int c) {

        super(x, y, m, c);

        this.points = new PVector[3];
        this.stepAng =  TWO_PI / 3;
        calculatePoints();
    }

    public void calculatePoints(){
        for(int i = 0; i<this.points.length; i++){
            float xi = (float)(this.x + this.size *Math.cos(this.stepAng*i));
            float yi = (float)(this.y + this.size *Math.sin(this.stepAng*i));
            this.points[i] = new PVector(xi, yi);
        }
    }

    public void setPosition(float x, float y){
        this.x = x; this.y = y;
        calculatePoints();
    }

    public boolean mouseOver(PApplet p5){
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
