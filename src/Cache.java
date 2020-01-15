/**
 * Interfata Cache : implementarea celor doua metode se face in LRUCache, 
 * FIFOCache si LFUCache
 * @author Maria Jianu 321CB
 *
 */
public interface Cache {
	public void add(Subscriptions s, int time);
	public void remove();
}