package com.huan.es.dao.mysql;

import com.huan.es.nosql.elasticsearch.document.ReportBean;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * @program: springelasticsearch
 * @description: this is the description of the ReportInfoDao class
 * @author: ahuan
 * @version: 2020-08-30 17:55
 **/
@Mapper
public interface ReportInfoDao {

    @Select("select *  from dr_reporter_info limit #{pageNum},#{pageSize}")
     List<ReportBean> page(@Param("pageNum")int pageNum,@Param("pageSize")int pageSize);
}