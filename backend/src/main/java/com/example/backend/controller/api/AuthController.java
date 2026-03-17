package com.example.backend.controller.api;

import com.example.backend.dto.auth.LoginRequest;
import com.example.backend.dto.auth.RegisterRequest;
import com.example.backend.service.MemberService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.context.HttpSessionSecurityContextRepository;
import org.springframework.security.web.context.SecurityContextRepository;
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

    @PostMapping("/register")
    public String userRegister(@RequestBody RegisterRequest data){
        // 넘어온 data로 회원가입 시도
        String msg = memberService.userRegister(data);
        if(msg.equals("already exist")){
            return "register fail, already exist";
        }
        return "register success";
    }

    @PostMapping("/login")
    public ResponseEntity<?> userLogin(@RequestBody LoginRequest data, HttpServletRequest request, HttpServletResponse response){

        Authentication auth = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(data.getMemberId(), data.getMemberPw()));

        SecurityContext context = SecurityContextHolder.createEmptyContext();
        context.setAuthentication(auth);
        SecurityContextHolder.setContext(context);

        securityContextRepository.saveContext(context, request, response);

        return ResponseEntity.ok(Map.of("message", "login success", "memberId", auth.getName()));
    }

    @PostMapping("/logout")
    public ResponseEntity<?> userLogout(HttpServletRequest request, HttpServletResponse response) {
        // SecurityContext 비우기
        SecurityContextHolder.clearContext();
        // Session 없애기
        HttpSession session = request.getSession(false);
        if(session != null){
            session.invalidate();
        }

        return ResponseEntity.ok(Map.of("message", "logout success"));
    }

    @GetMapping("/myInfo")
    public ResponseEntity<?> me(Authentication authentication){
        if(authentication == null){
            return ResponseEntity.status(401).body(Map.of("message", "Not logged in"));
        }

        List<String> roles = authentication.getAuthorities().stream()
                .map(GrantedAuthority::getAuthority)
                .toList();

        String memberName = memberService.findMemberByMemberId(authentication.getName()).getMemberName();

        return ResponseEntity.ok(Map.of(
                "memberId", authentication.getName(),
                "memberName", memberName,
                "roles", roles
                )
        );
    }
}
