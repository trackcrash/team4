package team4bbs.Service;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import team4bbs.DTO.UserDto;
import team4bbs.Model.User;
import team4bbs.Repository.UserRepository;

@RequiredArgsConstructor
@Service
public class UserService implements UserDetailsService {
    @Autowired
    private UserRepository userRepository;

    public User save(User user){
        checkDuplicateUser(user);
        return userRepository.save(user);
    }

    private void checkDuplicateUser(User user){
        User findUser = userRepository.findByEmail(user.getEmail());
        if (findUser != null){
            throw new IllegalStateException("이미 가입된 이메일입니다");
        }
    }
    @Override
    public UserDetails loadUserByUsername(String name) throws UsernameNotFoundException{
        User user = userRepository.findByName(name);
        if (user == null){
            throw  new UsernameNotFoundException(name);
        }
        return   org.springframework.security.core.userdetails.User.builder()
                .username(user.getName())
                .password(user.getPassword())
                .roles(user.getRole().toString())
                .build();
    }
}
