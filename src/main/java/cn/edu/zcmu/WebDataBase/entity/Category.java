package cn.edu.zcmu.WebDataBase.entity;

import javax.persistence.*;
import java.util.Date;

/**
 * 门类 实体类
 */
@Entity
@Table(name = "CATEGORYS")
public class Category {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Column(unique = true, nullable = false, length = 30)
    private String name; // 名称 文学
    @Column(unique = true, nullable = false, length = 30)
    private String cCode; // 代码 [05]
    private Date cTime; // 创建时间

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

    public String getcCode() {
        return cCode;
    }

    public void setcCode(String cCode) {
        this.cCode = cCode;
    }

    public Date getcTime() {
        return cTime;
    }

    public void setcTime(Date cTime) {
        this.cTime = cTime;
    }
}
