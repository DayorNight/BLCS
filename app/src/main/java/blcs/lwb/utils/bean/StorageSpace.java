package blcs.lwb.utils.bean;

/**
 * Created by mrd on 2019/1/30.
 */

public class StorageSpace {

    private String title;
    private String size;
    private String content;
    private String button;

    public StorageSpace(String title, String size, String content, String button) {
        this.title = title;
        this.size = size;
        this.content = content;
        this.button = button;
    }

    public String getTitle() {

        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getSize() {
        return size;
    }

    public void setSize(String size) {
        this.size = size;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getButton() {
        return button;
    }

    public void setButton(String button) {
        this.button = button;
    }
}
