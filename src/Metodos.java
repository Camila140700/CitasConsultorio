import java.util.List;

public interface Metodos {
	public boolean guardar(Cita cita);

	public List<Cita> listar();

	public Cita buscar(int indice);

	public void editar(int indice, Cita cita);

	public void eliminar(int indice);

}
