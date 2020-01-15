/**
	Aceasta este clasa LRUCache care implementeaza interfata Cache
	Metodele din ea vor:
	-adauga in cache de tip LRU
	-elimina copia unui element din cache
	-elimina un element cand Cache-ul este plin
		dupa regula de eliminare LRU

	@author Maria JIANU 321CB
*/
public class LRUCache implements Cache{

	protected Subscriptions totalLRU[];
	private int nrOfLRUCache = 0;

	public LRUCache(int size) {
		totalLRU = new Subscriptions [size];
	}

	public int getNrCache(){
		return nrOfLRUCache;
	}
	/**
	Aceasta este metoda care adauga un element in cache, setand timestamp-ul
	@param s Acesta este elementul de tip Subscriptie care trebuie adaugat
	@param time Acesta este timestampul necesar pentru eliminare
	*/	
	public void add(Subscriptions s, int time) {
		totalLRU[nrOfLRUCache] = s;
		totalLRU[nrOfLRUCache].setTimeStamp(time);
		if (totalLRU[nrOfLRUCache].getPremium() > 0) {
			totalLRU[nrOfLRUCache].decrementPremium();
		}
		else if (totalLRU[nrOfLRUCache].getBasic() >= 0) {
			totalLRU[nrOfLRUCache].decrementBasic();
		}
		nrOfLRUCache += 1;
	}
	/**
	Aceasta este metoda care eliminia copia unui obiect daca 
		acesta este deja in cache
	@param s Acesta este elemtnul de eliminat
	*/
	public void removeCopy(Subscriptions s){
		for(int i = 0; i < nrOfLRUCache; i++) {
			if(totalLRU[i].getName().equals(s.getName())){
				//remove it
				for(int j = i; j < nrOfLRUCache - 1; j++)
					totalLRU[j] = totalLRU[j+1];
				nrOfLRUCache -= 1;
				break; 
			}
		}
	}
	/**
	Aceasta este metoda care elimina din cache de tip LRU
		dupa regula de eliminare a LRU
	*/
	public void remove(){
		int minTimeStamp = totalLRU[0].getTimeStamp(), idx = 0 , i;
		//find min timeStamp
		for(i = 0; i < nrOfLRUCache; i++){
			if(totalLRU[i].getTimeStamp() < minTimeStamp){
				minTimeStamp = totalLRU[i].getTimeStamp();
				idx = i;
			}
		}
		//remove it
		for(int j = idx; j < nrOfLRUCache - 1; j++)
			totalLRU[j] = totalLRU[j + 1];
		nrOfLRUCache -= 1;
	}
}