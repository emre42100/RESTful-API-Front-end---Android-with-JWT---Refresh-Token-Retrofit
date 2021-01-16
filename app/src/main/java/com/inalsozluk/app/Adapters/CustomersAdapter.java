package com.inalsozluk.app.Adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.borjabravo.readmoretextview.ReadMoreTextView;
import com.inalsozluk.app.Models.Customer;
import com.inalsozluk.app.R;

import java.util.List;

import androidx.recyclerview.widget.RecyclerView;


public class CustomersAdapter extends RecyclerView.Adapter<CustomersAdapter.CustomViewHolder> {
    private List<Customer> customers;
    private TextView cizgi;


    public CustomersAdapter(List<Customer> customers) {
        this.customers = customers;
    }

    @Override
    public CustomViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.employee_list, parent, false);


        return new CustomViewHolder(itemView);

    }


    @Override
    public void onBindViewHolder(CustomViewHolder holder, int position) {

        Customer customer = customers.get(position);

        holder.setIsRecyclable(false);

        if (!customer.getLastUpdatedBy().equals("false")) {

            cizgi.setVisibility(View.VISIBLE);
            holder.salary.setText(customer.getLastUpdatedBy());

        }

        holder.readMoreTextView.setTrimCollapsedText("Devamını Oku");
        holder.readMoreTextView.setTrimExpandedText("");
        holder.readMoreTextView.setColorClickableText(R.color.colorAccent);
        holder.readMoreTextView.setTrimLength(300);
        holder.readMoreTextView.setColorClickableText(R.color.colorAccent);


        holder.employeeName.setText(customer.getName());
        holder.readMoreTextView.setText(customer.getAciklama());
        holder.designation.setText(customer.getAddress());
        holder.createdTime.setText(customer.getCreatedOn());





//        if (!customer.getLastUpdatedBy().equals("false")) {
//
//
//            break;
//
//        }

//        holder.dob.setText(customer.getId());
//        holder.contactNumber.setText(customer.getUserId());

    }

    @Override
    public int getItemCount() {
        return customers.size();
    }

    public class CustomViewHolder extends RecyclerView.ViewHolder {
        public TextView employeeName, designation, email, salary, createdTime;
        ReadMoreTextView readMoreTextView;
        public CustomViewHolder(View view) {
            super(view);
            employeeName = (TextView) view.findViewById(R.id.employeeName);
            readMoreTextView =  view.findViewById(R.id.email);
            designation = (TextView) view.findViewById(R.id.designation);
            createdTime = view.findViewById(R.id.createdTime);
            cizgi = view.findViewById(R.id.cizgi);

            salary = (TextView) view.findViewById(R.id.salary);
//            dob = (TextView) view.findViewById(R.id.dob);
//            contactNumber = (TextView) view.findViewById(R.id.contactNumber);
        }
    }


}
