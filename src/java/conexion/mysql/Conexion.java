package conexion.mysql;

import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * Conexion al SGBD MySQL
 * 
 * @author Nico
 */
public class Conexion extends conexion.Conexion {

    public static Conexion getOrCreate() {
        return new Conexion();
    }
    
    private Conexion() {
        host = "localhost";
        baseDeDatos = "imc";
        puerto = 3306;
        usuario = "root";
        password = "root";
    }

    @Override
    public void conectar() {
        if (this.estaConectado()) {
            return;
        }
        
        try {
            Class.forName("com.mysql.jdbc.Driver").newInstance();
        } catch (InstantiationException | IllegalAccessException | 
                ClassNotFoundException ex) {
            System.out.println(ex);
        }

        String URL = "jdbc:mysql://" + host + ":" + puerto + "/" + baseDeDatos
                + "?useSSL=false";
        
        try {
            con = DriverManager.getConnection(URL, usuario, password);
        } catch (SQLException ex) {
            System.out.println(ex);
        }
    }

    @Override
    public void desconectar() {
        try {
            if (this.estaConectado()) {
                con.close();
            }
        } catch (SQLException ex) {
            System.out.println(ex);
        }
    }

    @Override
    public boolean estaConectado() {
        if (this.con == null) {
            return false;
        }
        
        try {
            if (this.con.isClosed()) {
                return false;
            }
        } catch (SQLException ex) {
            this.con = null;
            return false;
        }
        
        return true;
    }

    @Override
    public void comenzarTransaccion() {
        if (!this.estaConectado()) {
            this.conectar();
        }
        
        try {
            con.setAutoCommit(false);
        } catch (SQLException ex) {
            System.out.println(ex);
        }
    }

    @Override
    public void terminarTransaccion() {
        try {
            con.commit();
        } catch (SQLException ex) {
            System.out.println(ex);
        }
    }

    @Override
    public ResultSet ejecutar(String consulta) {
        try {
            Statement stmt = con.createStatement();
            ResultSet res = stmt.executeQuery(consulta);
            return res;
        } catch (SQLException ex) {
            System.out.println(ex);
            return null;
        }
    }

    @Override
    public int ejecutarSimple(String sentencia) {
        try {
            Statement stmt = con.createStatement();
            int nb = stmt.executeUpdate(sentencia);
            return nb;
        } catch (SQLException ex) {
            System.out.println(ex);
            return 0;
        }
    }

    @Override
    public int ejecutarInsert(String sentencia) {
        try {
            Statement stmt = con.createStatement();
            stmt.executeUpdate(sentencia, Statement.RETURN_GENERATED_KEYS);
            ResultSet rs = stmt.getGeneratedKeys();
            rs.next();
            return rs.getInt(1);
        } catch (SQLException ex) {
            System.out.println(ex);
            return 0;
        }
    }

}
