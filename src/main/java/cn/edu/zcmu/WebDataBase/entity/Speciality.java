package cn.edu.zcmu.WebDataBase.entity;

import javax.persistence.*;

/**
 * 院校特性 实体类
 * 院校特性（985高校，211高校，研究生院，自划线院校）
 */
@Entity
@Table(name = "SPECIALITIES")
public class Speciality {
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
