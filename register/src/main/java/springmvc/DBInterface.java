package springmvc;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import java.io.InputStream;

/**
 * Created by chenxidu on 16/6/28.
 */

public class DBInterface {
	public static SqlSession session = null;
	public static InputStream confin = null;
	public static SqlSessionFactory sqlsessfac = null;

	static public SqlSession getSession() {
		System.out.println(DBInterface.class.getResource("/").getFile().toString());
		if (confin == null) {
			confin = DBInterface.class.getClassLoader().getResourceAsStream("../conf.xml");
			if (confin == null) {
				System.out.println("confin is null");
				System.exit(1);
			}
		}

		if (sqlsessfac == null) {
			sqlsessfac = new SqlSessionFactoryBuilder().build(confin);
			if (sqlsessfac == null) {
				System.out.println("sqlsessfac is null");
				System.exit(1);
			}
		}

		if (session == null) {
			session = sqlsessfac.openSession();
			if (session == null) {
				System.out.println("session is null");
				System.exit(1);
			}
		}

		return session;
	}

}
