package solucioInformatica;

import processing.core.PApplet;

import static solucioInformatica.Medidas.*;
import static solucioInformatica.Layout.*;

public class GUI {

    // Enumerat de les Pantalles de l'App
    public enum PANTALLA {INICIAL, PRINCIPAL, COLECCIÓN, EXPLORACIÓN, PERSONALIZACIÓN}; // COMPRA
    // Pantalla Actual
    public PANTALLA pantallaActual;

    // Colors i Fonts de l'APP
    Colores appColors;
    Fuentes fontsApp;

    // Constructor de la GUI
    public GUI(PApplet p5){
        pantallaActual = PANTALLA.INICIAL;
        appColors = new Colores(p5);   // Constructor dels colors de l'App
        fontsApp = new Fuentes(p5);     // Constructor de les fonts de l'App
    }


    // PANTALLES DE LA GUI

    public void dibuixaPantallaInicial(PApplet p5){

        p5.background(appColors.getColorAt(0));
        dibuixaLogo(p5);
        dibuixaInput(p5);
        dibuixaImatge(p5,360, 14*marginV);
        dibuixaLogIn(p5);
    }

    public void dibuixaPantallaPrincipal(PApplet p5){
        p5.background(appColors.getColorAt(0));
        dibuixaLogo(p5);dibuixaBanner(p5, 3*marginH + logoWidth, marginV);
        dibuixaImatge(p5, 300+marginH, 14*marginV);
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


    // ZONES DE LA GUI

    public void dibuixaLogo(PApplet p5){
        p5.fill(appColors.getSecondColor());
        p5.rect(1080-marginH, marginV, logoWidth, logoHeight);
        p5.fill(0); p5.textFont(fontsApp.getSecondFont()); p5.textSize(midaTitol);
        p5.text("LOGO", 1080-marginH + logoWidth/2, marginV + logoHeight/2);
    }

    public void dibuixaInput(PApplet p5){ // Pantalla inicial
        // Input Username ++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
        p5.fill(appColors.getColorAt(4));
        p5.rect(360, 2*marginV, inputWidth, inputHeight);
        p5.fill(0); p5.textFont(fontsApp.getThirdFont()); p5.textSize(midaSubtitol);
        p5.text("username", 540, 110);

        // Input password +++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
        p5.fill(appColors.getColorAt(4));
        p5.rect(360, 8*marginV, inputWidth, inputHeight);
        p5.fill(0); p5.textFont(fontsApp.getThirdFont()); p5.textSize(midaSubtitol);
        p5.text("password", 540, 215);
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
        p5.rect(X, Y, imagenCWidth, imagenCHeight); // Imatge
        p5.fill(appColors.getColorAt(1));
        p5.rect(X+200, Y, imagenCWidth+25, imagenCHeight-50); // Info de la imatge
        p5.fill(appColors.getColorAt(2));
        p5.rect(X+200, Y+130, imagenCWidth+25, imagenCHeight-130); // Botó afegir carrito
        p5.fill(0); p5.textFont(fontsApp.getThirdFont()); p5.textSize(midaParagraf);
        p5.text("imagen", X+75, Y+75); p5.text("info", X+300, Y+75);
        p5.text("carrito", X+300, Y+155);
    }

    public void dibuixaImatgeColecciónExploración12(PApplet p5, float x, float y){ // Pantalla colección i exploración
        float X = x; float Y = y;
        p5.fill(appColors.getColorAt(3));
        p5.rect(X, Y, imagenCWidth, imagenCHeight); // Imatge
        p5.fill(appColors.getColorAt(1));
        p5.rect(X+200, Y, imagenCWidth+25, imagenCHeight-50); // Info de la imatge
        p5.fill(appColors.getColorAt(2));
        p5.rect(X+200, Y+130, imagenCWidth+25, imagenCHeight-130); // Botó afegir carrito
        p5.fill(0); p5.textFont(fontsApp.getThirdFont()); p5.textSize(midaParagraf);
        p5.text("imagen", X+75, Y+75); p5.text("info", X+300, Y+75);
        p5.text("carrito", X+300, Y+155);
    }

    public void dibuixaImatgeColecciónExploración123(PApplet p5, float x, float y){ // Pantalla colección i exploración
        float X = x; float Y = y;
        p5.fill(appColors.getColorAt(3));
        p5.rect(X, Y, imagenCWidth, imagenCHeight); // Imatge
        p5.fill(appColors.getColorAt(1));
        p5.rect(X+200, Y, imagenCWidth+25, imagenCHeight-50); // Info de la imatge
        p5.fill(appColors.getColorAt(2));
        p5.rect(X+200, Y+130, imagenCWidth+25, imagenCHeight-130); // Botó afegir carrito
        p5.fill(0); p5.textFont(fontsApp.getThirdFont()); p5.textSize(midaParagraf);
        p5.text("imagen", X+75, Y+75); p5.text("info", X+300, Y+75);
        p5.text("carrito", X+300, Y+155);
    }

    public void dibuixaImatgeColecciónExploración1234(PApplet p5, float x, float y){ // Pantalla colección i exploración
        float X = x; float Y = y;
        p5.fill(appColors.getColorAt(3));
        p5.rect(X, Y, imagenCWidth, imagenCHeight); // Imatge
        p5.fill(appColors.getColorAt(1));
        p5.rect(X+200, Y, imagenCWidth+25, imagenCHeight-50); // Info de la imatge
        p5.fill(appColors.getColorAt(2));
        p5.rect(X+200, Y+130, imagenCWidth+25, imagenCHeight-130); // Botó afegir carrito
        p5.fill(0); p5.textFont(fontsApp.getThirdFont()); p5.textSize(midaParagraf);
        p5.text("imagen", X+75, Y+75); p5.text("info", X+300, Y+75);
        p5.text("carrito", X+300, Y+155);
    }

    public void dibuixaImatgePersonalizaciónCompra(PApplet p5, float x, float y){
        float X = x; float Y = y;
        p5.fill(appColors.getColorAt(3));
        p5.rect(X, Y, imagenPWidth, imagenPHeight);
        p5.text("imagen", X+150, y+150);
    }

    public void dibuixaLogIn(PApplet p5){
        p5.fill(appColors.getColorAt(2));
        p5.rect(480, marginV+600, 320, 50);
        p5.fill(0); p5.textFont(fontsApp.getThirdFont()); p5.textSize(midaSubtitol);
        p5.text("LOG IN", 640, 3*marginV+600);
    }

    public void dibuixaSideBar(PApplet p5){
        // Zona Sidebar ++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
        p5.fill(appColors.getColorAt(2));
        p5.rect(marginH, 2*marginV + logoHeight, sidebarWidth, sidebarHeight);
        p5.fill(0); p5.textFont(fontsApp.getSecondFont()); p5.textSize(midaParagraf);
        p5.text("Explora", marginH + sidebarWidth/2, marginV + logoHeight + sidebarHeight/2);
        p5.fill(appColors.getColorAt(2));
        p5.rect(marginH, 2*marginV + logoHeight + 105, sidebarWidth, sidebarHeight);
        p5.fill(0); p5.textFont(fontsApp.getSecondFont()); p5.textSize(midaParagraf);
        p5.text("Compra", marginH + sidebarWidth/2, marginV + logoHeight + sidebarHeight/2 + 105);
        p5.fill(appColors.getColorAt(2));
        p5.rect(marginH, 2*marginV + logoHeight + 105*2, sidebarWidth, sidebarHeight);
        p5.fill(0); p5.textFont(fontsApp.getSecondFont()); p5.textSize(midaParagraf);
        p5.text("Personaliza", marginH + sidebarWidth/2, marginV + logoHeight + sidebarHeight/2 + 105*2);
        p5.fill(appColors.getColorAt(2));
        p5.rect(marginH, 2*marginV + logoHeight + 105*3, sidebarWidth, sidebarHeight);
        p5.fill(0); p5.textFont(fontsApp.getSecondFont()); p5.textSize(midaParagraf);
        p5.text("Colección", marginH + sidebarWidth/2, marginV + logoHeight + sidebarHeight/2 + 105*3);
    }

    public void dibuixaBanner(PApplet p5, float x, float y){
        float X = x; float Y = y;
        p5.fill(appColors.getColorAt(4));
        p5.rect(X, Y, bannerWidth, bannerHeight);
        p5.fill(0); p5.textFont(fontsApp.getSecondFont()); p5.textSize(midaTitol);
        p5.text("PANTALLA " +  pantallaActual + "("+pantallaActual.ordinal() +")", X + bannerWidth/2, Y + bannerHeight/2);
    }

    public void dibuixaColumna1(PApplet p5){
        // Zona Columnes 1, 2 i 3 +++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
        p5.fill(appColors.getThirdColor());
        p5.rect(2*marginH + sidebarWidth, 2*marginV + bannerHeight, 3*columnWidth + 2*marginH, columnHeight);
        p5.fill(0); p5.textFont(fontsApp.getThirdFont()); p5.textSize(midaParagraf);
        p5.text("COLUMN 1", 2*marginH + sidebarWidth + 3*columnWidth/2, 2*marginV + bannerHeight + columnHeight/2);
    }

    public void dibuixaColumnes12(PApplet p5){
        // Zona Columnes 1, 2 i 3 +++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
        p5.fill(appColors.getThirdColor());
        p5.rect(2*marginH + sidebarWidth, 2*marginV + bannerHeight, columnWidth, columnHeight);
        p5.fill(0); p5.textFont(fontsApp.getThirdFont()); p5.textSize(midaParagraf);
        p5.text("COLUMN 1", 2*marginH + sidebarWidth +columnWidth/2, 2*marginV + bannerHeight + columnHeight/2);

        p5.fill(appColors.getThirdColor());
        p5.rect(3*marginH + sidebarWidth + columnWidth, 2*marginV + bannerHeight, 2*columnWidth + marginH, columnHeight);
        p5.fill(0); p5.textFont(fontsApp.getThirdFont()); p5.textSize(midaParagraf);
        p5.text("COLUMN 2", 3*marginH + sidebarWidth + 2*columnWidth, 2*marginV + bannerHeight + columnHeight/2);
    }

    public void dibuixaColumnes123(PApplet p5){
        // Zona Columnes 1, 2 i 3 +++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
        p5.fill(appColors.getThirdColor());
        p5.rect(2*marginH + sidebarWidth, 2*marginV + bannerHeight, columnWidth, columnHeight);
        p5.fill(0); p5.textFont(fontsApp.getThirdFont()); p5.textSize(midaParagraf);
        p5.text("COLUMN 1", 2*marginH + sidebarWidth +columnWidth/2, 2*marginV + bannerHeight + columnHeight/2);

        p5.fill(appColors.getThirdColor());
        p5.rect(3*marginH + sidebarWidth + columnWidth, 2*marginV + bannerHeight, columnWidth, columnHeight);
        p5.fill(0); p5.textFont(fontsApp.getThirdFont()); p5.textSize(midaParagraf);
        p5.text("COLUMN 2", 3*marginH + sidebarWidth + columnWidth +columnWidth/2, 2*marginV + bannerHeight + columnHeight/2);

        p5.fill(appColors.getThirdColor());
        p5.rect(4*marginH + sidebarWidth + 2*columnWidth, 2*marginV + bannerHeight, columnWidth, columnHeight);
        p5.fill(0); p5.textFont(fontsApp.getThirdFont()); p5.textSize(midaParagraf);
        p5.text("COLUMN 3", 4*marginH + sidebarWidth + 2*columnWidth +columnWidth/2, 2*marginV + bannerHeight + columnHeight/2);
    }
}
