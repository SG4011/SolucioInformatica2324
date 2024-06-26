package solucioInformatica;

import processing.core.PApplet;
import processing.core.PFont;

import static solucioInformatica.Sizes.*;


public class Fonts {

    // Array de tipografias
    PFont[] fonts;

    // Constructor de les Fonts de l'App
    public Fonts(PApplet p5){
        this.setFonts(p5);
    }

    // Estableix les fonts de l'App
    public void setFonts(PApplet p5){
        this.fonts = new PFont[5];
        this.fonts[0] = p5.createFont("data/KGRedHands.ttf", TitleSize);
        this.fonts[1] = p5.createFont("data/Super Fresh.otf", SubtitleSize);
        this.fonts[2] = p5.createFont("data/Zector.ttf", ParagraphSize);
        this.fonts[3] = p5.createFont("data/Ohyou.otf", TitleSize);
        this.fonts[4] = p5.createFont("data/Super Sunday Personal Use.ttf", TitleSize);

    }

    // Getter del número de fuentes
    public int getNumFonts(){
        return this.fonts.length;
    }

    // Getter de la font primaria
    public PFont getFirstFont(){
        return  this.fonts[0];
    }

    // Getter del font secundaria
    public PFont getSecondFont(){
        return  this.fonts[1];
    }

    // Getter del la font terciaria
    public PFont getThirdFont(){
        return  this.fonts[2];
    }

    // Getter de la font i-èssima
    public PFont getFontAt(int i){
        return this.fonts[i];
    }


    // Dibuja las fuentes de la App
    public void displayFonts(PApplet p5, float x, float y, float h){
        p5.pushStyle();
        for(int i=0; i<getNumFonts(); i++){
            p5.fill(0); p5.stroke(0); p5.strokeWeight(3);
            p5.textFont(getFontAt(i));
            p5.text("Tipografia "+i, x, y + i*h);
        }
        p5.popStyle();
    }
}
