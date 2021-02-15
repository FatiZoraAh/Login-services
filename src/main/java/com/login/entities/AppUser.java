package com.login.entities;

import java.util.ArrayList;
import java.util.Collection;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import org.hibernate.annotations.ManyToAny;

import org.springframework.transaction.annotation.EnableTransactionManagement;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Entity
@EnableTransactionManagement
@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class AppUser {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	@Column(unique = true)
	private String userName;
	private String passWord;
	private boolean isActived;
	@ManyToAny(fetch = FetchType.EAGER, metaColumn = @Column)
	private Collection<AppRole> role=new ArrayList<AppRole>();
	
	

}
