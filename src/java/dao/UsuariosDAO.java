package dao;

import dto.Usuarios;
import java.util.ArrayList;

public abstract class UsuariosDAO implements IDAO {

    /**
     * Devuelve una lista con todos los registros de la tabla
     * Usuarios
     * @return
     * @throws Exception 
     */
    public abstract ArrayList<Usuarios> seleccionarTodos() throws Exception;

}