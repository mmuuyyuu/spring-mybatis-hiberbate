package com.dao;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Properties;
import java.util.Set;

import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;

import com.mysql.jdbc.Connection;

@Repository
public class BaseDao {
	@Autowired
	@Qualifier("sessionFactory")
	private SessionFactory sessionFactory;
	
	
	@Autowired
	@Qualifier("mysqlSessionFactory")
	private SessionFactory mysqlSessionFactory;


	@SuppressWarnings("rawtypes")
	public final ThreadLocal sessionThreadLoacl = new ThreadLocal();
	/* jdbc start */

	private static String driverName = null;

	private static String url = null;

	private static String userName = null;

	private static String password = null;

	/**
	 * 通过静态块加载驱动
	 */
/*	static {
		if (url == null || userName == null|| password == null) {
			loadProperties();
		}
		loadDriver();
	}*/

	/**
	 * 加载配置
	 */
	public static void loadProperties() {/*
		System.out.println(BaseDao.class + "正在加载jdbc.properties配置");
		Properties properties = new Properties();
		// 读取jdbc.properties配置文件
		InputStream inStream = null;
		String appclassPath = BaseDao.class.getResource("/").getPath();
		appclassPath = appclassPath.replaceAll("%20", " ");
		System.out.println("appclassPath:" + appclassPath);
		String path = appclassPath + "database.properties";

		try {

			inStream = new FileInputStream(path);

		} catch (FileNotFoundException e1) {
			e1.printStackTrace();
		}

		try {
			properties.load(inStream);
		} catch (IOException e) {
			e.printStackTrace();
			return;
		}

		driverName = properties.getProperty("jdbc.driverClassName");
		url = properties.getProperty("mysql.uc.db.url");
		userName = properties.getProperty("mysql.uc.db.username");
		password = properties.getProperty("mysql.uc.db.password");

	*/}

	/**
	 * 加载驱动
	 */
/*	private static void loadDriver() {
		try {
			Class.forName(driverName);
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	}*/

	/**
	 * 获取连接
	 * 
	 * @return Connection
	 * @throws SQLException
	 */
	public static Connection getConn() throws SQLException {
		return (Connection) DriverManager
				.getConnection(url, userName, password);
	}

	/* jdbc end */
	public Session getSession() {
		// 事务必须是开启的，否则获取不到
		Session session = null;

		session = sessionFactory.getCurrentSession();

		return session;

	}
	
	
	public Session getMySqlSession() {
		// 事务必须是开启的，否则获取不到
		Session session = null;

		session = mysqlSessionFactory.getCurrentSession();

		return session;

	}


	@SuppressWarnings("unchecked")
	public Session getOpenSession() {
		Session session = (Session) sessionThreadLoacl.get();
		if (session == null) {
			session = sessionFactory.openSession();
			sessionThreadLoacl.set(session);
		}
		return session;
	}

	/**
	 * 释放Session
	 * 
	 * @throws HibernateException
	 */
	@SuppressWarnings("unchecked")
	public void closeSession() throws HibernateException {
		Session session = (Session) sessionThreadLoacl.get();
		if (session != null) {
			session.close();
		}
		sessionThreadLoacl.set(null);

	}


}
