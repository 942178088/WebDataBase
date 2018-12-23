package cn.edu.zcmu.WebDataBase.entity;

import javax.persistence.*;

/**
 * 一级学科 0101 哲学
 * 二级学科 010101 马克思主义哲学
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
    @ManyToOne(cascade = {CascadeType.MERGE}, fetch = FetchType.EAGER, targetEntity = Subject.class)
    private Subject father_subject; // 若是二级学科则关联其对应的一级学科
    @ManyToOne(cascade = {CascadeType.MERGE}, fetch = FetchType.EAGER, targetEntity = Category.class, optional = false)
    private Category category; // 所属门类

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

    public Subject getFather_subject() {
        return father_subject;
    }

    public void setFather_subject(Subject father_subject) {
        this.father_subject = father_subject;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }
}