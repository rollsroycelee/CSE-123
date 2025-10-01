public class Tiger {

    private String name;
    private String breed;
    private int age;


    public Tiger(String name, int age) {
        this.name = name;
        this.breed = "Tiger";
        this.age = age;
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

    public String meow() {
        return "Roar!";
    }

    public String scratch(String item) {
        return name + " scratched " + item + ".";
    }

    public String hunt(String animal) {
        return name + " the " + breed + " is hunting a(n) " + animal + "."; 
    }

    public String toString() {
        return "Cat{" +
                "name='" + name + '\'' +
                ", breed='" + breed + '\'' +
                ", age=" + age +
                '}';
    }
}
