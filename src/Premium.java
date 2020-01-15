/**
 * Clasa Premium derivata din Basic 
 * @author Maria Jianu 321CB
 *
 */
public class Premium extends Basic {
	/*
	 * numarul de subscriptii Premium
	 */
	private int nrOfPremiumSubscriptions;

	public Premium(String name,int nrB, int nrP) {
		super(name, nrB);
		nrOfPremiumSubscriptions = nrP;
	}
	/**
	 * Scade numarul de subscriptii Premium
	 * 
	 */
	public void decrementPremium(){
		nrOfPremiumSubscriptions -=1;
	}
	/**
	 * Intoarce numarul de subscriptii Basic 
	 * urcand pe lantul mostenirii
	 * @return numarul de subscriptii Basic
	 */
	public int getBasic(){
		return super.getBasic();
	}

	public void setBasic(int n){
		super.setBasic(n);
	}
	
	/**
	 * Afiseaza tipul obiectului
	 * Daca nu este Premium, urca pe lantul mostenirii
	 * @return tipul obiectului
	 */
	public String getType(){
		if(nrOfPremiumSubscriptions > 0)
			return "Premium";
		else
			return super.getType();
	}
	
	/**
	 * Afiseaza numarul de subscriptii Premium
	 * @return numarul de subscriptii Premium ale obiectului
	 */
	public int getPremium(){
		return nrOfPremiumSubscriptions;
	}
	
	/**
	 * Seteaza numaul de subscriptii Premium
	 * @param n numarul de subscriptii Premium
	 * 
	 */
	public void setPremium(int n){
		nrOfPremiumSubscriptions = n;
	}
}