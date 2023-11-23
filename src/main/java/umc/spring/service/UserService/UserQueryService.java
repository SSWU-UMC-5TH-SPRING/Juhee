package umc.spring.service.UserService;

import java.util.Optional;
import umc.spring.domain.User;

public interface UserQueryService {
    Optional<User> findUser(Long userIdx);
}
