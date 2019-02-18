package webserver.repos;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.CrudRepository;
import webserver.domain.GroupMetric;
import webserver.domain.Sensor;

public interface MetricRepo extends CrudRepository<Sensor, Long> {

    Page<Sensor> findAll(Pageable pageable);

    Page<Sensor> findByGroupMetric(GroupMetric group, Pageable pageable);

}
