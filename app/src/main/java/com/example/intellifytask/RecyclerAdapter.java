package com.example.intellifytask;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class RecyclerAdapter extends RecyclerView.Adapter<RecyclerAdapter.AdapterHolder> {
    StudentObjectClass studentObjectClasses;

    public RecyclerAdapter(StudentObjectClass studentObjectClasses) {
        this.studentObjectClasses = studentObjectClasses;
    }

    @NonNull
    @Override
    public AdapterHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {

        LayoutInflater inflater = LayoutInflater.from(viewGroup.getContext());
        View view = inflater.inflate(R.layout.list_theme, viewGroup, false);

        return new AdapterHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull AdapterHolder holder, int position) {
        holder.name_txt.setText("Class Name- " + studentObjectClasses.getAttendance().get(position).getClassName());
        holder.total_txt.setText("Total Lectures- " + studentObjectClasses.getAttendance().get(position).getTotalLectures());
        holder.present_txt.setText("Present- " + studentObjectClasses.getAttendance().get(position).getPresent());
        holder.absent_txt.setText("Absent- " + studentObjectClasses.getAttendance().get(position).getAbsent());
        holder.late_txt.setText("Late- " + studentObjectClasses.getAttendance().get(position).getLate());
        holder.sick_txt.setText("Sick Leave- " + studentObjectClasses.getAttendance().get(position).getSick());
    }

    @Override
    public int getItemCount() {
        return studentObjectClasses.getAttendance().size();
    }

    public class AdapterHolder extends RecyclerView.ViewHolder {

        TextView name_txt, total_txt, present_txt, absent_txt, late_txt, sick_txt;

        public AdapterHolder(@NonNull View itemView) {
            super(itemView);

            name_txt = (TextView) itemView.findViewById(R.id.classname);
            total_txt = (TextView) itemView.findViewById(R.id.totalclass);
            present_txt = (TextView) itemView.findViewById(R.id.classpresent);
            absent_txt = (TextView) itemView.findViewById(R.id.classabsent);
            late_txt = (TextView) itemView.findViewById(R.id.classlate);
            sick_txt = (TextView) itemView.findViewById(R.id.classsick);

        }
    }
}
