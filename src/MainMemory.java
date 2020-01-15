import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

/**
 * Aceasta este clasa ce reprezinta memoria principala
 * Metodele din ea:<p>
 * -adauga obiecte in vectorul memoriei principale <p>
 * -redimensioneaza acest vector cand si-a atins capacitatea maxima<p>
 * -implementeaza operatia de GET <p>
 * @author Maria Jianu 321CB
 *
 */
public class MainMemory {
	protected Subscriptions [] mainMemory;
	private int totalMemory, occupiedMemory = 0;
	private int sizeofCache;

	public MainMemory(int size, int sizeLRU){
		this.totalMemory = size;
		mainMemory = new Subscriptions [size];
		this.sizeofCache = sizeLRU;
	}

	public int getTotalMemory(){
		return totalMemory;
	}
	
	/**
	*Aceasta este metoda de redimensionare a memoriei principale
	*@param vect vectorului memoriei principale
	*/
	public void resize(Subscriptions [] vect){
		int i;
		Subscriptions [] copy = new Subscriptions [vect.length];
		System.arraycopy(vect, 0, copy, 0, vect.length);
		this.mainMemory = new Subscriptions[copy.length * 2];
		for(i = 0; i < copy.length; i++)
			this.mainMemory[i] = copy[i];
	}
	
	/**
	*Aceasta este metoda de adaugare in vector memoriei principale
	*@param s obiectul Basic de adaugat
	*@param nr numarul de subscriptii Basic
	*@param lru Cache-ul de tip lru, pentru eliminarea copiei
	*@param fifo Cache-ul de tip fifo, pentru eliminarea copiei
	*@param lfu Cache-ul de tip lfu, pentru eliminarea copiei
	*@param type String-ul care determina tipul de cache
	*/
	public void addInMemory(Basic s, int nr, LRUCache lru, FIFOCache fifo, LFUCache lfu, String type){
		int ok = 0;
		for(int i = 0; i < occupiedMemory; i++){
			if(occupiedMemory > 0) {
				if(s.getName().equals(mainMemory[i].getName())){
					//suprascriere
					s.setBasic(nr);
					mainMemory[i] = s;
					ok = 1;
					//remove from cache
					if(lru.getNrCache() > 0 && type.equals("LRU")){
						lru.removeCopy(s);
					}
					if(fifo.getNrCache() > 0 && type.equals("FIFO")){
						fifo.removeCopy(s);
					}
					if(lfu.getNrCache() > 0 && type.equals("LFU")){
						lfu.removeCopy(s);
					}
				}
			}
		}
		if(ok == 0) {
			s.setBasic(nr);
			mainMemory[occupiedMemory] = s;
			occupiedMemory += 1;
			
		}
	}
	/**
	*Aceasta este metoda de adaugare in vector memoriei principale
	*	pentru un obiect de tip Premium
	*@param s obiectul Premium de adaugat
	*@param nrb numarul de subscriptii Basic
	*@param nrp numarul de subscriptii Premium
	*@param lru Cache-ul de tip lru, pentru eliminarea copiei
	*@param fifo Cache-ul de tip fifo, pentru eliminarea copiei
	*@param lfu Cache-ul de tip lfu, pentru eliminarea copiei
	*@param type String-ul care determina tipul de cache
	*/
	public void addInMemory(Premium s,int nrb, int nrp, LRUCache lru,FIFOCache fifo, LFUCache lfu, String type){
		int ok = 0;
		for(int i = 0; i < occupiedMemory; i++){
			if(occupiedMemory > 0) {
				if(s.getName().equals(mainMemory[i].getName())) {
					//suprascriere
					s.setBasic(nrb);
					s.setPremium(nrp);
					mainMemory[i] = s;
					ok = 1;
					//eliminia din cacheul respectiv
					if(lru.getNrCache() > 0 && type.equals("LRU")){
						lru.removeCopy(s);
					}
					if(fifo.getNrCache() > 0 && type.equals("FIFO")){
						fifo.removeCopy(s);
					}
					if(lfu.getNrCache() > 0 && type.equals("LFU")){
						lfu.removeCopy(s);
					}
				}
			}
		}
		if (ok == 0) {
			s.setBasic(nrb);
			s.setPremium(nrp);
			mainMemory[occupiedMemory] = s;
			occupiedMemory += 1;
		}
	}
	/**
	 * Aceasta este metoda care se ocupa de comanda GET
	 * @param s numele obiectului pe care se face operatia GET
	 * @param lru Cache-ul LRU pentru adugare
	 * @param fifo Cache-ul FIFO pentru adaugare
	 * @param lfu Cache-ul LFU pentru adaugare
	 * @param time Timestampul pentru eliminarea dupa regula LRU
	 * @param file Fisierul in care se scrie output-ul
	 * @param type Stringul care determina tipul de cache
	 */
	public void findObject(String s,LRUCache lru,FIFOCache fifo,LFUCache lfu, int time, String file, String type) {
		int ok = 0;
		try {
			BufferedWriter writer = new BufferedWriter(new FileWriter(file, true));
			//search in cache
			for(int i = 0; i < occupiedMemory; i++){
				if(s.equals(mainMemory[i].getName())){
					//search in cache only if it's not empty
					mainMemory[i].setTimeStamp(time);
					if(lru.getNrCache() > 0 && type.equals("LRU")) {
						for(int j = 0; j < lru.getNrCache(); j++) {
							if(s.equals(lru.totalLRU[j].getName())) {
								//output 0 in file
								writer.write("0 ");
								writer.append(lru.totalLRU[j].getType());
								writer.newLine();
								//scade premium/basicS
								if(lru.totalLRU[j].getType().equals("Premium"))
									lru.totalLRU[j].decrementPremium();
								else if(lru.totalLRU[j].getType().equals("Basic"))
									lru.totalLRU[j].decrementBasic();
								ok = 2; //if found in cache
							}
						}
					}
					if(fifo.getNrCache() > 0 && type.equals("FIFO")){
						for(int j = 0; j < fifo.getNrCache(); j++) {
							if(s.equals(fifo.totalFIFO[j].getName())) {
								//output 0 in file
								writer.write("0 ");
								writer.append(fifo.totalFIFO[j].getType());
								writer.newLine();
								//scade premium/basicS
								if(fifo.totalFIFO[j].getType().equals("Premium"))
									fifo.totalFIFO[j].decrementPremium();
								else if(fifo.totalFIFO[j].getType().equals("Basic"))
									fifo.totalFIFO[j].decrementBasic();
								ok = 2; //if found in cache
							}
						}
					}
					if(lfu.getNrCache() > 0 && type.equals("LFU")){
						for(int j = 0; j < lfu.getNrCache(); j++) {
							if(s.equals(lfu.totalLFU[j].getName())) {
								//output 0 in file
								lfu.totalLFU[j].incrementUses();
								writer.write("0 ");
								writer.append(lfu.totalLFU[j].getType());
								writer.newLine();
								//scade premium/basicS
								if(lfu.totalLFU[j].getType().equals("Premium"))
									lfu.totalLFU[j].decrementPremium();
								else if(lfu.totalLFU[j].getType().equals("Basic"))
									lfu.totalLFU[j].decrementBasic();
								ok = 2; //if found in cache
							}
						}
					}
					if(ok != 2) {
						//output 1 in file
						writer.write("1 ");
						writer.append(mainMemory[i].getType());
						writer.newLine();
						if(lru.getNrCache() == sizeofCache && type.equals("LRU")){
							lru.remove();
						}
						if(fifo.getNrCache() == sizeofCache && type.equals("FIFO")){
							fifo.remove();
						}
						if(lfu.getNrCache() == sizeofCache && type.equals("LFU")){
							lfu.remove();
						}
						//add in cache
						if(type.equals("LRU"))
							lru.add(mainMemory[i],time);
						if(type.equals("FIFO"))
							fifo.add(mainMemory[i],0);
						if(type.equals("LFU"))
							lfu.add(mainMemory[i],mainMemory[i].getUses());
						ok = 1;
					}
				}	
			}
			if (ok == 0){
			//output 2 in file
				writer.append("2");
				writer.newLine();
			}
			writer.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}