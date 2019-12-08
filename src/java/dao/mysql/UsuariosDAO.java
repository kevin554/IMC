package dao.mysql;

import conexion.Conexion;
import dto.DTO;
import dto.Usuarios;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 * La implementacion DAO para MySQL de la tabla Usuarios
 */
class UsuariosDAO extends dao.UsuariosDAO {

    @Override
    public ArrayList<Usuarios> seleccionarTodos() throws Exception {
	ArrayList<Usuarios> lista = new ArrayList<>();

	Conexion con = Conexion.getOrCreate();

	String consulta = "select codigo_id, fecha_nacimiento, sexo, altura "
                + "from tblusuarios order by codigo_id asc";
	ResultSet rs = con.ejecutar(consulta);

	if (rs == null) {
	    throw new Exception("Error al traer datos");
	}

	while (rs.next()) {
	    Usuarios obj = obtenerObjDeResultSet(rs);
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

	String consulta = "select codigo_id, fecha_nacimiento, sexo, altura "
                + "from tblusuarios where codigo_id = " + ID;

	ResultSet rs = con.ejecutar(consulta);

	if (rs == null) {
	    throw new Exception("Error al traer datos");
	}

	if (!rs.next()) {
	    throw new Exception("No se encontro obj Usuario con ID:" + ID);
	}

	return obtenerObjDeResultSet(rs);
}

    @Override
    public void insertar(DTO obj) throws Exception {
	if (obj == null) {
	    throw new Exception("No se puede insertar un objeto nulo");
	}

	Conexion con = Conexion.getOrCreate();

	Usuarios objUsuarios = (Usuarios) obj;

	if (objUsuarios.getFechaNacimiento().trim().isEmpty()) {
	    throw new Exception("La fecha de nacimiento no puede estar vacio");
	}

	if (objUsuarios.getSexo().trim().isEmpty()) {
	    throw new Exception("El sexo no puede estar vacio");
	}

	if (objUsuarios.getAltura() <= 0) {
	    throw new Exception("La altura no puede ser menor o igual que cero");
	}

	String sentencia = "insert into tblusuarios(fecha_nacimiento, sexo, "
                + "altura) values (" + "'" + objUsuarios.getFechaNacimiento()
                + "'" + ", " + "'" + objUsuarios.getSexo() + "'" + ", "
                + objUsuarios.getAltura() + ")";

	int idGenerado = con.ejecutarInsert(sentencia);
	if (idGenerado == 0) {
	    throw new Exception("Error: no se pudo insertar los datos");
	}

	objUsuarios.setCodigoID(idGenerado);
    }

    @Override
    public void actualizar(DTO obj) throws Exception {
	if (obj == null) {
	    throw new Exception("No se puede actualizar un objeto nulo");
	}

	Usuarios objUsuarios = (Usuarios) obj;

	if (objUsuarios.getCodigoID() <= 0) {
	    throw new Exception("El ID no puede ser menor o igual que cero");
	}

	if (objUsuarios.getFechaNacimiento().trim().isEmpty()) {
	    throw new Exception("La fecha de nacimiento no puede estar vacio");
	}

	if (objUsuarios.getSexo().trim().isEmpty()) {
	    throw new Exception("El sexo no puede estar vacio");
	}

	if (objUsuarios.getAltura() <= 0) {
	    throw new Exception("La altura no puede ser menor o igual que cero");
	}

	String sentencia = "update tblusuarios set" + " fecha_nacimiento = "
                + "'" + objUsuarios.getFechaNacimiento() + "'" + ", "
                + " sexo = " + "'" + objUsuarios.getSexo() + "'" + ", "
                + " altura = " + objUsuarios.getAltura() + " where codigo_id = "
                + objUsuarios.getCodigoID();

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

	Usuarios objUsuarios = (Usuarios) obj;

	if (objUsuarios.getCodigoID() <= 0) {
	    throw new Exception("no se puede eliminar un objeto con ID <= 0");
	}

	Conexion con = Conexion.getOrCreate();

	String sentencia = "delete from tblusuarios where codigo_id = "
                + objUsuarios.getCodigoID();

	int filasAfectadas = con.ejecutarSimple(sentencia);

	if (filasAfectadas == 0) {
	    throw new Exception("Error: no se pudo eliminar los datos");
	}
    }

    private Usuarios obtenerObjDeResultSet(ResultSet rs) throws SQLException {
	Usuarios obj = new Usuarios();
	
	obj.setCodigoID(rs.getInt("codigo_id"));
	obj.setFechaNacimiento(rs.getString("fecha_nacimiento"));
	obj.setSexo(rs.getString("sexo"));
	obj.setAltura(rs.getDouble("altura"));

	return obj;
    }

}