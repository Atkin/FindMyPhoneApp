package com.jsonwitneses.findmyphone.dao;

import com.jsonwitneses.findmyphone.entity.Metric;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface LocationRepository extends MongoRepository<Metric,Long> {

//    @Query("SELECT DISTINCT m " +
//            "FROM Metric m " +
//            "WHERE m.id =?1 order by m.timeStamp desc")
//    Metric getLastMetricForDevice(Long id);

//    Metric getLocationMetricByDevice_Id(Long id);

//    List<Metric> findAllByDevice_Id(Long id);
    Metric getDistinctFirstByDevice_IdOrderByTimeStampDesc(Long id);
    Metric getDistinctByDevice_IdOrderByTimeStampDesc(Long id);
    List<Metric> findAllByDevice_Id(Long id);
}
