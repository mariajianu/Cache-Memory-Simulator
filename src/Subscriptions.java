/**
 * Clasa Subscriptie din care vor deriva clasele Free, Basic si Premium<p>
 * Metodele declarate abstracte sunt implementate in clasele derivate
 * @author Maria Jianu 321CB
 *
 */
public abstract class Subscriptions {
	private String objName;
	private int timeStamp, uses = 0;

	protected Subscriptions(String name){
		objName = name;
	}
	/*
	 * Toate metodele abstracte declarate mai jos vor fi
	 * implementate in clasele derivate	 
	 */
	public abstract void decrementBasic();
	public abstract void decrementPremium();
	public abstract String getType();
	public abstract void setType(String s);
	public abstract int getBasic();
	public abstract int getPremium();
	public abstract void setBasic(int r);
	public abstract void setPremium(int r);

	public String getName(){
		return objName;
	}

	public void setTimeStamp(int r){
		timeStamp = r;
	}

	public int getTimeStamp(){
		return timeStamp;
	}

	public void setUses(int r){
		uses = r;
	}

	public int getUses(){
		return uses;
	}

	public void incrementUses(){
		uses += 1;
	}
}