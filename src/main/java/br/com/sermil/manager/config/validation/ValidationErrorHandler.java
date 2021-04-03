package br.com.sermil.manager.config.validation;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.http.HttpStatus;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class ValidationErrorHandler { //TRATAMENTO DE ERROS DE VALIDAÇÃO
	
	@Autowired
	private MessageSource messageSource; //AJUDA A PEGAR A MENSAGEM DE ERRO, COM O IDIOMA SELECIONADO.
	
	@ResponseStatus(code = HttpStatus.BAD_REQUEST) //QUAL STATUS IRÁ DEVOLVER.
	@ExceptionHandler(MethodArgumentNotValidException.class) //SE CAIR QUALQUER ERRO DO TIPO, CHAMA O MÉTODO ABAIXO.
	public List<FormErrorDto> handle(MethodArgumentNotValidException exception) { //UMA LISTA COM OS ERROS QUE PODEM ACONTECER
		List<FormErrorDto> dto = new ArrayList<>();
		
		List<FieldError> fieldErrors = exception.getBindingResult().getFieldErrors(); //CONTEM TODOS OS ERROS DE FORMULÁRIO.
		fieldErrors.forEach(e -> {
			String message = messageSource.getMessage(e, LocaleContextHolder.getLocale());
			FormErrorDto erro = new FormErrorDto(e.getField(), e.getField() + " " + message);
			dto.add(erro);
		});
		
		return dto;
		
	}

}