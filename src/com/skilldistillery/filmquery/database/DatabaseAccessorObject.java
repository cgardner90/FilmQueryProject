package com.skilldistillery.filmquery.database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import com.skilldistillery.filmquery.entities.Actor;
import com.skilldistillery.filmquery.entities.Film;

public class DatabaseAccessorObject implements DatabaseAccessor {
	private static final String URL = "jdbc:mysql://localhost:3306/sdvid?useSSL=false";
	private String user = "student";
	private String pass = "student";

	public DatabaseAccessorObject() {
		try {
			Class.forName("com.mysql.jdbc.Driver");
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			System.err.println("Error loading database driver");
		}
	}

	@Override
	public Film findFilmById(int filmId) {
		Film film = null;

		try {
			Connection conn = DriverManager.getConnection(URL, user, pass);
			String sql = "SELECT film.id, film.title, film.description, film.release_year, film.language_id, film.rental_duration, "
					+ " film.rental_rate, film.length, film.replacement_cost, film.rating, film.special_features, language.name FROM film JOIN language on film.language_id = language.id WHERE film.id = ? ";
			PreparedStatement stmt = conn.prepareStatement(sql);
			stmt.setInt(1, filmId);
			ResultSet rs = stmt.executeQuery();
			if (rs.next()) {
				film = new Film();
				film.setId(rs.getInt("film.id"));
				film.setTitle(rs.getString("film.title"));
				film.setDescription(rs.getString("film.description"));
				film.setReleaseYear(rs.getInt("film.release_year"));
				film.setLanguageId(rs.getInt("film.language_id"));
				film.setRentalDuration(rs.getInt("film.rental_duration"));
				film.setRentalRate(rs.getDouble("film.rental_rate"));
				film.setLength(rs.getInt("film.length"));
				film.setReplacementCost(rs.getDouble("film.replacement_cost"));
				film.setRating(rs.getString("film.rating"));
				film.setSpecialFeatures(rs.getString("film.special_features"));
				film.setLanguage(rs.getString("language.name"));

			}
		} catch (SQLException e) {
			System.err.println();

		}

		return film;
	}

	@Override
	public Actor findActorById(int actorId) {
		Actor a = null;
		try {
			Connection conn = DriverManager.getConnection(URL, user, pass);
			String sql = "SELECT id, first_name, last_name " + " FROM actor WHERE first_name = %?% ";
			PreparedStatement stmt = conn.prepareStatement(sql);
			stmt.setInt(1, actorId);
			ResultSet rs = stmt.executeQuery();
			if (rs.next()) {
				a = new Actor();
				a.setId(rs.getInt("id"));
				a.setFirstName(rs.getString("first_name"));
				a.setLastName(rs.getString("last_name"));

			}
		} catch (SQLException e) {
			System.err.println("SQL Failure");
		}
		return a;
	}

	@Override
	public List<Actor> findActorsByFilmId(int filmId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Film findFilmByKeyword(String keyword) {
		Film film = null;
		try {
			Connection conn = DriverManager.getConnection(URL, user, pass);
			String sql = "SELECT film.id, film.title, film.description, film.release_year, film.language_id, film.rental_duration, "
					+ " film.rental_rate, film.length, film.replacement_cost, film.rating, film.special_features, language.name " + " FROM film JOIN language on film.language_id = language.id WHERE title LIKE ? OR description LIKE ? ";
			PreparedStatement stmt = conn.prepareStatement(sql);
			stmt.setString(1, "%"+ keyword+"%");
			stmt.setString(2, "%"+keyword+ "%");
			ResultSet rs = stmt.executeQuery();
			if (rs.next()) {
				film = new Film();
				film.setId(rs.getInt("id"));
				film.setTitle(rs.getString("title"));
				film.setDescription(rs.getString("description"));
				film.setReleaseYear(rs.getInt("release_year"));
				film.setLanguageId(rs.getInt("language_id"));
				film.setRentalDuration(rs.getInt("rental_duration"));
				film.setRentalRate(rs.getDouble("rental_rate"));
				film.setLength(rs.getInt("length"));
				film.setReplacementCost(rs.getDouble("replacement_cost"));
				film.setRating(rs.getString("rating"));
				film.setSpecialFeatures(rs.getString("special_features"));
				film.setLanguage(rs.getString("name"));

			}
		} catch (SQLException e) {
			System.err.println();

		}

		return film;
	

}

}
