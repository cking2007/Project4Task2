package com.example.demo;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.aggregation.Aggregation;
import org.springframework.data.mongodb.core.aggregation.AggregationResults;
import org.springframework.data.mongodb.core.query.Collation;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author
 * @version V1.0
 * @description
 * @date 2022/4/8 9:09 AM
 **/

@Service
public class MyService {

    public final Logger logger = LoggerFactory.getLogger(MyService.class);

    @Autowired
    public MongoTemplate mongoTemplate;

    public long getTotalCount() {
        long count = mongoTemplate.getCollection("statistics").countDocuments();

        System.out.println("statistics count :" + count);

        return count;
    }

    public int getTotalSearchCount() {
        List<StatisticsEntity> list = mongoTemplate.findAll(StatisticsEntity.class, "statistics");
        int count = 0;
        if (list.size() > 0) {
            for (StatisticsEntity statisticsEntity : list) {
                count = count + statisticsEntity.getSearchCount();
            }
        }
        return count;
    }

    public int getMostTime() {
        Query query = new Query();
        query.collation(Collation.of(Collation.CollationLocale.of("zh")));
        String[] str = {"apiTime"};
        query.with(Sort.by(Sort.Direction.DESC, str));

        DashEntity dashEntity = mongoTemplate.findOne(query, DashEntity.class, "dashBoard");
        int mostTime = dashEntity.getApiTime();
        System.out.println("mostTime  " + mostTime);

        return mostTime;
    }

    public int getLeastTime() {
        Query query = new Query();
        query.collation(Collation.of(Collation.CollationLocale.of("zh")));
        String[] str = {"apiTime"};
        query.with(Sort.by(Sort.Direction.ASC, str));

        DashEntity dashEntity = mongoTemplate.findOne(query, DashEntity.class, "dashBoard");
        int leastTime = dashEntity.getApiTime();
        System.out.println("leastTime  " + leastTime);

        return leastTime;
    }

    public int getCountBySearchCountry() {

        Query query = new Query();
        query.collation(Collation.of(Collation.CollationLocale.of("zh")));
        String[] str = {"searchCount"};
        query.with(Sort.by(Sort.Direction.DESC, str));

        StatisticsEntity statisticsEntity = mongoTemplate.findOne(query, StatisticsEntity.class, "statistics");
        int countByCountry = statisticsEntity.getSearchCount();
        System.out.println("countByCountry  " + countByCountry);

        return countByCountry;
    }

    public String getCountry() {

        Query query = new Query();
        query.collation(Collation.of(Collation.CollationLocale.of("zh")));
        String[] str = {"searchCount"};
        query.with(Sort.by(Sort.Direction.DESC, str));

        StatisticsEntity statisticsEntity = mongoTemplate.findOne(query, StatisticsEntity.class, "statistics");
        String country = statisticsEntity.getCountryCode();
        System.out.println("country  " + country);

        return country;
    }

    public List<DashEntity> getDashData() {
        List<DashEntity> listDash = mongoTemplate.findAll(DashEntity.class, "dashBoard");
        return listDash;
    }

}
