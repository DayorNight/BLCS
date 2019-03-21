package blcs.lwb.utils.bean;
import com.chad.library.adapter.base.entity.MultiItemEntity;
import blcs.lwb.utils.adapter.BaseDemoAdapter.ExpandableItemAdapter;

/**
 * Created by luoxw on 2016/8/10.
 */

public class Person implements MultiItemEntity {
    public Person(String name, int age) {
        this.age = age;
        this.name = name;
    }

    public String name;
    public int age;

    @Override
    public int getItemType() {
        return ExpandableItemAdapter.TYPE_PERSON;
    }
}