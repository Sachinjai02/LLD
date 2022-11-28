//OCP
public class Sparrow extends Bird implements SoundMaker, Flyable {

    //Dependency inversion
    private FlyingBehavior flyingBehavior;

    //Dependency Injection
    public Sparrow (FlyingBehavior flyingBehavior) {
        this.flyingBehavior = flyingBehavior;
    }

    @Override
    public void makeSound() {
        System.out.println("Sparrow goes chirp chirp");
    }

    public void fly() {
        this.flyingBehavior.makeFly();
    }


}