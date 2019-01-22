package webserver.repos;

import org.springframework.data.repository.CrudRepository;
import webserver.domain.Metric;

public interface MetricRepo extends CrudRepository<Metric, Long> {

}
