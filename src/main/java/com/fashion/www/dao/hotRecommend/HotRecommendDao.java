package com.fashion.www.dao.hotRecommend;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.fashion.www.goods.Goods;
@Repository
public class HotRecommendDao {
	@Autowired
	private DataSource dataSource;
	public List<Goods> queryHotRecommends(int currentPage,int perPage,String description,String enable){
		String querySql = "SELECT id,title,coverImage,description,tags,createdTime FROM goods_recommend WHERE enable = '1' ORDER BY id DESC LIMIT ?,?";

		
		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		List<Goods> goods = new ArrayList<Goods>();
		try{
			conn = dataSource.getConnection();
			if (description == null){
				String querySql1 = null;
				if (enable == null) {
					querySql1 = "SELECT id,title,coverImage,description,tags,createdTime,enable FROM goods_recommend WHERE enable = '1' ORDER BY id DESC LIMIT ?,?";
					stmt = conn.prepareStatement(querySql1);
					stmt.setInt(1, (currentPage - 1) * perPage);
					stmt.setInt(2, perPage);
				} else {
					querySql1 = "SELECT id,title,coverImage,description,tags,createdTime,enable FROM goods_recommend ORDER BY id DESC LIMIT ?,?";
					stmt = conn.prepareStatement(querySql1);
					stmt.setInt(1, (currentPage - 1) * perPage);
					stmt.setInt(2, perPage);
				}
			}else{
				String querySql2 = "SELECT id,title,coverImage,description,tags,createdTime,enable FROM goods_recommend WHERE enable = '1' AND (description LIKE '%" + description + "%' OR tags LIKE '%" + description + "%') ORDER BY id DESC LIMIT " + (currentPage - 1) * perPage + "," + perPage;
				System.out.println(querySql2);
				stmt = conn.prepareStatement(querySql2);
//				stmt.setInt(1, (currentPage - 1) * perPage);
//				stmt.setInt(2, currentPage * perPage);
				
			}
			
		
			rs = stmt.executeQuery();
			while(rs.next()){
				goods.add(new Goods(rs.getInt("id"),rs.getString("title"),rs.getString("coverImage"),rs.getString("description"),rs.getString("tags"),rs.getInt("createdTime"),rs.getString("enable")));
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
					if (stmt != null){
						stmt.close();
					}
				}catch(SQLException e){
					e.printStackTrace();
				}finally{
					try{
						if (conn != null) {
							conn.close();
						}
					}catch(SQLException e){
						e.printStackTrace();
					}
				}
			}
		}
		return goods;
	}
	public int getTotalCount(String description){
		String querySql = description == null ? "SELECT COUNT(id) AS count FROM goods_recommend WHERE enable = '1'" : "SELECT COUNT(id) AS count FROM goods_recommend WHERE enable = '1' AND (description LIKE '%" + description + "%' OR tags LIKE '%" + description + "%')";
		System.out.println(querySql);
		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		int count = 0;
		try{
			conn = dataSource.getConnection();
			stmt = conn.prepareStatement(querySql);
			rs = stmt.executeQuery();
			while(rs.next()){
				count = rs.getInt("count");
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
					if(stmt != null){
						stmt.close();
					}
				}catch(SQLException e){
					e.printStackTrace();
				}finally{
					try{
						if (conn != null){
							conn.close();
						}
					}catch(SQLException e){
						e.printStackTrace();
					}
				}
			}
		}
		System.out.println(count);
		return count;
	}
	public void createHotRecommend(String title,String coverImage,String description){
		int createdTime =(int) (new Date().getTime() / 1000);
		String sqlStr = "INSERT INTO goods_recommend (title,coverImage,description,createdTime) VALUES (?,?,?,?)";
		Connection conn = null;
		PreparedStatement stmt = null;
		try{
			conn = dataSource.getConnection();
			stmt = conn.prepareStatement(sqlStr);
			stmt.setString(1, title);
			stmt.setString(2, coverImage);
			stmt.setString(3, description);
			stmt.setInt(4, createdTime);
			stmt.executeUpdate();
		}catch(SQLException e){
			e.printStackTrace();
		}finally{
			try{
				if (stmt != null){
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
	public Goods findStreetSnapById(int id){
		String sql = "SELECT * FROM goods_recommend WHERE id = ?";
		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		Goods good = null;
		try{
			conn = dataSource.getConnection();
			stmt = conn.prepareStatement(sql);
			stmt.setInt(1, id);
			rs = stmt.executeQuery();
			while(rs.next()){
				good = new Goods(rs.getInt("id"),rs.getString("title"),rs.getString("coverImage"),rs.getString("description"),rs.getString("tags"),rs.getInt("createdTime"),rs.getString("enable"));
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
		return good;
	}
}
