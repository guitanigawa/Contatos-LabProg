package br.uel.ExercicioAPI.controller;

import br.uel.ExercicioAPI.model.Contact;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import br.uel.ExercicioAPI.service.ContactService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/contatos")
public class ContactController {
    private final ContactService service;

    @Autowired
    public ContactController(ContactService service) {
        this.service = service;
    }

    @GetMapping
    public List<Contact> listar() { return service.listar(); }

    @GetMapping("/{id}")
    public ResponseEntity<?> buscar(@PathVariable Long id) {
        Contact c = service.buscar(id);

        if(c != null){
            return ResponseEntity.ok(c);
        }else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Contato não encontrado!");
        }
    }

    @PostMapping
    public ResponseEntity<?> adicionar(@Valid @RequestBody Contact contact){
        try {
            Contact salvo = service.adicionar(contact);

            return ResponseEntity.status(HttpStatus.CREATED).body(salvo);
        }catch(RuntimeException e) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body(e.getMessage());
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> remover(@PathVariable Long id){
        try{
            service.remover(id);

            return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
        }catch(RuntimeException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> alterar(@Valid @RequestBody Contact contact, @PathVariable Long id){
        try{
            Contact atualizado = service.atualizar(id, contact);

            return ResponseEntity.ok(atualizado);
        }catch(RuntimeException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
    }
}
