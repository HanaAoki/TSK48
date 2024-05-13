package model.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionManager {
	/* データベースURL
	 */
	private final static String URL = "jdbc:mysql://localhost:3306/TSK48";

	/**
	 * ユーザ
	 */
	private final static String USER = "root";

	/**
	 * パスワード
	 */
	private final static String PASSWORD = "root";

	/**
	 * データベースへの接続を取得して返します。
	 *
	 * @return コネクション
	 * @throws SQLException
	 * @throws ClassNotFoundException 
	 */
	public static Connection getConnection() throws SQLException, ClassNotFoundException {

		Class.forName("com.mysql.cj.jdbc.Driver");
		return DriverManager.getConnection(URL, USER, PASSWORD);

	}

}
