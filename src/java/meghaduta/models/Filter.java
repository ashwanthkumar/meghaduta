package meghaduta.models;

public class Filter {
    private String attributeName;
    private Operator operator;
    private String operand;

    public Filter and(Filter other) {
        return null;
    }

    public Filter or(Filter other) {
        return null;
    }

    public boolean matches(Event event) {
        return false;
    }

}
