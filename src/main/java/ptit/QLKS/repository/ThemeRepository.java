package ptit.QLKS.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ptit.QLKS.entity.Theme;

@Repository
public interface ThemeRepository extends JpaRepository<Theme , Integer> {
}
