package solucioInformatica;

import processing.core.PApplet;

import static solucioInformatica.Layout.*;

public class programaPrincipal extends PApplet {

    // Interfície Gràfica (Pantalles i components)
    GUI gui;

    public static void main(String[] args) {
        PApplet.main("solucioInformatica.programaPrincipal", args);
    }

    public void settings(){
        fullScreen();                       // Pantalla completa
        //size(1920, 1080);        // Pantalla HD
        smooth(10);
    }

    public void setup(){
        noStroke();                         // Sense bordes
        textAlign(CENTER); textSize(18);   // Alineació i mida del text
        gui = new GUI(this);                   // Constructor de la GUI
    }

    public void draw(){

        // Dibuixa la pantalla corresponent
        switch(gui.pantallaActual){
            case INICIAL:   gui.dibuixaPantallaInicial(this);
                break;

            case PRINCIPAL:     gui.dibuixaPantallaPrincipal(this);
                break;

            case COLECCIÓN:   gui.dibuixaPantallaColección(this);
                break;

            case EXPLORA:   gui.dibuixaPantallaExploración(this);
                break;

            case PERSONALIZA:   gui.dibuixaPantallaPersonalización(this);
                break;

            case INSTRUCCIONES:     gui.dibuixaPantallaAbout(this);
                break;
        }


    }

    // ******************* KEYBOARD interaction ***************************** //

    public void keyPressed(){
        if(key=='0'){
            gui.pantallaActual = GUI.PANTALLA.INICIAL;
        }
        else if(key=='1'){
            gui.pantallaActual = GUI.PANTALLA.PRINCIPAL;
        }
        else if(key=='2'){
            gui.pantallaActual = GUI.PANTALLA.COLECCIÓN;
        }
        else if(key=='3'){
            gui.pantallaActual = GUI.PANTALLA.EXPLORA;
        }
        else if(key=='4'){
            gui.pantallaActual = GUI.PANTALLA.PERSONALIZA;
        }
        else if(key=='5'){
            gui.pantallaActual = GUI.PANTALLA.INSTRUCCIONES;
        }

        if(gui.pantallaActual==GUI.PANTALLA.PERSONALIZA){
            if(key=='a'){
                int numRandom = floor(random(3));
                Ornament o;
                switch (numRandom){
                    case 0: o= new OrnamentCercle(width/2, height/2, 25, color(255, 0, 0)); break;
                    case 1: o = new OrnamentTriangle(width/2, height/2, 25, color(0, 255, 0)); break;
                    case 2: o = new OrnamentEstrella(width/2, height/2, 50, color(255, 0, 255), 25, 10); break;
                    default: o = new Ornament(width/2, height/2, 25, color(100)); break;
                }
                gui.collarPersonal.addOrnament(o);
            }
        }

        //Text Fields Key Pressed ++++++++++++++++++++++++++++++++++++++++

        gui.username.keyPressed(key, keyCode);
        gui.password.keyPressed(key, keyCode);
        gui.nomCollar.keyPressed(key, keyCode);
    }

    // ******************* MOUSE interaction ***************************** //

    public void mousePressed(){

        println("X: "+mouseX+", Y:"+mouseY);

        if(gui.pantallaActual == GUI.PANTALLA.INICIAL){
            if(gui.logIn.mouseSobreBoto(this)){
                gui.pantallaActual = GUI.PANTALLA.PRINCIPAL;
            }
        }

        // Botons pantalla PRINCIPAL ++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
        if(gui.pantallaActual == GUI.PANTALLA.PRINCIPAL){
            if(gui.explora.mouseSobreBoto(this)){
                gui.pantallaActual = GUI.PANTALLA.EXPLORA;
            }
            else if(gui.colección.mouseSobreBoto(this)){
                gui.pantallaActual = GUI.PANTALLA.COLECCIÓN;
            }
            else if(gui.personaliza.mouseSobreBoto(this)){
                gui.collarPersonal = new Collar(20, imagenPWidth/2, imagenPHeight+75, 100, 200);
                gui.pantallaActual = GUI.PANTALLA.PERSONALIZA;
            }
            else if(gui.instrucciones.mouseSobreBoto(this)){
                gui.pantallaActual = GUI.PANTALLA.INSTRUCCIONES;
            }
        }
        // Botons pantalla INICAL +++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
        if(gui.pantallaActual == GUI.PANTALLA.INICIAL){
            gui.logo.activado = false;
        }
        else{
            gui.logo.activado = true;
        }

        if(gui.pantallaActual!=GUI.PANTALLA.INICIAL){
            if(gui.logo.mouseSobreBoto(this)){
                gui.pantallaActual = GUI.PANTALLA.PRINCIPAL;
            }
        }


        // Botons pantalla PERSONALIZACIÓN ++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
        if(gui.pantallaActual==GUI.PANTALLA.PERSONALIZA){
            gui.collarPersonal.checkDragged(this);
        }

        if(gui.pantallaActual==GUI.PANTALLA.PERSONALIZA){
            if(gui.ornCercle.mouseSobreBoto(this)){
                Ornament o = new OrnamentCercle(3*imagenPWidth/4, height/2, 25, color(255, 0, 0));
                gui.collarPersonal.addOrnament(o);
            }
            else if(gui.ornTriangle.mouseSobreBoto(this)){
                Ornament o = new OrnamentTriangle(3*imagenPWidth/4, height/2, 25, color(0, 255, 0));
                gui.collarPersonal.addOrnament(o);
            }
            else if(gui.ornEstrella.mouseSobreBoto(this)){
                Ornament o = new OrnamentEstrella(3*imagenPWidth/4, height/2, 32, color(255, 0, 255), 20, 10);
                gui.collarPersonal.addOrnament(o);
            }
        }



        // Text Field Is Pressed ++++++++++++++++++++++++++++++++++++++++++

        gui.username.isPressed(this);
        gui.password.isPressed(this);
        gui.nomCollar.isPressed(this);
    }

    public void mouseDragged(){
        if(gui.pantallaActual==GUI.PANTALLA.PERSONALIZA){
            gui.collarPersonal.checkDragged(this);
        }
    }

    public void mouseReleased() {
        println("MOUSE RELEASED");
    }

}
