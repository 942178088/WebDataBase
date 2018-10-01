package cn.edu.zcmu.WebDataBase.entity;

import javax.persistence.*;
import java.util.Date;

/**
 * 院校实体类
 */
@Entity
@Table(name = "COLLEGES")
public class College {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Column(unique = true, nullable = false, length = 30)
    private String name; // 中文名称
    @Column(unique = true, nullable = false, length = 15)
    private String abbrName; // 中文简称
    @Column(unique = true, nullable = false, length = 30)
    private String englishName; // 英文名称
    @Column(unique = true, nullable = false, length = 15)
    private String abbrEnglishName; // 英文简称
    private String badgeUrl; // 校徽 图片URL
    @Column(nullable = false)
    private Date foundingYear; // 创建时间
    @Column(nullable = false)
    @ManyToOne(cascade = {CascadeType.MERGE},fetch = FetchType.EAGER,targetEntity = Location.class,optional = false)
    private Location location; // 所在地 多对一
    // 院校隶属（全部，教育部，其他部委，地方）
    // 院校特性（985高校，211高校，研究生院，自划线院校）

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

    public String getAbbrName() {
        return abbrName;
    }

    public void setAbbrName(String abbrName) {
        this.abbrName = abbrName;
    }

    public String getEnglishName() {
        return englishName;
    }

    public void setEnglishName(String englishName) {
        this.englishName = englishName;
    }

    public String getAbbrEnglishName() {
        return abbrEnglishName;
    }

    public void setAbbrEnglishName(String abbrEnglishName) {
        this.abbrEnglishName = abbrEnglishName;
    }

    public String getBadgeUrl() {
        return badgeUrl;
    }

    public void setBadgeUrl(String badgeUrl) {
        this.badgeUrl = badgeUrl;
    }

    public Date getFoundingYear() {
        return foundingYear;
    }

    public void setFoundingYear(Date foundingYear) {
        this.foundingYear = foundingYear;
    }

    public Location getLocation() {
        return location;
    }

    public void setLocation(Location location) {
        this.location = location;
    }

    @Override
    public String toString() {
        return "College{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", abbrName='" + abbrName + '\'' +
                ", englishName='" + englishName + '\'' +
                ", abbrEnglishName='" + abbrEnglishName + '\'' +
                ", badgeUrl='" + badgeUrl + '\'' +
                ", foundingYear=" + foundingYear +
                ", location=" + location +
                '}';
    }
}
