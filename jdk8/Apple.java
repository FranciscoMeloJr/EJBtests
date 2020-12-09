//Testing first chapter::

public class Apple {
	String color;
	int weight;

	Apple(String c, int w){
		this.color=c;
		this.weight=w;
	}
	public void setColor(String color){
		this.color=color;
	}

	public String getColor(){
		return this.color;
	}

	public void setWeight(int w){
		this.weight=w;
	}

	public int getWeight(){
		return this.weight;
	}
}