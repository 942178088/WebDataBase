package cn.edu.zcmu.WebDataBase.entity;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

/**
 * 院校 实体类
 */
@Entity
@Table(name = "COLLEGES")
public class College {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Column(unique = true, nullable = false, length = 50)
    private String name; // 中文名称
    @Column(unique = true, nullable = false, length = 15)
    private String cCode; // 代码
    @Column(length = 15)
    private String cPartition; // 所处分区
    @ManyToOne(cascade = {CascadeType.MERGE}, fetch = FetchType.EAGER, targetEntity = Nature.class, optional = false)
    private Nature cNature; // 性质
    @ManyToOne(cascade = {CascadeType.MERGE}, fetch = FetchType.EAGER, targetEntity = Type.class, optional = false)
    private Type cType; // 类型
    private Integer ranking; // 综合排名
    private Integer areaCompetitiveRanking; // 地区竞争力排行
    private Integer collegesCompetitiveRanking; // 院校竞争力排行
    @Column(length = 100)
    private String phoneNumber; // 联系方式
    @Column(length = 300)
    private String badgeUrl; // 校徽 图片URL
    @Column(length = 300)
    private String imgUrl; // 图片URL
    @Column(length = 500)
    private String cDescription; // 介绍
    private Date foundingYear; // 创建时间
    @ManyToOne(cascade = {CascadeType.MERGE}, fetch = FetchType.EAGER, targetEntity = Location.class, optional = false)
    private Location location; // 所在地
    @ManyToMany(cascade = {CascadeType.MERGE}, fetch = FetchType.EAGER, targetEntity = Speciality.class)
    private List<Speciality> specialities; // 院校特性（985高校，211高校，研究生院，自划线院校）

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

    public String getcPartition() {
        return cPartition;
    }

    public void setcPartition(String cPartition) {
        this.cPartition = cPartition;
    }

    public Nature getcNature() {
        return cNature;
    }

    public void setcNature(Nature cNature) {
        this.cNature = cNature;
    }

    public Type getcType() {
        return cType;
    }

    public void setcType(Type cType) {
        this.cType = cType;
    }

    public Integer getRanking() {
        return ranking;
    }

    public void setRanking(Integer ranking) {
        this.ranking = ranking;
    }

    public Integer getAreaCompetitiveRanking() {
        return areaCompetitiveRanking;
    }

    public void setAreaCompetitiveRanking(Integer areaCompetitiveRanking) {
        this.areaCompetitiveRanking = areaCompetitiveRanking;
    }

    public Integer getCollegesCompetitiveRanking() {
        return collegesCompetitiveRanking;
    }

    public void setCollegesCompetitiveRanking(Integer collegesCompetitiveRanking) {
        this.collegesCompetitiveRanking = collegesCompetitiveRanking;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getBadgeUrl() {
        return badgeUrl;
    }

    public void setBadgeUrl(String badgeUrl) {
        this.badgeUrl = badgeUrl;
    }

    public String getImgUrl() {
        return imgUrl;
    }

    public void setImgUrl(String imgUrl) {
        this.imgUrl = imgUrl;
    }

    public String getcDescription() {
        return cDescription;
    }

    public void setcDescription(String cDescription) {
        this.cDescription = cDescription;
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

    public List<Speciality> getSpecialities() {
        return specialities;
    }

    public void setSpecialities(List<Speciality> specialities) {
        this.specialities = specialities;
    }
}
