package edu.ejercicios;

import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

import java.util.ArrayList;
import java.util.*;

public class Bot extends TelegramLongPollingBot {

    private List<String> mensajes = new ArrayList<>();

    @Override
    public String getBotUsername() {
        return "p3Manuelbot";
    }

    @Override
    public String getBotToken() {
        return "7185978730:AAHSdAQqoU1vrIsy-yMTcxV4JZ6Tth3gIEo";
    }

    //aqui va el mensaje
    @Override
    public void onUpdateReceived(Update update) {
        //System.out.println(update);
        var usuario = update.getMessage().getFrom();
        var mensaje = update.getMessage().getText();
        var id = usuario.getId();

        mensajes.add(usuario+":"+mensaje);

        System.out.println("id " +usuario.getId()+ " nombre " +" "+usuario.getFirstName()+" "+
                usuario.getLastName()+ " escribio "+ mensaje );

        if (mensaje.contains("/deplegar")&& mensajes.size()>0){
            String lista = "";
            for (String mensaje1 : mensajes){
                lista += mensaje1+"\n";
            }
            sendText(id, lista);
        }

        sendText(id, mensaje);

    }
    public void sendText(Long who, String what){
        SendMessage sm = SendMessage.builder()
                .chatId(who.toString()) //Who are we sending a message to
                .text(what).build();    //Message content
        try {
            execute(sm);                        //Actually sending the message
        } catch (TelegramApiException e) {
            throw new RuntimeException(e);      //Any error will be printed here
        }
    }
}
