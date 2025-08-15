package com.swechchha.SpringJDBCDemo_1.repo;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import com.swechchha.SpringJDBCDemo_1.model.Alien;

@Repository
public class AlienRepo {
	
	private JdbcTemplate template;
	
	
	public JdbcTemplate getTemplate() {
		return template;
	}
	@Autowired
	public void setTemplate(JdbcTemplate template) {
		this.template = template;
	}

	public void save(Alien alien) {
		// added
//		System.out.println("added");
		String sql = "insert into alien(id, name, tech) values (?,?,?)";
		int rows = template.update
				(sql, alien.getId(), alien.getName(), alien.getTech());
		System.out.println(rows + " row/s afftected");
	}
	
	public List<Alien> findAll(){
//		return new ArrayList<Alien>();
		String sql = "select * from alien";
//		RowMapper<Alien> mapper = new RowMapper<Alien>() {
//			
//			@Override
//			public Alien mapRow(ResultSet rs, int rowNum) throws SQLException {
//				// TODO Auto-generated method stub
//				Alien a = new Alien();
//				a.setId(rs.getInt(1));
//				a.setName(rs.getString(2));
//				a.setTech(rs.getString(3));
//				return a;
//			}
//		};	

		List<Alien> aliens = template.query(sql, ( rs, rowNum) -> {
			Alien a = new Alien();
			a.setId(rs.getInt(1));
			a.setName(rs.getString(2));
			a.setTech(rs.getString(3));
			return a;
	});

		return aliens;
	}
}
