package solucioInformatica;

import processing.core.PApplet;

import static processing.core.PConstants.BACKSPACE;

public class TextField {

    // Propiedades del camp de text
    int x, y, h, w;

    // Colors
    int bgColor, fgColor, selectedColor, borderColor;
    int borderWeight = 1;

    // Text del camp
    public String text = "";
    int textSize = (int) Sizes.SubtitleSize;

    boolean selected = false;

    // Constructor

    /**
     * Constructor de la clase TextField
     * @param p5 Parámetro de la librería Processing que permite dibujar
     * @param x Coordenada X dónde se va a dibujar el campo de texto
     * @param y Coordenada Y dónde se va a dibujar el campo de texto
     * @param w Anchura del campo de texto
     * @param h Altura del campo de texto
     * @param txtSize Tamaño del texto introducido por teclado
     */
    public TextField(PApplet p5, int x, int y, int w, int h, int txtSize) {
        this.x = x; this.y = y; this.w = w; this.h = h;
        this.bgColor = p5.color(0xFFEAF2D7);
        this.fgColor = p5.color(0, 0, 0);
        this.selectedColor = p5.color(0xFFEFCFE3);
        this.borderColor = p5.color(0xFFB3DEE2);
        this.borderWeight = 3;
        this.textSize = txtSize;
    }

    // Dibuja el Campo de Texto
    public void display(PApplet p5) {
        p5.pushStyle();
        if (selected) {
            p5.fill(selectedColor);
        } else {
            p5.fill(bgColor);
        }

        p5.strokeWeight(borderWeight);
        p5.stroke(borderColor);
        p5.rect(x, y, w, h, 5);

        p5.fill(fgColor);
        p5.textSize(textSize); p5.textAlign(p5.LEFT, p5.CENTER);
        p5.text(text, x + 5, y + h - textSize);
        p5.popStyle();
    }

    // Quitar, añadir el texto que se teclea

    /**
     * Función para detectar las teclas del teclado
     * @param key variable que detecta letras
     * @param keyCode variable que detecta la tecla espaciadora
     */
    public void keyPressed(char key, int keyCode) {
        if (selected) {
            if (keyCode == (int)BACKSPACE) {
                removeText();
            } else if (keyCode == 32) {
                addText(' '); // SPACE
            } else {

                boolean isKeyCapitalLetter = (key >= 'A' && key <= 'Z');
                boolean isKeySmallLetter = (key >= 'a' && key <= 'z');
                boolean isKeyNumber = (key >= '0' && key <= '9');

                if (isKeyCapitalLetter || isKeySmallLetter || isKeyNumber) {
                    addText(key);
                }
            }
        }
    }

    // Añadir la letra C al final del texto
    public void addText(char c) {
        if (this.text.length() + 1 < w) {
            this.text += c;
        }
    }

    // Quitar la última letra del texto
    public void removeText() {
        if (text.length() > 0) {
            text = text.substring(0, text.length() - 1);
        }
    }

    // Devuelve el texto
    public String getText(){
        return this.text;
    }

    // Indica si el ratón está sobre el campo de texto
    public boolean mouseOverTextField(PApplet p5) {
        return (p5.mouseX >= this.x && p5.mouseX <= this.x + this.w && p5.mouseY >= this.y && p5.mouseY <= this.y + this.h);
    }

    // Selecciona el campo de texto si presionas encima
    // Deselecciona el campo de texto si presionas fuera
    public void isPressed(PApplet p5) {
        if (mouseOverTextField(p5)) {
            selected = true;
        } else {
            selected = false;
        }
    }
}
