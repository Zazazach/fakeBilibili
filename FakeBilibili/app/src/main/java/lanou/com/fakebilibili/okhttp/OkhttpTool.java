package lanou.com.fakebilibili.okhttp;

/**
 * .       _ooOoo_
 * .      o8888888o
 * .      88" . "88
 * .      (| -_- |)
 * .      O\  =  /O
 * .   ____/`---'\____
 * . .'  \\|     |//  `.
 * ./  \\|||  :  |||//  \
 * Created by Zach on 17/3/9.
 */
public class OkhttpTool implements ISimplify {
    private static OkhttpTool ourInstance ;

    private ISimplify iSimplify;
    public static OkhttpTool getInstance() {
        if (ourInstance==null){
            synchronized (OkhttpTool.class){
                if (ourInstance==null){
                    ourInstance=new OkhttpTool();
                }
            }
        }
        return ourInstance;
    }

    private OkhttpTool() {
        iSimplify=new BaseMethod();
    }

    @Override
    public <T> void parse(String url, Class<T> tClass, ICallback<T> callback) {
        iSimplify.parse(url,tClass,callback);
    }
}
