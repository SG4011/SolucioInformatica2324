package solucioInformatica;

import processing.core.PApplet;
import processing.core.PImage;

import static solucioInformatica.Layout.*;

public class programaPrincipal extends PApplet {

    // Interfície Gráfica (Pantallas y componentes)
    GUI gui;

    // Base de datos
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
        noStroke();                         // Sine bordes
        textAlign(CENTER); textSize(18);   // Alineación y medida del texto

        bbdd = new DataBase("admin", "12345", "collares");
        bbdd.connect();

        gui = new GUI(this, bbdd);                   // Constructor de la GUI
    }

    public void draw(){

        // Dibuja la pantalla correspondiente
        switch(gui.actualScreen){
            case INICIAL:   gui.drawInitialScreen(this);
                break;

            case PRINCIPAL:     gui.drawPrincipalScreen(this);
                break;

            case COLECCIÓN:   gui.drawCollectionScreen(this);
                break;

            case EXPLORA:   gui.drawExplorationScreen(this);
                break;

            case PERSONALIZA:   gui.drawCustomScreen(this);
                break;

            case INSTRUCCIONES:     gui.drawInfoScreen(this);
                break;
        }


    }

    // ******************* KEYBOARD interaction ***************************** //

    public void keyPressed(){

        //Text Fields Key Pressed ++++++++++++++++++++++++++++++++++++++++

        gui.username.keyPressed(key, keyCode);
        gui.password.keyPressed(key, keyCode);
        gui.necklaceName.keyPressed(key, keyCode);
    }

    // ******************* MOUSE interaction ***************************** //

    public void mousePressed(){

        println("X: "+mouseX+", Y:"+mouseY);

        // Botones pantalla PRINCIPAL ++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
        if(gui.actualScreen == GUI.SCREEN.PRINCIPAL){
            if(gui.explore.mouseOnButton(this)){
                gui.actualScreen = GUI.SCREEN.EXPLORA;
                gui.infoNecklaces = gui.db.getInfoNecklaceTable(gui.actualPage);
                println("COLLARS PAGINA: "+ gui.infoNecklaces.length);
                for(int i = 0; i<gui.infoNecklaces.length; i++) {
                    gui.imgs[i] = loadImage(gui.infoNecklaces[i][0] + ".jpg");
                }
            }
            else if(gui.colection.mouseOnButton(this)){
                gui.actualScreen = GUI.SCREEN.COLECCIÓN;
                gui.infoNecklaces = gui.db.getInfoNecklaceTable(gui.actualPage);
                println("COLLARS PAGINA: "+ gui.infoNecklaces.length);
                for(int i = 0; i<gui.infoNecklaces.length; i++) {
                    gui.imgs[i] = loadImage(gui.infoNecklaces[i][0] + ".jpg");
                }
            }
            else if(gui.custom.mouseOnButton(this)){
                gui.personalNecklace = new Necklace(20, imagePWidth /2, imagePHeight +75, 100, 185);
                gui.actualScreen = GUI.SCREEN.PERSONALIZA;
            }
            else if(gui.instructions.mouseOnButton(this)){
                gui.actualScreen = GUI.SCREEN.INSTRUCCIONES;
            }
            if(gui.logOut.mouseOnButton(this)){
                gui.actualScreen = GUI.SCREEN.INICIAL;

            }
        }
        // Botones pantalla INICAL +++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
        if(gui.actualScreen == GUI.SCREEN.INICIAL){
            gui.logo.activated = false;
        }
        else{
            gui.logo.activated = true;
        }

        if(gui.actualScreen != GUI.SCREEN.INICIAL){
            if(gui.logo.mouseOnButton(this)){
                gui.actualScreen = GUI.SCREEN.PRINCIPAL;
            }
        }

        String userName = gui.username.getText();
        String password = gui.password.getText();

        if(bbdd.isValidUser(userName, password)){
            gui.logIn.activated = true;
        }

        else if(!bbdd.isValidUser(userName, password) && gui.logIn.mouseOnButton(this)){
            gui.logIn.activated = false;
            gui.badLogIn.setVisible(true);
        }

        if(gui.badLogIn.bAccept.mouseOnButton(this)){
            gui.badLogIn.setVisible(false);
        }

        if(gui.logIn.mouseOnButton(this) && gui.logIn.activated){
            gui.actualScreen = GUI.SCREEN.PRINCIPAL;
        }


        // Botones pantalla PERSONALIZACIÓN ++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
        if(gui.actualScreen == GUI.SCREEN.PERSONALIZA){

            gui.personalNecklace.checkDragged(this);

            int colorOrnament=gui.selectColor.getSelectedValue();
            float x = 3* imagePWidth /4; float y = height/2;
            String idCollar = gui.necklaceName.getText();

            if(gui.ornCircle.mouseOnButton(this)){
                Ornament o = new RoundOrnament(x, y, 25, colorOrnament);
                gui.personalNecklace.addOrnament(o);
                gui.db.insertOrnament(idCollar, String.valueOf(10), String.valueOf(10), String.valueOf(colorOrnament), "1");
            }
            else if(gui.ornTriangle.mouseOnButton(this)){
                Ornament o = new TriangleOrnament(x, y, 25, colorOrnament);
                gui.personalNecklace.addOrnament(o);
                gui.db.insertOrnament(idCollar, String.valueOf(10), String.valueOf(10), String.valueOf(colorOrnament), "0");
            }
            else if(gui.ornStar.mouseOnButton(this)){
                Ornament o = new StarOrnament(x, y, 32, colorOrnament, 20, 10);
                gui.personalNecklace.addOrnament(o);
                gui.db.insertOrnament(idCollar, String.valueOf(10), String.valueOf(10), String.valueOf(colorOrnament), "3");
            }

            else if(gui.selectColor.mouseOverSelect(this) && gui.selectColor.isEnabled()){
                if(!gui.selectColor.isCollapsed()){
                    gui.selectColor.update(this);
                }
                gui.selectColor.toggle();
            }
            else if(gui.saveNecklace.mouseOnButton(this)){

                if(!gui.necklaceName.getText().equals("")) {
                    gui.db.insertNecklaceAndOrnaments(this, gui.personalNecklace, idCollar);
                    gui.savedNecklace.setVisible(true);
                    gui.createNecklaceImg(this, gui.personalNecklace, idCollar);
                }
                else {
                    gui.noName.setVisible(true);
                }
            }
            else if(gui.noName.visible && gui.noName.bAccept.mouseOnButton(this)){
                gui.noName.setVisible(false);
            }
            else if(gui.savedNecklace.visible && gui.savedNecklace.bAccept.mouseOnButton(this)){
                gui.savedNecklace.setVisible(false);
            }
            else if(gui.delete.mouseOnButton(this)){
                gui.personalNecklace.deleteLastOrnament();
            }
        }

        // Botones pantalla EXPLORACIÓN y COLECCIÓN

        if(gui.actualScreen == GUI.SCREEN.COLECCIÓN || gui.actualScreen == GUI.SCREEN.EXPLORA){
            if(gui.prevPage.mouseOnButton(this) && gui.actualPage >1){
                gui.actualPage--;
                gui.infoNecklaces = gui.db.getInfoNecklaceTable(gui.actualPage);
                println("COLLARS PAGINA: "+ gui.infoNecklaces.length);
                gui.imgs = new PImage[4];
                for(int i = 0; i<gui.infoNecklaces.length; i++) {
                    gui.imgs[i] = loadImage(gui.infoNecklaces[i][0] + ".jpg");
                }
            }

            else if(gui.nextPage.mouseOnButton(this) && gui.actualPage <gui.numPages){
                gui.actualPage++;
                gui.infoNecklaces = gui.db.getInfoNecklaceTable(gui.actualPage);
                println("COLLARS PAGINA: "+ gui.infoNecklaces.length);
                gui.imgs = new PImage[4];
                for(int i = 0; i<gui.infoNecklaces.length; i++) {
                    gui.imgs[i] = loadImage(gui.infoNecklaces[i][0] + ".jpg");
                }
            }
        }



        // Text Field Is Pressed ++++++++++++++++++++++++++++++++++++++++++

        gui.username.isPressed(this);
        gui.password.isPressed(this);
        gui.necklaceName.isPressed(this);
    }

    public void mouseDragged(){
        if(gui.actualScreen == GUI.SCREEN.PERSONALIZA){
            gui.personalNecklace.checkDragged(this);
        }
    }

    public void mouseReleased() {
        println("MOUSE RELEASED");
    }

}
