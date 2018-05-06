package dao;

import model.Lot;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LotDao extends JpaRepository<Lot, String> {
}
