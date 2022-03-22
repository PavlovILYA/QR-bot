package ru.paraktikumbot.bot.main.botmanager.service;

import net.glxn.qrgen.core.scheme.Schema;
import org.springframework.stereotype.Service;
import ru.paraktikumbot.bot.main.botmanager.model.DialogState;
import java.util.HashMap;
import java.util.Map;

@Service
public class DialogStateService {

    private final Map<Integer, DialogState> dialogStates = new HashMap<>();
    private final Map<Integer, Schema> dialogData = new HashMap<>();

    public DialogState checkDialogState(int chatId) {
        if (!dialogStates.containsKey(chatId)) {
            dialogStates.put(chatId, DialogState.INITIAL);
        }
        return dialogStates.get(chatId);
    }

    public void putDialogState(int chatId, DialogState dialogState) {
        dialogStates.put(chatId, dialogState);
    }

    public DialogState getDialogState(int chatId) {
        return dialogStates.get(chatId);
    }

    public Schema getDialogData(int chatId) {
        return dialogData.get(chatId);
    }

    public void putDialogData(int chatId, Schema data) {
        dialogData.put(chatId, data);
    }

}
