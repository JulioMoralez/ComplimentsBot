package pack;

import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.TelegramBotsApi;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Message;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;
import org.telegram.telegrambots.updatesreceivers.DefaultBotSession;

import java.util.Random;

public class Bot extends TelegramLongPollingBot implements Compliments {

    Random rnd = new Random();
    int len = list.length;

    Bot(){
//        System.getProperties().put( "proxySet", "true" );
//        System.getProperties().put( "socksProxyHost", "127.0.0.1" );
//        System.getProperties().put( "socksProxyPort", "9150" );
        System.out.println("dll OK");
    }

    public static void main(String[] args) {
        System.out.println("START");

        try {
            TelegramBotsApi telegramBotsApi = new TelegramBotsApi(DefaultBotSession.class);
            Bot bot = new Bot();
            telegramBotsApi.registerBot(bot);
        } catch (TelegramApiException e) {
            e.printStackTrace();
        }
    }

    private void sendMsg(Message message, String text) {
        SendMessage sendMessage = new SendMessage();
        sendMessage.setChatId(message.getChatId().toString());
        sendMessage.setReplyToMessageId(message.getMessageId());
        sendMessage.setText(text);
        try {
            System.out.println(text);
            execute(sendMessage);
        } catch (TelegramApiException e) {
            e.printStackTrace();
        }
    }

    private String generateCompliment() {
       return "Ты такая " + list[rnd.nextInt(len)].toLowerCase() + "! :-)";
    }

    @Override
    public void onUpdateReceived(Update update) {
        Message message = update.getMessage();
        if (message != null && message.hasText()) {
            String s = message.getText();
            System.out.println(s);
            sendMsg(message, generateCompliment());
        }
    }

    @Override
    public String getBotUsername() {
        return "ComplimentsForMaryaBot";
    }

    @Override
    public String getBotToken() {
        return "1972594936:AAGnpWOKYNS4o-4walfEHfxa7BKLP8VfO8E";
    }
}
