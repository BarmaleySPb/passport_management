package ru.job4j.passport.management.repository;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import ru.job4j.passport.management.model.Owner;

public interface OwnerRepository extends CrudRepository<Owner, Long> {
    @Modifying
    @Query(value = "update owner set first_name = :firstName, second_name = :secondName"
            + " where id = :id", nativeQuery = true)
    void updateData(@Param("firstName") String firstName, @Param("secondName") String secondName,
                    @Param("id") long id);
}
