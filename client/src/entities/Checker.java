package entities;

@FunctionalInterface
public interface Checker<D> {
    boolean check(D data);
}
