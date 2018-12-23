package cn.edu.zcmu.WebDataBase.entity;

import javax.persistence.*;

/**
 * 全日制研究生 非全日制
 */
@Entity
@Table(name = "KINDS")
public class Kind {
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
