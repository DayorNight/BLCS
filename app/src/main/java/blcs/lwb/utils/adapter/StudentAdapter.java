package blcs.lwb.utils.adapter;
import androidx.paging.PagedListAdapter;
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import blcs.lwb.utils.R;
import blcs.lwb.utils.bean.Student;

public class StudentAdapter extends PagedListAdapter<Student, StudentAdapter.StudentViewHolder> {
    public StudentAdapter( DiffUtil.ItemCallback<Student> diffCallback) {
        super(diffCallback);
    }

    @Override
    public StudentViewHolder onCreateViewHolder( ViewGroup viewGroup, int i) {
        View inflate = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.adapter_dialog_fragment_text, viewGroup, false);
        return new StudentViewHolder(inflate);
    }

    @Override
    public void onBindViewHolder( StudentViewHolder studentViewHolder, int i) {
        studentViewHolder.tv.setText(getItem(i).getName());
    }

    class StudentViewHolder extends RecyclerView.ViewHolder{
        private final TextView tv;

        public StudentViewHolder( View itemView) {
            super(itemView);
            tv = itemView.findViewById(R.id.tv_adapter_dialog_fragment);
        }
    }
}
