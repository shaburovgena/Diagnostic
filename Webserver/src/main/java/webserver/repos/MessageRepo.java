package webserver.repos;

import org.springframework.data.jpa.repository.JpaRepository;
import webserver.domain.Message;

public interface MessageRepo extends JpaRepository<Message, Long> {
}