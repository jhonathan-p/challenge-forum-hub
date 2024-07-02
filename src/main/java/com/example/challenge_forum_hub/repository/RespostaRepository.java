package com.example.challenge_forum_hub.repository;

import com.example.challenge_forum_hub.model.Resposta;
import com.example.challenge_forum_hub.model.Topico;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface RespostaRepository extends JpaRepository<Resposta, Long> {

    List<Resposta> findByTopicoId(Topico id);

    Optional<Resposta> findByTopicoIdAndId(Topico topicoId, Long id);

    void deleteByTopicoIdAndId(Topico topicoId, Long id);

    boolean existsByTopicoIdAndSolucao(Topico topicoId, boolean solucao);

}
