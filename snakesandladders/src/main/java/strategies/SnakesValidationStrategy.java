package strategies;

public class SnakesValidationStrategy implements EntityValidationStrategy{
    @Override
    public boolean validate(int start, int end) {
        return end < start;
    }
}
