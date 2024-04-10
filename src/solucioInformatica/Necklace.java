package solucioInformatica;

import processing.core.PApplet;

public class Necklace {

    float x, y, rH, rV;
    Ornament[] ornaments;
    int numOrnaments;
    int numMaxOrnaments;

    public Necklace(int nmax, float x, float y, float rH, float rV){
        this.numOrnaments = 0;
        this.numMaxOrnaments = nmax;
        this.ornaments = new Ornament[nmax];

        this.x = x; this.y = y;
        this.rH = rH; this.rV = rV;
    }

    public void resetOrnaments(){
        this.numOrnaments = 0;
        this.ornaments = new Ornament[this.numMaxOrnaments];
    }

    public void addOrnament(Ornament o){
        if(this.numOrnaments < this.numMaxOrnaments) {
            this.ornaments[this.numOrnaments] = o;
            this.numOrnaments++;
        }
    }

    public void deleteLastOrnament(){
        if(this.numOrnaments>0) {
            this.ornaments[this.numOrnaments] = null;
            this.numOrnaments--;
        }
    }

    public void display(PApplet p5){
        p5.pushStyle();
        p5.noFill(); p5.strokeWeight(3);
        p5.ellipse(this.x, this.y, 2*this.rH, 2*this.rV);
        for(int i=0; i<this.numOrnaments; i++){
            this.ornaments[i].display(p5);
        }

        p5.fill(0); p5.textAlign(p5.CENTER); p5.textSize(14);
        p5.text(this.numOrnaments+"/"+this.numMaxOrnaments, this.x, this.y - this.rH);
        p5.popStyle();
    }

    public void checkDragged(PApplet p5){
        for(int i=0; i<this.numOrnaments; i++){
            p5.println(this.ornaments[i].mouseOver(p5));
            if(this.ornaments[i].mouseOver(p5)){
                this.ornaments[i].setPosition(p5.mouseX, p5.mouseY);
                break;
            }
        }
    }

}
