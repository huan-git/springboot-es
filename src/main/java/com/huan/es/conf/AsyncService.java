package com.huan.es.conf;

import com.huan.es.nosql.elasticsearch.document.ReportBean;
import com.huan.es.nosql.elasticsearch.repository.ElasticRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.Arrays;
import java.util.List;

/**
 * @program: disaster_report_pcbe
 * @description: this is the description of the AsyncService class
 * @author: ahuan
 * @version: 2020-08-27 17:25
 **/
@Component
public class AsyncService {
    private static final Logger log= LoggerFactory.getLogger("AsyncService");

    @Autowired
    private ElasticRepository elasticRepository;

    @Async("asyncExecutor")
    public void saveAll(List<ReportBean> list) throws InterruptedException {
        log.info("线程"+Thread.currentThread().getName()+"do task start...");
        Thread.sleep(10000);
        elasticRepository.saveAll(list);
        log.info("线程"+Thread.currentThread().getName()+"do task end...");
    }
}