package conexion;

import java.sql.Connection;

public abstract class Conexion implements IConexion {

    protected String host;
    protected String baseDeDatos;
    protected int puerto;
    protected String usuario;
    protected String password;
    protected Connection con;
    protected static Conexion instancia;

    public static Conexion getOrCreate() {
        if (instancia == null) {
            instancia = conexion.mysql.Conexion.getOrCreate();
        }
            
        instancia.conectar();
        
        return instancia;
    }
    
    // <editor-fold defaultstate="collapsed" desc="getters">

    /**
     * Devuelve el Host
     * @return 
     */
    public String getHost() {
        return host;
    }

    /** 
     * Devuelve el nombre de la Base de Datos
     * @return 
     */
    public String getBaseDeDatos() {
        return baseDeDatos;
    }

    /**
     * Devuelve el puerto
     * @return 
     */
    public int getPuerto() {
        return puerto;
    }

    /**
     * Devuelve el nombre de usuario
     * @return 
     */
    public String getUsuario() {
        return usuario;
    }

    /**
     * Devuelve el password
     * @return 
     */
    public String getPassword() {
        return password;
    }

    /**
     * Devuelve la conexion
     * @return 
     */
    public Connection getCon() {
        return con;
    }
    
    // </editor-fold>
    
}
