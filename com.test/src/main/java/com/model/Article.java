package com.model;

import java.io.Serializable;


import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

/**
 *  实体类
 *  用户oracle 测试
 * @author mumu
 * @date 2016-08-18
 */
@Table(name="article")
@Entity
public class Article implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	

	private long id ;
	
	private String name ; //文章名称
	
	private long count ; //点击量

	public Article(){
		
	}
	//IDENTITY SEQUENCE

	// ARTICLESEQ ：可以随便取 sequenceName ：orale中序列的名字
    @Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE)
	@SequenceGenerator(name="ARTICLESEQ",sequenceName="ARTICLE_SEQ")
	@Column(name = "id", unique = true, nullable = false)
	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	@Column(name = "name")
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	@Column(name = "count")
	public long getCount() {
		return count;
	}

	public void setCount(long count) {
		this.count = count;
	}

	
}
