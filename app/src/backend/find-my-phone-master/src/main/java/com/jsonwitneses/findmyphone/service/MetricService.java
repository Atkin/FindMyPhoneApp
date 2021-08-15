package com.jsonwitneses.findmyphone.service;

import com.jsonwitneses.findmyphone.dao.DeviceRepository;
import com.jsonwitneses.findmyphone.dao.LocationRepository;
import com.jsonwitneses.findmyphone.dao.UserRepository;
import com.jsonwitneses.findmyphone.entity.Device;
import com.jsonwitneses.findmyphone.entity.Metric;
import com.jsonwitneses.findmyphone.entity.User;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@Service
public class MetricService {
    private final LocationRepository locationRepository;
    private final UserRepository userRepository;
    private final DeviceRepository deviceRepository;

    public MetricService(LocationRepository locationRepository, UserRepository userRepository, DeviceRepository deviceRepository) {
        this.locationRepository = locationRepository;
        this.userRepository = userRepository;
        this.deviceRepository = deviceRepository;
    }

    public List<Metric> getAllMetrics(){
        return locationRepository.findAll();
    }
    public List<Metric> getAllDeviceMetrics(Long id){
        List<Metric> metric = locationRepository.findAllByDevice_Id(id);
        return metric;
    }

    public Metric getLastDeviceMetric(Long id){
       return locationRepository.getDistinctFirstByDevice_IdOrderByTimeStampDesc(id);
    }
    public List<Metric> getLastDeviceMetricsForUser(Long id){
        User user = userRepository.findById(id).orElseThrow(RuntimeException::new);
        Set<Device> devices = user.getDevices();
        List<Metric> metrics = new ArrayList<>();
        for (Device device: devices) {
            metrics.add(locationRepository.getDistinctFirstByDevice_IdOrderByTimeStampDesc(device.getId()));
        }
        return metrics;
    }
    public void insertMetric(Metric metric){
        locationRepository.save(metric);
    }

    public void insertMetrics(List<Metric> metrics) {
        for (Metric metric:metrics) {
            locationRepository.save(metric);
        }
    }
}
