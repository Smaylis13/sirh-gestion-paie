package dev.paie.service;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;
import org.springframework.test.context.ContextConfiguration;

import dev.paie.config.ServicesConfig;
import dev.paie.entite.Grade;
import dev.paie.spring.DataSourceMySQLConfig;

@Service
@ContextConfiguration(classes = { DataSourceMySQLConfig.class })
public class GradeServiceJdbcTemplate implements GradeService {

	private JdbcTemplate jdbcTemplate;

	@Autowired
	public GradeServiceJdbcTemplate(DataSource dataSource) {
		super();
		this.jdbcTemplate = new JdbcTemplate(dataSource);
	}

	@Override
	public void sauvegarder(Grade nouveauGrade) {
		String sql = "INSERT INTO GRADE (code,nbHeuresBase, tauxBase) VALUES(?,?,?)";
		this.jdbcTemplate.update(sql, nouveauGrade.getCode(), nouveauGrade.getNbHeuresBase(),
				nouveauGrade.getTauxBase());

	}

	@Override
	public void mettreAJour(Grade grade) {
		String sql = "UPDATE GRADE SET CODE=?, NBHEURESBASE=?, TAUXBASE=? WHERE CODE=?";
		this.jdbcTemplate.update(sql, grade.getCode(), grade.getNbHeuresBase(),
				grade.getTauxBase(),grade.getCode());

	}

	@Override
	public List<Grade> lister() {
		String query = "SELECT * FROM GRADE";
		return this.jdbcTemplate.query(query, new GradeMapper());
	}

	public class GradeMapper implements RowMapper<Grade> {

		@Override
		public Grade mapRow(ResultSet rs, int rownum) throws SQLException {
			Grade grade = new Grade();
			grade.setId(rs.getInt("ID"));
			grade.setCode(rs.getString("CODE"));
			grade.setNbHeuresBase(rs.getBigDecimal("nbHeuresBase"));
			grade.setTauxBase(rs.getBigDecimal("tauxBase"));

			return grade;
		}
	}
}
