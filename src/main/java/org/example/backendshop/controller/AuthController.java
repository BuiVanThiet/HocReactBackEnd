package org.example.backendshop.controller;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.example.backendshop.config.UserAuthProvider;
import org.example.backendshop.dto.CredentialsDTO;
import org.example.backendshop.dto.SigUpDTO;
import org.example.backendshop.dto.UserDTO;
import org.example.backendshop.repositores.UserRepository;
import org.example.backendshop.services.BlacklistTokenService;
import org.example.backendshop.services.UserService;
import org.json.JSONObject;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Base64;
import java.net.URI;

@RequiredArgsConstructor
@RestController
public class AuthController {
    private final UserService userService;
    private final UserAuthProvider userAuthProvider;
    private final BlacklistTokenService blacklistTokenService;
    private final UserRepository userRepository;
    @PostMapping("/login")
    public ResponseEntity<UserDTO> login(@RequestBody CredentialsDTO credentialsDTO,HttpSession session) {
        UserDTO userDTO = userService.login(credentialsDTO);
        System.out.println("doi tuong:"+userDTO.getFullName());
        userDTO.setToken(userAuthProvider.createToken(userDTO.getAccount()));
        session.setAttribute("accountLogin", userDTO);
        return ResponseEntity.ok(userDTO);
    }

    @PostMapping("/register")
    public ResponseEntity<UserDTO> register(@RequestBody @Valid SigUpDTO user) {
        UserDTO createdUser = userService.register(user);
        createdUser.setToken(userAuthProvider.createToken(user.getAccount()));
        return ResponseEntity.created(URI.create("/users/" + createdUser.getId())).body(createdUser);
    }
    @GetMapping("/account-login")
    public ResponseEntity<?> getCurrentUser(HttpServletRequest request, HttpSession session) {
        String token = getJwtFromRequest(request);
        System.out.println("🔹 Token nhận được: " + token);

        // Tách phần Payload của JWT
        String[] parts = token.split("\\.");
        if (parts.length < 2) {
            System.out.println("JWT không hợp lệ!");
            return ResponseEntity.ok("null");
        }

        // Giải mã phần Payload (Base64)
        String payload = new String(Base64.getUrlDecoder().decode(parts[1]));
        System.out.println("Payload: " + payload);

        // Parse JSON để lấy giá trị "iss"
        JSONObject jsonPayload = new JSONObject(payload);
        String issuer = jsonPayload.getString("iss"); // Lấy giá trị "iss"
        System.out.println("🔹 Issuer (iss): " + issuer);
        System.out.println("thong tin ss: "+session.getAttribute("accountLogin"));
        return ResponseEntity.ok(issuer); // Trả về giá trị "iss"
    }

    @PostMapping("/logout-account")
    public ResponseEntity<?> logout(HttpServletRequest request,HttpSession session) {
        String token = getJwtFromRequest(request);
        if (token == null) {
            return ResponseEntity.badRequest().body("Không tìm thấy token");
        }

        // Giải mã JWT để lấy thời gian hết hạn
        String[] parts = token.split("\\.");
        if (parts.length < 2) {
            return ResponseEntity.badRequest().body("Token không hợp lệ");
        }
        String payload = new String(Base64.getUrlDecoder().decode(parts[1]));
        long exp = extractExpirationTime(payload);
        LocalDateTime expiredAt = LocalDateTime.ofInstant(Instant.ofEpochSecond(exp), ZoneId.systemDefault());
        session.invalidate();

        // Thêm token vào blacklist
        blacklistTokenService.addToBlacklist(token, expiredAt);
        return ResponseEntity.ok("Đăng xuất thành công");
    }

    private String getJwtFromRequest(HttpServletRequest request) {
        String bearerToken = request.getHeader("Authorization");
        if (StringUtils.hasText(bearerToken) && bearerToken.startsWith("Bearer ")) {
            return bearerToken.substring(7);
        }
        return null;
    }

    private long extractExpirationTime(String payload) {
        try {
            String expPart = payload.split("\"exp\":")[1].split(",")[0];
            return Long.parseLong(expPart);
        } catch (Exception e) {
            return 0;
        }
    }

}
