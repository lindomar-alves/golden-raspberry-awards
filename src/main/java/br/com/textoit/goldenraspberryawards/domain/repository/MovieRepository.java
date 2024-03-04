package br.com.textoit.goldenraspberryawards.domain.repository;

import br.com.textoit.goldenraspberryawards.domain.Movie;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MovieRepository extends JpaRepository<Movie, Long> {
}
