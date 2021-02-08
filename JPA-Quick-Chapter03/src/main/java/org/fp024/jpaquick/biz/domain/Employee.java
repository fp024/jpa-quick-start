package org.fp024.jpaquick.biz.domain;

import javax.persistence.*;

import lombok.Data;

@Data
@Entity
@Table(name = "s_emp")
public class Employee {
	@Id
	@GeneratedValue //(strategy = GenerationType.IDENTITY)
	private Long id;
	private String name;

}