package com.jsonwitneses.findmyphone.mapper;

import com.jsonwitneses.findmyphone.dto.MetricDto;
import com.jsonwitneses.findmyphone.entity.Device;
import com.jsonwitneses.findmyphone.entity.Metric;

import java.util.Collections;

public class MetricMapper {
    public static MetricDto metricToDto(Metric metric){
        return new MetricDto(metric.getId(),metric.getDevice().getId(),
                metric.getTimeStamp(),metric.getLongitude(),metric.getLatitude(),
                metric.getCellid(),metric.getLac(),metric.getRsrp(),metric.getRsrq(),
                metric.getSinr(),metric.getDeviceid(),metric.getUserid(),metric.getImsi());
    }
        public static Metric dtoToMetric(MetricDto metric){
            return new Metric(metric.getId(),new Device(metric.getDeviceId(), "", Collections.emptySet()),
                    metric.getTimeStamp(),metric.getLongitude(),metric.getLatitude(),
                    metric.getCellid(),metric.getLac(),metric.getRsrp(),metric.getRsrq(),
                    metric.getSinr(),metric.getDeviceid(),metric.getUserid(),metric.getImsi());
        }
    }
