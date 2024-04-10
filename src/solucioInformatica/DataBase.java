package solucioInformatica;

import processing.core.PApplet;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

public class DataBase {

    // Variable de connexión a la BBDD
    Connection c;

    // Variable de consulta
    Statement query;

    // Datos de conexión (user, password, nombre de la base de datos)
    String user, password, databaseName;

    // Estado de la conexión
    boolean connected = false;

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
            connected = true;
        }
        catch(Exception e) {
            System.out.println(e);
        }
    }

    // Devuelve el número de filas de una tabla
    public int getNumRowsTable(String tableName){
        try {
            ResultSet rs = query.executeQuery( "SELECT COUNT(*) AS n FROM "+ tableName );
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

    // Devuelve el número de columnas de una tabla de la base de datos
    public int getNumColsTable(String tableName){
        try {
            String q = "SELECT count(*) as n FROM information_schema.columns WHERE table_name ='"+ tableName +"' AND table_schema='"+databaseName+"'";
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


    // Devuelve los datos de la columna idUsuario de la tabla USUARIO
    public String[] getColidUsuarioTableUsuario(){
        int numFiles = getNumRowsTable("usuario");
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

    // Devuelve los datos de las columnas RED, GREEN, BLUE de la tabla COLOR
    public int[] getColors(PApplet p5){
        int numRows = getNumRowsTable("color");
        int[] colors = new int[numRows];
        try {
            ResultSet rs = query.executeQuery( "SELECT red AS R, green AS G, blue AS B FROM color c ORDER BY idColor ASC");
            int nr = 0;
            while (rs.next()) {
                int r = rs.getInt("R");
                int g = rs.getInt("G");
                int b = rs.getInt("B");
                colors[nr] = p5.color(r, g, b);
                nr++;
            }
            return colors;
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

    // Determina si el usuario y la contraseña introducidos se encuentran en la base de datos
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

    void insertNecklaceAndOrnaments(PApplet p5, Necklace c, String idCollar){
        try {
            String q = "INSERT INTO `collar` (`idCollar`, `numOrnaments`) VALUES ('"+idCollar+"', '"+c.numOrnaments+"');";
            System.out.println(q);
            query.execute(q);

            for(int i=0; i<c.numOrnaments; i++){
                String posX = String.valueOf(c.ornaments[i].x);
                String posY = String.valueOf(c.ornaments[i].y);
                String figure ="";
                if(c.ornaments[i] instanceof RoundOrnament){
                    figure ="1";
                }
                else if(c.ornaments[i] instanceof TriangleOrnament){
                    figure ="0";
                }
                else if(c.ornaments[i] instanceof StarOrnament){
                    figure ="2";
                }

                float r = p5.red(c.ornaments[i].color);
                float g = p5.green(c.ornaments[i].color);
                float b = p5.blue(c.ornaments[i].color);
                String idColor = getIdColor(r, g, b);

                insertOrnament(idCollar, posX, posY, idColor, figure);
            }
        }
        catch(Exception e) {
            System.out.println(e);
        }
    }

    public String[][] getInfoNecklaceTable(){
        int numFiles = getNumRowsTable("collar");
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

    public String[][] getInfoNecklaceTable(int page){
        int offset = (page-1) * 4;
        String qn = "SELECT COUNT(*) As n FROM (SELECT * FROM COLLAR LIMIT 4 OFFSET "+offset+ ") AS CN";
        int numRows = getNumRowsQuery(qn);
        int numCols  = 2;
        String[][] info = new String[numRows][numCols];
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

    public String getNecklaceImage(){

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
