package cn.edu.zcmu.WebDataBase.entity;

import javax.persistence.*;
import java.util.Date;

/**
 * 专业类
 */
@Entity
@Table(name = "PROFESSIONALS")
public class Professional {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Column(unique = true, nullable = false, length = 50)
    private String name; // 中文名称
    @Column(unique = true, nullable = false, length = 20)
    private String pCode; // 专业代码
    @Column(nullable = false)
    private Date pTime; // 招生年份
    @ManyToOne(cascade = {CascadeType.MERGE}, fetch = FetchType.EAGER, targetEntity = Institute.class, optional = false)
    private Institute institute; // 所属院校
    @ManyToOne(cascade = {CascadeType.MERGE}, fetch = FetchType.EAGER, targetEntity = Kind.class, optional = false)
    private Kind kind; // 招生类别
    @ManyToOne(cascade = {CascadeType.MERGE}, fetch = FetchType.EAGER, targetEntity = Category.class, optional = false)
    private Category category; // 所属门类
    @ManyToOne(cascade = {CascadeType.MERGE}, fetch = FetchType.EAGER, targetEntity = Subject.class, optional = false)
    private Subject subject; // 所属一级学科

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

    public String getpCode() {
        return pCode;
    }

    public void setpCode(String pCode) {
        this.pCode = pCode;
    }

    public Date getpTime() {
        return pTime;
    }

    public void setpTime(Date pTime) {
        this.pTime = pTime;
    }

    public Institute getInstitute() {
        return institute;
    }

    public void setInstitute(Institute institute) {
        this.institute = institute;
    }

    public Kind getKind() {
        return kind;
    }

    public void setKind(Kind kind) {
        this.kind = kind;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public Subject getSubject() {
        return subject;
    }

    public void setSubject(Subject subject) {
        this.subject = subject;
    }
}
