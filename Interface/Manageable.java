package Interface;

public interface Manageable<A> {
    void add(Object entity);

    static void remove(String id) {

    }

    void getAll();
}
