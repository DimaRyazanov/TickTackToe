package ru.ryazanov.ticktacktoe.service.util;

import ru.ryazanov.ticktacktoe.model.GamePlayer;
import ru.ryazanov.ticktacktoe.model.Player;

import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentMap;
import java.util.stream.Collectors;

public final class ObserverUtil {

    private ObserverUtil() {
    }

    /**
     * Find player who must move in next turn. At the entrance
     * list players in game with positions and player who move last.
     * GamePlayer map to map<Integer, Player> (position, player).
     * Find min position, find max position from map.
     * Find position player who move last.
     * If move position + 1 > max position then return min position.
     * Else return move position + 1.
     *
     * @param gamePlayers    - list gamePlayers in game.
     * @param lastMovePlayer - player who move last.
     * @return Player who move in next turn.
     */
    public static Player getTurn(final List<GamePlayer> gamePlayers, final Player lastMovePlayer) {
        ConcurrentMap<Integer, Player> positionMap = gamePlayers
                .stream().collect(
                        Collectors.toConcurrentMap(GamePlayer::getPosition, GamePlayer::getPlayer));
        int minPosition = Collections.min(positionMap.keySet());
        int maxPosition = Collections.max(positionMap.keySet());

        if (positionMap.containsValue(lastMovePlayer)) {
            for (Map.Entry<Integer, Player> entry
                    : positionMap.entrySet()) {
                if (entry.getValue().getId() == lastMovePlayer.getId()) {
                    int currentPosition = entry.getKey();
                    if (currentPosition + 1 > maxPosition) {
                        return positionMap.get(minPosition);
                    } else {
                        return positionMap.get(currentPosition + 1);
                    }
                }
            }
        }

        return positionMap.get(minPosition);

    }
}
