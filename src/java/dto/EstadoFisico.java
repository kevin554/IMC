package dto;

public class EstadoFisico extends DTO {

    private int codigoID;
    private double peso;
    private double porcentajeGrasa;
    private String actividadFisica;
    private String fechaRegistro;
    private int usuarioID;

    public EstadoFisico() {
    }

    public EstadoFisico(int codigoID, double peso, double porcentajeGrasa,
            String actividadFisica, String fechaRegistro, int usuarioID) {
	this.codigoID = codigoID;
	this.peso = peso;
	this.porcentajeGrasa = porcentajeGrasa;
	this.actividadFisica = actividadFisica;
	this.fechaRegistro = fechaRegistro;
	this.usuarioID = usuarioID;
    }

    // <editor-fold defaultstate="collapsed" desc="getters y setters">

    public int getCodigoID() {
	return codigoID;
    }

    public void setCodigoID(int codigoID) {
	this.codigoID = codigoID;
    }

    public double getPeso() {
	return peso;
    }

    public void setPeso(double peso) {
	this.peso = peso;
    }

    public double getPorcentajeGrasa() {
	return porcentajeGrasa;
    }

    public void setPorcentajeGrasa(double porcentajeGrasa) {
	this.porcentajeGrasa = porcentajeGrasa;
    }

    public String getActividadFisica() {
	return actividadFisica;
    }

    public void setActividadFisica(String actividadFisica) {
	this.actividadFisica = actividadFisica;
    }

    public String getFechaRegistro() {
	return fechaRegistro;
    }

    public void setFechaRegistro(String fechaRegistro) {
	this.fechaRegistro = fechaRegistro;
    }

    public int getUsuarioID() {
	return usuarioID;
    }

    public void setUsuarioID(int usuarioID) {
	this.usuarioID = usuarioID;
    }

    // </editor-fold>

    @Override
    public String toString() {
	return "EstadoFisico{" + "codigoID=" + codigoID + ", peso=" + peso
                + ", porcentajeGrasa=" + porcentajeGrasa + ", actividadFisica="
                + actividadFisica + ", fechaRegistro=" + fechaRegistro
                + ", usuarioID=" + usuarioID + '}';
     }

}