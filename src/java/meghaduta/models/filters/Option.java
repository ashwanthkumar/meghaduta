package meghaduta.models.filters;

/**
 * Created by salaikumar on 19/9/15.
 */
public class Option<T> {
    T value;

    public Option(T value) {
        this.value  = value;
    }

    public Option(){

    }

    private boolean isDefined(){
        return  value != null;
    }

    private boolean isEmpty(){
        return value == null;
    }
}
