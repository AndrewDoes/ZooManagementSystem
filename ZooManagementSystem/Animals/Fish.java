package ZooManagementSystem.Animals;

import java.util.List;

import ZooManagementSystem.Enums.Colour;
import ZooManagementSystem.Enums.Pattern;

public class Fish extends Animal{
    protected double length;
	protected List<Colour> colours;
	protected Pattern pattern;
    public Fish(int age, int lifeSpan, double length, List<Colour> colours, Pattern pattern) {
        super(age, lifeSpan);
        this.length = length;
        this.colours = colours;
        this.pattern = pattern;
    }

    public Fish(int age, double Length, List<Colour> colours, Pattern pattern) {
        this(age, 25, Length, colours, pattern);
    }

    public double getLength() {
        return length;
    }
    public void setLength(double length) {
        this.length = length;
    }
    public String getColoursString() {
        String x = "";
		for(int i = 0; i < colours.size(); i++) {
			x+=colours.get(i)+  " ";
		}
		return x;
    }

    public List<Colour> getColours() {
        return colours;
    }  

    public void setColours(List<Colour> colours) {
        this.colours = colours;
    }
    public Pattern getPattern() {
        return pattern;
    }
    public void setPattern(Pattern pattern) {
        this.pattern = pattern;
    }
    public double feed(){
        if(this.getAge()<3) return 3;
        else return length+3;
    }

    public String makeNoise(){
        return "blob";
    }

    public String toString(){
        return "Fish";
    }
}
