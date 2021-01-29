package com.app.dao;

import com.app.entity.Client;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ClientRepository extends MongoRepository<Client,String> {
    public Client findByTel(String tel);
    public List<Client> findByAgentID(String id);
}
