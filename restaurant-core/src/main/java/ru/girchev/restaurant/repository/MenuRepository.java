package ru.girchev.restaurant.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import ru.girchev.restaurant.domain.Menu;

import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import java.util.Date;

import java.util.List;

/**
 * @author Girchev N.A. <ngirchev@gmail.com>
 *         Created on 22.11.15.
 */
@Repository
public interface MenuRepository extends JpaRepository<Menu, Long> {

//    @Query("select count(m)from Menu m where m.createdOn == ")
//    int countMenu(Date date);
//    List<Menu> findByMemberShipDate(@Temporal(TemporalType.DATE) Date date);
}
