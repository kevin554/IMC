package conexion;

import java.sql.ResultSet;

public interface IConexion {
    
    public void conectar();
    
    public void desconectar();
    
    /**
     * Verifica si el programa se encuentra conectado a la base
     * de datos
     * @return 
     */
    public boolean estaConectado();
    
    /**
     * Deshabilita el commit para comenzar una transaccion en
     * la base de datos
     */
    public void comenzarTransaccion();
    
    /**
     * Termina y guarda los cambios en la base de datos
     */
    public void terminarTransaccion();
    
    /**
     * Ejecuta una consulta en la base de datos
     * @param consulta
     * @return 
     */
    public ResultSet ejecutar(String consulta);
    
    /**
     * Ejecuta una sentencia en la base de datos
     * @param sentencia
     * @return La cantidad de registros afectados
     */
    public int ejecutarSimple(String sentencia);
        /**
     * Ejecuta una sentencia de tipo Insert en la base de datos
     * @param sentencia
     * @return El ID generado por la tabla afectada
     */
    public int ejecutarInsert(String sentencia);
    
}