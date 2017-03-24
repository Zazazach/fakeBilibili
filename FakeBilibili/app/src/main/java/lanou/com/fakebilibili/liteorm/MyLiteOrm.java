package lanou.com.fakebilibili.liteorm;

import com.litesuits.orm.LiteOrm;
import com.litesuits.orm.db.assit.QueryBuilder;
import com.litesuits.orm.db.assit.WhereBuilder;

import java.util.ArrayList;

import lanou.com.fakebilibili.app.MyApp;

/**
 * .       _ooOoo_
 * .      o8888888o
 * .      88" . "88
 * .      (| -_- |)
 * .      O\  =  /O
 * .   ____/`---'\____
 * . .'  \\|     |//  `.
 * ./  \\|||  :  |||//  \
 * Created by Zach on 2017/3/23.
 */
public class MyLiteOrm{
    private static MyLiteOrm ourInstance ;
    private static LiteOrm liteOrm;
//    private Class<T> tClass;
    public static MyLiteOrm getInstance() {
        if (ourInstance==null){
            synchronized (MyLiteOrm.class){
                if (ourInstance==null){
                    ourInstance=new MyLiteOrm();
                }
            }
        }
        return ourInstance;
    }

    private MyLiteOrm() {

        liteOrm=LiteOrm.newCascadeInstance(MyApp.getContext(),"Zach.db");

    }


//


    //不同实体类 自然会建立不同的表
    public <T> void insertOrm(T t){

        liteOrm.insert(t);
    }



    public<T> void saveList(ArrayList<T> list){
        liteOrm.save(list);
    }

    public<T> void remove(T t){
        liteOrm.delete(t);
    }

//    public<T> void remove(Class<T> tclass,String detail){
//        liteOrm.delete(new WhereBuilder(tclass,""));
//    }

    public <T> ArrayList<T> queryData(Class<T> tClass){

        ArrayList<T> arraylist= liteOrm.query(tClass);
        return arraylist;
    }

    public <T> boolean queryData(Class<T> tClass,T a){


        return  queryData(tClass).contains(a);
    }

    public<T> void REMOVEWHOLEDATA(Class<T> tClass){
        liteOrm.deleteDatabase();
        liteOrm.deleteAll(tClass);
    }

}
