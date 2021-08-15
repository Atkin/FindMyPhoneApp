package com.jsonwitneses.findmyphone.controller;

import com.jsonwitneses.findmyphone.dto.MetricDto;
import com.jsonwitneses.findmyphone.entity.Metric;
import com.jsonwitneses.findmyphone.mapper.MetricMapper;
import com.jsonwitneses.findmyphone.service.MetricService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Controller
@RequestMapping
public class MapController {

    private MetricService metricService;

    @Autowired
    public MapController(MetricService metricService) {
        this.metricService = metricService;
    }

    @GetMapping("/map")
    public String mapForm(Model model) {
//        MetricDto metric = MetricMapper.metricToDto(metricService.getLastDeviceMetric(1L));
//        model.addAttribute("metric", metric);


        List<Metric> metrics = metricService.getAllDeviceMetrics(1L);
        List<MetricDto> metricDtos = new ArrayList<>();
        for (Metric metric : metrics) {
            metricDtos.add(MetricMapper.metricToDto(metric));
        }
        model.addAttribute("metrics", metricDtos);
        return "map_form";
    }
}
