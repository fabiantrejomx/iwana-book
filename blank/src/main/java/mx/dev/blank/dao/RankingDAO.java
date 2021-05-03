package mx.dev.blank.dao;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

import mx.dev.blank.entity.Book;
import mx.dev.blank.entity.Ranking;
import mx.dev.blank.model.BookRankingDTO;
import org.springframework.validation.annotation.Validated;

import java.util.List;

@Validated
public interface RankingDAO {

  void create(@NotNull Ranking ranking);

}
