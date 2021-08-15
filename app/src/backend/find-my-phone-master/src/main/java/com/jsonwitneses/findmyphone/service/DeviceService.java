package com.jsonwitneses.findmyphone.service;

import com.jsonwitneses.findmyphone.dao.DeviceRepository;
import com.jsonwitneses.findmyphone.dao.UserRepository;
import com.jsonwitneses.findmyphone.entity.Device;
import com.jsonwitneses.findmyphone.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DeviceService {
    private final DeviceRepository deviceRepository;

    @Autowired
    public DeviceService(DeviceRepository userRepository) {
        this.deviceRepository = userRepository;
    }

    public Device getDeviceById(Long id){
        return deviceRepository.findById(id).orElseThrow(RuntimeException::new);
    }

    public  void save(Device device){
        deviceRepository.save(device);
    }
}
