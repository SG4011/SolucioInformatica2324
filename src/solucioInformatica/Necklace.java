package solucioInformatica;

import processing.core.PApplet;

public class Necklace {

    float x, y, rH, rV; // Dimensiones  y coordenadas del collar
    Ornament[] ornaments; // Array de ornamentos
    int numOrnaments; // Contador de ornamentos
    int numMaxOrnaments; // Número máximo de ornamentos por collar

    /**
     * Construtor de la clase Necklace
     * @param nmax Número máximo de ornamentos para un collar
     * @param x Coordenada X dónde se va a dibujar el collar
     * @param y Coordenada Y dónde se va a dibujar el collar
     * @param rH Radio horizontal del cordón del collar
     * @param rV Radio vertical del cordón del collar
     */
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

    // Añadir ornamentos

    /**
     * Función para añadir un ornamento al collar
     * @param o Ornamento en cuestión
     */
    public void addOrnament(Ornament o){
        if(this.numOrnaments < this.numMaxOrnaments) {
            this.ornaments[this.numOrnaments] = o;
            this.numOrnaments++;
        }
    }

    // Borrar el último ornamento insertado

    /**
     * Borrar el último ornamento creado
     */
    public void deleteLastOrnament(){
        if(this.numOrnaments>0) {
            this.ornaments[this.numOrnaments] = null;
            this.numOrnaments--;
        }
    }

    // Dibujar ornamento

    /**
     * Dibujar el ornamento
     * @param p5 Parámetro de la libreria Processing para dibujar
     */
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
    // Lee el movimiento del cursor
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
