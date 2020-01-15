/**
*	Metodele din ea vor:<p>
*	-adauga in cache de tip FIFO<p>
*	-elimina copia unui element din cache<p>
*	-elimina un element cand Cache-ul este plin
*		dupa regula de eliminare FIFO<p>
*
*	@author Maria JIANU 321CB
*/
public class FIFOCache implements Cache{
	/*
	*vetorul de tip Subsriptie -> toate
	*obiectele din cache
	*/
	protected Subscriptions totalFIFO[];
	private int nrOfFIFOCache = 0;

	public FIFOCache(int size) {
		totalFIFO = new Subscriptions [size];
	}

	public int getNrCache(){
		return nrOfFIFOCache;
	}

	/**
	*Aceasta este metoda care eliminia copia unui obiect daca 
	*	acesta este deja in cache
	*@param s Acesta este elemtnul de eliminat
	*/
	public void removeCopy(Subscriptions s){
		for(int i = 0; i < nrOfFIFOCache; i++) {
			if(totalFIFO[i].getName().equals(s.getName())){
				//remove it
				for(int j = i; j < nrOfFIFOCache - 1; j++)
					totalFIFO[j] = totalFIFO[j+1];
				nrOfFIFOCache -= 1;
				break; 
			}
		}
	}
	/**
	*Aceasta este metoda care adauga un element in cache
	*@param s Acesta este elementul de tip Subscriptie care trebuie adaugat
	*/
	public void add(Subscriptions s, int time) {
		totalFIFO[nrOfFIFOCache] = s;
		if (totalFIFO[nrOfFIFOCache].getPremium() > 0)
			totalFIFO[nrOfFIFOCache].decrementPremium();
		else if (totalFIFO[nrOfFIFOCache].getBasic() >= 0)
			totalFIFO[nrOfFIFOCache].decrementBasic();
		nrOfFIFOCache += 1;
	}
	
	/**
	*Aceasta este metoda care elimina din cache de tip FIFO
	*	dupa regula de eliminare a FIFO
	*/
	public void remove() {
		int i;
		for(i = 0; i < nrOfFIFOCache - 1; i++)
			totalFIFO[i] = totalFIFO[i + 1];

		nrOfFIFOCache -= 1;
		
	}
	
}