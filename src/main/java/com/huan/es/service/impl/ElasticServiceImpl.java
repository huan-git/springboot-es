/**
 * 文件名：ElasticServiceImpl.java 版权：Company Technologies Co.,Ltd.Copyright
 * YYYY-YYYY,All rights reserved 版权：Copyright (c) 2020, jia2040020@126.com All
 * Rights Reserved. 描述：<描述> 修改人：Administrator 修改时间：2020年4月6日 修改内容：<修改内容>
 */
package com.huan.es.service.impl;

import com.huan.es.nosql.elasticsearch.document.ReportBean;
import com.huan.es.nosql.elasticsearch.repository.ElasticRepository;
import com.huan.es.service.IElasticService;
import org.elasticsearch.search.sort.SortBuilders;
import org.elasticsearch.search.sort.SortOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.elasticsearch.core.ElasticsearchRestTemplate;
import org.springframework.data.elasticsearch.core.query.FetchSourceFilter;
import org.springframework.data.elasticsearch.core.query.NativeSearchQueryBuilder;
import org.springframework.data.elasticsearch.core.query.SearchQuery;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import java.util.Iterator;
import java.util.List;
import java.util.Optional;

import static org.elasticsearch.index.query.QueryBuilders.*;

/**
 * <一句话功能简述> <功能详细描述>
 *
 * @author ahuan
 * @version [版本号, 2020年4月6日]
 * @see [相关类/方法]
 * @since [产品/模块版本]
 */
@Service("elasticService")
public class ElasticServiceImpl implements IElasticService {
    @Autowired
    private ElasticsearchRestTemplate elasticsearchTemplate;

    @Autowired
    private ElasticRepository elasticRepository;

    private Pageable pageable = PageRequest.of(0, 10);

    public void createIndex() {
        elasticsearchTemplate.createIndex(ReportBean.class);
    }

    public void deleteIndex(String index) {
        elasticsearchTemplate.deleteIndex(index);
    }

    public void save(ReportBean bean) {
        elasticRepository.save(bean);
    }

    @Async
    public void saveAll(List<ReportBean> list) {
        elasticRepository.saveAll(list);
    }

    public Iterator<ReportBean> findAll() {
        return elasticRepository.findAll().iterator();
    }

    public  List<ReportBean> findByFieldAndV(String field, String v) {
        SearchQuery query = new NativeSearchQueryBuilder()
                .withQuery(matchQuery(field, v)).build();
        List<ReportBean> reportBeans = elasticsearchTemplate.queryForList(query, ReportBean.class);
        return reportBeans;
    }

    @Override
    public ReportBean findById(Long id) {
        Optional<ReportBean> optionalReportBean = elasticRepository.findById(id);
        if (optionalReportBean.isPresent()){
          return   optionalReportBean.get();
        }else {
            return null;
        }
    }

    /**
     * withPageable和withQuery同级
     * @param pageNum
     * @param pageSize
     * @return
     */
    @Override
    public List<ReportBean> page(Integer pageNum, Integer pageSize) {
        SearchQuery searchQuery = new NativeSearchQueryBuilder()
                .withQuery(matchAllQuery())
                .withPageable(PageRequest.of(pageNum,pageSize))
                .build();
        List<ReportBean> reportBeans = elasticsearchTemplate.queryForList(searchQuery, ReportBean.class);
        return reportBeans;
    }

    /**
     * 排序与query同级
     * @param field
     * @param order
     * @return
     */
    @Override
    public List<ReportBean> sort(String field, String order) {
        SearchQuery searchQuery = new NativeSearchQueryBuilder()
                .withQuery(matchAllQuery())
                .withSort(SortBuilders.fieldSort(field).order("desc".equalsIgnoreCase(order)==true?SortOrder.DESC:SortOrder.ASC))
                .build();
        List<ReportBean> reportBeans = elasticsearchTemplate.queryForList(searchQuery, ReportBean.class);
        return  reportBeans;
    }

    @Override
    public List<ReportBean> findSubField(String[] fields) {
        SearchQuery searchQuery = new NativeSearchQueryBuilder()
                .withSourceFilter(new FetchSourceFilter(fields, null))
                .withPageable(PageRequest.of(1, 10)).build();
        List<ReportBean> reportBeans = elasticsearchTemplate.queryForList(searchQuery, ReportBean.class);
        return  reportBeans;
    }

    @Override
    public List<ReportBean> findByFilterBymust(String field, String value) {
        SearchQuery searchQuery = new NativeSearchQueryBuilder()
                .withFilter(boolQuery().must(matchQuery(field, value)))
                .build();
        List<ReportBean> reportBeans = elasticsearchTemplate.queryForList(searchQuery, ReportBean.class);
        return  reportBeans;
    }

    @Override
    public List<ReportBean> findByFilterBymustAndfilterrange(String field, String value) {
        SearchQuery searchQuery = new NativeSearchQueryBuilder()
                .withFilter(boolQuery()
                        .must(matchQuery(field, value))
                        .filter(rangeQuery("id").gte(10000))
                )
                .build();
        List<ReportBean> reportBeans = elasticsearchTemplate.queryForList(searchQuery, ReportBean.class);
        return  reportBeans;
    }
}
