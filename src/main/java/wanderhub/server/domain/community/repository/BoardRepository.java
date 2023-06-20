package wanderhub.server.domain.community.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import wanderhub.server.domain.community.entity.Board;

import java.util.Optional;


@Repository
public interface BoardRepository extends JpaRepository<Board, Long> {


}
