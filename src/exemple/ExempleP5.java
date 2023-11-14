package exemple;

import processing.core.PApplet;

public class ExempleP5 extends PApplet {

    int c, w, z;

    public static void main(String[] args) {
        PApplet.main("exemple.ExempleP5", args);
    }

    public void settings(){
        size(800, 800, P2D);
        smooth(10);
    }

    public void setup(){
        c = color(255, 0, 255);
        w = color(0, 0, 100);
        z = color(12, 34, 120);
    }

    public void draw(){
        background(255, 255, 0);

        fill(c); strokeWeight(5);
        line(width/2, height/2, mouseX, mouseY);
        ellipse(mouseX, mouseY, 50, 50);


        fill(0); textSize(18);
        text("Exemple P5 + Intellij IDEA", 50, 50);
        if(mousePressed==true){
            fill(w); ellipse(width/2, height/2, 50, 50);
            ellipse(width/2+50, height/2, 50, 50);
            ellipse(width/2+25, height/2+100, 50, 200);

            fill(z); ellipse(width/2, height/2, 50, 50);
            ellipse(width/2+50, height/2, 50, 50);
            ellipse(width/2+25, height/2+100, 50, 200);
        }
    }


    // ******************* KEYBOARD interaction ***************************** //

    public void keyPressed(){
        println("KEY PRESSED");
    }

    // ******************* MOUSE interaction ***************************** //

    public void mousePressed(){
        println("MOUSE PRESSED");
    }

    public void mouseDragged(){
        println("MOUSE DRAGGED");
    }

    public void mouseReleased() {
        println("MOUSE RELEASED");
    }

}