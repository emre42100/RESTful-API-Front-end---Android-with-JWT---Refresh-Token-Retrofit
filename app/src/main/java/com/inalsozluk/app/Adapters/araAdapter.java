package com.inalsozluk.app.Adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.borjabravo.readmoretextview.ReadMoreTextView;
import com.inalsozluk.app.Models.ara_response;
import com.inalsozluk.app.R;

import java.util.List;

import androidx.recyclerview.widget.RecyclerView;


public class araAdapter extends RecyclerView.Adapter<araAdapter.CustomViewHolder> {
    private List<ara_response> customers;
    private TextView cizgi;


    public araAdapter(List<ara_response> customers) {
        this.customers = customers;
    }

    @Override
    public CustomViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.ara_list, parent, false);


        return new CustomViewHolder(itemView);
    }


    @Override
    public void onBindViewHolder(CustomViewHolder holder, int position) {


        ara_response ara_response = customers.get(position);
        holder.setIsRecyclable(false);
        if (!ara_response.getLastUpdatedBy().equals("false")) {
            holder.salary.setText(ara_response.getLastUpdatedBy());
            cizgi.setVisibility(View.VISIBLE);
        }

        holder.readMoreTextView.setTrimCollapsedText("Devamını Oku");
        holder.readMoreTextView.setTrimExpandedText("");
        holder.readMoreTextView.setColorClickableText(R.color.colorAccent);
        holder.readMoreTextView.setTrimLength(300);
        holder.readMoreTextView.setColorClickableText(R.color.colorAccent);

        holder.employeeName.setText(ara_response.getName());
        holder.readMoreTextView.setText(ara_response.getAciklama());
        holder.createdTime.setText(ara_response.getCreatedOn());


//        holder.dob.setText(customer.getId());
//        holder.contactNumber.setText(customer.getUserId());
    }

    @Override
    public int getItemCount() {
        return customers.size();
    }

    public class CustomViewHolder extends RecyclerView.ViewHolder {
        public TextView employeeName, email, salary, createdTime;
        ReadMoreTextView readMoreTextView;
        public CustomViewHolder(View view) {
            super(view);
            employeeName = (TextView) view.findViewById(R.id.employeeName);
            readMoreTextView = view.findViewById(R.id.email);
            createdTime = view.findViewById(R.id.createdTime);
            cizgi = view.findViewById(R.id.cizgi);
            salary = (TextView) view.findViewById(R.id.salary);
//            dob = (TextView) view.findViewById(R.id.dob);
//            contactNumber = (TextView) view.findViewById(R.id.contactNumber);
        }
    }


}
