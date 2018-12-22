package cn.edu.zcmu.WebDataBase.entity;

import javax.persistence.*;

/**
 * 院校类型
 * 综合类，理工类.....
 */
@Entity
@Table(name = "TYPES")
public class Type {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Column(unique = true, nullable = false, length = 30)
    private String name; // 名称

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
}
