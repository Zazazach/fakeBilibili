package lanou.com.fakebilibili.recommend.model;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.List;

/**
 * Created by Parcelable on 17/3/10.
 */

public class SynthesizeContent implements Parcelable{

    /**
     * from : local
     * result : suee
     * format : hdmp4
     * timelength : 112640
     * accept_format : mp4,hdmp4,flv
     * accept_quality : [3,2,1]
     * seek_param : start
     * seek_type : second
     * durl : [{"order":1,"length":112640,"size":17834961,"url":"http://cn-tj4-cu.acgvideo.com/vg3/c/06/14772544-1-hd.mp4?expires=1489162500&platform=android&ssig=EQcan9Qt5P04KVROxVNs9g&oi=2056146356&nfa=QnSG2yHMrAxScrzWLTVZdA==&dynamic=1","backup_url":["http://ws.acgvideo.com/8/35/14772544-1-hd.mp4?wsTime=1489162639&platform=android&wsSecret2=14871f57ab2e9c87d4a05846be9ccecc&oi=2056146356&rate=900"]}]
     */

    private String from;
    private String result;
    private String format;
    private int timelength;
    private String accept_format;
    private String seek_param;
    private String seek_type;
    private List<Integer> accept_quality;
    private List<DurlBean> durl;

    protected SynthesizeContent(Parcel in) {
        from = in.readString();
        result = in.readString();
        format = in.readString();
        timelength = in.readInt();
        accept_format = in.readString();
        seek_param = in.readString();
        seek_type = in.readString();
        durl = in.createTypedArrayList(DurlBean.CREATOR);
    }

    public static final Creator<SynthesizeContent> CREATOR = new Creator<SynthesizeContent>() {
        @Override
        public SynthesizeContent createFromParcel(Parcel in) {
            return new SynthesizeContent(in);
        }

        @Override
        public SynthesizeContent[] newArray(int size) {
            return new SynthesizeContent[size];
        }
    };

    public String getFrom() {
        return from;
    }

    public void setFrom(String from) {
        this.from = from;
    }

    public String getResult() {
        return result;
    }

    public void setResult(String result) {
        this.result = result;
    }

    public String getFormat() {
        return format;
    }

    public void setFormat(String format) {
        this.format = format;
    }

    public int getTimelength() {
        return timelength;
    }

    public void setTimelength(int timelength) {
        this.timelength = timelength;
    }

    public String getAccept_format() {
        return accept_format;
    }

    public void setAccept_format(String accept_format) {
        this.accept_format = accept_format;
    }

    public String getSeek_param() {
        return seek_param;
    }

    public void setSeek_param(String seek_param) {
        this.seek_param = seek_param;
    }

    public String getSeek_type() {
        return seek_type;
    }

    public void setSeek_type(String seek_type) {
        this.seek_type = seek_type;
    }

    public List<Integer> getAccept_quality() {
        return accept_quality;
    }

    public void setAccept_quality(List<Integer> accept_quality) {
        this.accept_quality = accept_quality;
    }

    public List<DurlBean> getDurl() {
        return durl;
    }

    public void setDurl(List<DurlBean> durl) {
        this.durl = durl;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(from);
        dest.writeString(result);
        dest.writeString(format);
        dest.writeInt(timelength);
        dest.writeString(accept_format);
        dest.writeString(seek_param);
        dest.writeString(seek_type);
        dest.writeTypedList(durl);
    }

    public static class DurlBean implements Parcelable{
        /**
         * order : 1
         * length : 112640
         * size : 17834961
         * url : http://cn-tj4-cu.acgvideo.com/vg3/c/06/14772544-1-hd.mp4?expires=1489162500&platform=android&ssig=EQcan9Qt5P04KVROxVNs9g&oi=2056146356&nfa=QnSG2yHMrAxScrzWLTVZdA==&dynamic=1
         * backup_url : ["http://ws.acgvideo.com/8/35/14772544-1-hd.mp4?wsTime=1489162639&platform=android&wsSecret2=14871f57ab2e9c87d4a05846be9ccecc&oi=2056146356&rate=900"]
         */

        private int order;
        private int length;
        private int size;
        private String url;
        private List<String> backup_url;

        protected DurlBean(Parcel in) {
            order = in.readInt();
            length = in.readInt();
            size = in.readInt();
            url = in.readString();
            backup_url = in.createStringArrayList();
        }

        public static final Creator<DurlBean> CREATOR = new Creator<DurlBean>() {
            @Override
            public DurlBean createFromParcel(Parcel in) {
                return new DurlBean(in);
            }

            @Override
            public DurlBean[] newArray(int size) {
                return new DurlBean[size];
            }
        };

        public int getOrder() {
            return order;
        }

        public void setOrder(int order) {
            this.order = order;
        }

        public int getLength() {
            return length;
        }

        public void setLength(int length) {
            this.length = length;
        }

        public int getSize() {
            return size;
        }

        public void setSize(int size) {
            this.size = size;
        }

        public String getUrl() {
            return url;
        }

        public void setUrl(String url) {
            this.url = url;
        }

        public List<String> getBackup_url() {
            return backup_url;
        }

        public void setBackup_url(List<String> backup_url) {
            this.backup_url = backup_url;
        }

        @Override
        public int describeContents() {
            return 0;
        }

        @Override
        public void writeToParcel(Parcel dest, int flags) {
            dest.writeInt(order);
            dest.writeInt(length);
            dest.writeInt(size);
            dest.writeString(url);
            dest.writeStringList(backup_url);
        }
    }
}
