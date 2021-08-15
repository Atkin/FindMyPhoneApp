package com.jsonwitneses.findmyphone.dao;

import com.jsonwitneses.findmyphone.entity.Device;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface DeviceRepository extends MongoRepository<Device,Long> {
}
