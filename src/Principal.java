import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.InputMismatchException;
import java.util.Scanner;

public class Principal {

	public static void main(String[] args) {
		int opc = 0, indice = 0, subMenu = 0, id;
		LocalDate fecha;
		LocalTime hora;
		String paciente, tipo;
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("H:mm");
		Implementacion imp = new Implementacion();
		Cita cita;
		Scanner lectura = new Scanner(System.in);

		do {
			System.out.println("\n +*+*+*+*+* BIENVENIDO A CITA PLUS +*+*+*+*+* \n ");
			System.out.println("1.- Registrar");
			System.out.println("2.- Visualizar Citas");
			System.out.println("3.- Buscar");
			System.out.println("4.- Editar");
			System.out.println("5.- Eliminar");
			System.out.println("6.- Salir \n");

			try {
				// Validaci�n de entrada con try-catch
				System.out.print("Ingresa tu opci�n: ");
				opc = lectura.nextInt();

				switch (opc) {
				case 1:
					try {
				
						System.out.println("Ingrese el ID: ");
						id = lectura.nextInt();

						System.out.println("Ingrese la fecha (Formato: yyyy-MM-dd): ");
						lectura.nextLine(); 
						String fechaIngresada = lectura.nextLine();
						fecha = LocalDate.parse(fechaIngresada);

						System.out.println("Ingrese la Hora (Formato: HH:mm): ");
						String horaIngresada = lectura.nextLine();
						hora = LocalTime.parse(horaIngresada, formatter);

						System.out.println("Ingrese el nombre del paciente: ");
						paciente = lectura.nextLine();

						System.out.println("Ingrese el tipo de cita General/Seguimiento/Especializada: ");
						tipo = lectura.nextLine();

						cita = new Cita(id, fecha, hora, paciente, tipo);
						if (!imp.guardar(cita)) {
							System.out.println("Cita registrada exitosamente :)");
						} else {
							System.out.println("ID en uso. Intente con uno nuevo");
						}

					} catch (Exception e) {
						System.out.println("Por favor ingresa de manera correcta los datos.");
					}
					break;

				case 2:
					if (imp.listar().size() > 0) {
						System.out.println("\nListado de citas: \n");
						System.out.println(imp.listar());
					} else {
						System.out.println("Por el momento no tienes citas registradas.");
					}
					break;

				case 3:
					if (imp.listaVacia()) {
						System.out.println("La lista de citas est� vac�a. No hay elementos para buscar.");
					} else {
						imp.indiceNombre();
						System.out.println("Ingresa el �ndice a buscar:");
						try {
							indice = lectura.nextInt();
							cita = imp.buscar(indice);
							System.out.println("El resultado de la b�squeda es el siguiente: " + cita);
						} catch (Exception e) {
							System.out.println("Ese �ndice no existe en la lista.");
						}
					}
					break;

				case 4:
					if (imp.listaVacia()) {
						System.out.println("Lo siento, no hay registros para editar :(");
					} else {
						imp.indiceNombre();

						System.out.println("�Qu� �ndice deseas editar?");
						try {
							indice = lectura.nextInt();
							cita = imp.buscar(indice);
							System.out.println(cita);
							do {
								boolean validSubMenu = false; // Variable para controlar la validaci�n del submen�
								do {
									try {
										System.out.println("Elige el campo que deseas editar \n");
										System.out.println("1.- Paciente");
										System.out.println("2.- Fecha");
										System.out.println("3.- Regresar");

										subMenu = lectura.nextInt();

										if (subMenu >= 1 && subMenu <= 3) {
											validSubMenu = true; // Solo salimos del bucle si el valor est� entre 1 y 3
										} else {
											System.out.println(
													"Opci�n inv�lida. Por favor, ingresa un n�mero entre 1 y 3.");
										}

									} catch (InputMismatchException e) {
										System.out.println("Error: Ingresa un n�mero v�lido.");
										lectura.next(); // Limpiar la entrada incorrecta
									}
								} while (!validSubMenu);

								switch (subMenu) {
								case 1:
									System.out.println("Ingresa el nuevo nombre del Paciente:");
									lectura.nextLine(); 
									paciente = lectura.nextLine();
									cita.setPaciente(paciente);
									imp.editar(indice, cita);
									System.out.println("�Se edit� correctamente! \n");
									System.out.println("Lista actualizada: \n" + imp.listar());
									break;

								case 2:
									boolean fechaValida = false; // Variable de control para verificar si la fecha es
																	// v�lida
									do {
										try {
											System.out.println("Ingresa la nueva fecha de la cita (Formato: yyyy-MM-dd):");
											lectura.nextLine(); // Consumir la l�nea anterior sobrante
											String nFechaIngresada = lectura.nextLine();
											// parsear la fecha
											fecha = LocalDate.parse(nFechaIngresada);
											// Si la fecha es v�lida, salir del bucle
											fechaValida = true;
											// Actualizar la fecha en la cita
											cita.setFecha(fecha);
											imp.editar(indice, cita);
											System.out.println("�Se edit� correctamente! \n");
											System.out.println("Lista actualizada: \n " + imp.listar());

										} catch (DateTimeParseException e) {
											// Si ocurre una excepci�n, mostrar un mensaje de error y pedir de nuevo la
											// fecha
											System.out.println("Formato de fecha inv�lido. Aseg�rate de usar el formato correcto (yyyy-MM-dd).");
										}
									} while (!fechaValida); // Repetir hasta que la fecha sea v�lida
									break;

								case 3:
									System.out.println("Regresando...");
									break;
								}
							} while (subMenu < 3);

						} catch (Exception e) {
							System.out.println("El �ndice no se encuentra en la lista.");
						}
					}
					break;

				case 5:
					if (imp.listaVacia()) {
						System.out.println("Ups, no hay registros para poder eliminar.");
					} else {
						try {
							imp.indiceNombre();
							System.out.println("Ingresa el �ndice que deseas eliminar:");
							indice = lectura.nextInt();
							imp.eliminar(indice);
							System.out.println("Registro eliminado con �xito.");
						} catch (Exception e) {
							System.out.println("Ese �ndice no existe. Ingresa uno v�lido.");
						}
					}
					break;

				case 6:
					System.out.println("Saliendo...");
					break;

				default:
					System.out.println("Opci�n no v�lida. Por favor, ingresa una opci�n entre 1 y 6.");
					break;
				}
			} catch (InputMismatchException e) {
				// Si el usuario ingresa una letra o s�mbolo en lugar de un n�mero
				System.out.println("\n Error: Ingresa un n�mero v�lido.");
				lectura.next(); 
			}

		} while (opc != 6);
	}
}
