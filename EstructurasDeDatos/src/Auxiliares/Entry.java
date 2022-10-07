package Auxiliares;

public interface Entry<K,V> {
	
	//Retorna la clave de tipo K de la entrada.
	public K getKey();
	
	//Retorna el valor de tipo V de la entrada.
	public V getValue();

}
