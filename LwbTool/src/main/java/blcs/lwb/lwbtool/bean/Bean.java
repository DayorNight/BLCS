package blcs.lwb.lwbtool.bean;

/**
 * {
 *     "res_code": 200,
 *     "err_msg": "Web page does not exist",
 *     "demo": {
 *         "id": "1001",
 *         "appid": "1021",
 *         "name": "sss",
 *         "showtype": "text"
 *     }
 * }
 */
public class Bean {

    @Override
    public String toString() {
        return "bean{" +
                "res_code=" + res_code +
                ", err_code=" + err_code +
                ", err_msg='" + err_msg + '\'' +
                ", demo=" + demo +
                '}';
    }
    private int res_code;
    private int err_code;
    private String err_msg;
    private DemoBean demo;

    public int getRes_code() {
        return res_code;
    }

    public void setRes_code(int res_code) {
        this.res_code = res_code;
    }

    public int getErr_code() {
        return err_code;
    }

    public void setErr_code(int err_code) {
        this.err_code = err_code;
    }

    public String getErr_msg() {
        return err_msg;
    }

    public void setErr_msg(String err_msg) {
        this.err_msg = err_msg;
    }

    public DemoBean getDemo() {
        return demo;
    }

    public void setDemo(DemoBean demo) {
        this.demo = demo;
    }

    public static class DemoBean {
        @Override
        public String toString() {
            return "DemoBean{" +
                    "id='" + id + '\'' +
                    ", appid='" + appid + '\'' +
                    ", name='" + name + '\'' +
                    ", showtype='" + showtype + '\'' +
                    '}';
        }

        private String id;
        private String appid;
        private String name;
        private String showtype;

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getAppid() {
            return appid;
        }

        public void setAppid(String appid) {
            this.appid = appid;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getShowtype() {
            return showtype;
        }

        public void setShowtype(String showtype) {
            this.showtype = showtype;
        }
    }
}
