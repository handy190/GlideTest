package com.handy.hongzhi.glidetest.bean;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Generated;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.Property;

/**
 * @author: hongzhi
 * @time 2017/6/29 10:41.
 *
 * @Entity: 表明这个实体类会在数据库中生成一个与之对应的表
 *
 * @Id :    对应数据表中的Id字段，有了解数据库的话，是一条数据的唯一识别
 *
 * @Property(nameInDb = "STUDENTNUM"):  表名这个属性对应数据表中的STUDENTNUM字段
 *
 * @Property:   可以自定义字段名，注意外键不能使用该属性
 *
 * @NotNULL:    该属性值不能为空
 *
 * @Transient   该属性不会被存入数据库中
 *
 * @Unique      表名该属性在数据库中只能有唯一值
 */
@Entity
public class StudentMsgBean {
    @Id
    private Long id;
    @Property(nameInDb = "STUDENTNUM")
    private String studentNum;
    @Property(nameInDb = "NAME")
    private String name;
    @Generated(hash = 381350025)
    public StudentMsgBean(Long id, String studentNum, String name) {
        this.id = id;
        this.studentNum = studentNum;
        this.name = name;
    }
    @Generated(hash = 160565988)
    public StudentMsgBean() {
    }
    public Long getId() {
        return this.id;
    }
    public void setId(Long id) {
        this.id = id;
    }
    public String getStudentNum() {
        return this.studentNum;
    }
    public void setStudentNum(String studentNum) {
        this.studentNum = studentNum;
    }
    public String getName() {
        return this.name;
    }
    public void setName(String name) {
        this.name = name;
    }


}
