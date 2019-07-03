package ru.ryazanov.ticktacktoe.service.util;

import ru.ryazanov.ticktacktoe.model.GamePlayer;
import ru.ryazanov.ticktacktoe.model.Player;

import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentMap;
import java.util.stream.Collectors;

public class ObserverUtil {
    public static Player getTurn(List<GamePlayer> gamePlayers, Player lastMovePlayer){
        ConcurrentMap<Integer, Player> positionMap = gamePlayers.stream().collect(Collectors.toConcurrentMap(GamePlayer::getPosition, GamePlayer::getPlayer));
        int minPosition = Collections.min(positionMap.keySet());
        int maxPosition = Collections.max(positionMap.keySet());

        if (positionMap.containsValue(lastMovePlayer)){
            for (Map.Entry<Integer, Player> entry:
                    positionMap.entrySet()){
                if (entry.getValue().getId() == lastMovePlayer.getId()){
                    int currentPosition = entry.getKey();
                    if (currentPosition + 1 > maxPosition){
                        return positionMap.get(minPosition);
                    }else{
                        return positionMap.get(currentPosition + 1);
                    }
                }
            }
        }

        return positionMap.get(minPosition);

    }
}
