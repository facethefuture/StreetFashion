package com.fashion.www.dao.hotRecommend;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.fashion.www.user.User;

@Repository
public class UserDao {
	@Autowired
	private DataSource datasource;
	public User queryUser(String username){
		User user = null;
		String querySql = "SELECT id,username,password,nickname,role FROM user WHERE username = ?";
		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		try{
			conn = datasource.getConnection();
			stmt = conn.prepareStatement(querySql);
			stmt.setString(1, username);
			rs = stmt.executeQuery();
			while(rs.next()){
				user = new User(rs.getInt("id"),rs.getString("username"),rs.getString("password"),rs.getString("nickname"),rs.getString("role"));
			}
		}catch(SQLException e){
			e.printStackTrace();
		}finally{
			try{
				if (rs != null){
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
		return user;
	}
	
}
