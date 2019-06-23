package com.geca.trackingboss.userinterface.main;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.geca.trackingboss.R;
import com.geca.trackingboss.model.relative.ListRelativeResponse;

import java.util.ArrayList;
import java.util.List;

public class RelativeAdapter extends RecyclerView.Adapter<RelativeAdapter.ViewHolder> {

    private final OnRelativeClick listener;

    List<ListRelativeResponse.RelativeResponse> relativeResponseList = new ArrayList<>();

    public RelativeAdapter(OnRelativeClick listener) {
        this.listener = listener;
    }

    public interface OnRelativeClick {
        void OnClick(ListRelativeResponse.RelativeResponse relativeResponse);
    }


    public void setRelativeResponseList(List<ListRelativeResponse.RelativeResponse> responseList) {
        this.relativeResponseList = responseList;
        notifyDataSetChanged();
    }

    @Override
    public RelativeAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_relative, parent, false));
    }

    @Override
    public void onBindViewHolder(RelativeAdapter.ViewHolder holder, int position) {
        holder.updateView(relativeResponseList.get(position));
    }

    @Override
    public int getItemCount() {
        return relativeResponseList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        TextView relativeUserNameTextView;
        TextView relativeFirstNameTextView;
        TextView relativeLastNameTextView;

        public ViewHolder(View itemView) {
            super(itemView);
            relativeUserNameTextView = itemView.findViewById(R.id.relativeUserNameTextView);
            relativeFirstNameTextView = itemView.findViewById(R.id.relativeFirstNameTextView);
            relativeLastNameTextView = itemView.findViewById(R.id.relativeLastNameTextView);
        }

        public void updateView(ListRelativeResponse.RelativeResponse response) {
            relativeUserNameTextView.setText(response.getUser().getUsername());
            relativeFirstNameTextView.setText(response.getUser().getFirstname());
            relativeLastNameTextView.setText(response.getUser().getLastname());
            itemView.setOnClickListener(view->listener.OnClick(response));
        }
    }
}