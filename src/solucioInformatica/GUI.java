package solucioInformatica;

import processing.core.PApplet;
import processing.core.PGraphics;
import processing.core.PImage;

import static solucioInformatica.Sizes.*;
import static solucioInformatica.Layout.*;

public class GUI {

    // Enumerat de les Pantalles de l'App
    public enum SCREEN {INICIAL, PRINCIPAL, COLECCIÓN, EXPLORA, PERSONALIZA, INSTRUCCIONES};
    // Pantalla Actual
    public SCREEN actualScreen;

    // Colors i Fonts de l'APP
    Colors appColors; int[] arrayColors;
    Fonts fontsApp;

    // PopUps +++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
    PopUp badLogIn;
    PopUp savedNecklace;
    PopUp noName;

    // Fotos ++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
    PImage photo1;

    // Select Color +++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
    SelectColor selectColor;
    // Text Fields ++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
    TextField username;
    TextField password;
    TextField necklaceName;
    // Botons +++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
    Button logIn; Button logo; Button logOut;
    Button explore; Button custom; Button colection; Button instructions;
    Button visualize; Button visualize2; Button visualize3; Button visualize4;
    Button ornTriangle; Button ornCircle; Button ornStar; Button saveNecklace; Button delete;
    Button nextPage; Button prevPage;
    // Collar
    Necklace personalNecklace;
    String imgCentralName;
    DataBase db;

    int numNecklaces;
    int numPages;

    int actualPage = 1;
    String[][] infoNecklaces;
    PImage[] imgs;




    // Constructor de la GUI
    public GUI(PApplet p5, DataBase db){
        actualScreen = SCREEN.INICIAL;

        this.db = db;

        this.imgCentralName = db.getNecklaceImage();

        appColors = new Colors(p5);   // Constructor de los colores de la App
        fontsApp = new Fonts(p5);     // Constructor de las fuentes de la App

        // PopUp +++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
        badLogIn = new PopUp(p5, "ERROR", "Usuario o contraseña incorrectos", 330, 250, 600, 300, appColors);
        badLogIn.setVisible(false);

        savedNecklace = new PopUp(p5, "ENHORABUENA!!!", "Collar guardado correctamente", 330, 250, 600, 300, appColors);
        savedNecklace.setVisible(false);

        noName = new PopUp(p5, "NO SE PUEDE GUARDAR", "ES NECESARIO PONER UN NOMBRE", 330, 250, 600, 300, appColors);
        noName.setVisible(false);

        // Fotos +++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
        photo1 = p5.loadImage("data/logo.png");

        // Select Color++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++

        arrayColors = db.getColors(p5);
        selectColor = new SelectColor(p5, arrayColors, marginH+1000, 275, 200, imagePHeight /14);

        // Text Fields ++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
        username = new TextField(p5, 360, (int)(2*marginV), (int)inputWidth, (int)inputHeight, (int) SubtitleSize);
        password = new TextField(p5, 360, (int)(8*marginV), (int)inputWidth, (int)inputHeight, (int) SubtitleSize);
        necklaceName = new TextField(p5, (int)(marginH+1000), 409, 200, (int)(imagePHeight /6), 25);

        // Botones +++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
        logIn = new Button(p5, "LOG IN", 480, marginV+600, 320, 50);
        logIn.setColors(appColors.getColorAt(2), appColors.getColorAt(2), appColors.getColorAt(3), 155);
        logIn.activated = false;

        logOut = new Button(p5, "LOG OUT", 1080-marginH, 600, sidebarWidth, sidebarHeight);
        logOut.setColors(appColors.getColorAt(2), appColors.getColorAt(2), appColors.getColorAt(3), 155);

        instructions = new Button(p5, "INSTRUCCIONES", marginH, 2*marginV + logoHeight + 105, sidebarWidth, sidebarHeight);
        instructions.setColors(appColors.getColorAt(2), appColors.getColorAt(2), appColors.getColorAt(3), 155);

        explore = new Button(p5, "EXPLORA", marginH, 2*marginV + logoHeight, sidebarWidth, sidebarHeight);
        explore.setColors(appColors.getColorAt(2), appColors.getColorAt(2), appColors.getColorAt(3), 155);

        custom = new Button(p5, "PERSONALIZA", marginH, 2*marginV + logoHeight + 105*2, sidebarWidth, sidebarHeight);
        custom.setColors(appColors.getColorAt(2), appColors.getColorAt(2), appColors.getColorAt(3), 155);

        colection = new Button(p5, "COLECCIÓN", marginH, 2*marginV + logoHeight + 105*3, sidebarWidth, sidebarHeight);
        colection.setColors(appColors.getColorAt(2), appColors.getColorAt(2), appColors.getColorAt(3), 155);

        logo = new Button(p5, "LOGO", 1080-marginH, marginV, logoWidth, logoHeight);
        logo.setColors(appColors.getColorAt(1), appColors.getColorAt(1), appColors.getColorAt(2),155 );

        visualize = new Button(p5, "VISUALIZA", marginH+200, 275+130, imageCWidth +25, imageCHeight -130);
        visualize.setColors(appColors.getColorAt(2), appColors.getColorAt(2), appColors.getColorAt(3), 155);

        visualize2 = new Button(p5, "VISUALIZA", marginH+200, 475+130, imageCWidth +25, imageCHeight -130);
        visualize2.setColors(appColors.getColorAt(2), appColors.getColorAt(2), appColors.getColorAt(3), 155);

        visualize3 = new Button(p5, "VISUALIZA", marginH+600+200, 275+130, imageCWidth +25, imageCHeight -130);
        visualize3.setColors(appColors.getColorAt(2), appColors.getColorAt(2), appColors.getColorAt(3), 155);

        visualize4 = new Button(p5, "VISUALIZA", marginH+600+200, 475+130, imageCWidth +25, imageCHeight -130);
        visualize4.setColors(appColors.getColorAt(2), appColors.getColorAt(2), appColors.getColorAt(3), 155);

        ornCircle = new Button(p5,"CÍRCULO", marginH+700, 275, 200, imagePHeight /4);
        ornCircle.setColors(appColors.getColorAt(0), appColors.getColorAt(2), appColors.getColorAt(1), 155);

        ornStar = new Button(p5,"ESTRELLA", marginH+700, 409, 200, imagePHeight /4);
        ornStar.setColors(appColors.getColorAt(0), appColors.getColorAt(2), appColors.getColorAt(1), 155);

        ornTriangle = new Button(p5,"TRIÁNGULO", marginH+700, 543, 200, imagePHeight /4);
        ornTriangle.setColors(appColors.getColorAt(0), appColors.getColorAt(2), appColors.getColorAt(1), 155);

        saveNecklace = new Button(p5, "GUARDAR", marginH+1000, 543, 200, imagePHeight /4);
        saveNecklace.setColors(appColors.getColorAt(4), appColors.getColorAt(3), appColors.getColorAt(2), 155);

        delete = new Button(p5, "BORRAR", marginH+475, 543, 150, imagePHeight /4);
        delete.setColors(appColors.getColorAt(4), appColors.getColorAt(3), appColors.getColorAt(2), 155);

        nextPage = new Button(p5, ">", 1080-marginH+logoWidth/2, logoHeight+100, logoWidth/2-10, 300);
        nextPage.setColors(appColors.getColorAt(4), appColors.getColorAt(3), appColors.getColorAt(2), 155);

        prevPage = new Button(p5, "<", 1080-marginH, logoHeight+100, logoWidth/2-10, 300);
        prevPage.setColors(appColors.getColorAt(4), appColors.getColorAt(3), appColors.getColorAt(2), 155);

        numNecklaces = db.getNumRowsTable("collar");
        numPages = numNecklaces /4;


        if(numNecklaces %4 != 0){
            numPages++;
        }

        imgs = new PImage[4];
    }



    // PANTALLAS DE LA GUI

    public void drawInitialScreen(PApplet p5){
        p5.background(appColors.getColorAt(0));
        drawLogo(p5, photo1, (float)1088.88-marginH, 10+marginV);
        drawInput(p5);
        drawImage(p5,360, 14*marginV);
        logIn.display(p5);
        badLogIn.display(p5);
    }

    public void drawPrincipalScreen(PApplet p5){
        p5.background(appColors.getColorAt(0));
        drawLogo(p5, photo1, (float)1088.8-marginH, 10+marginV);
        drawBanner(p5, 3*marginH + logoWidth, marginV);
        drawImage(p5, 300+marginH, 14*marginV);

        p5.textFont(fontsApp.getFontAt(1));
        drawSideBar(p5);
        logOut.display(p5);
    }

    public void drawCollectionScreen(PApplet p5){
        p5.background(appColors.getColorAt(0));
        drawLogo(p5, photo1, (float)1088.8-marginH, 10+marginV);
        drawBanner(p5, marginH, marginV);
        drawCollectionExplorationImg(p5, marginH, 275, imgs[0]);
        drawCollectionExplorationImg12(p5, marginH, 475, imgs[1]);
        drawCollectionExplorationImg123(p5, marginH+600, 275, imgs[2]);
        drawCollectionExplorationImg1234(p5, marginH+600, 475, imgs[3]);
        nextPage.display(p5);
        prevPage.display(p5);

        p5.fill(0);
        p5.text(actualPage +"/"+ numPages, 1080-marginH+logoWidth/2, 475+165);

        for(int i=0; i<4; i++){

        }

    }

    public void drawExplorationScreen(PApplet p5){
        p5.background(appColors.getColorAt(0));
        drawLogo(p5, photo1, (float)1088.8-marginH, 10+marginV);
        drawBanner(p5, marginH, marginV);
        drawCollectionExplorationImg(p5, marginH, 275, imgs[0]);
        drawCollectionExplorationImg12(p5, marginH, 475, imgs[1]);
        drawCollectionExplorationImg123(p5, marginH+600, 275, imgs[2]);
        drawCollectionExplorationImg1234(p5, marginH+600, 475, imgs[3]);
        nextPage.display(p5);
        prevPage.display(p5);
        p5.fill(0);
        p5.text(actualPage +"/"+ numPages, 1080-marginH+logoWidth/2, 475+165);
    }

    public void drawCustomScreen(PApplet p5){
        p5.background(appColors.getColorAt(3));
        drawLogo(p5, photo1, (float)1088.8-marginH, 10+marginV);
        drawBanner(p5, marginH, marginV);
        drawCustomScreen(p5, marginH, 275);
        ornCircle.display(p5); ornStar.display(p5); ornTriangle.display(p5);
        delete.display(p5);
        saveNecklace.display(p5); necklaceName.display(p5);
        personalNecklace.display(p5);
        p5.pushStyle();
        p5.textSize(12); p5.textAlign(p5.LEFT);
        p5.text("Guarda el collar con un nombre", marginH+1000,500);
        p5.text("Seleccione un color para la joya", marginH+1000, 350);
        p5.popStyle();
        selectColor.enabled = true; selectColor.display(p5);
        savedNecklace.display(p5); noName.display(p5);
    }

    public void drawInfoScreen(PApplet p5){
        p5.background(appColors.getColorAt(0));
        drawLogo(p5, photo1, (float)1088.8-marginH, 10+marginV);
        drawBanner(p5, marginH, marginV);
        p5.pushStyle();
        p5.textSize(CustomizationScreenSize); p5.fill(appColors.getColorAt(3));
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


    // ZONAS DE LA GUI (de diferentes pantallas)

    public void drawLogo(PApplet p5, PImage img, float x, float y){
        logo.display(p5);
        p5.image(img, x, y);
    }

    // Pantalla inicial

    public void drawInput(PApplet p5){
        // Input Username ++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
        username.display(p5);

        // Input password +++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
        password.display(p5);
    }

    // Pantalla inicial y principal

    public void drawImage(PApplet p5, float x, float y){

        PImage imgCollar = p5.loadImage(imgCentralName);
        float X = x; float Y = y;
        p5.image(imgCollar, X, Y, inputWidth, 320);
    }

    // Pantalla colección y exploración

    public void drawCollectionExplorationImg(PApplet p5, float x, float y, PImage img){
        float X = x; float Y = y;
        p5.fill(appColors.getColorAt(3));
        p5.rect(X, Y, imageCWidth, imageCHeight);
        p5.fill(0); p5.textFont(fontsApp.getThirdFont()); p5.textSize(ParagraphSize);
        // Imagen
        p5.image(img, X, Y, imageCWidth, imageCHeight);
        // Info de la imagen
        p5.fill(appColors.getColorAt(1));
        p5.rect(X+200, Y, imageCWidth +25, imageCHeight -50);
        p5.fill(appColors.getColorAt(2));
        visualize.display(p5);
        p5.fill(0);
        p5.text(infoNecklaces[0][0], X+300,Y+75);
    }

    public void drawCollectionExplorationImg12(PApplet p5, float x, float y, PImage img){
        if(img!=null) {
            float X = x;
            float Y = y;
            p5.fill(appColors.getColorAt(3));
            p5.rect(X, Y, imageCWidth, imageCHeight);
            p5.fill(0);
            p5.textFont(fontsApp.getThirdFont());
            p5.textSize(ParagraphSize);
            p5.image(img, X, Y, imageCWidth, imageCHeight);
            // Info de la imagen
            p5.fill(appColors.getColorAt(1));
            p5.rect(X + 200, Y, imageCWidth + 25, imageCHeight - 50);
            p5.fill(appColors.getColorAt(2));
            visualize2.display(p5);
            p5.fill(0);
            p5.text(infoNecklaces[1][0], X + 300, Y + 75);
        }

    }


    public void drawCollectionExplorationImg123(PApplet p5, float x, float y, PImage img){
        if(img!=null) {
            float X = x;
            float Y = y;
            p5.fill(appColors.getColorAt(3));
            p5.rect(X, Y, imageCWidth, imageCHeight);
            p5.fill(0);
            p5.textFont(fontsApp.getThirdFont());
            p5.textSize(ParagraphSize);
            p5.image(img, X, Y, imageCWidth, imageCHeight);
            // Info de la imagen
            p5.fill(appColors.getColorAt(1));
            p5.rect(X + 200, Y, imageCWidth + 25, imageCHeight - 50);
            p5.fill(appColors.getColorAt(2));
            visualize3.display(p5);
            p5.fill(0);
            p5.text(infoNecklaces[2][0], X + 300, Y + 75);
        }

    }

    public void drawCollectionExplorationImg1234(PApplet p5, float x, float y, PImage img){
        if(img!=null) {
            float X = x;
            float Y = y;
            p5.fill(appColors.getColorAt(3));
            p5.rect(X, Y, imageCWidth, imageCHeight);
            p5.fill(0);
            p5.textFont(fontsApp.getThirdFont());
            p5.textSize(ParagraphSize);
            p5.image(img, X, Y, imageCWidth, imageCHeight);
            // Info de la imagen
            p5.fill(appColors.getColorAt(1));
            p5.rect(X + 200, Y, imageCWidth + 25, imageCHeight - 50);
            p5.fill(appColors.getColorAt(2));
            visualize4.display(p5);
            p5.fill(0);
            p5.text(infoNecklaces[3][0], X + 300, Y + 75);
        }

    }

    public void drawCustomScreen(PApplet p5, float x, float y){
        float X = x; float Y = y;
        p5.fill(appColors.getFirstColor()); p5.stroke(0);
        p5.rect(X, Y, imagePWidth, imagePHeight);
        p5.text("imagen", X+150, y+150);
    }

    public void drawSideBar(PApplet p5){
        // Zona Sidebar ++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
        colection.display(p5);
        instructions.display(p5);
        explore.display(p5);
        custom.display(p5);
    }

    public void drawBanner(PApplet p5, float x, float y){
        float X = x; float Y = y;
        p5.fill(appColors.getColorAt(4)); p5.stroke(appColors.getColorAt(3));
        p5.strokeWeight(3);
        p5.pushStyle();
        p5.rect(marginV, marginH, 1240-logoWidth-marginV, bannerHeight);
        p5.fill(0); p5.textFont(fontsApp.getFontAt(4)); p5.textSize(TitleSize);
        p5.textAlign(p5.LEFT);
        if(actualScreen == SCREEN.PRINCIPAL){
            p5.text("BIENVENIDO/A ;)", 2*marginV, 7*marginH);
        }
        else{
            p5.text(actualScreen + "", 2*marginV, 7*marginH);
        }

        p5.popStyle();
    }

    public void createNecklaceImg(PApplet p5, Necklace c, String necklaceName){
        PGraphics img = p5.createGraphics((int) imagePWidth, (int) imagePHeight);
        img.beginDraw();
        img.background(appColors.getColorAt(3));
        img.translate(-marginH, -275); img.fill(appColors.getColorAt(3)); img.strokeWeight(3);
        img.ellipse(imagePWidth /2, imagePHeight +75, 200, 375);
        for(int i=0; i<c.ornaments.length; i++){
            if(c.ornaments[i]!=null) {
                c.ornaments[i].display(img);
            }
        }
        img.endDraw();
        img.save("data/"+necklaceName +".jpg");
    }
}
