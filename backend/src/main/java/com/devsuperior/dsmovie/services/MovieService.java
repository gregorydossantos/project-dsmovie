package com.devsuperior.dsmovie.services;

import com.devsuperior.dsmovie.models.dtos.MovieDTO;
import com.devsuperior.dsmovie.models.entities.Movie;
import com.devsuperior.dsmovie.repositories.MovieRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(readOnly = true)
public class MovieService {

    @Autowired
    private MovieRepository movieRepository;

    public Page<MovieDTO> findAll(Pageable pageable) {
        Page<Movie> result = movieRepository.findAll(pageable);
        Page<MovieDTO> page = result.map(MovieDTO::new);
        return page;
    }

    public MovieDTO findById(Long id) {
        Movie result = movieRepository.findById(id).get();
        return new MovieDTO(result);
    }
}
