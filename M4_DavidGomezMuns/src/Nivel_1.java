import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.swing.JOptionPane;

public class Nivel_1 {
	//Atributos de clase
	private static String[] platosArray = new String[3];
	private static int[] preciosArray = new int[platosArray.length];
	//private static int cinco = 5, diez = 10, veinte = 20, cincuenta = 50, cien = 100, doscientos = 200, quinientos = 500;
	private static Map<String, Integer> platosMap = new HashMap<String, Integer>();
	private static List<String> platosList = new ArrayList<String>();
	
	//Método main
	public static void main(String[] args) {
		
		//***********************
		//Llamar métodos de clase
		//***********************
		
		System.out.println("*** CREAR CARTA ***\n");
		//Intoducir datos en arrays
		fase_1();
		
		System.out.println("\n*** MOSTRAR CARTA ***\n");
		//Generar carta de platos y solicitar pedido
		fase_2();
		
		System.out.println("\n*** IMPRIMIR FACTURA ***\n");
		//Comparar carta con pedido cliente e imprimir importe
		fase_3();
	}

	//Getters
	public static String[] getPlatosArray() {
		return platosArray;
	}
	public static Map<String, Integer> getPlatosMap() {
		return platosMap;
	}
	
	//Setters
	public static void setPlatosArray(String[] platosArray) {
		Nivel_1.platosArray = platosArray;
	}
	public static void setPlatosMap(Map<String, Integer> platosMap) {
		Nivel_1.platosMap = platosMap;
	}
	
	//Método para inicializar arrays 
	public static void fase_1() {
		//Recorrer array platos para añadir elementos
		for(int i = 0; i < platosArray.length; i++) {
			platosArray[i] = JOptionPane.showInputDialog(null,"Introduce nombre plato nº " + (i+1), "CREAR CARTA (nombres)", 3);
			
			//Detener runtime si usuario pulsa boton cancelar
			if(platosArray[i] == null) {
				System.out.println("Programa cancelado...");
				System.exit(0);
			}
			
			System.out.println((i+1) + ".- " + platosArray[i]);	
			
		}
		
		System.out.println();
		
		String chekIfInt;
		int pvp;
		//Crear instancia de clase 
		Nivel_2_y_3 n2 = new Nivel_2_y_3();
		
		//Recorrer array precios para añadir elementos
		for(int i = 0; i < preciosArray.length; i++) {
			chekIfInt = JOptionPane.showInputDialog(null, "Introduce precio " + platosArray[i], "CREAR CARTA (precios)", 3);
			
			//Detener runtime si usuario pulsa boton cancelar
			if(chekIfInt == null) {
				System.out.println("Programa cancelado...");
				System.exit(0);
			}
			
			try {
				//Lanzar catch si el dato introducido no es un integer
				n2.esUnInteger(chekIfInt);
				
				//Si el dato no es un integer estas líneas no se ejecutan
				pvp = Integer.parseInt(chekIfInt);//Convertir datos
				
				//Añadir precio introducido por usuario a array
				preciosArray[i] = pvp;
				
				System.out.println((i+1) + ".- " + preciosArray[i] + "€");
				
			} catch (Exception e) {
				JOptionPane.showMessageDialog(null, "El dato introducido no es válido", "ERROR", 2);
				i--;
			}
		}
	}
	
	//Método para añadir elementos de array a HasMap, imprimir carta completa y solicitar pedido a usuario
	public static void fase_2() {
		//Recorrer array platos para añadir elementos de arrays a HashMap
		for(int i = 0; i < platosArray.length; i++) {
			platosMap.put(platosArray[i], preciosArray[i]);
		}
		
		//Recorrer HashMap para imprimir elementos
		for(Map.Entry<String, Integer> in: platosMap.entrySet()) {
			String key = in.getKey();
			int value = in.getValue();
			System.out.println("Plato: " + key + ", Precio: " + value + "€");
		}
		
		String salir = "";
		int cont = 1;
		Nivel_2_y_3 n2 = new Nivel_2_y_3();
		String nombrePlato;
		//Solicitar datos a usuario mientras condición de salida sea distinta a cero
		while(!salir.equals("0")) {		
			nombrePlato = JOptionPane.showInputDialog(null, "Introduce nombre plato nº " + cont, "REALIZAR PEDIDO", 3);
			
			try {
				//Lanzar catch si el plato no existe
				n2.platoExiste(nombrePlato);
				
				//Si el plato no existe estas líneas no se ejecutan
				platosList.add(nombrePlato);
				cont++;
			} catch (Exception e) {
				JOptionPane.showMessageDialog(null, "No tenemos " + nombrePlato, "PLATO NO EXISTE", 2);
			}
			
			salir = JOptionPane.showInputDialog(null, "¿Quieres pedir más platos? (SI = cualquier tecla | NO = 0)", "CONTINUAR / SALIR", 3);
			
			//Detener runtime si usuario pulsa boton cancelar
			if(salir == null) {
				System.out.println("Programa cancelado...");
				System.exit(0);
			}				
		}	
	}
	
	//Método para comparar elementos de array con lista de platos, imprimir resultado comparación y mostrar importe total
	public static void fase_3() {
		int cont = 0, cont2 = 0;;
		int totalFactura = 0;
		String printMenu = "";
		
		//Recorrer lista de platos
		for(int i = 0; i < platosList.size(); i++) {
			//Recorrer array de platos
			for(int j = 0; j < platosArray.length; j++) {
				//Guardar suma en variable si existe el plato e incrementar contador (cont)
				if(platosArray[j].equalsIgnoreCase(platosList.get(i))) {
					totalFactura += preciosArray[j];
					
					cont2++;
					if(cont2 == 1) printMenu = platosArray[j];
						else printMenu +=  " + " + platosArray[j];
					
					cont++;
				}
			}
			//Imprimir platos que no estan en la lista 
			if(cont == 0) System.out.println("No tenemos " + platosList.get(i));
			cont = 0;
		}
		//Imprimir factura
		System.out.println("Precio total: " + printMenu + " = " + totalFactura + "€");
	}
	
}
