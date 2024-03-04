package br.com.textoit.goldenraspberryawards.domain.repository;

import br.com.textoit.goldenraspberryawards.domain.Producer;
import br.com.textoit.goldenraspberryawards.domain.Studio;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface StudioRepository extends JpaRepository<Studio, Long> {

    Studio findByName(String name);

    @Query("SELECT s FROM Studio s where s.id in(:ids)")
    List<Studio> findByIds(List<Long> ids);

    @Query("SELECT s FROM Studio s LEFT JOIN FETCH s.movies m where m.id = :idMovie")
    List<Studio> findByIMovie(Long idMovie);
}
