package presentation.examples;

public class LazyInitObj {

    private static LazyInitObj instance;

    public static LazyInitObj getInstance(){
        if (instance == null)
            instance = new LazyInitObj();
        return instance;
    }
}
