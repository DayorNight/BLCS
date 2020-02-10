package blcs.lwb.utils.fragment.otherFragment.Jetpack;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.navigation.fragment.NavHostFragment;

import blcs.lwb.utils.R;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;


/**
 * A simple {@link Fragment} subclass.
 */
public class NavigationDemo2Fragment extends Fragment {


    private Unbinder bind;

    public NavigationDemo2Fragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View inflate = inflater.inflate(R.layout.fragment_navigation_demo2, container, false);
        bind = ButterKnife.bind(this, inflate);
        return inflate;
    }

    @OnClick(R.id.btn_back)
    public void onClick() {
        NavHostFragment.findNavController(this).popBackStack();
    }

    @Override
    public void onDetach() {
        super.onDetach();
        if (bind != null) {
            bind.unbind();
        }
    }
}
