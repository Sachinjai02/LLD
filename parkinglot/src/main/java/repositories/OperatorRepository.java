package repositories;

import models.Gate;
import models.Operator;

import java.util.HashMap;
import java.util.Map;

public class OperatorRepository {
    private Map<Long, Operator> operatorMap = new HashMap<>();
    private Long nextId = 1l;

    public Operator getById(Long id) {
        return this.operatorMap.get(id);
    }

    public Operator save(Operator operator) {
        operator.setId(nextId++);
        operatorMap.put(operator.getId(), operator);
        return operator;
    }


}
