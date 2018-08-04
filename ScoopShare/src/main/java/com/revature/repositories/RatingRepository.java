package com.revature.repositories;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Query;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.revature.beans.Rating;

@Repository
public class RatingRepository {

	static {
		System.out.println("[DEBUG] - RatingRepository instantiated...");
	}

	@Autowired
	SessionFactory sessionFactory;

	public List<Rating> getAll() {

		System.out.println("[DEBUG] - RatingRepository.getAll...");
		Session session = sessionFactory.getCurrentSession();
		return session.createQuery("from Rating", Rating.class).getResultList();
	}

	public Rating getById(int id) {
		System.out.println("[DEBUG] - RatingRepository.getById...");
		Session session = sessionFactory.getCurrentSession();
		return session.get(Rating.class, id);
	}

	public Rating addRating(Rating newRating) {
		System.out.println("[DEBUG] - In RatingRepository.addRating()...");
		Session currentSession = sessionFactory.getCurrentSession();
		currentSession.save(newRating);
		return newRating;
	}

	public Rating updateRating(Rating updatedRating) {
		System.out.println("[DEBUG] - In RatingRepository.updateRating()...");
		Session currentSession = sessionFactory.getCurrentSession();
		Rating rating = currentSession.get(Rating.class, updatedRating.getId());

		if(rating == null) {
			return rating;
		}

		rating = updatedRating;
		return rating;
	}

	public List<Rating> getByArticleId(int articleId) {
		System.out.println("[DEBUG] - In RatingRepository.getByArticleId()...");
		Session currentSession = sessionFactory.getCurrentSession();

		Query query = currentSession.createQuery("from Rating r where r.articleId = ?");
		query.setParameter(0, articleId);

		@SuppressWarnings("unchecked")
		List<Rating> result = query.getResultList();

		if(result == null) {
			return new ArrayList<Rating>();
		}

		return result;
	}
}