import java.util.ArrayList;
import java.util.List;

public class Implementacion implements Metodos{
	
	//Creamos lista para almacenar registros
	private List<Cita> listaCitas = new ArrayList<Cita>();

	@Override
	public boolean guardar(Cita cita) {
		// TODO Auto-generated method stub
		boolean bandera= false;
		//Ciclo y Condicional--- Verifica que no exista un registro ya con ese id, ya que el ID no se pude repetir
		for (Cita c : listaCitas) {
			if (c.getId()== cita.getId()) {
				bandera = true;
				break;
			}
		}
			if (bandera== false) 
				listaCitas.add(cita);
				return bandera;
	
	}

	@Override
	public List<Cita> listar() {
		// TODO Auto-generated method stub
		return listaCitas;
	}

	@Override
	public Cita buscar(int indice) {
		// TODO Auto-generated method stub
		return listaCitas.get(indice);
	}

	@Override
	public void editar(int indice, Cita cita) {
		// TODO Auto-generated method stub
		listaCitas.set(indice, cita);
		
	}

	@Override
	public void eliminar(int indice) {
		// TODO Auto-generated method stub
		listaCitas.remove(indice);
		
	}
	public void indiceNombre() {
		
		System.out.println("Lista de registros por índice:");
		System.out.println("----------------------");
		for (int i = 0; i < listaCitas.size(); i++) {
			System.out.println("["+i+"]"+ listaCitas.get(i).getPaciente());
		
			
		}
		System.out.println("----------------------");
	}
    public boolean listaVacia() {
        return listaCitas.isEmpty();
    }
	
	

}
