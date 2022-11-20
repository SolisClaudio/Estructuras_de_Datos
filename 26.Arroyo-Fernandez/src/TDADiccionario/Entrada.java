package TDADiccionario;

public class Entrada<K,V> implements Entry<K,V> {
	
	private K key;
	private V value;
	
	public Entrada(K k,V v ) {
		this.key = k;
		this.value = v;
	}

	@Override
	public K getKey() {
		return key;
	}

	@Override
	public V getValue() {
		return value;
	}

	public void setClave(K k) {
		this.key = k;
	}

	public void setValor(V v) {
		this.value = v;
	}

	@Override
	public String toString() {
		return "Entrada [clave=" + key + ", valor=" + value + "]";
	}
	
	
	
	
	
	
	
	
	

}
