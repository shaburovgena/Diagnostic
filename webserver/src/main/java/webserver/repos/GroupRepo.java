package webserver.repos;

import org.springframework.data.repository.CrudRepository;
import webserver.domain.GroupSensor;

import java.util.List;

public interface GroupRepo extends CrudRepository<GroupSensor, Long> {


    List<GroupSensor> findByGroupName(String name);

    List<GroupSensor> findByGroupTag(String name);
}
