public class Pigeon extends Bird implements SoundMaker, Flyable {

    //Dependency inversion
    private FlyingBehavior flyingBehavior;

    //Dependency Injection
    public Pigeon (FlyingBehavior flyingBehavior) {
        this.flyingBehavior = flyingBehavior;
    }

    @Override
    public void makeSound() {
        System.out.println("Pigeon goes kaw kaw");
    }

    public void fly() {
        this.flyingBehavior.makeFly();
    }


}