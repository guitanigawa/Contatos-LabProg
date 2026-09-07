package br.uel.ExercicioAPI.service;

import org.springframework.stereotype.Service;
import java.util.ArrayList;
import java.util.List;
import br.uel.ExercicioAPI.model.Contact;
import org.thymeleaf.util.StringUtils;

@Service
public class ContactService{
    private List<Contact> contatos = new ArrayList<>();

    public List<Contact> listar() {
        return contatos;
    }

    public Contact buscar(int index) { return contatos.get(index); }

    public void adicionar(Contact c) {
        if(
                c.getNome() == null || c.getNome().isBlank()
                || c.getTelefone() == null || c.getTelefone().isBlank()
                || c.getEmail() == null || c.getEmail().isBlank()
                || c.getEndereco() == null || c.getEndereco().isBlank()
                || c.getDataNascimento() == null
        ){
            throw new IllegalArgumentException();
        }else{
            contatos.add(c);
        }
    }

    public void remover(int index) {
        contatos.remove(index);
    }

    public void atualizar(int index, Contact c) {
        if(
                c.getNome() == null || c.getNome().isBlank()
                || c.getTelefone() == null || c.getTelefone().isBlank()
                || c.getEmail() == null || c.getEmail().isBlank()
                || c.getEndereco() == null || c.getEndereco().isBlank()
                || c.getDataNascimento() == null
        ){
            throw new IllegalArgumentException();
        }else{
            contatos.set(index, c);
        }
    }
}
