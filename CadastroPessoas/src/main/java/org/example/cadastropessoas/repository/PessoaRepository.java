package org.example.cadastropessoas.repository;

import org.example.cadastropessoas.model.Pessoa;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface PessoaRepository extends JpaRepository<Pessoa, Long> {
    // método opcional para o desafio: buscar por idioma
    List<Pessoa> findByIdiomaIgnoreCase(String idioma);
}

