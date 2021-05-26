package com.example.soundbook;

public class TextMessage extends ChatMessage implements Message{
    public TextMessage(){
        this.type=MessageType.Text;
    }
}
