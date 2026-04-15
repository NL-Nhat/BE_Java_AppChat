    package com.example.chatMulticat.service;

    import java.util.HashMap;
    import java.util.Map;

    import org.springframework.stereotype.Service;
    import org.springframework.transaction.annotation.Transactional;

    import com.example.chatMulticat.dto.request.LoginRequest;
    import com.example.chatMulticat.dto.request.RegisterRequest;
    import com.example.chatMulticat.model.User;
    import com.example.chatMulticat.repository.UserRepository;

    import lombok.RequiredArgsConstructor;

    @Service
    @RequiredArgsConstructor
    public class AuthService {

        private final UserRepository userRepository;

        public Map<String, Object> login(LoginRequest loginRequest) {

            User user = userRepository.findByUserName(loginRequest.getUserName());

            if(user == null) {
                throw new RuntimeException("Tên đăng nhập không đúng");
            }

            if (!user.getPass().equals(loginRequest.getPass())) {
                throw new RuntimeException("Mật khẩu không đúng");
            }

            Map<String, Object> result = new HashMap<>();
            result.put("id", user.getId());
            result.put("message", "đăng nhập thành công");

            return result;
        }

        @Transactional
        public String register(RegisterRequest registerRequest) {

            if(userRepository.existsByUserName(registerRequest.getUserName())) {
                throw new RuntimeException("Username đã tồn tại");
            }

            if(!registerRequest.getPass().equals(registerRequest.getConfirmPassword())) {
                throw new RuntimeException("Mật khẩu không khớp");
            }

            User user = new User();
            user.setPass(registerRequest.getPass());
            user.setUserName(registerRequest.getUserName());

            userRepository.save(user);

            return "Đăng ký thành công";
        }

    }
