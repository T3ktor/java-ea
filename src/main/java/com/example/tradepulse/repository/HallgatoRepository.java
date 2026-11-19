package com.example.tradepulse.repository;

import com.example.tradepulse.entity.Hallgato;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface HallgatoRepository extends JpaRepository<Hallgato, String> {
}
