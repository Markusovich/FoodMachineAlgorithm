package source;

public class Food {
	
	String name, group;
	int calories;
	double percentage;
	
    public boolean equals(Object other) {  //Equals method
        if (this == other)				   //Compares one object with another, and determines if they are equal or not
            return true;
        if (other == null || getClass() != other.getClass())
            return false;
        Food f = (Food) other;
        return (name.equals(f.name));
    }
	public Food(String name, String group, int calories, double percentage) {
		this.name = name;
		this.group = group;
		this.calories = calories;
		this.percentage = percentage;
	}
	public int getCalories() {
		return calories;
	}
	public void setCalories(int calories) {
		this.calories = calories;
	}
	public double getPercentage() {
		return percentage;
	}
	public void setPercentage(double percentage) {
		this.percentage = percentage;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getGroup() {
		return group;
	}
	public void setGroup(String group) {
		this.group = group;
	}
	public String toString(){
		String str1 = String.format("%-20s",name);
		String str2 = String.format("%-20s",group);
		String str3 = String.format("%-20s",calories);
		String str4 = String.format("%-20s",percentage);
		return(str1 + str2 + str3 + str4);
	}
	
}
