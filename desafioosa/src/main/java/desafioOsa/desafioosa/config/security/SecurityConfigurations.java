package desafioOsa.desafioosa.config.security;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
public class SecurityConfigurations {
    @Autowired
    SecurityFilter securityFilter;

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws Exception {
        return httpSecurity
                .csrf(csrf -> csrf.disable())
                .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .authorizeHttpRequests(authorize -> authorize
                        .requestMatchers(HttpMethod.POST, "/login").permitAll()
                        .requestMatchers(HttpMethod.POST, "/register").permitAll()
                        .requestMatchers(HttpMethod.GET,"/index").permitAll()
                        .requestMatchers(HttpMethod.GET, "/mentorado/perfilMentorado").authenticated()
                        .requestMatchers(HttpMethod.GET, "/mentor/perfilMentor").authenticated()
                        // .requestMatchers(HttpMethod.POST, "/product").hasRole("ADMIN")

                        .requestMatchers(HttpMethod.GET, "/").permitAll()
                        .requestMatchers(HttpMethod.GET, "/againLogin").permitAll()
                        // Allow access to /index
                        .requestMatchers(HttpMethod.GET, "/login").permitAll()
                        .requestMatchers(HttpMethod.GET, "/login").permitAll()
                        .requestMatchers(HttpMethod.POST, "/logar").permitAll()
                        .requestMatchers(HttpMethod.POST, "/mentor/inserirMentores").permitAll()
                        .requestMatchers(HttpMethod.POST, "/mentorado/inserirMentorados").permitAll()
                        .requestMatchers(HttpMethod.GET, "/mentor/sucesso").permitAll()
                        .requestMatchers(HttpMethod.GET, "/mentor/CadastroMentor").permitAll()
                        .requestMatchers(HttpMethod.GET, "/mentorado/CadastroMentorado").permitAll()
                        .requestMatchers("/static/**").permitAll() // Allow access to all static resources
                        .requestMatchers("/css/**", "/js/**", "/images/**",
                                "/assets/**")
                        .permitAll()
                        .anyRequest().authenticated())
                .addFilterBefore(securityFilter, UsernamePasswordAuthenticationFilter.class)
                .build();
    }

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration authenticationConfiguration)
            throws Exception {
        return authenticationConfiguration.getAuthenticationManager();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}