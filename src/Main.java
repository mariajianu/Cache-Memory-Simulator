/**
 * Metodele din aceasta clasa vor:<p> 
 * -goli fisierul de output<p>
 * -verifica numarul de argumente pasate la executie<p>
 * -parsa textul din fisierul de input<p>
 * @author Maria Jianu 321CB
 */
import java.util.Scanner;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

public class Main {

	public static void main (String args[]){

		emptyFile(args[1]);
		if(args.length > 0){
			parseInput(args[0], args[1]);

		}
		
	}	

	public static void emptyFile(String file_name){
		try {
			BufferedWriter writer = new BufferedWriter(new FileWriter(file_name));
			writer.write("");
			writer.close();	
		} catch (IOException e) {
			e.printStackTrace();
		}

	}
	/**
	 * Metoda de parsare a textului din fisierul de input
	 * @param in_file Fisierul de input
	 * @param out_file Fisierul in care se scrie outputul
	 */
	public static void parseInput(String in_file, String out_file) {
		Scanner x;
			try {
				x = new Scanner(new File(in_file));
				String a, objName, b ,aux = "NULL";
				int nrOfBasic, nrOfPremium, nrCmds;
				int sizeOfLRU, aux2;
				int nrOfAdds = 0, nrOfGets = 0, timestamp = 1;
				a = x.next();
				sizeOfLRU =  Integer.parseInt(x.next());
				nrCmds = Integer.parseInt(x.next());
				MainMemory memory = new MainMemory(300, sizeOfLRU);
				Basic basic;
				Premium p;
				LRUCache initLRU = new LRUCache(0);
				FIFOCache initFIFO = new FIFOCache(0);
				LFUCache initLFU = new LFUCache(0);
				aux2 = nrCmds;

				while(aux2 != 0) {

					if((!aux.equals("ADD")) && (!aux.equals("GET")))
						b = x.next();
					else 
						b = "NULLLLL";
					
					if (b.equals("GET") || aux.equals("GET")) {
						objName = x.next();
						if(objName.equals("GET"))
							objName = x.next();
						nrOfGets += 1;
						if(nrOfGets == 1 && a.equals("LRU"))
							initLRU = new LRUCache(sizeOfLRU);
						if(nrOfGets == 1 && a.equals("FIFO"))
							initFIFO = new FIFOCache(sizeOfLRU);
						if(nrOfGets == 1 && a.equals("LFU"))
							initLFU = new LFUCache(sizeOfLRU);
						memory.findObject(objName,initLRU,initFIFO,initLFU,timestamp,out_file,a);
						aux = "NULLLLL";
						b = "NULLLLL";
					}
					
					if(b.equals("ADD") || aux.equals("ADD")) {

						if(nrOfAdds >= memory.getTotalMemory())
							memory.resize(memory.mainMemory);

						objName = x.next();
						nrOfBasic = Integer.parseInt(x.next());

						if(x.hasNext()) {
							aux = x.next();
							if((!aux.equals("ADD")) && (!aux.equals("GET"))) {
								nrOfPremium = Integer.parseInt(aux);
								//add cache basic + premium
								p = new Premium(objName,nrOfBasic,nrOfPremium);
								memory.addInMemory(p,nrOfBasic,nrOfPremium,initLRU,initFIFO,initLFU,a);
								nrOfAdds += 1;
							}
							else {
								//add cache basic
								basic = new Basic(objName,nrOfBasic);
								memory.addInMemory(basic,nrOfBasic, initLRU,initFIFO,initLFU,a);
								nrOfAdds += 1;
							}
						}
						else {
							//add cache basic
							basic = new Basic(objName,nrOfBasic);
							memory.addInMemory(basic,nrOfBasic, initLRU,initFIFO,initLFU,a);
							nrOfAdds += 1;
						}
					}

					aux2--;
					timestamp++;
				}
			
			} catch (FileNotFoundException e){
				e.printStackTrace();
			}
	}

}