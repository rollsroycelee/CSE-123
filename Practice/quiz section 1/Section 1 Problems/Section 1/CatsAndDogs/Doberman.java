public class Doberman extends Cat {

    private String name;
    private String breed;
    private int age;
    private String type;


    public Doberman(String name, int age) {
        super(name, "Doberman", age);
        this.name = name;
        this.breed = "Doberman";
        this.age = age;
        this.type = "Dog";
    }

    public String getName() {
        return name;
    }

    public String getBreed() {
        return breed;
    }

    public int getAge() {
        return age;
    }

    public String bark() {
        return "Woof!";
    }

    public String fetch(String item) {
        return name + " runs off and returns with " + item + ".";
    }

    public String search(String item) {
        return name + " the " + breed + " is searching for " +item +".";
    }

    public String toString() {
        return type + "{" +
                "name='" + name + '\'' +
                ", breed='" + breed + '\'' +
                ", age=" + age +
                '}';
    }
}
