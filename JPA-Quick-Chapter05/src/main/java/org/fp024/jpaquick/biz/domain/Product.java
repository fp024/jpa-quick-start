package org.fp024.jpaquick.biz.domain;

import com.sun.xml.bind.v2.model.core.ID;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * 상품
 */
@Getter
@Setter
@ToString
@Entity
@Table(name = "s_product")
public class Product {
    /** 상품 아이디 */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /** 상품 이름 */
    private String name;

    /** 상품 설명 */
    private String shortDesc;

    /** 카테고리 */
    private String category;

}
