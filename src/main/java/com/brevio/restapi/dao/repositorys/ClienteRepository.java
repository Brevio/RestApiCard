package com.brevio.restapi.dao.repositorys;

import org.springframework.data.jpa.repository.JpaRepository;

import com.brevio.restapi.dao.entitys.ClienteEntity;

public interface ClienteRepository extends JpaRepository<ClienteEntity, Long>{

}
