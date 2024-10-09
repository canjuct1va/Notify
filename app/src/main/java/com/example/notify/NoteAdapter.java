package com.example.notify;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.firebase.ui.firestore.FirestoreRecyclerAdapter;
import com.firebase.ui.firestore.FirestoreRecyclerOptions;

public class NoteAdapter extends FirestoreRecyclerAdapter<Note, NoteAdapter.NoteViewHolder> {
    Context context;

    public NoteAdapter(@NonNull FirestoreRecyclerOptions<Note> options, Context context ) {
        super(options);
        this.context = context;
    }

    @Override
    protected void onBindViewHolder(@NonNull NoteViewHolder noteViewHolder, int i, @NonNull Note note) {
        noteViewHolder.titleTextView.setText(note.title);
        noteViewHolder.contentTextView.setText(note.content);
        noteViewHolder.TimestampTextView.setText(Utility.timestampToString(note.timestamp));

        noteViewHolder.itemView.setOnClickListener((v)->{
            Intent intent = new Intent(context, NoteDetailsActivity.class);
            intent.putExtra("title",note.title);
            intent.putExtra("content",note.content);
            String docId = this.getSnapshots().getSnapshot(i).getId();
            intent.putExtra("docId",docId);
            context.startActivity(intent);

        });

    }

    @NonNull
    @Override
    public NoteViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.recycler_note_item,parent,false);
        return new NoteViewHolder(view);
    }

    class NoteViewHolder extends RecyclerView.ViewHolder{

        TextView titleTextView, contentTextView, TimestampTextView;

        public NoteViewHolder(@NonNull View itemView) {
            super(itemView);
            titleTextView = itemView.findViewById(R.id.note_title_text_view);
            contentTextView = itemView.findViewById(R.id.note_content_text_view);
            TimestampTextView = itemView.findViewById(R.id.note_timestamp_text_view);

        }
    }
}
