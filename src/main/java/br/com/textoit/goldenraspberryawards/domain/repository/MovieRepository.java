package br.com.textoit.goldenraspberryawards.domain.repository;

import br.com.textoit.goldenraspberryawards.domain.Movie;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface MovieRepository extends JpaRepository<Movie, Long> {

    /*@Query("SELECT m FROM Movie m LEFT JOIN FETCH m.producers p LEFT JOIN FETCH m.studios s where m.id = :id")
    Movie getMoveById(Long id);*/
}
