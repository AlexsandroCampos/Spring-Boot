package academy.devdojo.springboot2.repository;

import academy.devdojo.springboot2.domain.Anime;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.List;
import java.util.Optional;


@DataJpaTest
@DisplayName("Test for anime repository")
class AnimeRepositoryTest {
    @Autowired
    private  AnimeRepository animeRepository;

    @Test
    @DisplayName("Save persistis anime when succesful")
    void save_PersistAnime_WhenSuccesful() {
        Anime animeToBeSaved = createAnime();
        Anime animeSaved = this.animeRepository.save(animeToBeSaved);
        Assertions.assertThat(animeSaved).isNotNull();
        Assertions.assertThat(animeSaved.getId()).isNotNull();
        Assertions.assertThat(animeSaved.getName()).isEqualTo(animeToBeSaved.getName());

    }

    @Test
    @DisplayName("Save updates anime when succesful")
    void save_UpdatesAnime_WhenSuccesful() {
        Anime animeToBeSaved = createAnime();
        Anime animeSaved = this.animeRepository.save(animeToBeSaved);
        animeSaved.setName("Charuto");
        Anime animeUpdated = this.animeRepository.save(animeSaved);
        Assertions.assertThat(animeUpdated).isNotNull();
        Assertions.assertThat(animeUpdated.getId()).isNotNull();
        Assertions.assertThat(animeUpdated.getName()).isEqualTo(animeSaved.getName());

    }

    @Test
    @DisplayName("Delete removes anime when succesful")
    void delete_RemovesAnime_WhenSuccesful() {
        Anime animeToBeSaved = createAnime();
        Anime animeSaved = this.animeRepository.save(animeToBeSaved);
        this.animeRepository.delete(animeSaved);
        Optional<Anime> animeOptional = this.animeRepository.findById(animeSaved.getId());
        Assertions.assertThat(animeOptional).isEmpty();

    }

    @Test
    @DisplayName("Find By Name returns a list of anime when succesful")
    void findByName_ReturnsAListOfAnime_WhenSuccesful() {
        Anime animeToBeSaved = createAnime();
        Anime animeSaved = this.animeRepository.save(animeToBeSaved);
        String name = animeSaved.getName();
        List<Anime> animes = this.animeRepository.findByName(name);
        Assertions.assertThat(animes).isNotEmpty();
        Assertions.assertThat(animes).contains(animeSaved);

    }

    @Test
    @DisplayName("Find By Name returns a empty list when no anime is found")
    void findByName_ReturnsEmpty_WhenAnimeIsNotFound() {
        List<Anime> animes = this.animeRepository.findByName("iuiu");
        Assertions.assertThat(animes).isEmpty();

    }

    private Anime createAnime() {
        return Anime.builder().name("Hjime").build();
    }
}