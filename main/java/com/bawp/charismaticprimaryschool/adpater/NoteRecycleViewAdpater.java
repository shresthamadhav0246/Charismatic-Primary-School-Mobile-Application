package com.bawp.charismaticprimaryschool.adpater;


import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bawp.charismaticprimaryschool.R;
import com.bawp.charismaticprimaryschool.data.DatabaseHandler;
import com.bawp.charismaticprimaryschool.model.Note;
import com.bawp.charismaticprimaryschool.utill.Util;

import java.text.SimpleDateFormat;
import java.util.List;
import java.util.Locale;

public class NoteRecycleViewAdpater extends RecyclerView.Adapter<NoteRecycleViewAdpater.ViewHolder> {
    private List<Note> allNote;
    private Context context;
    private noteItemClickListener noteItemClick;

    public NoteRecycleViewAdpater(List<Note> allNote, Context context, noteItemClickListener noteItemClick) {
        this.allNote = allNote;
        this.context = context;
      this.noteItemClick = noteItemClick;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.note_row, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull NoteRecycleViewAdpater.ViewHolder holder, int position) {
      Note note = allNote.get(position);
      holder.title.setText(note.getNote_name());
      holder.createdDate.setText(Util.formatDate(note.getCreatedDate()));

      holder.delete.setOnClickListener(view ->{
          // Delete the note from the list
          allNote.remove(position);
          // Notify the adapter of the changes
          notifyItemRemoved(position);
          notifyItemRangeChanged(position, allNote.size());
          DatabaseHandler db = new DatabaseHandler(context);
          db.deleteNote(note);
      });

    }

    @Override
    public int getItemCount() {
        return allNote.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        TextView title, createdDate;
        ImageView delete;
        noteItemClickListener noteItemClickListener;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            title = itemView.findViewById(R.id.note_name);
            createdDate = itemView.findViewById(R.id.createdDate);
            delete = itemView.findViewById(R.id.delete);

            this.noteItemClickListener = noteItemClick;
            itemView.setOnClickListener(this);
        }


        @Override
        public void onClick(View v) {
            int id  = v.getId();
            if(id == R.id.note_layout){
                Note currentNote = allNote.get(getAdapterPosition());
                noteItemClickListener.noteItemClick(getAdapterPosition(), currentNote);
            }

        }
    }

}
