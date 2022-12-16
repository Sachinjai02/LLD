package strategies;

import exceptions.GameBuilderException;

public class LaddersValidationStrategy implements EntityValidationStrategy{
    @Override
    public boolean validate(int start, int end) {
        return end > start;
    }
}
