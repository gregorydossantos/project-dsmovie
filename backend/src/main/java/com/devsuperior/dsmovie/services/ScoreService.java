package com.devsuperior.dsmovie.services;

import com.devsuperior.dsmovie.models.dtos.MovieDTO;
import com.devsuperior.dsmovie.models.dtos.ScoreDTO;
import com.devsuperior.dsmovie.models.entities.Movie;
import com.devsuperior.dsmovie.models.entities.Score;
import com.devsuperior.dsmovie.models.entities.User;
import com.devsuperior.dsmovie.models.entities.User.UserBuilder;
import com.devsuperior.dsmovie.repositories.MovieRepository;
import com.devsuperior.dsmovie.repositories.ScoreRepository;
import com.devsuperior.dsmovie.repositories.UserRepository;
import com.devsuperior.dsmovie.usefuls.ValidateUseful;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class ScoreService {

    @Autowired
    private MovieRepository movieRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private ScoreRepository scoreRepository;

    public MovieDTO saveScore(ScoreDTO dto) {
        User user = userRepository.findByEmail(dto.getEmail());
        if (ValidateUseful.isNullOrEmpty(user)) {
            user = User.builder().email(dto.getEmail()).build();
            user = userRepository.saveAndFlush(user);
        }

        Movie movie = movieRepository.findById(dto.getMovieId()).get();

        //TODO research how to using the builder() method here
        Score score = new Score();
        score.setMovie(movie);
        score.setUser(user);
        score.setValue(dto.getScore());
        score = scoreRepository.saveAndFlush(score);

        double sum = 0;
        for (Score s : movie.getScores()) {
            sum = sum + s.getValue();
        }

        double avg = sum / movie.getScores().size();

        movie.setScore(avg);
        movie.setCount(movie.getScores().size());
        movie = movieRepository.save(movie);

        return new MovieDTO(movie);
    }
}
