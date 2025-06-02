package ZooManagementSystem;

public abstract class Animal {
	private String name;
    private int age;
    private int happiness;
	protected static final int MAX_HAPPINESS = 100;
	
    public Animal(String name, int age) {
        this.name = setName(name);
        this.age = age;
        this.happiness = MAX_HAPPINESS; // Start all animals happy
    }
    
    // Constructor for animals that might not have a specific given name
    public Animal(int age) {
        this(null, age); // Delegates to the main constructor with name as null
    }

    // Abstract methods to be implemented by concrete animal classes
    public abstract String makeNoise();
    public abstract boolean ageOneYear();
    public abstract int getLifeSpan();
    public abstract double feed();
    
    public String getName() {
		return name;
	}

	public String setName(String name) {
		if (name != null && !name.isEmpty()) {
            return name;
        }
        
        return toString();
	}

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}

	public int getHappiness() {
		return happiness;
	}

	public void setHappiness(int happiness) {
        if (happiness < 0) {
            this.happiness = 0;
        } else if (happiness > MAX_HAPPINESS) {
            this.happiness = MAX_HAPPINESS;
        } else {
            this.happiness = happiness;
        }
    }
	
	protected boolean handleAging() {
        this.age += 1;
        
        if (this.age > getLifeSpan()) {
            System.out.println(this.getName() + " is Dead because of his age");
            return false; // Died of old age
        }
        
        this.setHappiness(this.getHappiness() - (int)(Math.random() * 20 + 1));
        
        if (this.getHappiness() <= 0) {
            System.out.println(this.getName() + " is Dead because of his Sadness :(");
            return false; // Died of sadness
        }
        
        return true; // Still alive
    }
}
