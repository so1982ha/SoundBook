package com.example.soundbook;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class ChatAdapterAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    List<Message> listOfMessage = new ArrayList();

    public void setListOfMessage(List<Message> listOfMessage) {
        this.listOfMessage = listOfMessage;
        notifyDataSetChanged();
    }

    @Override
    public int getItemViewType(int position) {
        if (listOfMessage.get(position) instanceof TextMessage) {
            return 1;
        } else if (listOfMessage.get(position) instanceof PhotoMessage) {
            return 2;
        }

        return 0;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        if (viewType == 1) {
            View xml = LayoutInflater.from(parent.getContext()).inflate(R.layout.row_text_chat, parent, false);
            TextXmlRow xmlRow = new TextXmlRow(xml);
            return xmlRow;
        } else {

            View xml = LayoutInflater.from(parent.getContext()).inflate(R.layout.row_photo_chat, parent, false);
            PhotoXmlRow xmlRow = new PhotoXmlRow(xml);
            return xmlRow;

        }

//        XmlRow viewHolderObject = new XmlRow(xml);
//        return viewHolderObject;
    }


    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder xmlRow, int position) {
        if (xmlRow instanceof TextXmlRow) {
            ///
            TextMessage message =(TextMessage)listOfMessage.get(position);

        } else if (xmlRow instanceof PhotoXmlRow) {
            PhotoMessage message =(PhotoMessage)listOfMessage.get(position);

        }

    }

    @Override
    public int getItemCount() {
        return listOfMessage.size();
    }

    class PhotoXmlRow extends RecyclerView.ViewHolder {

        public PhotoXmlRow(@NonNull View itemView) {
            super(itemView);
        }
    }

    class TextXmlRow extends RecyclerView.ViewHolder {

        public TextXmlRow(@NonNull View itemView) {
            super(itemView);
        }
    }
}
