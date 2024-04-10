package solucioInformatica;

import processing.core.PApplet;
import processing.core.PGraphics;
import processing.core.PImage;

import static solucioInformatica.Medidas.*;
import static solucioInformatica.Layout.*;

public class GUI {

    // Enumerat de les Pantalles de l'App
    public enum PANTALLA {INICIAL, PRINCIPAL, COLECCIÓN, EXPLORA, PERSONALIZA, INSTRUCCIONES};
    // Pantalla Actual
    public PANTALLA pantallaActual;

    // Colors i Fonts de l'APP
    Colores appColors; int[] arrayColors;
    Fuentes fontsApp;

    // PopUps +++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
    PopUp badLogIn;
    PopUp guardaCollar;
    PopUp sinNombre;

    // Fotos ++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
    PImage foto1;

    // Select Color +++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
    SelectColor selectColor;
    // Text Fields ++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
    TextField username;
    TextField password;
    TextField nomCollar;
    // Botons +++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
    Boton logIn; Boton logo; Boton logOut;
    Boton explora; Boton personaliza; Boton colección; Boton instrucciones;
    Boton visualiza; Boton visualiza2; Boton visualiza3; Boton visualiza4;
    Boton ornTriangle; Boton ornCercle; Boton ornEstrella; Boton guardarCollar; Boton borrar;
    Boton paginaSiguiente; Boton paginaAnterior;
    // Collar
    Collar collarPersonal;
    String imgNombreCentral;
    DataBase db;

    int numCollars;
    int numPagines;

    int paginaActual = 1;
    String[][] infoCollares;
    PImage[] imgs;




    // Constructor de la GUI
    public GUI(PApplet p5, DataBase db){
        pantallaActual = PANTALLA.INICIAL;

        this.db = db;

        this.imgNombreCentral = db.getImagenCollar();

        appColors = new Colores(p5);   // Constructor dels colors de l'App
        fontsApp = new Fuentes(p5);     // Constructor de les fonts de l'App

        // PopUp +++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
        badLogIn = new PopUp(p5, "ERROR", "Usuario o contraseña incorrectos", 330, 250, 600, 300, appColors);
        badLogIn.setVisible(false);

        guardaCollar = new PopUp(p5, "ENHORABUENA!!!", "Collar guardado correctamente", 330, 250, 600, 300, appColors);
        guardaCollar.setVisible(false);

        sinNombre = new PopUp(p5, "NO SE PUEDE GUARDAR", "ES NECESARIO PONER UN NOMBRE", 330, 250, 600, 300, appColors);
        sinNombre.setVisible(false);

        // Fotos +++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
        foto1 = p5.loadImage("data/logo.png");

        // Select Color++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++

        arrayColors = db.getColores(p5);
        selectColor = new SelectColor(p5, arrayColors, marginH+1000, 275, 200, imagenPHeight/14);

        // Text Fields ++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
        username = new TextField(p5, 360, (int)(2*marginV), (int)inputWidth, (int)inputHeight, (int)midaSubtitol);
        password = new TextField(p5, 360, (int)(8*marginV), (int)inputWidth, (int)inputHeight, (int)midaSubtitol );
        nomCollar = new TextField(p5, (int)(marginH+1000), 409, 200, (int)(imagenPHeight/6), 25);

        // Botons +++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
        logIn = new Boton(p5, "LOG IN", 480, marginV+600, 320, 50);
        logIn.setColors(appColors.getColorAt(2), appColors.getColorAt(2), appColors.getColorAt(3), 155);
        logIn.activado = false;

        logOut = new Boton(p5, "LOG OUT", 1080-marginH, 600, sidebarWidth, sidebarHeight);
        logOut.setColors(appColors.getColorAt(2), appColors.getColorAt(2), appColors.getColorAt(3), 155);

        instrucciones = new Boton(p5, "INSTRUCCIONES", marginH, 2*marginV + logoHeight + 105, sidebarWidth, sidebarHeight);
        instrucciones.setColors(appColors.getColorAt(2), appColors.getColorAt(2), appColors.getColorAt(3), 155);

        explora = new Boton(p5, "EXPLORA", marginH, 2*marginV + logoHeight, sidebarWidth, sidebarHeight);
        explora.setColors(appColors.getColorAt(2), appColors.getColorAt(2), appColors.getColorAt(3), 155);

        personaliza = new Boton(p5, "PERSONALIZA", marginH, 2*marginV + logoHeight + 105*2, sidebarWidth, sidebarHeight);
        personaliza.setColors(appColors.getColorAt(2), appColors.getColorAt(2), appColors.getColorAt(3), 155);

        colección = new Boton(p5, "COLECCIÓN", marginH, 2*marginV + logoHeight + 105*3, sidebarWidth, sidebarHeight);
        colección.setColors(appColors.getColorAt(2), appColors.getColorAt(2), appColors.getColorAt(3), 155);

        logo = new Boton(p5, "LOGO", 1080-marginH, marginV, logoWidth, logoHeight);
        logo.setColors(appColors.getColorAt(1), appColors.getColorAt(1), appColors.getColorAt(2),155 );

        visualiza = new Boton(p5, "VISUALIZA", marginH+200, 275+130, imagenCWidth+25, imagenCHeight-130);
        visualiza.setColors(appColors.getColorAt(2), appColors.getColorAt(2), appColors.getColorAt(3), 155);

        visualiza2 = new Boton(p5, "VISUALIZA", marginH+200, 475+130, imagenCWidth+25, imagenCHeight-130);
        visualiza2.setColors(appColors.getColorAt(2), appColors.getColorAt(2), appColors.getColorAt(3), 155);

        visualiza3 = new Boton(p5, "VISUALIZA", marginH+600+200, 275+130, imagenCWidth+25, imagenCHeight-130);
        visualiza3.setColors(appColors.getColorAt(2), appColors.getColorAt(2), appColors.getColorAt(3), 155);

        visualiza4 = new Boton(p5, "VISUALIZA", marginH+600+200, 475+130, imagenCWidth+25, imagenCHeight-130);
        visualiza4.setColors(appColors.getColorAt(2), appColors.getColorAt(2), appColors.getColorAt(3), 155);

        ornCercle = new Boton(p5,"CÍRCULO", marginH+700, 275, 200, imagenPHeight/4);
        ornCercle.setColors(appColors.getColorAt(0), appColors.getColorAt(2), appColors.getColorAt(1), 155);

        ornEstrella = new Boton(p5,"ESTRELLA", marginH+700, 409, 200, imagenPHeight/4);
        ornEstrella.setColors(appColors.getColorAt(0), appColors.getColorAt(2), appColors.getColorAt(1), 155);

        ornTriangle = new Boton(p5,"TRIÁNGULO", marginH+700, 543, 200, imagenPHeight/4);
        ornTriangle.setColors(appColors.getColorAt(0), appColors.getColorAt(2), appColors.getColorAt(1), 155);

        guardarCollar = new Boton(p5, "GUARDAR", marginH+1000, 543, 200, imagenPHeight/4);
        guardarCollar.setColors(appColors.getColorAt(4), appColors.getColorAt(3), appColors.getColorAt(2), 155);

        borrar = new Boton(p5, "BORRAR", marginH+475, 543, 150, imagenPHeight/4);
        borrar.setColors(appColors.getColorAt(4), appColors.getColorAt(3), appColors.getColorAt(2), 155);

        paginaSiguiente = new Boton (p5, ">", 1080-marginH+logoWidth/2, logoHeight+100, logoWidth/2-10, 300);
        paginaSiguiente.setColors(appColors.getColorAt(4), appColors.getColorAt(3), appColors.getColorAt(2), 155);

        paginaAnterior = new Boton (p5, "<", 1080-marginH, logoHeight+100, logoWidth/2-10, 300);
        paginaAnterior.setColors(appColors.getColorAt(4), appColors.getColorAt(3), appColors.getColorAt(2), 155);

        numCollars = db.getNumRowsTaula("collar");
        numPagines = numCollars/4;


        if(numCollars%4 != 0){
            numPagines++;
        }

        imgs = new PImage[4];
    }



    // PANTALLES DE LA GUI

    public void dibuixaPantallaInicial(PApplet p5){
        p5.background(appColors.getColorAt(0));
        dibuixaLogo(p5, foto1, (float)1088.88-marginH, 10+marginV);
        dibuixaInput(p5);
        dibuixaImatge(p5,360, 14*marginV);
        logIn.display(p5);
        badLogIn.display(p5);
    }

    public void dibuixaPantallaPrincipal(PApplet p5){
        p5.background(appColors.getColorAt(0));
        dibuixaLogo(p5, foto1, (float)1088.8-marginH, 10+marginV);
        dibuixaBanner(p5, 3*marginH + logoWidth, marginV);
        dibuixaImatge(p5, 300+marginH, 14*marginV);

        p5.textFont(fontsApp.getFontAt(1));
        dibuixaSideBar(p5);
        logOut.display(p5);
    }

    public void dibuixaPantallaColección(PApplet p5){
        p5.background(appColors.getColorAt(0));
        dibuixaLogo(p5, foto1, (float)1088.8-marginH, 10+marginV);
        dibuixaBanner(p5, marginH, marginV);
        dibuixaImatgeColecciónExploración1(p5, marginH, 275, imgs[0]);
        dibuixaImatgeColecciónExploración12(p5, marginH, 475, imgs[1]);
        dibuixaImatgeColecciónExploración123(p5, marginH+600, 275, imgs[2]);
        dibuixaImatgeColecciónExploración1234(p5, marginH+600, 475, imgs[3]);
        paginaSiguiente.display(p5);
        paginaAnterior.display(p5);


        p5.fill(0);
        p5.text(paginaActual+"/"+numPagines, 1080-marginH+logoWidth/2, 475+165);

        for(int i=0; i<4; i++){

        }

    }

    public void dibuixaPantallaExploración(PApplet p5){
        p5.background(appColors.getColorAt(0));
        dibuixaLogo(p5, foto1, (float)1088.8-marginH, 10+marginV);
        dibuixaBanner(p5, marginH, marginV);
        dibuixaImatgeColecciónExploración1(p5, marginH, 275, imgs[0]);
        dibuixaImatgeColecciónExploración12(p5, marginH, 475, imgs[1]);
        dibuixaImatgeColecciónExploración123(p5, marginH+600, 275, imgs[2]);
        dibuixaImatgeColecciónExploración1234(p5, marginH+600, 475, imgs[3]);
        paginaSiguiente.display(p5);
        paginaAnterior.display(p5);
        p5.fill(0);
        p5.text(paginaActual+"/"+numPagines, 1080-marginH+logoWidth/2, 475+165);
    }

    public void dibuixaPantallaPersonalización(PApplet p5){
        p5.background(appColors.getColorAt(3));
        dibuixaLogo(p5, foto1, (float)1088.8-marginH, 10+marginV);
        dibuixaBanner(p5, marginH, marginV);
        dibuixaImatgePersonalización(p5, marginH, 275);
        ornCercle.display(p5); ornEstrella.display(p5); ornTriangle.display(p5);
        borrar.display(p5);guardarCollar.display(p5); nomCollar.display(p5);
        collarPersonal.display(p5);
        p5.pushStyle();
        p5.textSize(12); p5.textAlign(p5.LEFT);
        p5.text("Guarda el collar con un nombre", marginH+1000,500);
        p5.text("Seleccione un color para la joya", marginH+1000, 350);
        p5.popStyle();
        selectColor.enabled = true; selectColor.display(p5);
        guardaCollar.display(p5); sinNombre.display(p5);
    }

    public void dibuixaPantallaAbout(PApplet p5){
        p5.background(appColors.getColorAt(0));
        dibuixaLogo(p5, foto1, (float)1088.8-marginH, 10+marginV);
        dibuixaBanner(p5, marginH, marginV);
        p5.pushStyle();
        p5.textSize(midaPantallaPersonalizacion); p5.fill(appColors.getColorAt(3));
        p5.textAlign(p5.LEFT);
        float xt = marginH;
        p5.text("Instrucciones para crear y personalizar un collar:", xt, bannerHeight+100);
        p5.text("1-Cuando se encuentra en la pantalla principal, ir a PERSONALIZACIÓN",
                xt, bannerHeight+150);
        p5.text("2-Cuando se encuentra en la pantalla PERSONALIZACIÓN tiene 3 botones para crear joyas en función de su forma",
                xt, bannerHeight+200);
        p5.text("3-Elegir color de la joya", xt, bannerHeight+250);
        p5.text("4-Pulsar el botón de la forma deseada y arrastrarlo para colocarlo donde se desee",
                xt, bannerHeight+300);
        p5.text("5-Una vez finalizada la creación, poner un nombre y GUARDAR", xt, bannerHeight+350);
        p5.popStyle();
    }


    // ZONES DE LA GUI (de diferents pantalles)

    public void dibuixaLogo(PApplet p5, PImage img, float x, float y){
        logo.display(p5);
        p5.image(img, x, y);
    }

    public void dibuixaInput(PApplet p5){ // Pantalla inicial
        // Input Username ++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
        username.display(p5);

        // Input password +++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
        password.display(p5);
    }

    public void dibuixaImatge(PApplet p5, float x, float y){ // Pantalla inicial y principal

        PImage imgCollar = p5.loadImage(imgNombreCentral);
        float X = x; float Y = y;
        p5.image(imgCollar, X, Y, inputWidth, 320);
    }

    public void dibuixaImatgeColecciónExploración1(PApplet p5, float x, float y, PImage img){ // Pantalla colección i exploración
        float X = x; float Y = y;
        p5.fill(appColors.getColorAt(3));
        p5.rect(X, Y, imagenCWidth, imagenCHeight);
        p5.fill(0); p5.textFont(fontsApp.getThirdFont()); p5.textSize(midaParagraf);
        // Imatge
        p5.image(img, X, Y, imagenCWidth, imagenCHeight);
        p5.fill(appColors.getColorAt(1));
        p5.rect(X+200, Y, imagenCWidth+25, imagenCHeight-50); // Info de la imatge
        p5.fill(appColors.getColorAt(2));
        visualiza.display(p5);
        p5.fill(0);
        p5.text(infoCollares[0][0], X+300,Y+75);
    }

    public void dibuixaImatgeColecciónExploración12(PApplet p5, float x, float y, PImage img){ // Pantalla colección i exploración
        if(img!=null) {
            float X = x;
            float Y = y;
            p5.fill(appColors.getColorAt(3));
            p5.rect(X, Y, imagenCWidth, imagenCHeight);
            p5.fill(0);
            p5.textFont(fontsApp.getThirdFont());
            p5.textSize(midaParagraf);
            p5.image(img, X, Y, imagenCWidth, imagenCHeight);
            p5.fill(appColors.getColorAt(1));
            p5.rect(X + 200, Y, imagenCWidth + 25, imagenCHeight - 50); // Info de la imatge
            p5.fill(appColors.getColorAt(2));
            visualiza2.display(p5);
            p5.fill(0);
            p5.text(infoCollares[1][0], X + 300, Y + 75);
        }

    }

    public void dibuixaImatgeColecciónExploración123(PApplet p5, float x, float y, PImage img){ // Pantalla colección i exploración
        if(img!=null) {
            float X = x;
            float Y = y;
            p5.fill(appColors.getColorAt(3));
            p5.rect(X, Y, imagenCWidth, imagenCHeight);
            p5.fill(0);
            p5.textFont(fontsApp.getThirdFont());
            p5.textSize(midaParagraf);
            p5.image(img, X, Y, imagenCWidth, imagenCHeight);
            p5.fill(appColors.getColorAt(1));
            p5.rect(X + 200, Y, imagenCWidth + 25, imagenCHeight - 50); // Info de la imatge
            p5.fill(appColors.getColorAt(2));
            visualiza3.display(p5);
            p5.fill(0);
            p5.text(infoCollares[2][0], X + 300, Y + 75);
        }

    }

    public void dibuixaImatgeColecciónExploración1234(PApplet p5, float x, float y, PImage img){ // Pantalla colección i exploración
        if(img!=null) {
            float X = x;
            float Y = y;
            p5.fill(appColors.getColorAt(3));
            p5.rect(X, Y, imagenCWidth, imagenCHeight);
            p5.fill(0);
            p5.textFont(fontsApp.getThirdFont());
            p5.textSize(midaParagraf);
            p5.image(img, X, Y, imagenCWidth, imagenCHeight);
            p5.fill(appColors.getColorAt(1));
            p5.rect(X + 200, Y, imagenCWidth + 25, imagenCHeight - 50); // Info de la imatge
            p5.fill(appColors.getColorAt(2));
            visualiza4.display(p5);
            p5.fill(0);
            p5.text(infoCollares[3][0], X + 300, Y + 75);
        }

    }

    public void dibuixaImatgePersonalización(PApplet p5, float x, float y){
        float X = x; float Y = y;
        p5.fill(appColors.getFirstColor()); p5.stroke(0);
        p5.rect(X, Y, imagenPWidth, imagenPHeight);
        p5.text("imagen", X+150, y+150);
    }

    public void dibuixaSideBar(PApplet p5){
        // Zona Sidebar ++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
        colección.display(p5);
        instrucciones.display(p5);
        explora.display(p5);
        personaliza.display(p5);
    }

    public void dibuixaBanner(PApplet p5, float x, float y){
        float X = x; float Y = y;
        p5.fill(appColors.getColorAt(4)); p5.stroke(appColors.getColorAt(3));
        p5.strokeWeight(3);
        p5.pushStyle();
        p5.rect(marginV, marginH, 1240-logoWidth-marginV, bannerHeight);
        p5.fill(0); p5.textFont(fontsApp.getFontAt(4)); p5.textSize(midaTitol);
        p5.textAlign(p5.LEFT);
        if(pantallaActual == PANTALLA.PRINCIPAL){
            p5.text("BIENVENIDO/A ;)", 2*marginV, 7*marginH);
        }
        else{
            p5.text(pantallaActual + "", 2*marginV, 7*marginH);
        }

        p5.popStyle();
    }

    public void creaImagenCollar(PApplet p5, Collar c, String nombreCollar){
        PGraphics img = p5.createGraphics((int)imagenPWidth, (int)imagenPHeight);
        img.beginDraw();
        img.background(appColors.getColorAt(3));
        img.translate(-marginH, -275); img.fill(appColors.getColorAt(3)); img.strokeWeight(3);
        img.ellipse(imagenPWidth/2, imagenPHeight+75, 200, 375);
        for(int i=0; i<c.ornaments.length; i++){
            if(c.ornaments[i]!=null) {
                c.ornaments[i].display(img);
            }
        }
        img.endDraw();
        img.save("data/"+nombreCollar +".jpg");
    }
}
