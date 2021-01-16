package com.inalsozluk.app.Adapters;

import android.app.Activity;
import android.content.Context;
import android.content.res.Resources;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.borjabravo.readmoretextview.ReadMoreTextView;
import com.inalsozluk.app.Models.getCustomerDetails_response;
import com.inalsozluk.app.R;

import java.util.List;

import androidx.recyclerview.widget.RecyclerView;


public class MyCustomerAdapter extends RecyclerView.Adapter<MyCustomerAdapter.CustomViewHolder> {
    private List<getCustomerDetails_response> customers;
    private TextView cizgi;
    private OnItemClickListener mListener;
    Context context;
    Activity activity;

    public interface OnItemClickListener {

        void onItemClick(int position);
    }

    public void setOnItemClickListener(OnItemClickListener listener) {

        mListener = listener;
    }

    public MyCustomerAdapter(List<getCustomerDetails_response> customers, Context context, Activity activity) {
        this.customers = customers;
        this.context = context;
        this.activity = activity;


    }


    @Override
    public CustomViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.employee_list, parent, false);

        return new CustomViewHolder(itemView, mListener);
    }

    public void removeAt(int position) {
        customers.remove(0);

//        notifyItemRemoved(position);
//        notifyItemRangeChanged(position, customers.size());

    }

    public List<getCustomerDetails_response> getCustomers() {
        return customers;
    }

    public void setCustomers(List<getCustomerDetails_response> customers) {
        this.customers = customers;
    }

    public OnItemClickListener getmListener() {
        return mListener;
    }

    @Override
    public String toString() {
        return "MyCustomerAdapter{" +
                "customers=" + customers +
                ", mListener=" + mListener +
                ", context=" + context +
                ", activity=" + activity +
                '}';
    }

    public void setmListener(OnItemClickListener mListener) {
        this.mListener = mListener;
    }


    @Override
    public void onBindViewHolder(final CustomViewHolder holder, int position) {

        final getCustomerDetails_response customer = customers.get(position);

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


        //context.getSystemService(Context.WINDOW_SERVICE);

        final float densty_dpi = Resources.getSystem().getDisplayMetrics().densityDpi;
        final float met = Resources.getSystem().getDisplayMetrics().density;
        final float wd = Resources.getSystem().getDisplayMetrics().widthPixels;

        if (densty_dpi >= 200 && densty_dpi <= 299) {
            ViewGroup.MarginLayoutParams params = (ViewGroup.MarginLayoutParams) holder.nokta.getLayoutParams();

            params.leftMargin = 660;

        }


        if (densty_dpi == 420) {
            ViewGroup.MarginLayoutParams params = (ViewGroup.MarginLayoutParams) holder.nokta.getLayoutParams();

            params.leftMargin = 1000;

        }

        System.out.println(Resources.getSystem().getDisplayMetrics().density);

        if (densty_dpi == 408 || densty_dpi == 400 && met == 2.5) {
            ViewGroup.MarginLayoutParams params = (ViewGroup.MarginLayoutParams) holder.nokta.getLayoutParams();


            params.leftMargin = 1000;

        }

        if (densty_dpi == 560 && met == 3.5) {
            ViewGroup.MarginLayoutParams params = (ViewGroup.MarginLayoutParams) holder.nokta.getLayoutParams();

            params.leftMargin = 1300;

        }

        if (densty_dpi >= 300 && densty_dpi <= 399) {
            ViewGroup.MarginLayoutParams params = (ViewGroup.MarginLayoutParams) holder.nokta.getLayoutParams();

            params.leftMargin = 660;

        }

        if (densty_dpi >= 500 && densty_dpi <= 599) {
            ViewGroup.MarginLayoutParams params = (ViewGroup.MarginLayoutParams) holder.nokta.getLayoutParams();

            params.leftMargin = 1300;

        }

//        if (densty_dpi == 560 || densty_dpi == 540 || densty_dpi == 531 || densty_dpi == 568|| densty_dpi == 522 || densty_dpi == 576 || densty_dpi == 577 ||
//                densty_dpi == 534 || densty_dpi == 521 || densty_dpi == 570 || densty_dpi == 571 || densty_dpi == 529 || densty_dpi == 572 || densty_dpi == 565
//                || densty_dpi == 515 || densty_dpi == 510 || densty_dpi == 537 || densty_dpi == 523) {
//
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
        ImageView nokta;
        ReadMoreTextView readMoreTextView;

        public CustomViewHolder(View view, final OnItemClickListener listener) {
            super(view);
            employeeName = (TextView) view.findViewById(R.id.employeeName);

            designation = (TextView) view.findViewById(R.id.designation);
            createdTime = view.findViewById(R.id.createdTime);
            cizgi = view.findViewById(R.id.cizgi);
            nokta = view.findViewById(R.id.nokta);
            readMoreTextView = view.findViewById(R.id.email);




            salary = (TextView) view.findViewById(R.id.salary);

            view.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    if (listener != null) {

                        int position = getAdapterPosition();
                        if (position != RecyclerView.NO_POSITION) {
                            listener.onItemClick(position);
                        }
                    }
                }
            });

//            dob = (TextView) view.findViewById(R.id.dob);
//            contactNumber = (TextView) view.findViewById(R.id.contactNumber);
        }
    }


}
