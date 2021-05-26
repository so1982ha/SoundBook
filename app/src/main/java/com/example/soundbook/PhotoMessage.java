package com.example.soundbook;

public class PhotoMessage extends ChatMessage implements Message {
    PhotoMessage() {
        type = MessageType.Photo;
    }
}
