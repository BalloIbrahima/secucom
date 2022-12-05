package com.securi.Secucom.Configuration;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;

import org.springframework.security.config.http.SessionCreationPolicy;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.securi.Secucom.Configuration.JWT.AuthEntryPointJwt;
import com.securi.Secucom.Configuration.JWT.AuthTokenFilter;

import com.securi.Secucom.ServiceImplementation.UserDetailsServiceImpl;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true) // pour spécifier les role dans le controller il faut cette
                                                   // annotation dans le security config
public class SpringSecurityConfig {

    @Autowired
    UserDetailsServiceImpl userDetailsService;

    @Autowired
    private AuthEntryPointJwt unauthorizedHandler;

    @Bean
    public AuthTokenFilter authenticationJwtTokenFilter() {
        return new AuthTokenFilter();
    }

    @Bean
    public DaoAuthenticationProvider authenticationProvider() {
        DaoAuthenticationProvider authProvider = new DaoAuthenticationProvider();

        authProvider.setUserDetailsService(userDetailsService);
        authProvider.setPasswordEncoder(passwordEncoder());

        return authProvider;
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http.cors().and().csrf().disable()
                .exceptionHandling().authenticationEntryPoint(unauthorizedHandler).and()
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS).and()
                .authorizeRequests().antMatchers("/login/**").permitAll()
                // .antMatchers("/api/test/**").permitAll()
                .anyRequest().authenticated();

        http.authenticationProvider(authenticationProvider());

        http.addFilterBefore(authenticationJwtTokenFilter(), UsernamePasswordAuthenticationFilter.class);

        return http.build();
    }

    // 1:::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::
    // private RsakeysConfig rsakeysConfig;
    // //Nous allons injecter password encoder et declarer dans le constructeur
    // private PasswordEncoder passwordEncoder;

    // public void Securityconfiguration(RsakeysConfig rsakeysConfig,
    // PasswordEncoder passwordEncoder) {
    // this.rsakeysConfig = rsakeysConfig;
    // this.passwordEncoder = passwordEncoder;
    // }

    // @Bean
    // public AuthenticationManager authenticationManager(UserDetailsService
    // userDetailsService){

    // var authProvider = new DaoAuthenticationProvider();

    // //Nous allons attacher le passwordEncoder que nous volons utiliser
    // authProvider.setPasswordEncoder(passwordEncoder);
    // //
    // authProvider.setUserDetailsService(userDetailsService);

    // return new ProviderManager(authProvider);
    // }

    // @Bean
    // public UserDetailsService inMemoryUserDetailsManager(){
    // return new InMemoryUserDetailsManager(
    // //nous allons utiliser password(passwordEncoder.encode( Pour cripter notre
    // code
    // User.withUsername("collaborateur").password(passwordEncoder.encode("1234")).authorities("COLL").build(),
    // User.withUsername("user").password(passwordEncoder.encode("1234")).authorities("USER").build(),
    // User.withUsername("admin").password(passwordEncoder.encode("1234")).authorities("USER","COLl","ADMIN").build()

    // );
    // }

    // @Bean
    // public SecurityFilterChain filterChain(HttpSecurity httpSecurity) throws
    // Exception {
    // return httpSecurity
    // .csrf(csrf -> csrf.disable())
    // .authorizeRequests(
    // auth -> auth.anyRequest().authenticated())
    // .sessionManagement(sess ->
    // sess.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
    // .oauth2ResourceServer(OAuth2ResourceServerConfigurer::jwt)
    // .httpBasic(org.springframework.security.config.Customizer.withDefaults())
    // .build();
    // }

    // //Pour verifier la signature du token j'ai besoin du public key
    // @Bean
    // JwtDecoder jwtDecoder(){
    // return NimbusJwtDecoder.withPublicKey(rsakeysConfig.publicKey()).build();
    // }

    // //Pour signer le token j'ai besoin d'utiliser le private key et public key
    // @Bean
    // JwtEncoder jwtEncoder(){

    // JWK jwk = new
    // RSAKey.Builder(rsakeysConfig.publicKey()).privateKey(rsakeysConfig.privateKey()).build();
    // JWKSource<SecurityContext> jwkSource = new ImmutableJWKSet<>(new
    // JWKSet(jwk));
    // return new NimbusJwtEncoder(jwkSource);
    // }

    // Nous avons besoin de créer un netpoint qui permet de generer le token

    // :::::::::::::::::::::::::::::::::::::::::::first
    // private CollaborateurService collaborateurService;

    // public void SecurityConfig(CollaborateurService collaborateurService) {
    // this.collaborateurService = collaborateurService;
    // }

    // @Override
    // protected void configure(AuthenticationManagerBuilder auth) throws Exception
    // {
    // auth.userDetailsService(new UserDetailsService() {
    // @Override
    // public UserDetails loadUserByUsername(String username) throws
    // UsernameNotFoundException {
    // Collaborateur collaborateur = collaborateurService.findByPseudo(username);
    // Collection<GrantedAuthority> authorities = new ArrayList<>();
    // collaborateur.getRoles().forEach(r -> authorities.add(new
    // SimpleGrantedAuthority(r.getLibelle())));
    // return new User(collaborateur.getPseudo(), collaborateur.getPassword(),
    // authorities);
    // }
    // });
    // }

    // @Override
    // protected void configure(HttpSecurity http) throws Exception {
    // // http.csrf().disable();
    // http.cors().and().csrf().disable();
    // http.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);
    // http.authorizeHttpRequests().anyRequest().authenticated();
    // // http.headers().frameOptions().disable();
    // http.formLogin();
    // http.addFilter(new JwtAuthentication(authenticationManagerBean()));
    // http.addFilterBefore(new JwtAuthorizationFilter(),
    // UsernamePasswordAuthenticationFilter.class);
    // }

    // @Bean
    // @Override
    // public AuthenticationManager authenticationManagerBean() throws Exception {
    // return super.authenticationManagerBean();
    // }
    // :::::::::::::::::::::::::::::::::::::::::::secaind

    // @Autowired
    // UserDetailsServiceImpl userDetailsService;

    // @Autowired
    // private AuthEntryPointJwt unauthorizedHandler;

    // @Bean
    // public AuthTokenFilter authenticationJwtTokenFilter() {
    // return new AuthTokenFilter();
    // }

    // @Override
    // public void configure(AuthenticationManagerBuilder
    // authenticationManagerBuilder) throws Exception {
    // authenticationManagerBuilder.userDetailsService(userDetailsService).passwordEncoder(passwordEncoder());
    // }

    // @Override
    // protected void configure(HttpSecurity http) throws Exception {
    // // TODO Auto-generated method stub
    // http.authorizeRequests()
    // .antMatchers("/login").permitAll()
    // .anyRequest().authenticated()
    // .and()
    // .formLogin();
    // // super.configure(http);
    // }

    // @Bean
    // public PasswordEncoder passwordEncoder() {
    // return new BCryptPasswordEncoder();
    // }

    // @Bean
    // @Override
    // public AuthenticationManager authenticationManagerBean() throws Exception {
    // return super.authenticationManagerBean();
    // }
}
