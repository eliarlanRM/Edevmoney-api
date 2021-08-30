package com.eliarlan.edevmoneyapi.config;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.config.annotation.configurers.ClientDetailsServiceConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configuration.AuthorizationServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerEndpointsConfigurer;
import org.springframework.security.oauth2.provider.token.TokenEnhancer;
import org.springframework.security.oauth2.provider.token.TokenEnhancerChain;
import org.springframework.security.oauth2.provider.token.TokenStore;
import org.springframework.security.oauth2.provider.token.store.JwtAccessTokenConverter;
import org.springframework.security.oauth2.provider.token.store.JwtTokenStore;

import com.eliarlan.edevmoneyapi.config.token.CustomTokenEnhancer;


@Profile("oauth-security")
@Configuration
@EnableAuthorizationServer
public class AuthorizationServerConfig extends AuthorizationServerConfigurerAdapter{
		
		@Autowired
		private PasswordEncoder passwordEncoder;
		
		@Autowired
		private AuthenticationManager authenticationManager;
		
		@Autowired
		private UserDetailsService userDetailsService;
		
		@Override
		public void configure(ClientDetailsServiceConfigurer clients) throws Exception {
			clients.inMemory() // Está gravado em memoria, mas pode ser por JDBC (banco de dados)
						.withClient("angular") // nome do usuario
						.secret(passwordEncoder.encode("@ngul@r0")) // O passwordEncoder encoda a senha durante a execução do sistema
						.scopes("read", "write") // Escopo LER e GRAVAR
						.authorizedGrantTypes("password", "refresh_token") // Modo de tokens para usuario, sinceramente nao entendi essa parte
						.accessTokenValiditySeconds(1800) // Quantos segundos esse Token ficara ativo. 1800/60 = 30 min
						.refreshTokenValiditySeconds(3600 * 24)
					.and()
						.withClient("mobile") // nome do usuario
						.secret(passwordEncoder.encode("m0b1l3")) // O passwordEncoder encoda a senha durante a execução do sistema
						.scopes("read") // Escopo LER e GRAVAR
						.authorizedGrantTypes("password", "refresh_token") // Modo de tokens para usuario, sinceramente nao entendi essa parte
						.accessTokenValiditySeconds(1800) // Quantos segundos esse Token ficara ativo. 1800/60 = 30 min
						.refreshTokenValiditySeconds(3600 * 24);
				}
				@Override
				public void configure(AuthorizationServerEndpointsConfigurer endpoints) throws Exception {
					TokenEnhancerChain tokenEnhancerChain = new TokenEnhancerChain();
					tokenEnhancerChain.setTokenEnhancers(Arrays.asList(tokenEnhancer(), accessTokenConverter()));
					endpoints
						.tokenStore(tokenStore())
						.tokenEnhancer(tokenEnhancerChain)
						.accessTokenConverter(this.accessTokenConverter())
						.reuseRefreshTokens(false)
						.userDetailsService(this.userDetailsService)
				        .authenticationManager(this.authenticationManager);
				}
				@Bean
				public JwtAccessTokenConverter accessTokenConverter() {
					JwtAccessTokenConverter accessTokenConverter = new JwtAccessTokenConverter();
					accessTokenConverter.setSigningKey("algaworks");
					return accessTokenConverter;
				}
				@Bean
				public TokenStore tokenStore() {
					return new JwtTokenStore(accessTokenConverter());
				}
				@Bean
				public TokenEnhancer tokenEnhancer() {
				    return new CustomTokenEnhancer();
				}
				
}