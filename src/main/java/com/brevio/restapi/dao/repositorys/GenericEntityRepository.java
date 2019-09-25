package com.brevio.restapi.dao.repositorys;

import org.springframework.data.jpa.repository.JpaRepository;

import com.brevio.restapi.dao.entitys.GenericEntity;

public interface GenericEntityRepository  extends JpaRepository<GenericEntity, Long>{

}
