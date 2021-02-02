/**
* 文件名：IElasticService.java
* 版权：Company Technologies Co.,Ltd.Copyright YYYY-YYYY,All rights reserved
* 版权：Copyright (c) 2020, jia2040020@126.com All Rights Reserved.
* 描述：<描述>
* 修改人：Administrator
* 修改时间：2020年4月6日
* 修改内容：<修改内容>
*/
package com.huan.es.service;


import com.huan.es.nosql.elasticsearch.document.ReportBean;

import java.util.Iterator;
import java.util.List;

/**
* <一句话功能简述>
* <功能详细描述>
* 
* @author ahuan
* @version [版本号,2020年4月6日]
* @see [相关类/方法]
* @since [产品/模块版本]
*/
public interface IElasticService
{
    void createIndex();

    void deleteIndex(String index);

    void save(ReportBean docBean);

    void saveAll(List<ReportBean> list);

    Iterator<ReportBean> findAll();

    List<ReportBean> findByFieldAndV(String field, String v);

    ReportBean findById(Long id);

    List<ReportBean> page(Integer pageNum, Integer pageSize);

    List<ReportBean> sort(String field, String order);

    List<ReportBean> findSubField(String[] fields);

    List<ReportBean> findByFilterBymust(String field, String value);

    List<ReportBean> findByFilterBymustAndfilterrange(String field, String value);
}
