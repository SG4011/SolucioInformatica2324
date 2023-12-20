package solucioInformatica;

import processing.core.PApplet;

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

            case EXPLORACIÓN:   gui.dibuixaPantallaExploración(this);
                break;

            case PERSONALIZACIÓN:   gui.dibuixaPantallaPersonalización(this);
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
            gui.pantallaActual = GUI.PANTALLA.EXPLORACIÓN;
        }
        else if(key=='4'){
            gui.pantallaActual = GUI.PANTALLA.PERSONALIZACIÓN;
        }
        else if(key=='5'){
            gui.pantallaActual = GUI.PANTALLA.INSTRUCCIONES;
        }

        //Text Fields Key Pressed ++++++++++++++++++++++++++++++++++++++++

        gui.username.keyPressed(key, keyCode);
        gui.password.keyPressed(key, keyCode);
    }

    // ******************* MOUSE interaction ***************************** //

    public void mousePressed(){

        println("X: "+mouseX+", Y:"+mouseY);

        if(gui.pantallaActual == GUI.PANTALLA.INICIAL){
            if(gui.logIn.mouseSobreBoto(this)){
                gui.pantallaActual = GUI.PANTALLA.PRINCIPAL;
            }
        }

        if(gui.pantallaActual == GUI.PANTALLA.PRINCIPAL){
            if(gui.explora.mouseSobreBoto(this)){
                gui.pantallaActual = GUI.PANTALLA.EXPLORACIÓN;
            }
            else if(gui.colección.mouseSobreBoto(this)){
                gui.pantallaActual = GUI.PANTALLA.COLECCIÓN;
            }
            else if(gui.personaliza.mouseSobreBoto(this)){
                gui.pantallaActual = GUI.PANTALLA.PERSONALIZACIÓN;
            }
            else if(gui.instrucciones.mouseSobreBoto(this)){
                gui.pantallaActual = GUI.PANTALLA.INSTRUCCIONES;
            }
        }
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



        // Text Field Is Pressed ++++++++++++++++++++++++++++++++++++++++++

        gui.username.isPressed(this);
        gui.password.isPressed(this);
    }

    public void mouseDragged(){
        println("MOUSE DRAGGED");
    }

    public void mouseReleased() {
        println("MOUSE RELEASED");
    }

}
