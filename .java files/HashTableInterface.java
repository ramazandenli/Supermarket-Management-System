public interface HashTableInterface<K,V> {
	
	public V  put(K key,V value);
	
	public V getValue(K key);
	
	public V remove(K key);
	
	public void resize(int capacity);
	
	public int SSF(K key);
	
	public int PAF(K key);
	
	public int linearProbe(int index,K key);
	
	public int doubleHash(int index,K key);
	
	
	
	

}
