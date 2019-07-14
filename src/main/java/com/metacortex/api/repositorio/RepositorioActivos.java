package com.metacortex.api.repositorio;

import com.metacortex.api.entidades.PrecioActivo;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface RepositorioActivos extends MongoRepository<PrecioActivo,String> {}
