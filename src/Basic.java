/**
 * Clasa Basic derivata din Free
 * @author Maria Jianu 321CB
 *
 */
public class Basic extends Free {
	/**
	 * numarul de subscriptii Basic ale obiecului
	 */
	int nrOfSubscriptions;

	public Basic(String name, int nr) {
		super(name);
		nrOfSubscriptions = nr;
	}
	/**
	 * Seteaza numarul de subscriptii de tip Basic
	 * @param n numarul de subscriptii Basic
	 */
	public void setBasic(int n){
		nrOfSubscriptions = n;
	}
	/**
	 * Scade numarul de subscriptii Basic
	 * la fiecare comanda de tip GET
	 */
	public void decrementBasic(){
		nrOfSubscriptions -= 1;
	}

	public void decrementPremium(){
	}

	public void setType(String s){
	}
	
	/**
	 * Afiseaza tipul unui obiect, iar daca numarul de subscriptii
	 * Basic este nul, urca pe lantul mostenirii 
	 * apeland metoda getType() din clasa Free
	 */
	public String getType(){
		if(nrOfSubscriptions > 0)
			return "Basic";
		else
			return super.getType();
	}
	
	public int getBasic(){
		return nrOfSubscriptions;
	}
	
	public int getPremium(){
		return 0;
	}

	public void setPremium(int n){
	}
}