package com.zs.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.zs.util.DBUtils;


public class BaseDaoOfJDBC {
	
	Connection con=null;
	PreparedStatement pstmt=null;
	ResultSet rs=null;
	
	
	public List query(String sql) throws SQLException {
		List list=new ArrayList();
		try {
			con=DBUtils.openDBFromProxool();
			pstmt=con.prepareStatement(sql);
			rs=pstmt.executeQuery();
			while (rs.next()) {
				ResultSetMetaData rsm=rs.getMetaData();
				Map map=new HashMap();
				for(int i=0;i<rsm.getColumnCount();i++){
					map.put(rsm.getColumnName(i+1), rs.getObject(i+1));
				}
				list.add(map);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}finally{
			if (rs!=null) {
				rs.close();
			}
			if (pstmt!=null) {
				pstmt.close();
			}
			if (con!=null) {
				con.close();
			}
		}
		return list;
	}
	
	public void aud(String sql) throws SQLException {
		try {
			con=DBUtils.openDBFromProxool();
			pstmt=con.prepareStatement(sql);
			pstmt.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		}finally{
			if (rs!=null) {
				rs.close();
			}
			if (pstmt!=null) {
				pstmt.close();
			}
			if (con!=null) {
				con.close();
			}
		}
	}
}
