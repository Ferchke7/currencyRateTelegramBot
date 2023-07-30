package com.example.currencyratetelegrambot.botInstance;



import com.example.currencyratetelegrambot.config.BotConfig;
import lombok.AllArgsConstructor;
import com.example.currencyratetelegrambot.model.CurrencyModel;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.bots.DefaultBotOptions;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;
import com.example.currencyratetelegrambot.service.CurrencyService;

import java.io.IOException;
import java.text.ParseException;

@Component

public class TelegramBot extends TelegramLongPollingBot {
    private final BotConfig botConfig;

    public TelegramBot(BotConfig botConfig) {
        super(botConfig.getToken());

        this.botConfig = botConfig;
    }

    @Override
    public String getBotUsername() {
        return botConfig.getBotName();
    }


    @Override
    public void onUpdateReceived(Update update) {
        CurrencyModel currencyModel = new CurrencyModel();
        String currency = "";

        if (update.hasMessage() && update.getMessage().hasText()) {
            String messageText = update.getMessage().getText();
            long chatId = update.getMessage().getChatId();

            switch (messageText.toLowerCase()) {
                case "/start":
                    startCommandReceived(chatId,update.getMessage().getChat()
                            .getFirstName());
                    break;
                case "/startkorean":
                    startCommandReceivedInKorean(chatId, update.getMessage().getChat().getFirstName());
                    break;
                default:
                    try {
                        currency = CurrencyService.getCurrencyRate(messageText,currencyModel);
                    } catch (IOException e) {
                        sendMessage(chatId, " We have not found such a currency"
                        + "\nEnter the currency whose official exchange rate"
                        +"\nyou want to know in relation to KRW"
                        +"\nFor Example: USD");
                    } catch (ParseException e) {
                        throw new RuntimeException(e);
                    }
                    sendMessage(chatId,currency);
            }
        }
    }

    private void startCommandReceived(Long chatId, String name) {
        String answer = "Hi, " + name + ", nice to meet you!" + "\n"
                + "Enter the currency whose official exchange rate " +
                "\nyou want to know in relation to KRW. " +
                "For example: USD";
        sendMessage(chatId,answer);
    }
    private void startCommandReceivedInKorean(Long chatId, String name) {
        String answer = "안녕하세요, " + name + "님!" + "\n"
                + "원화와 관련하는 공식 환율을 알고자 하시는다면 입력하십시오 " +
                "\n예:USD";
        sendMessage(chatId,answer);
    }

    private void sendMessage(Long chatId, String textToSend) {
        SendMessage sendMessage = new SendMessage();
        sendMessage.setChatId(String.valueOf(chatId));
        sendMessage.setText(textToSend);
        try {
            execute(sendMessage);
        } catch (TelegramApiException ignored) {

        }
    }


}
