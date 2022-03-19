package ru.paraktikumbot.bot.main.botmanager.service;

import org.springframework.stereotype.Service;
import ru.paraktikumbot.bot.main.botmanager.model.DialogState;
import java.util.HashMap;
import java.util.Map;

@Service
public class DialogStateService {

    private final Map<Integer, DialogState> dialogStates = new HashMap<>();

    public DialogState checkDialogState(int chatId) {
        if (!dialogStates.containsKey(chatId)) {
            dialogStates.put(chatId, DialogState.INITIAL);
        }
        return dialogStates.get(chatId);
    }

    public void putDialogState(int chatId, DialogState dialogState) {
        dialogStates.put(chatId, dialogState);
    }

}
