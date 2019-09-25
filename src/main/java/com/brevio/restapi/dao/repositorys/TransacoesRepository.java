package com.brevio.restapi.dao.repositorys;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.brevio.restapi.dao.entitys.TransacoesEntity;

@Repository
public interface TransacoesRepository extends JpaRepository<TransacoesEntity, String>{

}
