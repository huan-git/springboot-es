/**
 * 文件名：ElasticRepository.java 版权：Company Technologies Co.,Ltd.Copyright
 * YYYY-YYYY,All rights reserved 版权：Copyright (c) 2020, jia2040020@126.com All
 * Rights Reserved. 描述：<描述> 修改人：Administrator 修改时间：2020年4月6日 修改内容：<修改内容>
 */
package com.huan.es.nosql.elasticsearch.repository;

import com.huan.es.nosql.elasticsearch.document.ReportBean;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

/**
 * <一句话功能简述> <功能详细描述>
 * 
 * @author ahuan
 * @version [版本号,2020年4月6日]
 * @see [相关类/方法]
 * @since [产品/模块版本]
 */
public interface ElasticRepository extends ElasticsearchRepository<ReportBean, Long> {

}
