package webserver.repos;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.repository.CrudRepository;
import org.springframework.transaction.annotation.Transactional;
import webserver.domain.GroupSensor;
import webserver.domain.Sensor;

import java.util.Set;

public interface SensorRepo extends CrudRepository<Sensor, Long> {

    Page<Sensor> findAll(Pageable pageable);

    Iterable<Sensor> findAll();

    Page<Sensor> findByGroupSensor(GroupSensor group, Pageable pageable);

    Set<Sensor> findByGroupSensor(GroupSensor group);

    //    Кастомный запрос к БД с использованием HQL
    //    @Query(value = "delete from Metric m where group_id = ?1")
    @Modifying
    @Transactional
    Integer deleteAllByGroupSensor(GroupSensor group);
}
