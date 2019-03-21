package blcs.lwb.utils.adapter.BaseDemoAdapter;
import com.chad.library.adapter.base.BaseSectionQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import java.util.List;
import blcs.lwb.utils.bean.MySection;
import blcs.lwb.utils.R;

public class SectionAdapter extends BaseSectionQuickAdapter<MySection, BaseViewHolder> {

    public SectionAdapter(int layoutResId, int sectionHeadResId, List<MySection> data) {
        super(layoutResId, sectionHeadResId, data);
    }

    @Override
    protected void convertHead(BaseViewHolder helper, MySection item) {
        helper.setText(R.id.header, item.header);
        helper.setVisible(R.id.more, item.isMore());
        helper.addOnClickListener(R.id.more);//添加点击事件
    }

    @Override
    protected void convert(BaseViewHolder helper, MySection item) {
//        Video video = (Video) item.t;
//        switch (helper.getLayoutPosition() % 2) {
//            case 0:
//                helper.setImageResource(R.id.item_multiple_img, R.mipmap.ic4);
//                break;
//            case 1:
//                helper.setImageResource(R.id.item_multiple_img, R.mipmap.ic4);
//                break;
//
//        }
//        helper.setText(R.id.tv, video.getName());
    }
}
