/**
	Aceasta este clasa LFUCache care implementeaza interfata Cache
	Metodele din ea vor:
	-adauga in cache de tip LFU
	-elimina copia unui element din cache
	-elimina un element cand Cache-ul este plin
		dupa regula de eliminare LFU

	@author Maria JIANU 321CB
*/
public class LFUCache implements Cache{
	/*
	vetorul de tip Subsriptie -> toate
	obiectele din cache
	*/
	protected Subscriptions [] totalLFU;
	private int nrOfLFUCache = 0;

	public LFUCache(int size) {
		totalLFU = new Subscriptions [size];
	}

	public int getNrCache(){
		return nrOfLFUCache;
	}
	/**
	Aceasta este metoda care eliminia copia unui obiect daca 
		acesta este deja in cache
	@param s Acesta este elemtnul de eliminat
	*/
	public void removeCopy(Subscriptions s){
		for(int i = 0; i < nrOfLFUCache; i++) {
			if(totalLFU[i].getName().equals(s.getName())){
				//remove it
				for(int j = i; j < nrOfLFUCache - 1; j++)
					totalLFU[j] = totalLFU[j+1];
				nrOfLFUCache -= 1;
				break; 
			}
		}
	}
	/**
	Aceasta este metoda care adauga un element in cache, setand ultilizarile
	@param s Acesta este elementul de tip Subscriptie care trebuie adaugat
	@param uses Acesta este numarul de utilizari necesar pentru eliminare
	*/	
	public void add(Subscriptions s, int uses) {
		totalLFU[nrOfLFUCache] = s;
		totalLFU[nrOfLFUCache].setUses(uses);
		if (totalLFU[nrOfLFUCache].getPremium() > 0)
			totalLFU[nrOfLFUCache].decrementPremium();
		else if (totalLFU[nrOfLFUCache].getBasic() >= 0)
			totalLFU[nrOfLFUCache].decrementBasic();
		nrOfLFUCache += 1;
	}
	/**
	Aceasta este metoda care elimina din cache de tip LFU
		dupa regula de eliminare a LFU
	*/
	public void remove(){
		int idx = -1, minUses = totalLFU[0].getUses(), copy = -1;
		for(int i = 0; i < nrOfLFUCache; i++){
			if(minUses >= totalLFU[i].getUses()){
				minUses = totalLFU[i].getUses();
				idx = i;
				copy = idx;
			}
		}
		for(int j = 0; j < copy + 1; j++){
			if(minUses == totalLFU[j].getUses()){
				if(idx > j){
					idx = j;
					break;
				}
			}
		}
		for(int i = idx; i < nrOfLFUCache - 1; i++)
			totalLFU[i] = totalLFU[i + 1];
		nrOfLFUCache -= 1;
	}				
}