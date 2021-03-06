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
    @Column(nullable = false)
    private Date pTime; // 招生年份
    @ManyToOne(cascade = {CascadeType.MERGE}, fetch = FetchType.EAGER, targetEntity = Institute.class, optional = false)
    private Institute institute; // 所属院校
    @ManyToOne(cascade = {CascadeType.MERGE}, fetch = FetchType.EAGER, targetEntity = Kind.class, optional = false)
    private Kind kind; // 招生类别
    @ManyToOne(cascade = {CascadeType.MERGE}, fetch = FetchType.EAGER, targetEntity = Subject.class, optional = false)
    private Subject subject; // 所属二级学科

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
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

    public Subject getSubject() {
        return subject;
    }

    public void setSubject(Subject subject) {
        this.subject = subject;
    }
}
