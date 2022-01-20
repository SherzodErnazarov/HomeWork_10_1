package uz.pdp.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import uz.pdp.entity.Hotel;

public interface HotelRepository extends JpaRepository<Hotel,Integer> {
}
