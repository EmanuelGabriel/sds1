package br.com.emanuelgabriel.exceptionhandler;

import java.time.OffsetDateTime;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.NoHandlerFoundException;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import br.com.emanuelgabriel.exceptionhandler.Problema.Campo;
import br.com.emanuelgabriel.exceptions.RegraNegocioException;

@ControllerAdvice
public class ApiExceptionHandler extends ResponseEntityExceptionHandler {

	@Autowired
	private MessageSource messageSource;

	private static final String VALIDACAO_CAMPOS = "Um ou mais campos estão inválidos. Faça o preenchimento correto e tente novamente.";

	private static final String MSG_ERRO_GENERICA_USUARIO_FINAL = "Ocorreu um erro interno inesperado no sistema. Tente novamente e se "
			+ "o problema persistir, entre em contato com o administrador do sistema.";

	@Override
	protected ResponseEntity<Object> handleHttpMessageNotReadable(HttpMessageNotReadableException ex,
			HttpHeaders headers, HttpStatus status, WebRequest request) {

		TipoProblema tipoProblema = TipoProblema.DADOS_INVALIDOS;
		String detalheErro = ex.getMessage();

		ProblemaResponse problema = criarProblemaBuilder(status, tipoProblema).mensagem(detalheErro).build();

		return super.handleExceptionInternal(ex, problema, headers, HttpStatus.BAD_REQUEST, request);
	}

	@Override
	protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex,
			HttpHeaders headers, HttpStatus status, WebRequest request) {

		List<Campo> campos = new ArrayList<Problema.Campo>();
		for (ObjectError error : ex.getBindingResult().getAllErrors()) {
			String nome = ((FieldError) error).getField();
			String mensagem = messageSource.getMessage(error, LocaleContextHolder.getLocale());
			campos.add(new Problema.Campo(nome, mensagem));
		}

		Problema problema = new Problema();
		problema.setStatus(status.value());
		problema.setTitulo(VALIDACAO_CAMPOS);
		problema.setDataHora(OffsetDateTime.now());
		problema.setCampos(campos);

		return super.handleExceptionInternal(ex, problema, headers, status, request);
	}

	@Override
	protected ResponseEntity<Object> handleNoHandlerFoundException(NoHandlerFoundException ex, HttpHeaders headers,
			HttpStatus status, WebRequest request) {

		TipoProblema tipoProblema = TipoProblema.RECURSO_NAO_ENCONTRADO;
		String detalhe = String.format("O recurso %s, que você tentou acessar, é inexistente.", ex.getRequestURL());

		ProblemaResponse problema = criarProblemaBuilder(status, tipoProblema, detalhe)
				.mensagem(MSG_ERRO_GENERICA_USUARIO_FINAL).build();

		return super.handleExceptionInternal(ex, problema, headers, status, request);
	}

	@ExceptionHandler(RegraNegocioException.class)
	public ResponseEntity<?> handleRegraNegocioException(RegraNegocioException ex, WebRequest request) {

		HttpStatus status = HttpStatus.BAD_REQUEST;
		TipoProblema tipoProblema = TipoProblema.ERRO_NEGOCIO;
		String detalheErro = ex.getMessage();

		ProblemaResponse problema = criarProblemaBuilder(status, tipoProblema).mensagem(detalheErro).build();

		return handleExceptionInternal(ex, problema, new HttpHeaders(), status, request);
	}

	private ProblemaResponse.ProblemaResponseBuilder criarProblemaBuilder(HttpStatus status,
			TipoProblema tipoProblema) {

		return ProblemaResponse.builder().timestamp(OffsetDateTime.now()).status(status.value())
				.tipo(tipoProblema.getUri()).titulo(tipoProblema.getTitulo());
	}

	private ProblemaResponse.ProblemaResponseBuilder criarProblemaBuilder(HttpStatus status, TipoProblema tipoProblema,
			String detalhe) {

		return ProblemaResponse.builder().timestamp(OffsetDateTime.now()).status(status.value())
				.tipo(tipoProblema.getUri()).titulo(tipoProblema.getTitulo()).detalhe(detalhe);
	}

}
