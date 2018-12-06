package com.example.zero.texteditor;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class DocumentAdapter extends RecyclerView.Adapter<DocumentAdapter.ViewHolder> {
    public JSONArray elements;
    private Context context;
    public String userFromId;

    public DocumentAdapter(JSONArray elements, Context context, String userFromId){
        this.elements = elements;
        this.context = context;
        this.userFromId = userFromId;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView first_line, second_line;
        RelativeLayout container;

        public ViewHolder(View itemView) {
            super(itemView);
            first_line = itemView.findViewById(R.id.element_view_first_line);
            second_line = itemView.findViewById(R.id.element_view_second_line);
            container = itemView.findViewById(R.id.element_view_container);
        }
    }

    @NonNull
    @Override
    public DocumentAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.document_view,parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull DocumentAdapter.ViewHolder holder, int position) {
        try {
            JSONObject element = elements.getJSONObject(position);
            String name = element.getString("name");
            final String date = element.getString("date");
            final String id = element.getString("id");
            holder.first_line.setText(name);
            holder.second_line.setText(date);

            holder.container.setOnClickListener(new View.OnClickListener(){

                @Override
                public void onClick(View v) {
                    Intent goToMessage = new Intent(context,EditorActivity.class);
                    goToMessage.putExtra("user_from_id",userFromId);
                    goToMessage.putExtra("user_to_id",id);
                    goToMessage.putExtra("username", date);
                    context.startActivity(goToMessage);
                }
            });


        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    @Override
    public int getItemCount() {
        return elements.length();
    }
}