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

    // Retorna les dades d'una taula en concret
    public String[][] getInfoTaulaUnitat(){
        int numFiles = getNumRowsTaula("unitat");
        int numCols  = 2;
        String[][] info = new String[numFiles][numCols];
        try {
            ResultSet rs = query.executeQuery( "SELECT * FROM unitat");
            int nr = 0;
            while (rs.next()) {
                info[nr][0] = String.valueOf(rs.getInt("numero"));
                info[nr][1] = rs.getString("nom");
                nr++;
            }
            return info;
        }
        catch(Exception e) {
            System.out.println(e);
            return null;
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
}
