package ru.ryazanov.ticktacktoe.service.interfaces;

import org.springframework.stereotype.Service;
import ru.ryazanov.ticktacktoe.model.Game;

import java.util.List;

public interface RoomGameService {
    List<Game> getRegistrationGames();
}
