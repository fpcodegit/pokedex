package cl.fp.pokedex.mapper;

public interface Mapper<T, U> {
    U map(T source);
}
