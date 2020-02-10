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
public class NavigationDemo1Fragment extends Fragment {


    private Unbinder bind;

    public NavigationDemo1Fragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View inflate = inflater.inflate(R.layout.fragment_navigation_demo1, container, false);
        bind = ButterKnife.bind(this, inflate);
        return inflate;
    }

    @OnClick(R.id.btn_go)
    public void onClick(){
        NavHostFragment.findNavController(this).navigate(R.id.action_navigationDemo1Fragment_to_navigationDemo2Fragment);
    }
    @Override
    public void onDetach() {
        super.onDetach();
        if (bind != null) {
            bind.unbind();
        }
    }
}
