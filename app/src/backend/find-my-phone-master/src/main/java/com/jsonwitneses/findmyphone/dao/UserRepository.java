package com.jsonwitneses.findmyphone.dao;

import com.jsonwitneses.findmyphone.entity.User;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface UserRepository extends MongoRepository<User,Long> {
}
