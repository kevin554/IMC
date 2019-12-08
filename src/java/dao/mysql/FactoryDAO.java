package dao.mysql;

import dao.EstadoFisicoDAO;
import dao.UsuariosDAO;

/**
 * Devuelve implementaciones DAO para el SGBD MySQL
 */
public class FactoryDAO extends dao.FactoryDAO {

    public static FactoryDAO getOrCreate() {
	return new FactoryDAO();
    }

    private FactoryDAO() {
    }

    @Override
    public UsuariosDAO newUsuariosDAO() {
	return new dao.mysql.UsuariosDAO();
    }

    @Override
    public EstadoFisicoDAO newEstadoFisicoDAO() {
        return new dao.mysql.EstadoFisicoDAO();
    }

}