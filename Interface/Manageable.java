package Interface;

public interface Manageable<A> {
    void add(Object entity);

    void remove(String id);

    void getAll();
}
