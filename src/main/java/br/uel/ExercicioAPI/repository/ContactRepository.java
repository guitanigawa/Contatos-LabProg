package br.uel.ExercicioAPI.repository;

import br.uel.ExercicioAPI.model.Contact;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ContactRepository extends JpaRepository<Contact, Long> {
    Boolean existsByEmail(String email);
    Boolean existsByTelefone(String telefone);
}
