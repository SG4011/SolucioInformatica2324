package solucioInformatica;

import processing.core.PApplet;

import static solucioInformatica.Medidas.*;
import static solucioInformatica.Layout.*;

public class GUI {

    // Enumerat de les Pantalles de l'App
    public enum PANTALLA {INICIAL, PRINCIPAL, COLECCIÓN, EXPLORACIÓN, PERSONALIZACIÓN, INSTRUCCIONES};
    // Pantalla Actual
    public PANTALLA pantallaActual;

    // Colors i Fonts de l'APP
    Colores appColors;
    Fuentes fontsApp;
    // Text Fields ++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
    TextField username;
    TextField password;
    // Botons +++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
    Boton logIn; Boton logo;
    Boton explora; Boton personaliza; Boton colección; Boton instrucciones;
    Boton visualiza; Boton visualiza2; Boton visualiza3; Boton visualiza4;


    // Constructor de la GUI
    public GUI(PApplet p5){
        pantallaActual = PANTALLA.INICIAL;
        appColors = new Colores(p5);   // Constructor dels colors de l'App
        fontsApp = new Fuentes(p5);     // Constructor de les fonts de l'App

        // Text Fields ++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
        username = new TextField(p5, 360, (int)(2*marginV), (int)inputWidth, (int)inputHeight);
        password = new TextField(p5, 360, (int)(8*marginV), (int)inputWidth, (int)inputHeight );

        // Botons +++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
        logIn = new Boton(p5, "LOG IN", 480, marginV+600, 320, 50);
        logIn.setColors(appColors.getColorAt(2), appColors.getColorAt(2), appColors.getColorAt(3), 155);

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

    }

    // PANTALLES DE LA GUI

    public void dibuixaPantallaInicial(PApplet p5){

        p5.background(appColors.getColorAt(0));
        dibuixaLogo(p5);
        dibuixaInput(p5);
        dibuixaImatge(p5,360, 14*marginV);
        logIn.display(p5);
    }

    public void dibuixaPantallaPrincipal(PApplet p5){
        p5.background(appColors.getColorAt(0));
        dibuixaLogo(p5);dibuixaBanner(p5, 3*marginH + logoWidth, marginV);
        dibuixaImatge(p5, 300+marginH, 14*marginV);

        p5.textFont(fontsApp.getFontAt(1));
        dibuixaSideBar(p5);
    }

    public void dibuixaPantallaColección(PApplet p5){
        p5.background(appColors.getColorAt(0));
        dibuixaLogo(p5);dibuixaBanner(p5, marginH, marginV);
        dibuixaImatgeColecciónExploración1(p5, marginH, 275);
        dibuixaImatgeColecciónExploración12(p5, marginH, 475);
        dibuixaImatgeColecciónExploración123(p5, marginH+600, 275);
        dibuixaImatgeColecciónExploración1234(p5, marginH+600, 475);
    }

    public void dibuixaPantallaExploración(PApplet p5){
        p5.background(appColors.getColorAt(0));
        dibuixaLogo(p5);dibuixaBanner(p5, marginH, marginV);
        dibuixaImatgeColecciónExploración1(p5, marginH, 275);
        dibuixaImatgeColecciónExploración12(p5, marginH, 475);
        dibuixaImatgeColecciónExploración123(p5, marginH+600, 275);
        dibuixaImatgeColecciónExploración1234(p5, marginH+600, 475);
    }

    public void dibuixaPantallaPersonalización(PApplet p5){
        p5.background(appColors.getColorAt(0));
        dibuixaLogo(p5);dibuixaBanner(p5, marginH, marginV);
        dibuixaImatgePersonalizaciónCompra(p5, marginH, 275);

    }

    public void dibuixaPantallaAbout(PApplet p5){
        p5.background(appColors.getColorAt(0));
        dibuixaLogo(p5); dibuixaBanner(p5, marginH, marginV);
    }


    // ZONES DE LA GUI

    public void dibuixaLogo(PApplet p5){
        logo.display(p5);
    }

    public void dibuixaInput(PApplet p5){ // Pantalla inicial
        // Input Username ++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
        username.display(p5);

        // Input password +++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
        password.display(p5);
    }

    public void dibuixaImatge(PApplet p5, float x, float y){ // Pantalla inicial y principal
        p5.fill(appColors.getColorAt(3));
        float X = x; float Y = y;
        p5.rect(X, Y, inputWidth, 320);
        p5.fill(0); p5.textFont(fontsApp.getThirdFont()); p5.textSize(midaSubtitol);
        p5.text("imagen", X+270, Y+160);
    }

    public void dibuixaImatgeColecciónExploración1(PApplet p5, float x, float y){ // Pantalla colección i exploración
        float X = x; float Y = y;
        p5.fill(appColors.getColorAt(3));
        p5.rect(X, Y, imagenCWidth, imagenCHeight);
        p5.fill(0); p5.textFont(fontsApp.getThirdFont()); p5.textSize(midaParagraf);
        p5.text("imagen", X+75, Y+75);      // Imatge
        p5.fill(appColors.getColorAt(1));
        p5.rect(X+200, Y, imagenCWidth+25, imagenCHeight-50); // Info de la imatge
        p5.fill(appColors.getColorAt(2));
        visualiza.display(p5);
        p5.text("info", X+300,Y+75);
    }

    public void dibuixaImatgeColecciónExploración12(PApplet p5, float x, float y){ // Pantalla colección i exploración
        float X = x; float Y = y;
        p5.fill(appColors.getColorAt(3));
        p5.rect(X, Y, imagenCWidth, imagenCHeight);
        p5.fill(0); p5.textFont(fontsApp.getThirdFont()); p5.textSize(midaParagraf);
        p5.text("imagen", X+75, Y+75);// Imatge
        p5.fill(appColors.getColorAt(1));
        p5.rect(X+200, Y, imagenCWidth+25, imagenCHeight-50); // Info de la imatge
        p5.fill(appColors.getColorAt(2));
        visualiza2.display(p5);
        p5.text("info", X+300, Y+75);

    }

    public void dibuixaImatgeColecciónExploración123(PApplet p5, float x, float y){ // Pantalla colección i exploración
        float X = x; float Y = y;
        p5.fill(appColors.getColorAt(3));
        p5.rect(X, Y, imagenCWidth, imagenCHeight);
        p5.fill(0); p5.textFont(fontsApp.getThirdFont()); p5.textSize(midaParagraf);
        p5.text("imagen", X+75, Y+75);      // Imatge
        p5.fill(appColors.getColorAt(1));
        p5.rect(X+200, Y, imagenCWidth+25, imagenCHeight-50); // Info de la imatge
        p5.fill(appColors.getColorAt(2));
        visualiza3.display(p5);
        p5.text("info", X+300, Y+75);

    }

    public void dibuixaImatgeColecciónExploración1234(PApplet p5, float x, float y){ // Pantalla colección i exploración
        float X = x; float Y = y;
        p5.fill(appColors.getColorAt(3));
        p5.rect(X, Y, imagenCWidth, imagenCHeight);
        p5.fill(0); p5.textFont(fontsApp.getThirdFont()); p5.textSize(midaParagraf);
        p5.text("imagen", X+75, Y+75);      // Imatge
        p5.fill(appColors.getColorAt(1));
        p5.rect(X+200, Y, imagenCWidth+25, imagenCHeight-50); // Info de la imatge
        p5.fill(appColors.getColorAt(2));
        visualiza4.display(p5);
        p5.text("info", X+300, Y+75);

    }

    public void dibuixaImatgePersonalizaciónCompra(PApplet p5, float x, float y){
        float X = x; float Y = y;
        p5.fill(appColors.getColorAt(3));
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
        p5.fill(appColors.getColorAt(4));
        p5.rect(X, Y, bannerWidth, bannerHeight);
        p5.fill(0); p5.textFont(fontsApp.getFontAt(0)); p5.textSize(midaTitol);
        p5.text(pantallaActual + "("+pantallaActual.ordinal() +")", X + bannerWidth/2, Y + bannerHeight/2);
    }
}
