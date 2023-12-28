package solucioInformatica;

import processing.core.PApplet;

public class Colores {

    private int[] colors;

    public Colores(PApplet p5){
        this.setColors(p5);
    }

    // Estableix colors de l'App
    void setColors(PApplet p5){
        this.colors = new int[5];
        this.colors[0] = p5.color(0xFFE27396); //0xFF717C89 0xFFF4E04D 0xFF99D5C9 0xFFE8D6CB 0xFFEDD2E0
        this.colors[1] = p5.color(0xFFEA9AB2); //0xFF8AA2A9 0xFFF2ED6F 0xFF6C969D 0xFFD0ADA7 0xFFEDBBB4
        this.colors[2] = p5.color(0xFFEFCFE3); //0xFF90BAAD 0xFFCEE397 0xFF645E9D 0xFFAD6A6C 0xFFDBABBE
        this.colors[3] = p5.color(0xFFEAF2D7); //0xFFA1E5AB 0xFF8DB1AB 0xFF392B58 0xFF5D2E46 0xFFBAA1A7
        this.colors[4] = p5.color(0xFFB3DEE2); //0xFFADF6B1 0xFF587792 0xFF2D0320 0xFFB58DB6 0xFF797B84
    }

    // Getter del número de colors
    public int getNumColors(){
        return this.colors.length;
    }

    // Getter del color primari
    public int getFirstColor(){
        return  this.colors[0];
    }

    // Getter del color secundari
    public int getSecondColor(){
        return  this.colors[1];
    }

    // Getter del color terciari
    public int getThirdColor(){
        return  this.colors[2];
    }

    // Getter del color i-èssim
    public int getColorAt(int i){
        return this.colors[i];
    }


    // Dibuixa paleta de colors
    public void displayColors(PApplet p5, float x, float y, float w){
        p5.pushStyle();
        //Llegenda
        p5.fill(0); p5.textAlign(p5.LEFT); p5.textSize(36);
        p5.text("Colors:", x, y-10);

        // Paleta de colors
        float wc = w / getNumColors();
        for(int i=0; i<getNumColors(); i++){
            p5.fill(getColorAt(i)); p5.stroke(0); p5.strokeWeight(3);
            p5.rect(x + i*wc, y, wc, wc);
        }
        p5.popStyle();
    }
}
