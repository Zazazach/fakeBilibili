package lanou.com.fakebilibili;

import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import java.util.ArrayList;
import java.util.List;

import lanou.com.fakebilibili.fragment.RecommendFragment;

public class MainActivity extends AppCompatActivity {

    private List<Fragment> list;
    private FragmentAdapter adapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        adapter=new FragmentAdapter(getSupportFragmentManager());
        list=new ArrayList<>();
        list.add(new RecommendFragment());

        adapter.setList(list);

    }
}
