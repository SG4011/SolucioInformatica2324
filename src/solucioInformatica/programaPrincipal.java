package solucioInformatica;

import processing.core.PApplet;

import static solucioInformatica.Layout.*;

public class programaPrincipal extends PApplet {

    // Interfície Gràfica (Pantalles i components)
    GUI gui;

    // Base de dades
    DataBase bbdd;

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

        bbdd = new DataBase("admin", "12345", "collares");
        bbdd.connect();

        gui = new GUI(this, bbdd);                   // Constructor de la GUI
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
        /*if(key=='0'){
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
            if(key=='6'){
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
        }*/


        //Text Fields Key Pressed ++++++++++++++++++++++++++++++++++++++++

        gui.username.keyPressed(key, keyCode);
        gui.password.keyPressed(key, keyCode);
        gui.nomCollar.keyPressed(key, keyCode);
    }

    // ******************* MOUSE interaction ***************************** //

    public void mousePressed(){

        println("X: "+mouseX+", Y:"+mouseY);

        // Botons pantalla PRINCIPAL ++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
        if(gui.pantallaActual == GUI.PANTALLA.PRINCIPAL){
            if(gui.explora.mouseSobreBoto(this)){
                gui.pantallaActual = GUI.PANTALLA.EXPLORA;
            }
            else if(gui.colección.mouseSobreBoto(this)){
                gui.pantallaActual = GUI.PANTALLA.COLECCIÓN;
            }
            else if(gui.personaliza.mouseSobreBoto(this)){
                gui.collarPersonal = new Collar(20, imagenPWidth/2, imagenPHeight+75, 100, 185);
                gui.pantallaActual = GUI.PANTALLA.PERSONALIZA;
            }
            else if(gui.instrucciones.mouseSobreBoto(this)){
                gui.pantallaActual = GUI.PANTALLA.INSTRUCCIONES;
            }
            if(gui.logOut.mouseSobreBoto(this)){
                gui.pantallaActual = GUI.PANTALLA.INICIAL;

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

        String userName = gui.username.getText();
        String password = gui.password.getText();

        if(bbdd.isValidUser(userName, password)){
            gui.logIn.activado = true;
        }

        else if(!bbdd.isValidUser(userName, password) && gui.logIn.mouseSobreBoto(this)){
            gui.logIn.activado = false;
            gui.badLogIn.setVisible(true);
        }

        if(gui.badLogIn.bAceptar.mouseSobreBoto(this)){
            gui.badLogIn.setVisible(false);
        }

        if(gui.logIn.mouseSobreBoto(this) && gui.logIn.activado){
            gui.pantallaActual = GUI.PANTALLA.PRINCIPAL;
        }


        // Botons pantalla PERSONALIZACIÓN ++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
        if(gui.pantallaActual==GUI.PANTALLA.PERSONALIZA){

            gui.collarPersonal.checkDragged(this);

            int colorOrnament=gui.selectColor.getSelectedValue();
            float x = 3*imagenPWidth/4; float y = height/2;
            String idCollar = gui.nomCollar.getText();

            if(gui.ornCercle.mouseSobreBoto(this)){
                Ornament o = new OrnamentCercle(x, y, 25, colorOrnament);
                gui.collarPersonal.addOrnament(o);
                gui.db.insertOrnament(idCollar, String.valueOf(10), String.valueOf(10), String.valueOf(colorOrnament), "1");
            }
            else if(gui.ornTriangle.mouseSobreBoto(this)){
                Ornament o = new OrnamentTriangle(x, y, 25, colorOrnament);
                gui.collarPersonal.addOrnament(o);
                gui.db.insertOrnament(idCollar, String.valueOf(10), String.valueOf(10), String.valueOf(colorOrnament), "0");
            }
            else if(gui.ornEstrella.mouseSobreBoto(this)){
                Ornament o = new OrnamentEstrella(x, y, 32, colorOrnament, 20, 10);
                gui.collarPersonal.addOrnament(o);
                gui.db.insertOrnament(idCollar, String.valueOf(10), String.valueOf(10), String.valueOf(colorOrnament), "3");
            }

            else if(gui.selectColor.mouseOverSelect(this) && gui.selectColor.isEnabled()){
                if(!gui.selectColor.isCollapsed()){
                    gui.selectColor.update(this);
                }
                gui.selectColor.toggle();
            }
            else if(gui.guardarCollar.mouseSobreBoto(this)){

                if(!gui.nomCollar.getText().equals("")) {
                    gui.db.insertCollariOrnaments(this, gui.collarPersonal, idCollar);
                    gui.guardaCollar.setVisible(true);
                    gui.creaImagenCollar(this, gui.collarPersonal, idCollar);
                }
                else {
                    gui.sinNombre.setVisible(true);
                }
            }
            else if(gui.sinNombre.visible && gui.sinNombre.bAceptar.mouseSobreBoto(this)){
                gui.sinNombre.setVisible(false);
            }
            else if(gui.guardaCollar.visible && gui.guardaCollar.bAceptar.mouseSobreBoto(this)){
                gui.guardaCollar.setVisible(false);
            }
            else if(gui.borrar.mouseSobreBoto(this)){
                gui.collarPersonal.deleteLastOrnament();
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
