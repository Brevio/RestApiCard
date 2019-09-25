package com.brevio.restapi.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.brevio.restapi.dao.entitys.Transacoes;

@Repository
public interface TransacoesDao extends JpaRepository<Transacoes, String>{

}
