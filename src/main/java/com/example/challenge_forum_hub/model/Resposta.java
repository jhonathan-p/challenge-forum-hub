package com.example.challenge_forum_hub.model;

import jakarta.persistence.*;
import lombok.*;

@Table(name = "respostas")
@Entity(name = "Resposta")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
public class Resposta {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToOne
    @JoinColumn(name = "topico_id")
    private Topico topicoId;
    private String mensagem;
    private String dataCriacao;
    private String autor;
    private Boolean solucao;

}
