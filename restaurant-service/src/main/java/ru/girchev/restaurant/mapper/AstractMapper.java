package ru.girchev.restaurant.mapper;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ru.girchev.restaurant.domain.AbstractEntity;
import ru.girchev.restaurant.dto.AbstractDTO;

import java.lang.reflect.ParameterizedType;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Girchev N.A. <ngirchev@gmail.com>
 *         Created on 22.11.15.
 * @param <E> - entity
 * @param <D> - dto
 */
class AbstractMapper<E extends AbstractEntity, D extends AbstractDTO> {

    private static final Logger LOGGER = LoggerFactory.getLogger(AbstractMapper.class);

    /**
     * Convert domain object to DTO.
     *
     * @param e - entity.
     * @return dto.
     */
    public D map(E e) {
        Class<D> classType = getDtoClass();
        D dto = null;
        try {
            dto = classType.newInstance();
            if (e != null) {
                dto.setId(e.getId());
            }
        } catch (InstantiationException | IllegalAccessException ex) {
            LOGGER.error("Error while creating an instance of the DTO class ", ex);
        }
        return dto;
    }

    /**
     * Getting generic type for DTO.
     * @return dto type
     */
    protected Class getDtoClass() {
        Class<?> actualClass = getClass();
        Class<?> superClass = actualClass.getSuperclass();
        while (!AbstractMapper.class.equals(superClass)) {
            actualClass = superClass;
            superClass = superClass.getSuperclass();
        }
        return (Class) ((ParameterizedType) actualClass.getGenericSuperclass()).getActualTypeArguments()[1];
    }

    /**
     * Map for entity.
     *
     * @param <M> mapper type
     * @param <E> entity type
     * @param <D> dto type
     * @param mapper mapper
     * @param e entity
     * @return dto
     */
    public static <M extends AbstractMapper<E, D>, E extends AbstractEntity, D extends AbstractDTO> D map(M mapper, E e) {
        return mapper.map(e);
    }

    /**
     * Mapping for list of entities.
     *
     * @param <M> mapper type
     * @param <E> entity type
     * @param <D> dto type
     * @param mapper mapper
     * @param e list of entities
     * @return list of dto's
     */
    public static <M extends AbstractMapper<E, D>, E extends AbstractEntity, D extends AbstractDTO> List<D> map(M mapper, List<E> e) {
        List<D> result = new ArrayList<>();
        for (E elem : e) {
            result.add(map(mapper, elem));
        }
        return result;
    }

    /**
     * Invoke it for creating DTO's with id only.
     *
     * @param <E> entity generic type
     * @param <D> dto generic type
     * @param dtoClass  dto class
     * @param e entity
     * @return dto
     */
    public static <E extends AbstractEntity, D extends AbstractDTO> D mapIdOnly(Class<D> dtoClass, E e) {
        D dto = null;
        try {
            dto = dtoClass.newInstance();
            dto.setId(e.getId());
        } catch (InstantiationException | IllegalAccessException ex) {
            LOGGER.error("Ошибка создания экземпляра класса DTO для маппера. ", ex);
        }
        return dto;
    }

    /**
     * Invoke it for creating list of DTO's with id only.
     *
     * @param <E> entity generic type
     * @param <D> dto generic type
     * @param dtoClass  dto class
     * @param e list of entities
     * @return list of dto
     */
    public static <E extends AbstractEntity, D extends AbstractDTO> List<D> mapIdOnly(Class<D> dtoClass, List<E> e) {
        List<D> result = new ArrayList<>();
        for (E elem : e) {
            result.add(mapIdOnly(dtoClass, elem));
        }
        return result;
    }
}
