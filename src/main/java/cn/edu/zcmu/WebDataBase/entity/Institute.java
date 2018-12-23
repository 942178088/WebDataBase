package cn.edu.zcmu.WebDataBase.entity;

import javax.persistence.*;
import java.util.Date;

/**
 * 学院表
 */
@Entity
@Table(name = "INSTITUTES")
public class Institute {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Column(unique = true, nullable = false, length = 50)
    private String name; // 中文名称
    private Date iTime; // 开设日期
    @ManyToOne(cascade = {CascadeType.MERGE}, fetch = FetchType.EAGER, targetEntity = College.class, optional = false)
    private College college;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Date getiTime() {
        return iTime;
    }

    public void setiTime(Date iTime) {
        this.iTime = iTime;
    }

    public College getCollege() {
        return college;
    }

    public void setCollege(College college) {
        this.college = college;
    }
}
