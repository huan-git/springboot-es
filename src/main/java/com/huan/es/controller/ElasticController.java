/**
 * 文件名：ElasticController.java
 * 版权：Company Technologies Co.,Ltd.Copyright YYYY-YYYY,All rights reserved
 * 版权：Copyright (c) 2020, jia2040020@126.com All Rights Reserved.
 * 描述：<描述>
 * 修改人：Administrator
 * 修改时间：2020年4月6日
 * 修改内容：<修改内容>
 */
package com.huan.es.controller;

import com.huan.es.dao.mysql.ReportInfoDao;
import com.huan.es.nosql.elasticsearch.document.ReportBean;
import com.huan.es.service.IElasticService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * <一句话功能简述>
 * <功能详细描述>
 *
 * @author ahuan
 * @version [版本号, 2020年4月6日]
 * @see [相关类/方法]
 * @since [产品/模块版本]
 */
@RestController
@RequestMapping("/elastic")
public class ElasticController {
    private static final Logger log= LoggerFactory.getLogger("ElasticController");

    @Autowired
    private IElasticService elasticService;

    @Resource
    ReportInfoDao reportInfoDao;

    @GetMapping("/init")
    public void init() {
        int pageSize=5000;
        int totalPageNum =161;
        for (int curPage = 1; curPage <=totalPageNum; curPage++) {
            int pageNum= (curPage-1)*pageSize;
            log.info("pageNum:{},pageSize:{}",pageNum,pageSize);
            List<ReportBean> list = reportInfoDao.page(pageNum,pageSize);
            elasticService.saveAll(list);
        }
    }

    @GetMapping("/findByTd")
    public ReportBean findById(@RequestParam Long id) {
        return elasticService.findById(id);
    }

    @GetMapping("/all")
    public List<ReportBean> all() {
        Iterator<ReportBean> all = elasticService.findAll();
        return copyIterator(all);
    }

    @GetMapping("/page")
    public List<ReportBean> page(Integer pageNum, Integer pageSize) {
        return elasticService.page(pageNum, pageSize);
    }

    @GetMapping("/sort")
    public List<ReportBean> sort(String field,String order) {
        return elasticService.sort(field, order);
    }

    /**
     * 指定字段查询
     * @param fields
     * @return
     */
    @PostMapping("/findSubField")
    public List<ReportBean> findSubField(@RequestBody String[] fields) {
        return elasticService.findSubField(fields);
    }

    /**
     * {
     *   "query": {
     *     "bool": {
     *       "must": {
     *         "match":{
     *           "price":31    //只支持单字段
     *         }
     *       }
     *     }
     *   }
     * }
     * @return
     */
    @GetMapping("must")
    public List<ReportBean> findByFilterBymust(String field,String value){
        return elasticService.findByFilterBymust(field,value);
    }

    /**
     * {
     *   "query": {
     *     "bool": {
     *       "must": {
     *         "match":{
     *           "price":31    //只支持单字段
     *         }
     *       }
     *     }
     *   }
     * }
     * @return
     */
    @GetMapping("mustAndfilterrange")
    public List<ReportBean> findByFilterBymustAndfilterrange(String field,String value){
        return elasticService.findByFilterBymustAndfilterrange(field,value);
    }

    /**
     * 指定字段和值查询
     * @param field
     * @param value
     * @return
     */
    @GetMapping("/findBykv")
    public List<ReportBean> findByFieldAndV(@RequestParam String field, @RequestParam String value) {
        return elasticService.findByFieldAndV(field, value);
    }

    public static <T> List<T> copyIterator(Iterator<T> iter) {
        List<T> copy = new ArrayList<T>();
        while (iter.hasNext())
            copy.add(iter.next());
        return copy;
    }

}
