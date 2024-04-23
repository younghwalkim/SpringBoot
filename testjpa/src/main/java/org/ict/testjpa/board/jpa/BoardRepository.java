package org.ict.testjpa.board.jpa;

import org.ict.testjpa.board.dto.Board;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BoardRepository extends JpaRepository<Board, Long> {

}
