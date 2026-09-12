package br.uel.ExercicioAPI.service;

import br.uel.ExercicioAPI.repository.ContactRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import br.uel.ExercicioAPI.model.Contact;

@Service
public class ContactService{
    @Autowired
    private ContactRepository contactRepository;

    public List<Contact> listar() {
        return contactRepository.findAll();
    }

    public Contact buscar(Long id) {
        return contactRepository.findById(id).orElse(null);
    }

    public Contact adicionar(Contact c) {
        if(
                contactRepository.existsByEmail(c.getEmail())
                || contactRepository.existsByTelefone(c.getTelefone())
        ){
            throw new RuntimeException("E-mail ou telefone em uso.");
        }

        return contactRepository.save(c);
    }

    public void remover(Long id) {
        if (!contactRepository.existsById(id)) {
            throw new RuntimeException("Contato não encontrado com id: " + id);
        }

        contactRepository.deleteById(id);
    }

    public Contact atualizar(Long id, Contact c) {

        return contactRepository.findById(id).map(
                c_antigo -> {
                    c_antigo.setNome(c.getNome());
                    c_antigo.setTelefone(c.getTelefone());
                    c_antigo.setEmail(c.getEmail());
                    c_antigo.setEndereco(c.getEndereco());
                    c_antigo.setDataNascimento(c.getDataNascimento());

                    return c_antigo;
                }
            ).orElseThrow(
                    () -> new RuntimeException("Contato não encontrado com id:" + id)
        );

    }
}
