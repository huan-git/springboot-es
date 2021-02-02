/**
  * Copyright 2020 bejson.com 
  */
package com.huan.es.nosql.elasticsearch.document;

import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.data.elasticsearch.annotations.Field;
import org.springframework.data.elasticsearch.annotations.FieldType;
import org.springframework.data.elasticsearch.annotations.GeoPointField;
import org.springframework.data.elasticsearch.core.geo.GeoPoint;

import java.util.Date;

/**
 * Auto-generated: 2020-08-30 17:23:15
 *
 * @author bejson.com (i@bejson.com)
 * @website http://www.bejson.com/java2pojo/
 */
@Document(indexName = "report" ,type = "_doc" , createIndex = true,shards = 1,replicas = 0)
public class ReportBean {

    @Id
    private long id;
    @Field(type = FieldType.Keyword)
    private String register_province;
    @Field(type = FieldType.Keyword)
    private String register_city;
    @Field(type = FieldType.Keyword)
    private String register_country;
    @Field(type = FieldType.Keyword)
    private String register_town;
    @Field(type = FieldType.Keyword)
    private String name;
    @Field(type = FieldType.Keyword)
    private String age;
    @Field(type = FieldType.Keyword)
    private String sex;
    @Field(type = FieldType.Text)
    private String idCard;
    @Field(type = FieldType.Text)
    private String education;
    @Field(type = FieldType.Text)
    private String office_name;
    @Field(type = FieldType.Text)
    private String job_level;
    @Field(type = FieldType.Text)
    private String job;
    @Field(type = FieldType.Text)
    private String job_content;
    @Field(type = FieldType.Text)
    private long mobile_phone;
    @Field(type = FieldType.Text)
    private String office_phone;
    @GeoPointField
    private GeoPoint lat;
    @GeoPointField
    private GeoPoint lng;
    @Field(type = FieldType.Keyword)
    private String permission;
    @Field(type = FieldType.Keyword)
    private String permission_level;
    @Field(type = FieldType.Keyword)
    private String permission_province;
    @Field(type = FieldType.Keyword)
    private String permission_city;
    @Field(type = FieldType.Keyword)
    private String permission_country;
    @Field(type = FieldType.Keyword)
    private String permission_town;
    @Field(type = FieldType.Keyword)
    private String account;
    @Field(type = FieldType.Keyword)
    private String password;
    @Field(type = FieldType.Keyword)
    private String status;
    @Field(type = FieldType.Date)
    private Date create_time;
    @Field(type = FieldType.Date)
    private Date update_time;
    public void setId(long id) {
         this.id = id;
     }
     public long getId() {
         return id;
     }

    public void setRegister_province(String register_province) {
         this.register_province = register_province;
     }
     public String getRegister_province() {
         return register_province;
     }

    public void setRegister_city(String register_city) {
         this.register_city = register_city;
     }
     public String getRegister_city() {
         return register_city;
     }

    public void setRegister_country(String register_country) {
         this.register_country = register_country;
     }
     public String getRegister_country() {
         return register_country;
     }

    public void setRegister_town(String register_town) {
         this.register_town = register_town;
     }
     public String getRegister_town() {
         return register_town;
     }

    public void setName(String name) {
         this.name = name;
     }
     public String getName() {
         return name;
     }

    public void setAge(String age) {
         this.age = age;
     }
     public String getAge() {
         return age;
     }

    public void setSex(String sex) {
         this.sex = sex;
     }
     public String getSex() {
         return sex;
     }

    public void setIdCard(String idCard) {
         this.idCard = idCard;
     }
     public String getIdCard() {
         return idCard;
     }

    public void setEducation(String education) {
         this.education = education;
     }
     public String getEducation() {
         return education;
     }

    public void setOffice_name(String office_name) {
         this.office_name = office_name;
     }
     public String getOffice_name() {
         return office_name;
     }

    public void setJob_level(String job_level) {
         this.job_level = job_level;
     }
     public String getJob_level() {
         return job_level;
     }

    public void setJob(String job) {
         this.job = job;
     }
     public String getJob() {
         return job;
     }

    public void setJob_content(String job_content) {
         this.job_content = job_content;
     }
     public String getJob_content() {
         return job_content;
     }

    public void setMobile_phone(long mobile_phone) {
         this.mobile_phone = mobile_phone;
     }
     public long getMobile_phone() {
         return mobile_phone;
     }

    public void setOffice_phone(String office_phone) {
         this.office_phone = office_phone;
     }
     public String getOffice_phone() {
         return office_phone;
     }



    public void setPermission(String permission) {
         this.permission = permission;
     }
     public String getPermission() {
         return permission;
     }

    public void setPermission_level(String permission_level) {
         this.permission_level = permission_level;
     }
     public String getPermission_level() {
         return permission_level;
     }

    public void setPermission_province(String permission_province) {
         this.permission_province = permission_province;
     }
     public String getPermission_province() {
         return permission_province;
     }

    public void setPermission_city(String permission_city) {
         this.permission_city = permission_city;
     }
     public String getPermission_city() {
         return permission_city;
     }

    public void setPermission_country(String permission_country) {
         this.permission_country = permission_country;
     }
     public String getPermission_country() {
         return permission_country;
     }

    public void setPermission_town(String permission_town) {
         this.permission_town = permission_town;
     }
     public String getPermission_town() {
         return permission_town;
     }


    public void setPassword(String password) {
         this.password = password;
     }
     public String getPassword() {
         return password;
     }



    public void setCreate_time(Date create_time) {
         this.create_time = create_time;
     }
     public Date getCreate_time() {
         return create_time;
     }


    public void setUpdate_time(Date update_time) {
         this.update_time = update_time;
     }
     public Date getUpdate_time() {
         return update_time;
     }


    public GeoPoint getLat() {
        return lat;
    }

    public void setLat(GeoPoint lat) {
        this.lat = lat;
    }

    public GeoPoint getLng() {
        return lng;
    }

    public void setLng(GeoPoint lng) {
        this.lng = lng;
    }


    public String getAccount() {
        return account;
    }

    public void setAccount(String account) {
        this.account = account;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "ReportBean{" +
                "id=" + id +
                ", register_province='" + register_province + '\'' +
                ", register_city='" + register_city + '\'' +
                ", register_country='" + register_country + '\'' +
                ", register_town='" + register_town + '\'' +
                ", name='" + name + '\'' +
                ", age='" + age + '\'' +
                ", sex='" + sex + '\'' +
                ", idCard='" + idCard + '\'' +
                ", education='" + education + '\'' +
                ", office_name='" + office_name + '\'' +
                ", job_level='" + job_level + '\'' +
                ", job='" + job + '\'' +
                ", job_content='" + job_content + '\'' +
                ", mobile_phone=" + mobile_phone +
                ", office_phone='" + office_phone + '\'' +
                ", lat=" + lat +
                ", lng=" + lng +
                ", permission='" + permission + '\'' +
                ", permission_level='" + permission_level + '\'' +
                ", permission_province='" + permission_province + '\'' +
                ", permission_city='" + permission_city + '\'' +
                ", permission_country='" + permission_country + '\'' +
                ", permission_town='" + permission_town + '\'' +
                ", account='" + account + '\'' +
                ", password='" + password + '\'' +
                ", status='" + status + '\'' +
                ", create_time=" + create_time +
                ", update_time=" + update_time +
                '}';
    }
}