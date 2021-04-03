package br.com.sermil.manager.config.security;

import java.util.Date;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import br.com.sermil.manager.model.User;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

@Service
public class TokenService {
	
	@Value("${forum.jwt.expiration}") //VALOR BUSCADO NO ARQUIVO APPLICATION.PROPERTIES - TEMPO DE LOGIN
	private String expiration;
	
	@Value("${forum.jwt.secret}")     //VALOR BUSCADO NO ARQUIVO APPLICATION.PROPERTIES - SENHA
	private String secret;
	
	public String generateToken(Authentication authentication) {
		
		User logado = (User) authentication.getPrincipal(); //RECUPERAR O USUÁRIO QUE ESTÁ LOGADO;
		Date today = new Date();
		Date dataExpiracao = new Date(today.getTime() + Long.parseLong(expiration)); //DATA E HORA DO LOGIN + TEMPO DE EXPIRAÇÃO;
		
		return Jwts.builder()
				
				.setIssuer("API Sermil Manager")            //QUEM ESTÁ GERANDO O TOKEN;
				.claim("id", logado.getId().toString())     //A QUEM PERTENCE O TOKEN;
				.claim("name", logado.getName())            //A QUEM PERTENCE O TOKEN;
				.claim("email", logado.getEmail())
				.claim("profile", logado.getProfile())
				.setIssuedAt(today)                         //DATA DO LOGIN;
				.setExpiration(dataExpiracao)               //TEMPO DE EXPIRAÇÃO DE LOGIN;
				.signWith(SignatureAlgorithm.HS256, secret) //ALGORÍTMO DE CRIPTOGRAFIA E A SENHA DA MINHA APLICAÇÃO, PARA ASSINATURA;
				.compact();                                 //COMPACTAR TUDO E TRANSFORMAR EM UMA STRING; 
	}

	public boolean isValidToken(String token) {
		try {
			Jwts.parser().setSigningKey(this.secret).parseClaimsJws(token); //IRÁ DESCRIPTOGRAFAR A CHAVE E FARÁ UMA VERIFICAÇÃO;
			return true; //SE OK, RETORNA TRUE;
		} catch (Exception e) {
			return false; //SE NÃO, RETORNA FALSE;
		}
		
	}

	public Long getUserId(String token) { //MÉTODO QUE IRÁ BUSCAR O ID DO USUÁRIO;
		Claims claims = Jwts.parser().setSigningKey(this.secret).parseClaimsJws(token).getBody();
		return Long.parseLong(claims.getSubject());
	}

}
