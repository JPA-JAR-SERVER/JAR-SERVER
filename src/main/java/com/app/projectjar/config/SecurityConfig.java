package com.app.projectjar.config;

import com.app.projectjar.service.member.MemberOAuthService;
import com.app.projectjar.type.Role;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.autoconfigure.security.servlet.PathRequest;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.annotation.web.configuration.WebSecurityCustomizer;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import org.springframework.web.bind.annotation.GetMapping;

@EnableWebSecurity
@Configuration
@RequiredArgsConstructor @Slf4j
public class SecurityConfig  {
//    메인
    private static final String IGNORE_MAIN_PATH = "/main/**";
//    관리자
    private static final String ADMIN_PATH = "/admin/**";
//    마이 페이지
    private static final String MYPAGE_PATH = "/mypage/**";
//    게시판
    private static final String IGNORE_BOARD_CHALLENGE_PATH = "/board/challenge/personal/**";

    private static final String IGNORE_BOARD_GROUP_CHALLENGE_PATH = "/board/challenge/group/**";

    private static final String IGNORE_BOARD_SUGGEST_LIST_PATH = "/board/suggest/list";
    private static final String IGNORE_BOARD_SUGGEST_DETAIL_PATH = "/board/suggest/detail";
    private static final String BOARD_SUGGEST_WRITE_PATH = "/board/suggest/write";

    private static final String IGNORE_BOARD_DIARY_LIST_PATH = "/board/diary/list";
    private static final String IGNORE_BOARD_DIARY_DETAIL_PATH = "/board/detail/list";
    private static final String BOARD_DIARY_WRITE_PATH = "/board/write/list";

    private static final String IGNORE_BOARD_INQUIRE_LIST_PATH = "/board/inquire/list";
    private static final String IGNORE_BOARD_INQUIRE_DETAIL_PATH = "/board/inquire/detail";
    private static final String BOARD_INQUIRE_WRITE_PATH = "/board/inquire/write";

    private static final String IGNORE_BOARD_NOTICE_LIST_PATH = "/board/notice/list";
    private static final String IGNORE_BOARD_NOTICE_DETAIL_PATH = "/board/notice/detail";

//    서비스
    private static final String IGNORE_SERVICE_PATH = "/service/**";

//    faq
    private static final String IGNORE_FAQ_PATH = "/faq/**";

//    파비콘
    private static final String IGNORE_FAVICON = "/favicon.ico";

    private static final String JOIN_OAUTH = "/member/join-OAuth";
    private static final String PASSWORD = "/member/password";
    private static final String CHANGE_PASSWORD = "/member/change-password";
    private static final String ACCOUNT_CONFIRM = "/member/account-confirm";
    private static final String PHONE_CERTIFICATION = "/member/phone-certification";

//    로그인
    private static final String LOGIN_PAGE = "/member/login";
    private static final String LOGIN_PROCESSING_URL = "/member/login";
    private static final String LOGOUT_URL = "/member/logout";
    private static final String LOGOUT_SUCCESS_URL = "/member/login";

    private static final String REMEMBER_ME_TOKEN_KEY = "have a nice day";
    private static final int REMEMBER_ME_TOKEN_EXPIRED = 86400 * 14;

    private final AccessDeniedHandler accessDeniedHandler;
    private final AuthenticationEntryPoint authenticationEntryPoint;
    private final AuthenticationSuccessHandler authenticationSuccessHandler;
    private final AuthenticationFailureHandler authenticationFailureHandler;
    private final UserDetailsService userDetailsService;
    private final MemberOAuthService memberOAuthService;

//    비밀번호 암호화
    @Bean
    public PasswordEncoder passwordEncoder() {return new BCryptPasswordEncoder();}


    @Bean
    public WebSecurityCustomizer webSecurityCustomizer() {
//        WebSecurity에서 관여하지 않을 경로
//        즉, 권한이 없어도 사용이 가능한 경로
        return web -> web.ignoring()
                .mvcMatchers(IGNORE_FAVICON) //favicon은 필터에서 제외
                .requestMatchers(PathRequest.toStaticResources().atCommonLocations()); //static 경로도 필터에서 제외
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
                .authorizeRequests()        //인가 설정 (권한 설정)
                .antMatchers(ADMIN_PATH).hasRole(Role.ADMIN.name())
                .antMatchers(BOARD_SUGGEST_WRITE_PATH).hasRole(Role.MEMBER.name())
                .antMatchers(BOARD_DIARY_WRITE_PATH).hasRole(Role.MEMBER.name())
                .antMatchers(BOARD_INQUIRE_WRITE_PATH).hasRole(Role.MEMBER.name())
                .antMatchers(MYPAGE_PATH).hasRole(Role.MEMBER.name())
                .anyRequest().permitAll()
                .and()
                .csrf().disable()
                .exceptionHandling()
                /* 인가, 인증 Exception Handler */
                .accessDeniedHandler(accessDeniedHandler) //인가 실패
                .authenticationEntryPoint(authenticationEntryPoint); //인증 실패

        log.info(userDetailsService.toString());

        http
                .formLogin() // 일반 로그인
                .loginPage(LOGIN_PAGE) // 로그인하는 페이지 경로
                .usernameParameter("memberEmail") // 로그인 버튼 클릭 시 전달될 uesrname 파라미터 명 수정
                .passwordParameter("memberPassword")  // 로그인 버튼 클릭 시 전달될 password 파라미터 명 수정
                .loginProcessingUrl(LOGIN_PROCESSING_URL) // form에서 submit을 통해 진행될 경로
                .successHandler(authenticationSuccessHandler) // 로그인 성공 시
                .failureHandler(authenticationFailureHandler) // 로그인 실패 시
                .and()
                .logout().logoutRequestMatcher(new AntPathRequestMatcher(LOGOUT_URL))   //로그아웃 시시 이동할 경로
                .logoutSuccessUrl(LOGOUT_SUCCESS_URL)   //로그아웃 성공
                .invalidateHttpSession(Boolean.TRUE)    //세션 초기화
                .and()
                .rememberMe()
                .rememberMeParameter("remember-me")     // 자동 로그인 부분의 checkbox 파라미터명 설정
                .key(REMEMBER_ME_TOKEN_KEY)     // 키값
                .tokenValiditySeconds(REMEMBER_ME_TOKEN_EXPIRED)
                .userDetailsService(userDetailsService)
                .authenticationSuccessHandler(authenticationSuccessHandler) // 인증 성공 (로그인 성공)
                .and()
                .oauth2Login()
                .userInfoEndpoint()
                .userService(memberOAuthService);

        return http.build();
    }
}
