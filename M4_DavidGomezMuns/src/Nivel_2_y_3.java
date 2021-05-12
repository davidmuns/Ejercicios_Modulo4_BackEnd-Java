public class Nivel_2_y_3 {

	//Método para lanzar una excepción si el dato introducido no es un integer
	public void esUnInteger(String chekIfInt) throws Exception {
		int a = Integer.parseInt(chekIfInt);
	}
	//Método para lanzar una excepción si el plato introducido no existe
	public void platoExiste(String plato) throws Exception{
		//Crear instancia de clase para acceder a los atributos
		Nivel_1 n1 = new Nivel_1();
		
		//get(Object key)
		//Returns the value to which the specified key is mapped, or null if this map contains no mapping for the key.
		int a = n1.getPlatosMap().get(plato);
	}

}
