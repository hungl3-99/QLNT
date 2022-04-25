package ptit.QLKS.mapper;

import com.fasterxml.jackson.core.JsonProcessingException;

import java.util.List;

public interface MapStructMapper<E,D> {
    E toEntity(D dto) throws JsonProcessingException;

    D toDTO(E entity);

    List<E> toListEntity(List<D> dtoList);

    List<D> toListDTO(List<E> surveys);
}