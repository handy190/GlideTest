package com.handy.hongzhi.glidetest;

import android.content.Context;
import android.os.Environment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.load.resource.drawable.GlideDrawable;
import com.bumptech.glide.request.RequestListener;
import com.bumptech.glide.request.target.Target;
import com.handy.hongzhi.glidetest.bean.DaoMaster;
import com.handy.hongzhi.glidetest.bean.DaoSession;
import com.handy.hongzhi.glidetest.bean.StudentMsgBean;
import com.handy.hongzhi.glidetest.bean.StudentMsgBeanDao;
import com.squareup.leakcanary.LeakCanary;

import java.io.File;
import java.util.List;


public class MainActivity extends AppCompatActivity {

    private Button mButton;
    private ImageView mImageView;
    private Context mContext;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        if (LeakCanary.isInAnalyzerProcess(this)) {
            // This process is dedicated to LeakCanary for heap analysis.
            // You should not init your app in this process.
            return;
        }

        LeakCanary.install(getApplication());

        mButton = (Button) findViewById(R.id.btn_button);
        mImageView = (ImageView) findViewById(R.id.iv_imageView);

        mButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                loadImageFromURL();
//              loadImageFromLocalDisk();
//              loadImageFromDrawable();
            }


        });


        OperationDAO();




    }

    /**
     *  数据库增删改查操作
     */
    private void OperationDAO() {
        //  初始化数据库
        DaoMaster.DevOpenHelper devOpenHelper = new DaoMaster.DevOpenHelper(getApplicationContext(), "student.db", null);
        DaoMaster daoMaster = new DaoMaster(devOpenHelper.getWritableDb());
        DaoSession daoSession = daoMaster.newSession();
//      daoSession.clear();//实体类有更新，需要调用daoSeession.clear()清除缓存，才能得到更新
        //  增
        StudentMsgBeanDao msgBeanDao = daoSession.getStudentMsgBeanDao();
        StudentMsgBean studentMsgBean = new StudentMsgBean();
        studentMsgBean.setName("zone");
        studentMsgBean.setStudentNum("123456");
        msgBeanDao.insert(studentMsgBean);


        //删
        List<StudentMsgBean> list = msgBeanDao.queryBuilder().build().list();
        for(int i = 0; i < list.size(); i++) {
            Log.d("handy", "studentnumber: " + list.get(i).getStudentNum() );
            Log.d("handy", "name: "+ list.get(i).getName());
            if (i == 0){
                msgBeanDao.deleteByKey(list.get(0).getId());//通过ID来删除数据
//              msgBeanDao.delete(list.get(0));//通过传入实体类的实例来删除数据
            }
        }

        //改

        List<StudentMsgBean> list1 = msgBeanDao.queryBuilder().build().list();
        for (int i = 0; i < list1.size(); i++ ){
            Log.d("handy", "studentNumber: "+ list1.get(i).getStudentNum());
            Log.d("handy", "name: "+ list1.get(i).getName());
            if (i == 0) {
                list1.get(0).setName("hongzhi=======>");
                msgBeanDao.update(list1.get(0));
            }
        }


        //查
//        msgBeanDao.queryBuilder()
//                .offset(1)//偏移量，相当于SQL语句中的skip
//                .limit(3)// 只获取结果集的前3个数据
//                .orderAsc(StudentMsgBeanDao.Properties.StudentNum)//通过StudentNum这个属性进行正序排序
//                .where(StudentMsgBeanDao.Properties.Name.eq("zone"))
//                .build()
//                .list();

    }

    /**
     * 从资源加载图片
     */
    private void loadImageFromDrawable() {
        int resource = R.drawable.test;
        Glide.with(this).load(resource)
                .crossFade(1000)
                .into(mImageView);
    }

    /**
     * 从手机本地加载图片
     */
    private void loadImageFromLocalDisk() {

        File file = new File(Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES),"test.jpg");
        Glide.with(this).load(file)
                .crossFade()
                .into(mImageView);
    }

    private void loadImageFromURL() {

        String url = "http://img5.imgtn.bdimg.com/it/u=3066928183,3725629400&fm=23&gp=0.jpg";
        Glide.with(this).load(url)
//                .placeholder(R.drawable.test)    //占位图。即网络图片加载未完成时显示的图片
//                .error(R.drawable.test)  //网络错误时显示的图片
                .diskCacheStrategy(DiskCacheStrategy.NONE)  //是否缓存图片
                .override(800,800)  //设置显示图片的大小
                .thumbnail(0.1f)    //缩略图，解析度为原来的十分之一
                .into(mImageView);
    }


}
