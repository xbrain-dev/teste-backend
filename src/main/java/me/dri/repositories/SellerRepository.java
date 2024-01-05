package me.dri.repositories;

import me.dri.entities.Seller;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SellerRepository  extends MongoRepository<Seller, String> {
}


