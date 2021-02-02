import com.huan.es.SpringbootEsApplication;
import com.huan.es.nosql.elasticsearch.document.ReportBean;
import com.huan.es.nosql.elasticsearch.repository.ElasticRepository;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.search.aggregations.AggregationBuilders;
import org.elasticsearch.search.aggregations.Aggregations;
import org.elasticsearch.search.aggregations.bucket.terms.ParsedStringTerms;
import org.elasticsearch.search.aggregations.bucket.terms.Terms;
import org.elasticsearch.search.fetch.subphase.highlight.HighlightBuilder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.elasticsearch.core.ElasticsearchRestTemplate;
import org.springframework.data.elasticsearch.core.query.NativeSearchQueryBuilder;
import org.springframework.data.elasticsearch.core.query.SearchQuery;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @program: springboot-es
 * @description: this is the description of the SpringbootEsApplicationTest class
 * @author: ahuan
 * @version: 2020-09-01 18:30
 **/
@RunWith(SpringRunner.class)
@SpringBootTest(classes = SpringbootEsApplication.class)
public class SpringbootEsApplicationTest {
    private static final Logger LOG = LoggerFactory.getLogger("test");
    @Autowired
    ElasticsearchRestTemplate elasticsearchRestTemplate;

    @Resource
    ElasticRepository elasticRepository;

    SearchQuery query;

    @Test
    public void wildcard() {
        query = new NativeSearchQueryBuilder()
                .withQuery(QueryBuilders.wildcardQuery("name", "*欢"))
                .withPageable(PageRequest.of(1, 100))
                .build();
        List<ReportBean> reportBeans = elasticsearchRestTemplate.queryForList(query, ReportBean.class);
        reportBeans.forEach(e -> System.out.println("name:" + e.getName()));
    }

    @Test
    public void prefix() {
        query = new NativeSearchQueryBuilder()
                .withQuery(QueryBuilders.prefixQuery("office_name", "河"))
                .withPageable(PageRequest.of(1, 100))
                .build();
        List<ReportBean> reportBeans = elasticsearchRestTemplate.queryForList(query, ReportBean.class);
        reportBeans.forEach(e -> System.out.println("office_name:" + e.getOffice_name()));
    }

    @Test
    public void regexp() {
        query = new NativeSearchQueryBuilder()
                .withQuery(QueryBuilders.regexpQuery("office_name", "河.*"))
                .withPageable(PageRequest.of(1, 100))
                .build();
        List<ReportBean> reportBeans = elasticsearchRestTemplate.queryForList(query, ReportBean.class);
        reportBeans.forEach(e -> System.out.println("office_name:" + e.getOffice_name()));
    }

    @Test
    public void range() {
        query = new NativeSearchQueryBuilder()
                .withQuery(QueryBuilders.rangeQuery("id").gte(777679).lte(777700))
                .withPageable(PageRequest.of(1, 100))
                .build();
        List<ReportBean> reportBeans = elasticsearchRestTemplate.queryForList(query, ReportBean.class);
        reportBeans.forEach(e -> System.out.println("age:" + e.getAge()));
    }

    @Test
    public void boolMust() {
        query = new NativeSearchQueryBuilder()
                .withQuery(QueryBuilders.boolQuery()
                        .must(QueryBuilders.matchQuery("name", "张欢"))
                        .mustNot(QueryBuilders.matchQuery("register_province", "宁夏回族自治区")))
                .build();
        List<ReportBean> reportBeans = elasticsearchRestTemplate.queryForList(query, ReportBean.class);
        reportBeans.forEach(e -> System.out.println(e));
    }

    @Test
    public void boolFilter() {
        query = new NativeSearchQueryBuilder()
                .withQuery(QueryBuilders.boolQuery()
                        .filter(QueryBuilders.matchQuery("name", "张欢")))
                .build();
        List<ReportBean> reportBeans = elasticsearchRestTemplate.queryForList(query, ReportBean.class);
        reportBeans.forEach(e -> System.out.println(e));
    }

    // TODO　
    @Test
    public void boolShould() {
        query = new NativeSearchQueryBuilder()
                .withQuery(QueryBuilders.boolQuery()
                        .must(QueryBuilders.matchQuery("name", "张欢"))
                        .must(QueryBuilders.matchQuery("permission_level", "1")))
                .build();
        List<ReportBean> reportBeans = elasticsearchRestTemplate.queryForList(query, ReportBean.class);
        reportBeans.forEach(e -> System.out.println(e));
    }

    /**
     * 全文搜索
     */
    @Test
    public void phrase(){
        SearchQuery searchQuery = new NativeSearchQueryBuilder()
                .withQuery(QueryBuilders.matchPhraseQuery("name", "张欢"))
                .withHighlightFields(new HighlightBuilder.Field("name"))
                .build();
        List<ReportBean> reportBeans = elasticsearchRestTemplate.queryForList(searchQuery, ReportBean.class);
        System.out.println(reportBeans);
    }

    /**
     * 聚合操作 找打姓名为张欢，按register_province分组
     */
    @Test
    public void agg1(){
        SearchQuery searchQuery = new NativeSearchQueryBuilder()
                .withQuery(QueryBuilders.matchQuery("name", "张欢"))
                .addAggregation(AggregationBuilders.terms("register_provinces").field("register_province")
                .subAggregation(AggregationBuilders.terms("permissions").field("permission"))
                )
                .build();
        Aggregations aggregations = elasticsearchRestTemplate.query(searchQuery, e -> e.getAggregations());
        ParsedStringTerms aggregation = (ParsedStringTerms) aggregations.getAsMap().get("register_provinces");
        Map<String, Object> map = new HashMap<>();
        Map<String, Long> subMap = null;
        for (Terms.Bucket bucket : aggregation.getBuckets()) {
            Object key = bucket.getKey();
            ParsedStringTerms subAggregation = (ParsedStringTerms) bucket.getAggregations().getAsMap().get("permissions");
            subMap = new HashMap<>();
            for (Terms.Bucket subAggregationBucket : subAggregation.getBuckets()) {
                subMap.put((String) subAggregationBucket.getKey(),subAggregationBucket.getDocCount());
            }
            map.put((String) key,subMap);
        }
        System.out.println(map);
    }
}