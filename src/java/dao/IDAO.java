package dao;

import dto.DTO;

/**
 * Una interfaz generica con las operaciones basicas (Insertar,
 * Actualizar, Eliminar, Seleccionar) en una base de datos.
 * 
 * Los objetos que necesiten usar esta interfaz, deben extender
 * de la clase abstracta DTO
*/
public interface IDAO {

    /**
     * Selecciona un registro de la base de datos
     * @param llave El Id (llave primaria) del registro
     * @return 
     * @throws Exception 
     */
    DTO seleccionar(Object llave) throws Exception;

    /**
     * Inserta un registro a la base de datos
     * @param obj El objeto a insertar
     * @throws Exception 
     */
    void insertar(DTO obj) throws Exception;

    /**
     * Actualiza un regitro de la base de datos
     * @param obj El objeto a actualizar
     * @throws Exception 
     */
    void actualizar(DTO obj) throws Exception;

    /**
     * Elimina un registro de la base de datos
     * @param obj El objeto a eliminar
     * @throws Exception 
     */
    void eliminar(DTO obj) throws Exception;

}
