package team4bbs.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import team4bbs.Model.User;

public interface UserRepository extends JpaRepository<User, Long> {
    User findByEmail(String email);
    User findByName(String name);
}
