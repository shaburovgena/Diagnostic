package webserver.repos;

import org.springframework.data.jpa.repository.JpaRepository;
import webserver.domain.User;

public interface UserRepo extends JpaRepository<User, Long> {

    User findByUsername(String username);

    User findByActivationCode(String code);
}
