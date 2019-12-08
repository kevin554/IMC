package dto;

public class Usuarios extends DTO {

    private int codigoID;
    private String fechaNacimiento;
    private String sexo;
    private double altura;

    public Usuarios() {
    }

    public Usuarios(int codigoID, String fechaNacimiento, String sexo,
            double altura) {
	this.codigoID = codigoID;
	this.fechaNacimiento = fechaNacimiento;
	this.sexo = sexo;
	this.altura = altura;
    }

    // <editor-fold defaultstate="collapsed" desc="getters y setters">

    public int getCodigoID() {
	return codigoID;
    }

    public void setCodigoID(int codigoID) {
	this.codigoID = codigoID;
    }

    public String getFechaNacimiento() {
	return fechaNacimiento;
    }

    public void setFechaNacimiento(String fechaNacimiento) {
	this.fechaNacimiento = fechaNacimiento;
    }

    public String getSexo() {
	return sexo;
    }

    public void setSexo(String sexo) {
	this.sexo = sexo;
    }

    public double getAltura() {
	return altura;
    }

    public void setAltura(double altura) {
	this.altura = altura;
    }

    // </editor-fold>

    @Override
    public String toString() {
	return "Usuarios{" + "codigoID=" + codigoID + ", fechaNacimiento="
                + fechaNacimiento + ", sexo=" + sexo + ", altura=" + altura
                + '}';
     }

}