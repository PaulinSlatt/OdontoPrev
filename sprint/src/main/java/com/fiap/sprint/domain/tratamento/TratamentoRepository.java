package com.fiap.sprint.domain.tratamento;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

//Classe que pega os dados de medico e faz a persistencia com o banco de dados



public interface TratamentoRepository extends JpaRepository<Tratamento, Long> {

    Page<Tratamento> findAllByAtivoTrue(Pageable paginacao);
}
