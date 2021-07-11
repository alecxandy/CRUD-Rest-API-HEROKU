package com.alexandredvlp.crudCompletoHeroku.repository;

import com.alexandredvlp.crudCompletoHeroku.model.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UsuarioRepository extends JpaRepository<Usuario, Long> {
    //trim: metodo para retirar espaço do string
    //upper: colocar string me maiusculo
    @Query(value = "select u from Usuario u where upper(trim(u.nome)) like %?1%")
    List<Usuario> buscarPorNome(String name);
}