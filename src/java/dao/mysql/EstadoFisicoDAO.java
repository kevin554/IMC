package dao.mysql;

import conexion.Conexion;
import dto.DTO;
import dto.EstadoFisico;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 * La implementacion DAO para MySQL de la tabla EstadoFisico
 */
public class EstadoFisicoDAO extends dao.EstadoFisicoDAO {

    @Override
    public ArrayList<EstadoFisico> seleccionarTodos() throws Exception {
	ArrayList<EstadoFisico> lista = new ArrayList<>();

	Conexion con = Conexion.getOrCreate();

	String consulta = "select codigo_id, peso, porcentaje_grasa, "
                + "actividad_fisica, fecha_registro, usuario_id "
                + "from tblestado_fisico order by codigo_id asc";
	ResultSet rs = con.ejecutar(consulta);

	if (rs == null) {
	    throw new Exception("Error al traer datos");
	}

	while (rs.next()) {
	    EstadoFisico obj = obtenerObjDeResultSet(rs);
	    lista.add(obj);
	}

	return lista;
    }

    @Override
    public DTO seleccionar(Object llave) throws Exception {
	if (!(llave instanceof Integer)) {
	    throw new Exception("La llave debe ser un entero");
	}

	int ID = (int) llave;
	if (ID <= 0) {
	    throw new Exception("El ID debe ser un entero positivo");
	}

	Conexion con = Conexion.getOrCreate();

	String consulta = "select codigo_id, peso, porcentaje_grasa, "
                + "actividad_fisica, fecha_registro, usuario_id "
                + "from tblestado_fisico where codigo_id = " + ID;

	ResultSet rs = con.ejecutar(consulta);

	if (rs == null) {
	    throw new Exception("Error al traer datos");
	}

	if (!rs.next()) {
	    throw new Exception("No se encontro obj Estado Fisico con ID:" + ID);
	}

	return obtenerObjDeResultSet(rs);
}

    @Override
    public void insertar(DTO obj) throws Exception {
	if (obj == null) {
	    throw new Exception("No se puede insertar un objeto nulo");
	}

	Conexion con = Conexion.getOrCreate();

	EstadoFisico objEstadoFisico = (EstadoFisico) obj;

	if (objEstadoFisico.getPeso() <= 0) {
	    throw new Exception("El peso no puede ser menor o igual que cero");
	}

	if (objEstadoFisico.getPorcentajeGrasa() <= 0) {
	    throw new Exception("El porcentaje de grasa no puede ser menor o "
                    + "igual que cero");
	}

	if (objEstadoFisico.getActividadFisica().trim().isEmpty()) {
	    throw new Exception("La actividad fisica no puede estar vacia");
	}

	if (objEstadoFisico.getFechaRegistro().trim().isEmpty()) {
	    throw new Exception("La fecha de registro no puede estar vaci1");
	}

	if (objEstadoFisico.getUsuarioID() <= 0) {
	    throw new Exception("El ID del Usuario no puede ser menor o igual "
                    + "que cero");
	}

	String sentencia = "insert into tblestado_fisico(peso, porcentaje_grasa, "
                + "actividad_fisica, fecha_registro, usuario_id) "
                + "values (" + objEstadoFisico.getPeso() + ", "
                + objEstadoFisico.getPorcentajeGrasa() + ", " + "'"
                + objEstadoFisico.getActividadFisica() + "'" + ", " + "'"
                + objEstadoFisico.getFechaRegistro() + "'" + ", "
                + objEstadoFisico.getUsuarioID() + ")";

	int idGenerado = con.ejecutarInsert(sentencia);
	if (idGenerado == 0) {
	    throw new Exception("Error: no se pudo insertar los datos");
	}

	objEstadoFisico.setCodigoID(idGenerado);
    }

    @Override
    public void actualizar(DTO obj) throws Exception {
	if (obj == null) {
	    throw new Exception("No se puede actualizar un objeto nulo");
	}

	EstadoFisico objEstadoFisico = (EstadoFisico) obj;

	if (objEstadoFisico.getCodigoID() <= 0) {
	    throw new Exception("El ID no puede ser menor o igual que cero");
	}

	if (objEstadoFisico.getPeso() <= 0) {
	    throw new Exception("El peso no puede ser menor o igual que cero");
	}

	if (objEstadoFisico.getPorcentajeGrasa() <= 0) {
	    throw new Exception("El porcentaje de grasa no puede ser menor o "
                    + "igual que cero");
	}

	if (objEstadoFisico.getActividadFisica().trim().isEmpty()) {
	    throw new Exception("La actividad fisica no puede estar vacia");
	}

	if (objEstadoFisico.getFechaRegistro().trim().isEmpty()) {
	    throw new Exception("La fecha de registro no puede estar vacia");
	}

	if (objEstadoFisico.getUsuarioID() <= 0) {
	    throw new Exception("El ID del usuario no puede ser menor o igual "
                    + "que cero");
	}

	String sentencia = "update tblestado_fisico set" + " peso = "
                + objEstadoFisico.getPeso() + ", " + " porcentaje_grasa = "
                + objEstadoFisico.getPorcentajeGrasa() + ", "
                + " actividad_fisica = " + "'"
                + objEstadoFisico.getActividadFisica() + "'" + ", "
                + " fecha_registro = " + "'"
                + objEstadoFisico.getFechaRegistro() + "'" + ", "
                + " usuario_id = " + objEstadoFisico.getUsuarioID()
                + " where codigo_id = " + objEstadoFisico.getCodigoID();

	Conexion con = Conexion.getOrCreate();
	int filasAfectadas = con.ejecutarSimple(sentencia);

	if (filasAfectadas == 0) {
	    throw new Exception("Error: no se pudo actualizar los datos");
	}
    }

    @Override
    public void eliminar(DTO obj) throws Exception {
	if (obj == null) {
	    throw new Exception("No se puede eliminar un objeto nulo");
	}

	EstadoFisico objEstadoFisico = (EstadoFisico) obj;

	if (objEstadoFisico.getCodigoID() <= 0) {
	    throw new Exception("no se puede eliminar un objeto con ID <= 0");
	}

	Conexion con = Conexion.getOrCreate();

	String sentencia = "delete from tblestado_fisico where codigo_id = "
                + objEstadoFisico.getCodigoID();

	int filasAfectadas = con.ejecutarSimple(sentencia);

	if (filasAfectadas == 0) {
	    throw new Exception("Error: no se pudo eliminar los datos");
	}
    }

    private EstadoFisico obtenerObjDeResultSet(ResultSet rs) throws SQLException {
	EstadoFisico obj = new EstadoFisico();
	
	obj.setCodigoID(rs.getInt("codigo_id"));
	obj.setPeso(rs.getDouble("peso"));
	obj.setPorcentajeGrasa(rs.getDouble("porcentaje_grasa"));
	obj.setActividadFisica(rs.getString("actividad_fisica"));
	obj.setFechaRegistro(rs.getString("fecha_registro"));
	obj.setUsuarioID(rs.getInt("usuario_id"));

	return obj;
    }
    
    @Override
    public EstadoFisico ultimaActualizacion(Object llave) throws Exception {
        if (!(llave instanceof Integer)) {
            throw new IllegalArgumentException("el ID del usuario debe ser un entero");
        }

        int usuarioID = (int) llave;
        if (usuarioID < 0) {
            throw new IllegalArgumentException("el ID del usario debe ser un entero positivo");
        }

        Conexion con = Conexion.getOrCreate();
        
        String consulta = "select codigo_id, peso, porcentaje_grasa, "
                + "actividad_fisica, fecha_registro, usuario_id "
                + "from tblestado_fisico where usuario_id = " + usuarioID
                + " order by fecha_registro desc limit 1";

        ResultSet rs = con.ejecutar(consulta);

	if (rs == null) {
	    throw new Exception("Error al traer datos");
	}

	if (!rs.next()) {
	    throw new Exception("No se encontro la ultima activad fisica");
	}

	return obtenerObjDeResultSet(rs);
    }
    
    @Override
    public ArrayList<EstadoFisico> todosPorFechaAscendente(Object llave) throws Exception{
        if (!(llave instanceof Integer)) {
            throw new IllegalArgumentException("el ID del usuario debe ser un "
                    + "entero");
        }

        int usuarioID = (int) llave;
        if (usuarioID < 0) {
            throw new IllegalArgumentException("el ID del usario debe ser un "
                    + "entero positivo");
        }

        Conexion con = Conexion.getOrCreate();

        String consulta = "select codigo_id, peso, porcentaje_grasa, "
                + "actividad_fisica, fecha_registro, usuario_id "
                + "from tblestado_fisico where usuario_id = " + usuarioID
                + " order by fecha_registro asc";
        
        ArrayList<EstadoFisico> lista = new ArrayList<>();
        
        ResultSet rs = con.ejecutar(consulta);

	if (rs == null) {
	    throw new Exception("Error al traer datos");
	}

	while (rs.next()) {
	    EstadoFisico obj = obtenerObjDeResultSet(rs);
	    lista.add(obj);
	}

	return lista;
    }
    
}