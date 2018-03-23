package com.packt.thimblerig.api;

import com.packt.thimblerig.api.dto.BetReport;
import com.packt.thimblerig.api.dto.NewBet;
import com.packt.thimblerig.domain.BetResult;
import com.packt.thimblerig.domain.ThimblerigService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/v1/thimblerig")
public class ThimblerigAPI {
  private ThimblerigService thimblerigService;

  @Autowired
  public ThimblerigAPI(ThimblerigService thimblerigService) {
    this.thimblerigService = thimblerigService;
  }

  @ResponseBody
  @PostMapping(value = "/placeBet",
      consumes = MediaType.APPLICATION_JSON_VALUE)
  public BetReport placeBet(@RequestBody NewBet bet) {
    BetResult betResult =
        thimblerigService.placeBet(bet.getPick(), bet.getAmount());
    return new BetReport(betResult);
  }

  @ResponseBody
  @ResponseStatus(HttpStatus.BAD_REQUEST)
  @ExceptionHandler(IllegalArgumentException.class)
  public Error handleInvalidArgumentException(Exception e) {
    return new Error(e.getMessage());
  }
}
