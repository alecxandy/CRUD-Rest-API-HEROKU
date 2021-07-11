package com.alexandredvlp.crudCompletoHeroku.controller;

import com.alexandredvlp.crudCompletoHeroku.model.Usuario;
import com.alexandredvlp.crudCompletoHeroku.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class UsuarioController {

    @Autowired // injeção de dependecias
    private UsuarioRepository usuarioRepository;


    @PostMapping(value = "salvar") /* mapeia a url */
    @ResponseBody /* Descricao da resposta */
    public ResponseEntity<Usuario> salvar(@RequestBody Usuario usuario) { /* Recebe os dados para salvar */
        Usuario user = usuarioRepository.save(usuario);
        return new ResponseEntity<Usuario>(user, HttpStatus.CREATED);
    }

    @PutMapping(value = "atualizar") /* mapeia a url */
    @ResponseBody /* Descricao da resposta */
    public ResponseEntity<?> atualizar(@RequestBody Usuario usuario) { /* Recebe os dados para salvar */
        if (usuario.getId() == null) {
            return new ResponseEntity<String>("Id não foi informado para atualização.", HttpStatus.OK);
        }
        Usuario user = usuarioRepository.saveAndFlush(usuario);
        return new ResponseEntity<Usuario>(user, HttpStatus.OK);
    }

    @GetMapping(value = "listatodos") /* Nosso primeiro método de API */
    @ResponseBody /* Retorna os dados par ao corpo da resposta */
    public ResponseEntity<List<Usuario>> listaUsuario() {
        List<Usuario> usuarios = usuarioRepository.findAll();/* executa a consulta no banco de dados */
        return new ResponseEntity<List<Usuario>>(usuarios, HttpStatus.OK);/* Retorna a lista em JSON */
    }

    @DeleteMapping(value = "delete") /* mapeia a url */
    @ResponseBody /* Descricao da resposta */
    public ResponseEntity<String> delete(@RequestParam Long iduser) { /* Recebe os dados para delete */
        usuarioRepository.deleteById(iduser);
        return new ResponseEntity<String>("User deletado com sucesso", HttpStatus.OK);
    }

    @GetMapping(value = "buscaruserid") /* mapeia a url */
    @ResponseBody /* Descricao da resposta */
    public ResponseEntity<Usuario> buscaruserid(@RequestParam(name = "iduser") Long iduser) { /* Recebe os dados para consultar */
        Usuario usuario = usuarioRepository.findById(iduser).get();
        return new ResponseEntity<Usuario>(usuario, HttpStatus.OK);
    }

    @GetMapping(value = "buscarPorNome") /* mapeia a url */
    @ResponseBody /* Descricao da resposta */
    public ResponseEntity<List<Usuario>> buscarPorNome(@RequestParam(name = "name") String name) { /* Recebe os dados para consultar */

        List<Usuario> usuario = usuarioRepository.buscarPorNome(name.trim().toUpperCase());
        return new ResponseEntity<List<Usuario>>(usuario, HttpStatus.OK);

    }


}
