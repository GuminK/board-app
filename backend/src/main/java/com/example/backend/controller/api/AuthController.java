package com.example.backend.controller.api;

import com.example.backend.domain.Member;
import com.example.backend.dto.auth.LoginRequest;
import com.example.backend.dto.auth.MyInfoResponse;
import com.example.backend.dto.auth.RegisterRequest;
import com.example.backend.service.MemberService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.context.HttpSessionSecurityContextRepository;
import org.springframework.security.web.context.SecurityContextRepository;
import org.springframework.security.web.csrf.CsrfToken;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/auth")
public class AuthController {
    private final MemberService memberService;
    private final AuthenticationManager authenticationManager;

    private final SecurityContextRepository securityContextRepository = new HttpSessionSecurityContextRepository();

    public AuthController(MemberService memberService, AuthenticationManager authenticationManager) {
        this.memberService = memberService;
        this.authenticationManager = authenticationManager;
    }

    // csrf 발급
    @GetMapping("/csrf")
    public ResponseEntity<?> csrf(CsrfToken token){
        return ResponseEntity.ok(Map.of("token", token.getToken()));
    }

    // 회원가입
    @PostMapping("/register")
    public ResponseEntity<?> userRegister(@RequestBody RegisterRequest data){
        String msg = memberService.userRegister(data);
        if("already exist".equals(msg)){
            return ResponseEntity.status(HttpStatus.CONFLICT).body(Map.of("message", "register fail, already exist"));
        }
        return ResponseEntity.status(HttpStatus.CREATED).body(Map.of("message", "register success"));
    }

    // 로그인
    @PostMapping("/login")
    public ResponseEntity<?> userLogin(@RequestBody LoginRequest data, HttpServletRequest request, HttpServletResponse response){

        Authentication auth = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(data.getMemberId(), data.getMemberPw()));

        SecurityContext context = SecurityContextHolder.createEmptyContext();
        context.setAuthentication(auth);
        SecurityContextHolder.setContext(context);

        securityContextRepository.saveContext(context, request, response);

        return ResponseEntity.ok(Map.of("message", "login success"));
    }

    // 로그아웃
    @PostMapping("/logout")
    public ResponseEntity<?> userLogout(HttpServletRequest request, HttpServletResponse response) {
        SecurityContextHolder.clearContext();
        HttpSession session = request.getSession(false);
        if(session != null){
            session.invalidate();
        }

        return ResponseEntity.ok(Map.of("message", "logout success"));
    }

    // 내 정보 조회
    @GetMapping("/myinfo")
    public ResponseEntity<?> me(Authentication authentication){
        if(authentication == null
        || !authentication.isAuthenticated()){
            return ResponseEntity.status(401).body(Map.of("message", "Not logged in"));
        }
        Member member = memberService.findMemberByMemberId(authentication.getName());

        return ResponseEntity.ok(new MyInfoResponse(member.getMemberName(), member.getMemberId(), member.getRoleType()));
    }
}
