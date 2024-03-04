package br.com.textoit.goldenraspberryawards.domain.repository;

import br.com.textoit.goldenraspberryawards.domain.Producer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProducerRepository extends JpaRepository<Producer, Long> {

    @Query("SELECT p FROM Producer p LEFT JOIN FETCH p.movies m where m.winner = true order by p.name desc")
    List<Producer> findAllWithMovies();

    @Query("SELECT p FROM Producer p LEFT JOIN FETCH p.movies where p.id in(:ids)")
    List<Producer> findByIds(List<Long> ids);

    @Query("SELECT p FROM Producer p LEFT JOIN FETCH p.movies m where m.id = :idMovie")
    List<Producer> findByIMovie(Long idMovie);

}
