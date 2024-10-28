import java.time.LocalDate;

import java.time.LocalTime;

public class Cita {

	private int id;
	private LocalDate fecha;
	private LocalTime hora;
	private String paciente;
	private String tipo;
	public Cita() {
	}
	public Cita(int id, LocalDate fecha, LocalTime hora, String paciente, String tipo) {
		this.id = id;
		this.fecha = fecha;
		this.hora = hora;
		this.paciente = paciente;
		this.tipo = tipo;
	}
	@Override
	public String toString() {
		return "Cita [id=" + id + ", fecha=" + fecha + ", hora=" + hora + ", paciente=" + paciente + ", tipo=" + tipo
				+ "]\n";
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public LocalDate getFecha() {
		return fecha;
	}
	public void setFecha(LocalDate fecha) {
		this.fecha = fecha;
	}
	public LocalTime getHora() {
		return hora;
	}
	public void setHora(LocalTime hora) {
		this.hora = hora;
	}
	public String getPaciente() {
		return paciente;
	}
	public void setPaciente(String paciente) {
		this.paciente = paciente;
	}
	public String getTipo() {
		return tipo;
	}
	public void setTipo(String tipo) {
		this.tipo = tipo;
	}
	
	
}
