package webserver.repos;

import org.springframework.data.repository.CrudRepository;
import webserver.domain.GroupMetric;

import java.util.List;

public interface GroupRepo extends CrudRepository<GroupMetric, Long> {
    List<GroupMetric> findByGroupName(String name);
}
