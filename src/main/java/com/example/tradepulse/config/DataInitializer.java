package com.example.tradepulse.config;

import com.example.tradepulse.entity.Hallgato;
import com.example.tradepulse.entity.User;
import com.example.tradepulse.repository.HallgatoRepository;
import com.example.tradepulse.repository.UserRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.time.LocalDate;

@Component
public class DataInitializer implements CommandLineRunner {

    private final UserRepository userRepository;
    private final HallgatoRepository hallgatoRepository;
    private final PasswordEncoder passwordEncoder;

    public DataInitializer(UserRepository userRepository,
                           HallgatoRepository hallgatoRepository,
                           PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.hallgatoRepository = hallgatoRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public void run(String... args) {
        // Alapértelmezett felhasználók létrehozása
        if (!userRepository.existsByUsername("admin")) {
            userRepository.save(new User("admin", passwordEncoder.encode("admin123"), "ADMIN"));
        }
        if (!userRepository.existsByUsername("user")) {
            userRepository.save(new User("user", passwordEncoder.encode("user123"), "USER"));
        }

        // Hallgatók feltöltése (feladat.sql alapján a React CRUD modulhoz)
        if (hallgatoRepository.count() == 0) {
            hallgatoRepository.save(new Hallgato("AAAABBB.KEFO", "Kiss", "István", "pista@kiss.com", LocalDate.of(1990, 2, 23), 1, 10));
            hallgatoRepository.save(new Hallgato("CCCCBBB.KEFO", "Nagy", "Béla", "bela@freemail.hu", LocalDate.of(1981, 5, 10), 1, 25));
            hallgatoRepository.save(new Hallgato("DDDFFFF.KEFO", "Kicsi", "Huba", "hubi@jager.com", LocalDate.of(1988, 8, 1), 4, 30));
            hallgatoRepository.save(new Hallgato("QQQQBBB.KEFO", "Közepes", "Károly", "karcsi@kozepfolde.hu", LocalDate.of(1992, 11, 30), 2, 56));
            hallgatoRepository.save(new Hallgato("RRRRTTT.KEFO", "Nagy", "Aladár", "ali@express.com", LocalDate.of(1989, 9, 29), 3, 82));
            hallgatoRepository.save(new Hallgato("FIY9TP.NJE", "Kovács", "Ákos", "kovacs.akos@gamf.uni-neumann.hu", LocalDate.of(2003, 4, 15), 1, 110));
        }
    }
}
