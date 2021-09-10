package org.generation.blogPessoal.repository;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

import org.generation.Edg.model.Usuario;
import org.generation.Edg.repository.UsuarioRepository;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;

@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class UsuarioRepositoryTest {
	
	@Autowired
	private UsuarioRepository usuarioRepository;
	
	@BeforeAll
	void start() {
		
	    LocalDate data = LocalDate.parse("2000-07-22", DateTimeFormatter.ofPattern("yyyy-MM-dd"));
	    
	    Usuario usuario = new Usuario(0, "João da Silva", "joao@email.com.br", "13465278", data);
	    if(usuarioRepository.findByEmail(usuario.getEmail()).isEmpty())
	    	usuarioRepository.save(usuario);
	    
	    usuario = new Usuario(0, "Frederico da Silva", "frederico@email.com.br", "13465278", data);
		if(!usuarioRepository.findByEmail(usuario.getEmail()).isPresent())
			usuarioRepository.save(usuario);

        usuario = new Usuario(0, "Paulo Antunes", "paulo@email.com.br", "13465278", data);
        if(!usuarioRepository.findByEmail(usuario.getEmail()).isPresent())
            usuarioRepository.save(usuario);
	    
	}
	
	@Test
	@DisplayName("Retorna o nome")
	public void findBynomeRetornaNome() {
		Usuario usuario = usuarioRepository.findByNome("João da Silva");
		assertTrue(usuario.getNome().equals("João da Silva"));
	}
	
	@Test
	@DisplayName("Retorna 2 usuarios")
	public void findAllByNomeContainingIgnoreCaseRetornaTresUsuarios() {

		List<Usuario> listaDeUsuarios = usuarioRepository.findAllByNomeContainingIgnoreCase("Silva");
		assertEquals(2, listaDeUsuarios.size());
	}

	@AfterAll
	public void end() {
		
		System.out.println("Teste Finalizado!");
		
	}
	
	


}
