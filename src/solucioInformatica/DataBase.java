package solucioInformatica;

import processing.core.PApplet;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

public class DataBase {

    // Variable de connexió a la BBDD
    Connection c;

    // Variable de consulta
    Statement query;

    // Dades de connexió (user, password, nom de la base de dades)
    String user, password, databaseName;

    // Estat de la connexió
    boolean connectat = false;

    public DataBase(String user, String password, String databaseName){
        this.user = user;
        this.password = password;
        this.databaseName = databaseName;
    }

    public void connect(){
        try {
            c = DriverManager.getConnection("jdbc:mysql://localhost:3306/"+databaseName, user, password);
            query = c.createStatement();
            System.out.println("Connectat a la BBDD! :) ");
            connectat = true;
        }
        catch(Exception e) {
            System.out.println(e);
        }
    }

    // Retorna el número de files d'una taula
    public int getNumRowsTaula(String nomTaula){
        try {
            ResultSet rs = query.executeQuery( "SELECT COUNT(*) AS n FROM "+ nomTaula );
            rs.next();
            int numRows = rs.getInt("n");
            return numRows;
        }
        catch(Exception e) {
            System.out.println(e);
            return 0;
        }
    }

    public int getNumRowsQuery(String q){
        try {
            ResultSet rs = query.executeQuery( q);
            rs.next();
            return rs.getInt("n");
        }
        catch(Exception e) {
            System.out.println(e);
            return 0;
        }
    }

    // Retorna el número de columnes d'una taula de la base de dades
    public int getNumColsTaula(String nomTaula){
        try {
            String q = "SELECT count(*) as n FROM information_schema.columns WHERE table_name ='"+ nomTaula +"' AND table_schema='"+databaseName+"'";
            System.out.println(q);
            ResultSet rs = query.executeQuery( q);
            rs.next();
            int numCols = rs.getInt("n");
            return numCols;
        }
        catch(Exception e) {
            System.out.println(e);
            return 0;
        }
    }


    // Retorna les dades de la columna idUsuario de la taula USUARIO
    public String[] getColumnaidUsuarioTaulaUsuario(){
        int numFiles = getNumRowsTaula("usuario");
        String[] info = new String[numFiles];
        try {
            ResultSet rs = query.executeQuery( "SELECT idUsuario FROM usuario ORDER BY nom ASC");
            int nr = 0;
            while (rs.next()) {
                info[nr] = rs.getString("idUsuario");
                nr++;
            }
            return info;
        }
        catch(Exception e) {
            System.out.println(e);
            return null;
        }
    }
    // Retorna les dades de les columnes RED, GREEN, BLUE de la taula COLOR
    public int[] getColores(PApplet p5){
        int numFiles = getNumRowsTaula("color");
        int[] colores = new int[numFiles];
        try {
            ResultSet rs = query.executeQuery( "SELECT red AS R, green AS G, blue AS B FROM color c ORDER BY idColor ASC");
            int nr = 0;
            while (rs.next()) {
                int r = rs.getInt("R");
                int g = rs.getInt("G");
                int b = rs.getInt("B");
                colores[nr] = p5.color(r, g, b);
                nr++;
            }
            return colores;
        }
        catch(Exception e) {
            System.out.println(e);
            return null;
        }
    }

    public String getIdColor(float r, float g, float b){
        String id="";
        String q = "SELECT idColor AS id FROM color c WHERE red = '"+r+"' AND green='"+g+"' AND blue='"+b+"'";
        try {
            ResultSet rs = query.executeQuery( q);
            rs.next();
            return String.valueOf(rs.getInt("id"));
        }
        catch(Exception e) {
            System.out.println(e);
            return null;
        }
    }

    // Determina si l'usuari i contrasenya introduits són a la base de dades
    public boolean isValidUser(String userName, String password){
        String q = "SELECT COUNT(*) AS n FROM usuario WHERE idUsuario = '"+userName+"' AND password='"+password+"'";
        try {
            ResultSet rs = query.executeQuery( q);
            rs.next();
            return rs.getInt("n")==1;
        }
        catch(Exception e) {
            System.out.println(e);
            return false;
        }
    }

    void insertOrnament(String idCollar, String posX, String posY, String idColor, String idForma){
        try {
            String q = "INSERT INTO `ornaments` (`idOrnament`, `posX`, `posY`, `idCollar`, `idColor`, `idForma`) "+
                        "VALUES (NULL, '"+ posX +"','" + posY +"', '"+idCollar +"', '"+idColor+"', '"+idForma+"');";
            System.out.println(q);
            query.execute(q);
        }
        catch(Exception e) {
            System.out.println(e);
        }
    }

    void insertCollariOrnaments(PApplet p5, Collar c, String idCollar){
        try {
            String q = "INSERT INTO `collar` (`idCollar`, `numOrnaments`) VALUES ('"+idCollar+"', '"+c.numOrnaments+"');";
            System.out.println(q);
            query.execute(q);

            for(int i=0; i<c.numOrnaments; i++){
                String posX = String.valueOf(c.ornaments[i].x);
                String posY = String.valueOf(c.ornaments[i].y);
                String forma ="";
                if(c.ornaments[i] instanceof OrnamentCercle){
                    forma ="1";
                }
                else if(c.ornaments[i] instanceof OrnamentTriangle){
                    forma ="0";
                }
                else if(c.ornaments[i] instanceof OrnamentEstrella){
                    forma ="2";
                }

                float r = p5.red(c.ornaments[i].color);
                float g = p5.green(c.ornaments[i].color);
                float b = p5.blue(c.ornaments[i].color);
                String idColor = getIdColor(r, g, b);

                insertOrnament(idCollar, posX, posY, idColor, forma);
            }
        }
        catch(Exception e) {
            System.out.println(e);
        }
    }

    public String[][] getInfoTaulaCollar(){
        int numFiles = getNumRowsTaula("collar");
        int numCols  = 2;
        String[][] info = new String[numFiles][numCols];
        try {
            ResultSet rs = query.executeQuery( "SELECT * FROM collar");
            int nr = 0;
            while (rs.next()) {
                info[nr][0] = String.valueOf(rs.getInt("idCollar"));
                info[nr][1] = rs.getString("numOrnaments");
                nr++;
            }
            return info;
        }
        catch(Exception e) {
            System.out.println(e);
            return null;
        }
    }

    public String[][] getInfoTaulaCollar(int pagina){
        int offset = (pagina-1) * 4;
        String qn = "SELECT COUNT(*) As n FROM (SELECT * FROM COLLAR LIMIT 4 OFFSET "+offset+ ") AS CN";
        int numFiles = getNumRowsQuery(qn);
        int numCols  = 2;
        String[][] info = new String[numFiles][numCols];
        try {
            String q = "SELECT * FROM COLLAR LIMIT 4 OFFSET "+offset;
            System.out.println(q);
            ResultSet rs = query.executeQuery( q);
            int nr = 0;
            while (rs.next()) {
                info[nr][0] = rs.getString("idCollar");
                info[nr][1] = String.valueOf(rs.getInt("numOrnaments"));
                nr++;
            }
            return info;
        }
        catch(Exception e) {
            System.out.println(e);
            return null;
        }
    }

    public String getImagenCollar(){

        String q = "SELECT idCollar FROM collar ORDER BY RAND()";
        try {
            ResultSet rs = query.executeQuery( q);
            rs.next();
            return rs.getString("idCollar")+ ".jpg";
        }
        catch(Exception e) {
            System.out.println(e);
            return "aaaa.jpg";
        }
    }

}
