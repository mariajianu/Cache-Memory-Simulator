/**
 * Clasa Free derivata din clasa Subscriptie
 * @author Maria Jianu
 *
 */
public class Free extends Subscriptions {
	
	public Free(String name) {
		super(name);
	}
	
	/**
	 * @return tipul obiectului
	 */
	public String getType(){
			return "Free";
	}

	public void decrementPremium(){
	}

	public void decrementBasic(){
	}
	
	public void setType(String s){
	}
	
	public int getBasic(){
		return 0;
	}

	public int getPremium(){
		return 0;
	}

	public void setBasic(int n){
	}

	public void setPremium(int n){
	}
}