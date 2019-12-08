package dao;

import dto.EstadoFisico;
import java.util.ArrayList;

public abstract class EstadoFisicoDAO implements IDAO {

    /**
     * Devuelve una lista con todos los registros de la tabla
     * Estado Fisico
     * @return
     * @throws Exception 
     */
    public abstract ArrayList<EstadoFisico> seleccionarTodos()
            throws Exception;
    
    public abstract EstadoFisico ultimaActualizacion(Object llave)
            throws Exception;
    
    public abstract ArrayList<EstadoFisico> todosPorFechaAscendente(Object llave)
            throws Exception;
    
}