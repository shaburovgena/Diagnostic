package webserver.repos;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.CrudRepository;
import webserver.domain.GroupMetric;
import webserver.domain.Metric;

public interface MetricRepo extends CrudRepository<Metric, Long> {

    Page<Metric> findAll(Pageable pageable);

    Page<Metric> findByGroupMetric(GroupMetric group, Pageable pageable);

}
