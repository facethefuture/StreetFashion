package com.fashion.www.dao.hotRecommend;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.fashion.www.goods.PhotographyModule;

@Repository
public class PhotographyModuleDao {
	@Autowired
	private DataSource dataSource;
	public List<PhotographyModule> queryPhotographyModule(){
		String querySql = "SELECT id,name,coverImage FROM photography_module WHERE enable = '1'";
		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		List<PhotographyModule> list = new ArrayList<PhotographyModule>();
		try{
			conn = dataSource.getConnection();
			stmt = conn.prepareStatement(querySql);
			rs = stmt.executeQuery();
			while(rs.next()){
				list.add(new PhotographyModule(rs.getInt("id"),rs.getString("name"),rs.getString("coverImage")));
			}
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
					if(conn != null){
						conn.close();
					}
				}catch(SQLException e){
					e.printStackTrace();
				}
			}
		}
		return list;
	}
}
