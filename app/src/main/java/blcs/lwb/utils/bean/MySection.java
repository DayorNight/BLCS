package blcs.lwb.utils.bean;


import com.chad.library.adapter.base.entity.SectionEntity;

/**
 * https://github.com/CymChad/BaseRecyclerViewAdapterHelper
 */
public class MySection implements SectionEntity {
    private boolean isMore;
    public MySection(boolean isHeader, String header, boolean isMroe) {
        this.isMore = isMroe;
    }

    public boolean isMore() {
        return isMore;
    }

    public void setMore(boolean mroe) {
        isMore = mroe;
    }

    @Override
    public boolean isHeader() {
        return false;
    }

    @Override
    public int getItemType() {
        return 0;
    }
}
