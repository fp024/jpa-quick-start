package org.fp024.jpaquick.biz.domain;

import javax.persistence.*;

import lombok.Data;
import org.hibernate.annotations.DynamicUpdate;

@Data
@Entity
@DynamicUpdate
@Table(name = "s_emp")
public class Employee {
	@Id
	@GeneratedValue (strategy = GenerationType.IDENTITY)
	private Long id;
	private String name;

}