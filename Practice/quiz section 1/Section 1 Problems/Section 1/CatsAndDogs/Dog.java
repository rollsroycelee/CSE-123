public class Dog {

    private String name;
    private String breed;
    private int age;


    public Dog(String name, String breed, int age) {
        this.name = name;
        this.breed = breed;
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

    public String bark() {
        return "Woof!";
    }

    public String fetch(String item) {
        return name + " runs off and returns with " + item + ".";
    }

    public String toString() {
        return "Dog{" +
                "name='" + name + '\'' +
                ", breed='" + breed + '\'' +
                ", age=" + age +
                '}';
    }
}
