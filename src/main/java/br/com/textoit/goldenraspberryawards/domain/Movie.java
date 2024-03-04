package br.com.textoit.goldenraspberryawards.domain;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.With;

import java.util.ArrayList;
import java.util.List;

@Getter
@With
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "movie")
public class Movie {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column
    private Long id;

    @Column
    private String title;

    @Column
    private Integer releaseYear;

    @Column
    private Boolean winner;

    @Builder.Default
    @ManyToMany(mappedBy = "movies")
    private List<Producer> producers = new ArrayList<>();

    @Builder.Default
    @ManyToMany(mappedBy = "movies")
    private List<Studio> studios = new ArrayList<>();

    public void addProducer(Producer producer) {

        boolean containsProducer = this.producers.stream().anyMatch(p -> p.getName().equals(producer.getName()));

        if (!containsProducer) {
            this.producers.add(producer);
        }
    }

    public void addStudio(Studio studio) {
        boolean containsStudio = this.studios.stream().anyMatch(s -> s.getName().equals(studio.getName()));

        if (!containsStudio) {
            this.studios.add(studio);
        }
    }
}
