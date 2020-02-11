package blcs.lwb.utils.adapter;

import android.arch.paging.PagedListAdapter;
import android.support.annotation.NonNull;
import android.support.v7.util.DiffUtil;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import blcs.lwb.utils.R;
import blcs.lwb.utils.bean.Student;

public class StudentAdapter extends PagedListAdapter<Student, StudentAdapter.StudentViewHolder> {
    public StudentAdapter(@NonNull DiffUtil.ItemCallback<Student> diffCallback) {
        super(diffCallback);
    }
    @NonNull
    @Override
    public StudentViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View inflate = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.adapter_dialog_fragment_text, viewGroup, false);
        return new StudentViewHolder(inflate);
    }

    @Override
    public void onBindViewHolder(@NonNull StudentViewHolder studentViewHolder, int i) {
        studentViewHolder.tv.setText(getItem(i).getName());
    }

    class StudentViewHolder extends RecyclerView.ViewHolder{
        private final TextView tv;

        public StudentViewHolder(@NonNull View itemView) {
            super(itemView);
            tv = itemView.findViewById(R.id.tv_adapter_dialog_fragment);
        }
    }
}
