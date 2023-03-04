package br.com.cwi.myFavoritePet.service;

import org.springframework.stereotype.Service;

import java.time.LocalDate;

@Service
public class NowService {

    public LocalDate today() {
        return LocalDate.now();
    }
}
