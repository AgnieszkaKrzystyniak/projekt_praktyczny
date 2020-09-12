package pl.sda.racing.winning;

import lombok.AllArgsConstructor;
import pl.sda.racing.Pigeon;

import java.math.BigDecimal;
@AllArgsConstructor
public class Bet {
    private  final Pigeon firstplaceWin;
    private final BigDecimal betAmount;
    private final  BetType betType;

}
