package solucioInformatica;

import processing.core.PApplet;

public class Colors {

    private int[] colors;

    public Colors(PApplet p5){
        this.setColors(p5);
    }

    // Establece colores de la App
    void setColors(PApplet p5){
        this.colors = new int[5];
        this.colors[0] = p5.color(0xFFE27396);
        this.colors[1] = p5.color(0xFFEA9AB2);
        this.colors[2] = p5.color(0xFFEFCFE3);
        this.colors[3] = p5.color(0xFFEAF2D7);
        this.colors[4] = p5.color(0xFFB3DEE2);
    }

    // Getter del número de colores
    public int getNumColors(){
        return this.colors.length;
    }

    // Getter del color primario
    public int getFirstColor(){
        return  this.colors[0];
    }

    // Getter del color secundario
    public int getSecondColor(){
        return  this.colors[1];
    }

    // Getter del color terciario
    public int getThirdColor(){
        return  this.colors[2];
    }

    // Getter del color i-ésimo
    public int getColorAt(int i){
        return this.colors[i];
    }


    // Dibuja paleta de colores
    public void displayColors(PApplet p5, float x, float y, float w){
        p5.pushStyle();
        //Leyenda
        p5.fill(0); p5.textAlign(p5.LEFT); p5.textSize(36);
        p5.text("Colors:", x, y-10);

        // Paleta de colores
        float wc = w / getNumColors();
        for(int i=0; i<getNumColors(); i++){
            p5.fill(getColorAt(i)); p5.stroke(0); p5.strokeWeight(3);
            p5.rect(x + i*wc, y, wc, wc);
        }
        p5.popStyle();
    }
}
