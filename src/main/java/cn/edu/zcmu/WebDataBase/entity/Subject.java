package cn.edu.zcmu.WebDataBase.entity;

import javax.persistence.*;
import java.util.Date;

/**
 * 一级学科 [01]中国语言文学
 */
@Entity
@Table(name = "SUBJECTS")
public class Subject {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Column(unique = true, nullable = false, length = 30)
    private String name; // 名称
    @Column(unique = true, nullable = false, length = 30)
    private String sCode; // 代码
    private Date sTime; // 创建时间

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

    public String getsCode() {
        return sCode;
    }

    public void setsCode(String sCode) {
        this.sCode = sCode;
    }

    public Date getsTime() {
        return sTime;
    }

    public void setsTime(Date sTime) {
        this.sTime = sTime;
    }
}