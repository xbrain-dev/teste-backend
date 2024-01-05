package me.dri.repositories;

import me.dri.entities.Seller;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface SellerRepository  extends MongoRepository<Seller, String> {

    Optional<Seller> findByName(String name);
}


