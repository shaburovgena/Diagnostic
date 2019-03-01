package webserver.repos;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.data.repository.CrudRepository;
import org.springframework.transaction.annotation.Transactional;
import webserver.domain.GroupMetric;
import webserver.domain.Metric;

import java.util.Set;

@EnableJpaRepositories
public interface MetricRepo extends CrudRepository<Metric, Long> {

    Page<Metric> findAll(Pageable pageable);

    Page<Metric> findByGroupMetric(GroupMetric group, Pageable pageable);

    Set<Metric> findByGroupMetric(GroupMetric group);

    //    Кастомный запрос к БД с использованием HQL
    //    @Query(value = "delete from Metric m where group_id = ?1")
    @Modifying
    @Transactional
    Integer deleteAllByGroupMetric(GroupMetric group);
}
