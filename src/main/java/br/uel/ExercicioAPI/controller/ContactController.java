package br.uel.ExercicioAPI.controller;

import br.uel.ExercicioAPI.model.Contact;
import org.apache.coyote.Response;
import org.springframework.beans.factory.annotation.Autowired;
import br.uel.ExercicioAPI.service.ContactService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/contatos")
public class ContactController {
    private ContactService service;

    @Autowired
    public ContactController(ContactService service) {
        this.service = service;
    }

    @GetMapping
    public List<Contact> listar() { return service.listar(); }

    @GetMapping("/{index}")
    public ResponseEntity<Contact> buscar(@PathVariable int index) {
        try{
            Contact c = service.buscar(index);

            return ResponseEntity.ok(c);
        }catch(IndexOutOfBoundsException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }

    @PostMapping
    public ResponseEntity<Void> adicionar(@RequestBody Contact contact){
        try{
            service.adicionar(contact);

            return ResponseEntity.status(HttpStatus.CREATED).build();
        }catch(IllegalArgumentException e){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }

    }

    @DeleteMapping("/{index}")
    public ResponseEntity<Void> remover(@PathVariable int index){
        try{
            service.remover(index);

            return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
        }catch(IndexOutOfBoundsException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }

    @PutMapping("/{index}")
    public ResponseEntity<Void> alterar(@RequestBody Contact contact, @PathVariable int index){
        try{
            service.atualizar(index, contact);

            return ResponseEntity.ok().build();
        }catch(IndexOutOfBoundsException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }
}
