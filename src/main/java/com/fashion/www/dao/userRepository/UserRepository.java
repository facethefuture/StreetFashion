package com.fashion.www.dao.userRepository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.sql.DataSource;

import org.apache.commons.dbcp.BasicDataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import com.fashion.www.user.User;
@Repository
public class UserRepository {
	@Autowired
	private DataSource dataSource;
//	DataSource dataSource = new BasicDataSource();
	public User findOneUser(String username) {
		System.out.println("查询USER");
		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		String sql = "SELECT * FROM user WHERE username = ?";
		User user = null;
		try{
			System.out.println("dataSource这里");
			conn = dataSource.getConnection();
			stmt = conn.prepareStatement(sql);
			stmt.setString(1, username);
			rs = stmt.executeQuery();
		
			while(rs.next()){
				user = new User(rs.getInt("id"),rs.getString("username"),rs.getString("password"),rs.getString("nickname"),rs.getString("role"));
			}
			return user;
		}catch(SQLException e){
			e.printStackTrace();
		}finally{
			try{
				if(rs != null){
					rs.close();
				}
			}catch(SQLException e){
				e.printStackTrace();
			}finally{
				try{
					if(stmt != null){
						stmt.close();
					}
				}catch(SQLException e){
					e.printStackTrace();
				}finally{
					try{
						if(conn != null){
							conn.close();
						}
					}catch(SQLException e){
						e.printStackTrace();
					}
				}
			}
		}
		user = new User();
		System.out.println("空USER");
		return user;
	}
}
