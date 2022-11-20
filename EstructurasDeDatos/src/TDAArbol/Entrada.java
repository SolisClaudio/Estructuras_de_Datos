package TDAArbol;

public class Entrada<K, V>{
	private K key;
	private V value;
	public void put(K k , V v) {
		key=k;
		value=v;
	}
	public Entrada(K k, V v) {
		key = k;
		value = v;
	}

	public K getKey() {
		return key;
	}

	public void setKey(K key) {
		this.key = key;
	}

	public V getValue() {
		return value;
	}

	public void setValue(V value) {
		this.value = value;
	}
	
}
