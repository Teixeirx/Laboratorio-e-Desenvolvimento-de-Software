package org.example.cadastropessoas.controller;

import org.example.cadastropessoas.model.Pessoa;
import org.example.cadastropessoas.repository.PessoaRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/pessoas")
public class PessoaController {

    private final PessoaRepository pessoaRepository;

    public PessoaController(PessoaRepository pessoaRepository) {
        this.pessoaRepository = pessoaRepository;
    }

    // GET /pessoas
    @GetMapping
    public List<Pessoa> listar() {
        return pessoaRepository.findAll();
    }

    // GET /pessoas/{id}
    @GetMapping("/{id}")
    public ResponseEntity<Pessoa> getPessoaPorId(@PathVariable Long id) {
        Optional<Pessoa> p = pessoaRepository.findById(id);
        return p.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    // POST /pessoas
    @PostMapping
    public ResponseEntity<?> adicionarPessoa(@RequestBody Pessoa pessoa) {
        if (pessoa.getNome() == null || pessoa.getNome().isBlank()) {
            return ResponseEntity.badRequest().body("Nome é obrigatório");
        }
        Pessoa salva = pessoaRepository.save(pessoa);
        return ResponseEntity.status(HttpStatus.CREATED).body(salva);
    }

    // PUT /pessoas/{id}
    @PutMapping("/{id}")
    public ResponseEntity<Pessoa> updatePessoa(@PathVariable Long id, @RequestBody Pessoa pessoa) {
        Optional<Pessoa> existe = pessoaRepository.findById(id);
        if (existe.isPresent()) {
            Pessoa atual = existe.get();
            atual.setNome(pessoa.getNome());
            atual.setSexo(pessoa.getSexo());
            atual.setIdioma(pessoa.getIdioma());
            Pessoa salva = pessoaRepository.save(atual);
            return ResponseEntity.ok(salva);
        } else {
            // cria nova pessoa com id informado
            pessoa.setId(id);
            Pessoa nova = pessoaRepository.save(pessoa);
            return ResponseEntity.status(HttpStatus.CREATED).body(nova);
        }
    }

    // DELETE /pessoas/{id}
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletarPessoa(@PathVariable Long id) {
        if (pessoaRepository.existsById(id)) {
            pessoaRepository.deleteById(id);
            return ResponseEntity.ok().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    // Desafio: GET /pessoas/idioma/{idioma}
    @GetMapping("/idioma/{idioma}")
    public List<Pessoa> buscarPorIdioma(@PathVariable String idioma) {
        return pessoaRepository.findByIdiomaIgnoreCase(idioma);
    }
}
